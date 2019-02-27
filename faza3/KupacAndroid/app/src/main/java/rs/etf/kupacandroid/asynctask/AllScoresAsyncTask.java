package rs.etf.kupacandroid.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.Utils;
import rs.etf.kupacandroid.network.MockDatabaseService;
import rs.etf.kupacandroid.network.dto.ScoreDTO;
import rs.etf.kupacandroid.network.dto.UserDTO;

public class AllScoresAsyncTask extends AsyncTask<Void, Void, List<ScoreDTO>> {

    private WeakReference<MainActivity> mainActivityWeakReference;
    private UserDTO user;
    private ProgressDialog dialog = null;

    public AllScoresAsyncTask(MainActivity mainActivity, UserDTO user) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
        this.user = user;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Activity activity = mainActivityWeakReference.get();
        if (activity == null) {
            return;
        }
        dialog = Utils.showProgressDialog(activity, activity.getString(R.string.dialog_loading_title), activity.getString(R.string.wait_for_all_scores));
    }

    @Override
    protected List<ScoreDTO> doInBackground(Void... voids) {
        MockDatabaseService mds = MockDatabaseService.getInstance();
        try {
            return mds.getAllScores(user);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<ScoreDTO> result) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
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
