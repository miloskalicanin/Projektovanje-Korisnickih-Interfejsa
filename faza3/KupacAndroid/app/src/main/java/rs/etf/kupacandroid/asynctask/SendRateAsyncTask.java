package rs.etf.kupacandroid.asynctask;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.Utils;
import rs.etf.kupacandroid.network.MockDatabaseService;
import rs.etf.kupacandroid.network.dto.CreateRequestDTO;
import rs.etf.kupacandroid.network.dto.RequestInfoDTO;

public class SendRateAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private WeakReference<MainActivity> mainActivityWeakReference;
    private double rate;
    private RequestInfoDTO request;
    private ProgressDialog dialog = null;

    public SendRateAsyncTask(MainActivity mainActivity, RequestInfoDTO request, double rate) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
        this.request = request;
        this.rate = rate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Activity activity = mainActivityWeakReference.get();
        if (activity == null) {
            return;
        }
        dialog = Utils.showProgressDialog(activity, activity.getString(R.string.dialog_loading_title), activity.getString(R.string.wait_for_send_rate));
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        MockDatabaseService mds = MockDatabaseService.getInstance();
        try {
            Thread.sleep(1000);
            mds.sendRate(request, rate);
            request.setRateSent(true);
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
            displayDialog(activity);
        } else {
            Toast.makeText(activity, R.string.send_rate_error_message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void displayDialog(final MainActivity activity) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(activity);
        }

        builder.setTitle(activity.getString(R.string.send_rate_successful_title))
                .setMessage(activity.getString(R.string.send_rate_successful_message));

        DialogInterface.OnDismissListener onDismissListener = new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dismiss(activity);
            }
        };

        builder.setPositiveButton(activity.getString(R.string.ok), null);
        builder.setOnDismissListener(onDismissListener);

        builder.show();
    }

    private void dismiss(MainActivity activity) {
        activity.onBackPressed();
    }
}
