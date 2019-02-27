package rs.etf.majstorandroid.ui;

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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import rs.etf.majstorandroid.MainActivity;
import rs.etf.majstorandroid.R;
import rs.etf.majstorandroid.Utils;
import rs.etf.majstorandroid.asynctask.SearchRequestsAsyncTask;
import rs.etf.majstorandroid.network.dto.SearchRequestsParametersDTO;
import rs.etf.majstorandroid.network.dto.UserDTO;

public class MainScreen extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainScreen.class.getSimpleName();

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private Date dateBegin = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) {
            return view;
        }

        final EditText inputDistance = view.findViewById(R.id.input_distance);
        final TextView dateBeginTextView = view.findViewById(R.id.date_begin);
        final Button dateBeginEdit = view.findViewById(R.id.date_begin_edit);
        final CheckBox inputUrgency = view.findViewById(R.id.urgency);
        final Button search = view.findViewById(R.id.search);

        dateBeginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateBeginTextView.setText("-");
                dateBegin = null;
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
                dialog.show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String distance = inputDistance.getText().toString();
                boolean urgency = inputUrgency.isChecked();
                search(distance, dateBegin, urgency);
            }
        });

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
            // user
            menu.setGroupVisible(R.id.menu_user_info, true);
            String fullName = currentUser.getFirstName() + " " + currentUser.getLastName();
            userInfo.setText(fullName);
            userInfo.setVisibility(View.VISIBLE);
        }

        dateBegin = null;

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

        if (id == R.id.change_user_info) {
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

        }

        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }

        return true;
    }

    private void search(String distance, Date dateBegin, boolean urgency) {
        MainActivity.hideSoftKeyboard(getActivity());

        SearchRequestsParametersDTO searchParameters = new SearchRequestsParametersDTO();

        try {
            searchParameters.setDistance(Double.parseDouble(distance));
        } catch (Exception e) {
            // ignore
        }

        if (dateBegin != null) {
            searchParameters.setDateBegin(dateBegin);
        }
        searchParameters.setUrgency(urgency);

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            new SearchRequestsAsyncTask(mainActivity, searchParameters).execute();
        }
    }
}
