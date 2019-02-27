package rs.etf.kupacandroid.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.Utils;
import rs.etf.kupacandroid.network.MockDatabaseService;
import rs.etf.kupacandroid.network.dto.UserDTO;

public class RegisterAsyncTask extends AsyncTask<Void, Void, UserDTO> {

    private WeakReference<MainActivity> mainActivityWeakReference;
    private UserDTO userDTO;
    private ProgressDialog dialog = null;

    public RegisterAsyncTask(MainActivity mainActivity, UserDTO userDTO) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
        this.userDTO = userDTO;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Activity activity = mainActivityWeakReference.get();
        if (activity == null) {
            return;
        }
        dialog = Utils.showProgressDialog(activity, activity.getString(R.string.dialog_loading_title), activity.getString(R.string.wait_for_register));
    }

    @Override
    protected UserDTO doInBackground(Void... voids) {
        MockDatabaseService mds = MockDatabaseService.getInstance();
        try {
            Thread.sleep(1000);
            return mds.register(userDTO);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(UserDTO result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        MainActivity activity = mainActivityWeakReference.get();
        if (activity == null) {
            return;
        }

        if (result == null) {
            Toast.makeText(activity, R.string.registration_error_message, Toast.LENGTH_LONG).show();
        } else {
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity activity = mainActivityWeakReference.get();
                    if (activity != null) {
                        activity.openWelcomeScreen();
                    }
                }
            };
            Utils.displayAlertDialog(activity, activity.getString(R.string.registration_succ_title), activity.getString(R.string.registration_succ_message), activity.getString(R.string.ok), null, listener);
        }

        super.onPostExecute(result);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
