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
import rs.etf.kupacandroid.network.dto.SearchParametersDTO;
import rs.etf.kupacandroid.network.dto.UserDTO;

public class SearchAsyncTask extends AsyncTask<Void, Void, List<UserDTO>> {

    private WeakReference<MainActivity> mainActivityWeakReference;
    private SearchParametersDTO searchParametersDTO;
    private ProgressDialog dialog = null;

    public SearchAsyncTask(MainActivity mainActivity, SearchParametersDTO searchParametersDTO) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
        this.searchParametersDTO = searchParametersDTO;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Activity activity = mainActivityWeakReference.get();
        if (activity == null) {
            return;
        }
        dialog = Utils.showProgressDialog(activity, activity.getString(R.string.dialog_loading_title), activity.getString(R.string.wait_for_search));
    }

    @Override
    protected List<UserDTO> doInBackground(Void... voids) {
        MockDatabaseService mds = MockDatabaseService.getInstance();
        try {
            Thread.sleep(1000);
            return mds.search(searchParametersDTO);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<UserDTO> result) {
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
            activity.displaySearchResult(result);
        } else {
            Toast.makeText(activity, R.string.search_error_message, Toast.LENGTH_LONG).show();
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
