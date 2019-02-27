package rs.etf.kupacandroid.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.Utils;
import rs.etf.kupacandroid.network.MockDatabaseService;
import rs.etf.kupacandroid.network.dto.RequestInfoDTO;

public class GetRequestsAsyncTask extends AsyncTask<Void, Void, List<RequestInfoDTO>> {

    private WeakReference<MainActivity> mainActivityWeakReference;
    private boolean active;
    private ProgressDialog dialog = null;

    public GetRequestsAsyncTask(MainActivity mainActivity, boolean active) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
        this.active = active;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Activity activity = mainActivityWeakReference.get();
        if (activity == null) {
            return;
        }
        dialog = Utils.showProgressDialog(activity, activity.getString(R.string.dialog_loading_title), activity.getString(R.string.wait_for_get_requests));
    }

    @Override
    protected List<RequestInfoDTO> doInBackground(Void... voids) {
        MockDatabaseService mds = MockDatabaseService.getInstance();
        try {
            Thread.sleep(200);
            if (active) {
                return mds.getActiveRequests(MainActivity.getCurrentUser());
            } else {
                return mds.getFinishedRequests(MainActivity.getCurrentUser());
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<RequestInfoDTO> result) {
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
            if (active) {
                activity.openActiveRequestsScreen(result);
            } else {
                activity.openFinishedRequestsScreen(result);
            }
        } else {
            Toast.makeText(activity, R.string.get_requests_error_message, Toast.LENGTH_LONG).show();
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
