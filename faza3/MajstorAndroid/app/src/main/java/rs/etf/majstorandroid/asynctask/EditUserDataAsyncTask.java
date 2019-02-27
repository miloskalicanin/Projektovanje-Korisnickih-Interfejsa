package rs.etf.majstorandroid.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import rs.etf.majstorandroid.MainActivity;
import rs.etf.majstorandroid.R;
import rs.etf.majstorandroid.Utils;
import rs.etf.majstorandroid.network.MockDatabaseService;
import rs.etf.majstorandroid.network.dto.UserDTO;

public class EditUserDataAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private WeakReference<MainActivity> mainActivityWeakReference;
    private UserDTO userDTO;
    private ProgressDialog dialog = null;

    public EditUserDataAsyncTask(MainActivity mainActivity, UserDTO userDTO) {
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
        dialog = Utils.showProgressDialog(activity, activity.getString(R.string.dialog_loading_title), activity.getString(R.string.wait_for_edit_user_data));
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        MockDatabaseService mds = MockDatabaseService.getInstance();
        try {
            Thread.sleep(1000);
            mds.editUserData(userDTO);
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
            Toast.makeText(activity, R.string.edit_user_data_error_message, Toast.LENGTH_LONG).show();
        } else {
            UserDTO currentUser = MainActivity.getCurrentUser();
            currentUser.setFirstName(userDTO.getFirstName());
            currentUser.setLastName(userDTO.getLastName());
            currentUser.setAddress(userDTO.getAddress());
            currentUser.setPhoneNumber(userDTO.getPhoneNumber());

            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity activity = mainActivityWeakReference.get();
                    if (activity != null) {
                        activity.onBackPressed();
                    }
                }
            };
            Utils.displayAlertDialog(activity, activity.getString(R.string.edit_user_data_succ_title), activity.getString(R.string.edit_user_data_succ_message), activity.getString(R.string.ok), null, listener);
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
