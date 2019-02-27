package rs.etf.kupacandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

import java.io.File;

public class Utils {

    public static ProgressDialog showProgressDialog(Activity activity, String title, String message) {
        ProgressDialog progressDialog = new ProgressDialog(activity);

        progressDialog.setTitle(title);
        progressDialog.setMessage(message);

        progressDialog.show();

        return progressDialog;
    }

    public static void displayAlertDialog(Context context, String title, String message, String positiveBtnValue, String negativeBtnValue, DialogInterface.OnClickListener positiveBtnListener) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }

        builder.setTitle(title)
                .setMessage(message);

        if (positiveBtnValue != null) {
            builder.setPositiveButton(positiveBtnValue, positiveBtnListener);
        }

        if (negativeBtnValue != null) {
            builder.setNegativeButton(negativeBtnValue, null);
        }

        builder.show();
    }

    public static void clearCacheFolder(Activity activity) {
        try {
            File file = new File(activity.getCacheDir(), "Screenshot.png");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            // ignore
        }
    }
}
