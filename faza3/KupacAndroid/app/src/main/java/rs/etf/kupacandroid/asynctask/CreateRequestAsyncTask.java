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
import rs.etf.kupacandroid.network.dto.CreateRequestDTO;

public class CreateRequestAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private WeakReference<MainActivity> mainActivityWeakReference;
    private CreateRequestDTO createRequestDTO;
    private ProgressDialog dialog = null;

    public CreateRequestAsyncTask(MainActivity mainActivity, CreateRequestDTO createRequestDTO) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
        this.createRequestDTO = createRequestDTO;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Activity activity = mainActivityWeakReference.get();
        if (activity == null) {
            return;
        }
        dialog = Utils.showProgressDialog(activity, activity.getString(R.string.dialog_loading_title), activity.getString(R.string.wait_for_send_request));
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        MockDatabaseService mds = MockDatabaseService.getInstance();
        try {
            Thread.sleep(1000);
            mds.createRequest(createRequestDTO);
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

        if (mainActivityWeakReference == null) {
            return;
        }

        final MainActivity activity = mainActivityWeakReference.get();
        if (activity == null) {
            return;
        }

        if (result != null && result) {
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Activity activity = mainActivityWeakReference.get();
                    if (activity != null) {
                        activity.onBackPressed();
                    }
                }
            };
            Utils.displayAlertDialog(activity, activity.getString(R.string.send_request_dialog_title), activity.getString(R.string.send_request_dialog_message), activity.getString(R.string.ok), null, listener);
        } else {
            Toast.makeText(activity, R.string.send_request_error_message, Toast.LENGTH_LONG).show();
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
