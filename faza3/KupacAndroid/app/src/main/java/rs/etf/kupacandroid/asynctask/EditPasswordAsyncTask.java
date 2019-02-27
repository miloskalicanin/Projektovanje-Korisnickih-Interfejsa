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

public class EditPasswordAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private WeakReference<MainActivity> mainActivityWeakReference;
    private UserDTO userDTO;
    private String newPassword;
    private ProgressDialog dialog = null;

    public EditPasswordAsyncTask(MainActivity mainActivity, UserDTO userDTO, String newPassword) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
        this.userDTO = userDTO;
        this.newPassword = newPassword;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Activity activity = mainActivityWeakReference.get();
        if (activity == null) {
            return;
        }
        dialog = Utils.showProgressDialog(activity, activity.getString(R.string.dialog_loading_title), activity.getString(R.string.wait_for_edit_user_data));
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        MockDatabaseService mds = MockDatabaseService.getInstance();
        try {
            Thread.sleep(1000);
            mds.editPassword(userDTO, newPassword);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        MainActivity activity = mainActivityWeakReference.get();
        if (activity == null) {
            return;
        }

        if (result == null || !result) {
            Toast.makeText(activity, R.string.change_password_error_message_3, Toast.LENGTH_LONG).show();
        } else {
            UserDTO currentUser = MainActivity.getCurrentUser();
            currentUser.setPassword(userDTO.getPassword());

            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity activity = mainActivityWeakReference.get();
                    if (activity != null) {
                        activity.onBackPressed();
                    }
                }
            };
            Utils.displayAlertDialog(activity, activity.getString(R.string.edit_password_succ_title), activity.getString(R.string.edit_password_succ_message), activity.getString(R.string.ok), null, listener);
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
