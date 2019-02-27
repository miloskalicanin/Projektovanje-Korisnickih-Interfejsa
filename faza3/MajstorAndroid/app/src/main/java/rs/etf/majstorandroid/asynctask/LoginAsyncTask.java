package rs.etf.majstorandroid.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import rs.etf.majstorandroid.MainActivity;
import rs.etf.majstorandroid.R;
import rs.etf.majstorandroid.Utils;
import rs.etf.majstorandroid.network.MockDatabaseService;
import rs.etf.majstorandroid.network.dto.UserDTO;

public class LoginAsyncTask extends AsyncTask<Void, Void, UserDTO> {

    private WeakReference<MainActivity> mainActivityWeakReference;
    private String username;
    private String password;
    private boolean remember;
    private ProgressDialog dialog = null;

    public LoginAsyncTask(MainActivity mainActivity, String username, String password, boolean remember) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
        this.username = username;
        this.password = password;
        this.remember = remember;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Activity activity = mainActivityWeakReference.get();
        if (activity == null) {
            return;
        }
        dialog = Utils.showProgressDialog(activity, activity.getString(R.string.dialog_loading_title), activity.getString(R.string.wait_for_login));
    }

    @Override
    protected UserDTO doInBackground(Void... voids) {
        MockDatabaseService mds = MockDatabaseService.getInstance();
        try {
            Thread.sleep(1000);
            return mds.login(username, password);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(UserDTO result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        if (mainActivityWeakReference == null) {
            return;
        }

        MainActivity activity = mainActivityWeakReference.get();
        if (activity == null) {
            return;
        }

        if (result != null) {
            activity.finishLogin(result, remember);
        } else {
            Toast.makeText(activity, R.string.login_error_message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
