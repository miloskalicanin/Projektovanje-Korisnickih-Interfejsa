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
import rs.etf.kupacandroid.network.dto.RequestInfoDTO;

public class SendCommentAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private WeakReference<MainActivity> mainActivityWeakReference;
    private String comment;
    private RequestInfoDTO request;
    private ProgressDialog dialog = null;

    public SendCommentAsyncTask(MainActivity mainActivity, RequestInfoDTO request, String comment) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
        this.request = request;
        this.comment = comment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Activity activity = mainActivityWeakReference.get();
        if (activity == null) {
            return;
        }
        dialog = Utils.showProgressDialog(activity, activity.getString(R.string.dialog_loading_title), activity.getString(R.string.wait_for_send_comment));
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        MockDatabaseService mds = MockDatabaseService.getInstance();
        try {
            Thread.sleep(1000);
            mds.sendComment(request, comment);
            request.setCommentSent(true);
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
            Toast.makeText(activity, R.string.send_comment_error_message, Toast.LENGTH_LONG).show();
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

        builder.setTitle(activity.getString(R.string.send_comment_successful_title))
                .setMessage(activity.getString(R.string.send_comment_successful_message));

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
