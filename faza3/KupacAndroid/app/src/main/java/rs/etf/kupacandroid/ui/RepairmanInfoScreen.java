package rs.etf.kupacandroid.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.ShareActionProvider;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.asynctask.AllCommentsAsyncTask;
import rs.etf.kupacandroid.asynctask.AllScoresAsyncTask;
import rs.etf.kupacandroid.network.dto.CommentDTO;
import rs.etf.kupacandroid.network.dto.ScoreDTO;
import rs.etf.kupacandroid.network.dto.UserDTO;

public class RepairmanInfoScreen extends Fragment {
    private static final String TAG = RepairmanInfoScreen.class.getSimpleName();

    public static RepairmanInfoScreen newInstance(UserDTO repairman) {
        RepairmanInfoScreen fragment = new RepairmanInfoScreen();

        RepairmanInfoScreen.repairman = repairman;

        return fragment;
    }

    private static UserDTO repairman;

    private ShareActionProvider mShareActionProvider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_repairman_info, container, false);

        TextView firstName = view.findViewById(R.id.first_name);
        TextView lastName = view.findViewById(R.id.last_name);
        TextView job = view.findViewById(R.id.job);
        TextView address = view.findViewById(R.id.address);
        TextView phoneNumber = view.findViewById(R.id.phone_number);
        TextView price = view.findViewById(R.id.price);
        TextView rating = view.findViewById(R.id.rating);
        TextView experience = view.findViewById(R.id.experience);
        TextView specialTechniques = view.findViewById(R.id.special_techniques);
        TextView viewAllScores = view.findViewById(R.id.view_all_scores);
        TextView viewAllComments = view.findViewById(R.id.view_all_comments);
        Button createRequest = view.findViewById(R.id.create_request);

        StringBuilder sb = new StringBuilder();
        try {
            List<String> techniques = repairman.getSpecialTechniques();
            sb.append(techniques.get(0));
            for (int i = 1; i < techniques.size(); i++) {
                sb.append(", ").append(techniques.get(i));
            }
        } catch (Exception e) {
            // ignore
        }

        String priceText = repairman.getPrice() + " din";
        String experienceText = repairman.getExperience() + " godina";

        firstName.setText(repairman.getFirstName());
        lastName.setText(repairman.getLastName());
        job.setText(repairman.getJob());
        address.setText(repairman.getAddress());
        phoneNumber.setText(repairman.getPhoneNumber());
        price.setText(priceText);
        rating.setText(String.valueOf(repairman.getRating()));
        experience.setText(experienceText);
        specialTechniques.setText(sb.toString());

        specialTechniques.setMovementMethod(new ScrollingMovementMethod());

        viewAllScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyAllScoresAsyncTask((MainActivity) getActivity(), repairman).execute();
            }
        });
        viewAllComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyAllCommentsAsyncTask((MainActivity) getActivity(), repairman).execute();
            }
        });
        createRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                if (MainActivity.getCurrentUser() == null && activity != null) {
                    Toast.makeText(activity, R.string.create_request_guest_message, Toast.LENGTH_LONG).show();
                    return;
                }

                if (activity != null) {
                    activity.openCreateRequestScreen(repairman);
                }
            }
        });

        setHasOptionsMenu(true);

        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.share_menu, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mShareActionProvider != null) {
                    createScreenShot();
                    Intent sharingIntent = createSharingIntent();
                    mShareActionProvider.setShareIntent(sharingIntent);
                }
            }
        }, 100);
    }

    private void createScreenShot() {
        try {
            View rootView = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);

            View screenView = rootView.getRootView();
            screenView.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
            screenView.setDrawingCacheEnabled(false);

            File file = new File(getActivity().getCacheDir(), "Screenshot.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            Log.e(TAG, "Failed to create screenshot", e);
        }
    }

    private Intent createSharingIntent() {
        try {
            File file = new File(getActivity().getCacheDir(), "Screenshot.png");
            //Uri uri = Uri.fromFile(file);
            Uri uri = FileProvider.getUriForFile(getActivity().getApplicationContext(), getActivity().getApplicationContext().getPackageName() + ".fileprovider", file);
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("image/*");

            String shareBody = repairman.getFirstName() + " " + repairman.getLastName();
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Preporucen majstor");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            return intent;
        } catch (Exception e) {
            Log.e(TAG, "Failed to create screenshot sharing intent", e);
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBody = repairman.getFirstName() + " " + repairman.getLastName();
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Preporucen majstor");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            return intent;
        }
    }

    private class MyAllScoresAsyncTask extends AllScoresAsyncTask {

        public MyAllScoresAsyncTask(MainActivity mainActivity, UserDTO user) {
            super(mainActivity, user);
        }

        @Override
        protected void onPostExecute(List<ScoreDTO> result) {
            super.onPostExecute(result);

            MainActivity activity = (MainActivity) getActivity();
            if (activity == null) {
                return;
            }

            if (result != null) {
                ScoresDialog dialog = ScoresDialog.newInstance(activity, result);
                dialog.show();
            } else {
                Toast.makeText(activity, R.string.get_all_scores_error_message, Toast.LENGTH_LONG).show();
            }
        }
    }

    private class MyAllCommentsAsyncTask extends AllCommentsAsyncTask {

        public MyAllCommentsAsyncTask(MainActivity mainActivity, UserDTO user) {
            super(mainActivity, user);
        }

        @Override
        protected void onPostExecute(List<CommentDTO> result) {
            super.onPostExecute(result);

            MainActivity activity = (MainActivity) getActivity();
            if (activity == null) {
                return;
            }

            if (result != null) {
                CommentsDialog dialog = CommentsDialog.newInstance(activity, result);
                dialog.show();
            } else {
                Toast.makeText(activity, R.string.get_all_comments_error_message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
