package rs.etf.kupacandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

import rs.etf.kupacandroid.network.dto.RequestInfoDTO;
import rs.etf.kupacandroid.network.dto.UserDTO;
import rs.etf.kupacandroid.ui.ActiveRequestScreen;
import rs.etf.kupacandroid.ui.ActiveRequestsScreen;
import rs.etf.kupacandroid.ui.CommentScreen;
import rs.etf.kupacandroid.ui.CreateRequestScreen;
import rs.etf.kupacandroid.ui.EditPasswordScreen;
import rs.etf.kupacandroid.ui.EditUserDataScreen;
import rs.etf.kupacandroid.ui.FinishedRequestScreen;
import rs.etf.kupacandroid.ui.FinishedRequestsScreen;
import rs.etf.kupacandroid.ui.LoginScreen;
import rs.etf.kupacandroid.ui.MainScreen;
import rs.etf.kupacandroid.ui.RateScreen;
import rs.etf.kupacandroid.ui.RegistrationScreen;
import rs.etf.kupacandroid.ui.RepairmanInfoScreen;
import rs.etf.kupacandroid.ui.SearchResultScreen;
import rs.etf.kupacandroid.ui.WelcomeScreen;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static UserDTO currentUser = null;
    private static MySharedPreferences mySharedPreferences;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(getString(R.string.app_label));
        }

        mySharedPreferences = MySharedPreferences.getInstance(this);

        checkLoginStatus();

        Utils.clearCacheFolder(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "Back pressed");
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    private void checkLoginStatus() {
        UserDTO userDTO = mySharedPreferences.getSavedUser();

        if (userDTO == null) {
            openWelcomeScreen();
        } else {
            currentUser = userDTO;
            replaceFragment(new MainScreen());
        }
    }

    public void openWelcomeScreen() {
        replaceFragment(new WelcomeScreen());
    }

    public void openRegistrationScreen() {
        replaceFragment(new RegistrationScreen());
    }

    public void openLoginScreen() {
        replaceFragment(new LoginScreen());
    }

    public void logout() {
        currentUser = null;
        mySharedPreferences.deleteSavedUser();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        replaceFragment(new WelcomeScreen());
    }

    public void finishLogin(UserDTO userDTO, boolean remember) {
        currentUser = userDTO;

        Log.d(TAG, "Save user: " + remember);

        if (remember) {
            mySharedPreferences.saveUser(userDTO);
        }

        replaceFragment(new MainScreen());
    }

    public void openMainScreen() {
        replaceFragment(new MainScreen());
    }

    public void openEditUserDataScreen() {
        replaceFragment(new EditUserDataScreen());
    }

    public void openEditPasswordScreen() {
        replaceFragment(new EditPasswordScreen());
    }

    public void displaySearchResult(List<UserDTO> searchResult) {
        replaceFragment(SearchResultScreen.newInstance(searchResult));
    }

    public void openRepairmanInfo(UserDTO repairman) {
        replaceFragment(RepairmanInfoScreen.newInstance(repairman));
    }

    public void openCreateRequestScreen(UserDTO repairman) {
        replaceFragment(CreateRequestScreen.newInstance(repairman));
    }

    public void openActiveRequestsScreen(List<RequestInfoDTO> requests) {
        replaceFragment(ActiveRequestsScreen.newInstance(requests));
    }

    public void openFinishedRequestsScreen(List<RequestInfoDTO> requests) {
        replaceFragment(FinishedRequestsScreen.newInstance(requests));
    }

    public void openActiveRequestScreen(RequestInfoDTO request) {
        replaceFragment(ActiveRequestScreen.newInstance(request));
    }

    public void openFinishedRequestScreen(RequestInfoDTO request) {
        replaceFragment(FinishedRequestScreen.newInstance(request));
    }

    public void openRateScreen(RequestInfoDTO request) {
        replaceFragment(RateScreen.newInstance(request));
    }

    public void openCommentScreen(RequestInfoDTO request) {
        replaceFragment(CommentScreen.newInstance(request));
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public static UserDTO getCurrentUser() {
        return currentUser;
    }

    private void replaceFragment(Fragment fragment) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();

            if (fragment instanceof WelcomeScreen || fragment instanceof MainScreen) {
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            FragmentTransaction transaction = fragmentManager.beginTransaction();

            //transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
            transaction.replace(R.id.main_activity_fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            Log.d(TAG, "Opened fragment " + fragment.getClass().getSimpleName());
        } catch (Exception e) {
            Log.d(TAG, "Failed to replace fragment " + fragment.getClass().getSimpleName());
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity == null) {
            return;
        }

        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(
                    activity.findViewById(android.R.id.content).getWindowToken(), 0);
        }
    }
}
