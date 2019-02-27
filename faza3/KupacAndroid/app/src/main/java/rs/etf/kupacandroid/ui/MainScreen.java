package rs.etf.kupacandroid.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.Utils;
import rs.etf.kupacandroid.asynctask.GetRequestsAsyncTask;
import rs.etf.kupacandroid.asynctask.SearchAsyncTask;
import rs.etf.kupacandroid.network.MockDatabaseService;
import rs.etf.kupacandroid.network.dto.SearchParametersDTO;
import rs.etf.kupacandroid.network.dto.UserDTO;

public class MainScreen extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainScreen.class.getSimpleName();

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Date dateBegin = null;
    private Date dateEnd = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) {
            return view;
        }

        final Spinner inputJob = view.findViewById(R.id.input_job);
        final EditText inputPriceFrom = view.findViewById(R.id.input_price_from);
        final EditText inputPriceTo = view.findViewById(R.id.input_price_to);
        final TextView dateBeginTextView = view.findViewById(R.id.date_begin);
        final Button dateBeginEdit = view.findViewById(R.id.date_begin_edit);
        final TextView dateEndTextView = view.findViewById(R.id.date_end);
        final Button dateEndEdit = view.findViewById(R.id.date_end_edit);
        final EditText inputExperienceFrom = view.findViewById(R.id.experience_from);
        final EditText inputExperienceTo = view.findViewById(R.id.experience_to);
        final EditText inputRatingFrom = view.findViewById(R.id.rating_from);
        final EditText inputRatingTo = view.findViewById(R.id.rating_to);
        final CheckBox inputSpecialTechniques = view.findViewById(R.id.special_technique);
        final CheckBox inputUrgency = view.findViewById(R.id.urgency);
        final Button search = view.findViewById(R.id.search);

        dateBeginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateBeginTextView.setText("-");
                dateBegin = null;
            }
        });
        dateEndTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateEndTextView.setText("-");
                dateEnd = null;
            }
        });

        dateBeginEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        Calendar c = Calendar.getInstance();
                        c.set(selectedYear, selectedMonth, selectedDay, 0, 0);
                        dateBegin = c.getTime();
                        String formatedDate = DateFormat.format("dd.MM.yyyy", dateBegin).toString();
                        dateBeginTextView.setText(formatedDate);
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), listener, year, month, day);
                if (dateEnd != null) {
                    dialog.getDatePicker().setMaxDate(dateEnd.getTime());
                }
                dialog.show();
            }
        });
        dateEndEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        Calendar c = Calendar.getInstance();
                        c.set(selectedYear, selectedMonth, selectedDay, 0, 0);
                        dateEnd = c.getTime();
                        String formatedDate = DateFormat.format("dd.MM.yyyy", dateEnd).toString();
                        dateEndTextView.setText(formatedDate);
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), listener, year, month, day);
                if (dateBegin != null) {
                    dialog.getDatePicker().setMinDate(dateBegin.getTime());
                }
                dialog.show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String job = inputJob.getSelectedItem().toString();
                String priceFrom = inputPriceFrom.getText().toString();
                String priceTo = inputPriceTo.getText().toString();
                String experienceFrom = inputExperienceFrom.getText().toString();
                String experienceTo = inputExperienceTo.getText().toString();
                String ratingFrom = inputRatingFrom.getText().toString();
                String ratingTo = inputRatingTo.getText().toString();
                boolean specialTech = inputSpecialTechniques.isChecked();
                boolean urgency = inputUrgency.isChecked();
                search(job, priceFrom, priceTo, dateBegin, dateEnd, experienceFrom, experienceTo, ratingFrom, ratingTo, specialTech, urgency);
            }
        });

        List<String> jobs = MockDatabaseService.getInstance().getJobs();
        jobs.add(0, "svi poslovi");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_spinner_dropdown_item, jobs);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputJob.setAdapter(adapter);

        Toolbar toolbar = activity.getToolbar();

        drawer = view.findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerLayout = navigationView.getHeaderView(0);
        TextView userInfo = headerLayout.findViewById(R.id.user_info);

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    }
                }
                return false;
            }
        });

        UserDTO currentUser = MainActivity.getCurrentUser();
        Menu menu = navigationView.getMenu();
        if (menu != null) {
            if (currentUser != null) {
                // user
                menu.setGroupVisible(R.id.menu_guest, false);
                menu.setGroupVisible(R.id.menu_user_info, true);
                menu.setGroupVisible(R.id.menu_user_activities, true);
                String fullName = currentUser.getFirstName() + " " + currentUser.getLastName();
                userInfo.setText(fullName);
                userInfo.setVisibility(View.VISIBLE);
            } else {
                // guest
                menu.setGroupVisible(R.id.menu_guest, true);
                menu.setGroupVisible(R.id.menu_user_info, false);
                menu.setGroupVisible(R.id.menu_user_activities, false);
                userInfo.setVisibility(View.GONE);
            }
        }

        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        return view;
    }

    @Override
    public void onResume() {
        if (toggle != null) {
            toggle.setDrawerIndicatorEnabled(true);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            toggle.setToolbarNavigationClickListener(null);
        }
        MainActivity.hideSoftKeyboard(getActivity());
        super.onResume();
    }

    @Override
    public void onStop() {
        if (toggle != null) {
            toggle.setDrawerIndicatorEnabled(false);
            toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity activity = getActivity();
                    if (activity != null) {
                        activity.onBackPressed();
                    }
                }
            });
        }
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (toggle != null) {
            toggle.setToolbarNavigationClickListener(null);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        item.setChecked(false);
        int id = item.getItemId();

        if (id == R.id.register) {
            Log.d(TAG, "Register");
            MainActivity activity = (MainActivity) getActivity();
            if (activity != null) {
                activity.openRegistrationScreen();
            }

        } else if (id == R.id.login) {
            Log.d(TAG, "Login");
            MainActivity activity = (MainActivity) getActivity();
            if (activity != null) {
                activity.openLoginScreen();
            }

        } else if (id == R.id.change_user_info) {
            Log.d(TAG, "Change user info");
            MainActivity activity = (MainActivity) getActivity();
            if (activity != null) {
                activity.openEditUserDataScreen();
            }

        } else if (id == R.id.change_password) {
            Log.d(TAG, "Change password");
            MainActivity activity = (MainActivity) getActivity();
            if (activity != null) {
                activity.openEditPasswordScreen();
            }

        } else if (id == R.id.logout) {
            Log.d(TAG, "Logout");
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity activity = (MainActivity) getActivity();
                    if (activity != null) {
                        activity.logout();
                    }
                }
            };
            Utils.displayAlertDialog(getActivity(), getString(R.string.logout_dialog_title), getString(R.string.logout_dialog_messsage), getString(R.string.yes), getString(R.string.back), listener);

        } else if (id == R.id.active_requests) {
            Log.d(TAG, "Active requests");
            MainActivity activity = (MainActivity) getActivity();
            if (activity != null) {
                new GetRequestsAsyncTask(activity, true).execute();
            }

        } else if (id == R.id.finished_requests) {
            Log.d(TAG, "Finished requests");
            MainActivity activity = (MainActivity) getActivity();
            if (activity != null) {
                new GetRequestsAsyncTask(activity, false).execute();
            }
        }

        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }

    private void search(String job, String priceFrom, String priceTo, Date dateBegin, Date dateEnd,
                        String experienceFrom, String experienceTo, String ratingFrom, String ratingTo,
                        boolean specialTechniques, boolean urgency) {

        MainActivity.hideSoftKeyboard(getActivity());

        SearchParametersDTO searchParameters = new SearchParametersDTO();

        if (job != null && !job.isEmpty()) {
            searchParameters.setJob(job);
        }

        if (priceFrom != null && !priceFrom.isEmpty()) {
            searchParameters.setPriceFrom(priceFrom);
        }

        if (priceTo != null && !priceTo.isEmpty()) {
            searchParameters.setPriceTo(priceTo);
        }

        if (dateBegin != null) {
            searchParameters.setDateBegin(dateBegin);
        }

        if (dateEnd != null) {
            searchParameters.setDateEnd(dateEnd);
        }

        if (experienceFrom != null && !experienceFrom.isEmpty()) {
            searchParameters.setExperienceFrom(experienceFrom);
        }

        if (experienceTo != null && !experienceTo.isEmpty()) {
            searchParameters.setExperienceTo(experienceTo);
        }

        if (ratingFrom != null && !ratingFrom.isEmpty()) {
            searchParameters.setRatingFrom(ratingFrom);
        }

        if (ratingTo != null && !ratingTo.isEmpty()) {
            searchParameters.setRatingTo(ratingTo);
        }

        searchParameters.setSpecialTechniques(specialTechniques);
        searchParameters.setUrgency(urgency);

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            new SearchAsyncTask(mainActivity, searchParameters).execute();
        }
    }
}
