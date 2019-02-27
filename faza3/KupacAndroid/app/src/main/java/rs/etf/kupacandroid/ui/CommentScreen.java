package rs.etf.kupacandroid.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.Utils;
import rs.etf.kupacandroid.asynctask.SendCommentAsyncTask;
import rs.etf.kupacandroid.asynctask.SendRateAsyncTask;
import rs.etf.kupacandroid.network.dto.RequestInfoDTO;

public class CommentScreen extends Fragment {
    private static final String TAG = CommentScreen.class.getSimpleName();

    public static CommentScreen newInstance(RequestInfoDTO request) {
        CommentScreen fragment = new CommentScreen();

        CommentScreen.request = request;

        return fragment;
    }

    private static RequestInfoDTO request;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) {
            return view;
        }

        final EditText inputComment = view.findViewById(R.id.input_comment);
        final Button sendComment = view.findViewById(R.id.send_comment);

        sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.hideSoftKeyboard(getActivity());

                final String comment = inputComment.getText().toString();

                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity activity = (MainActivity) getActivity();
                        if(activity != null) {
                            new SendCommentAsyncTask(activity, request, comment).execute();
                            Log.d(TAG, "Sending comment: " + comment);
                        }
                    }
                };
                Utils.displayAlertDialog(getActivity(), getString(R.string.send_comment_dialog_title), getString(R.string.send_comment_dialog_message), getString(R.string.yes), getString(R.string.back), listener);
            }
        });

        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        return view;
    }
}
