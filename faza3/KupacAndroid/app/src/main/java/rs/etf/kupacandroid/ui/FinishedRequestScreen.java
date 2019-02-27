package rs.etf.kupacandroid.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.format.DateFormat;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.network.dto.RequestInfoDTO;
import rs.etf.kupacandroid.network.dto.UserDTO;

public class FinishedRequestScreen extends Fragment {
    private static final String TAG = FinishedRequestScreen.class.getSimpleName();

    public static FinishedRequestScreen newInstance(RequestInfoDTO request) {
        FinishedRequestScreen fragment = new FinishedRequestScreen();

        FinishedRequestScreen.request = request;

        return fragment;
    }

    private static RequestInfoDTO request;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_finished_request, container, false);

        TextView firstName = view.findViewById(R.id.first_name);
        TextView lastName = view.findViewById(R.id.last_name);
        TextView job = view.findViewById(R.id.job);
        TextView address = view.findViewById(R.id.address);
        TextView phoneNumber = view.findViewById(R.id.phone_number);
        TextView price = view.findViewById(R.id.price);
        TextView rating = view.findViewById(R.id.rating);
        TextView experience = view.findViewById(R.id.experience);
        TextView specialTechniques = view.findViewById(R.id.special_techniques);
        TextView dateInterval = view.findViewById(R.id.date_interval);
        TextView paymentMethod = view.findViewById(R.id.payment_method);
        final Button rate = view.findViewById(R.id.rate);
        final Button comment = view.findViewById(R.id.comment);

        final UserDTO repairman = request.getRepairman();
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

        String dateIntervalText = DateFormat.format("dd.MM.yyyy", request.getDateBegin()).toString() +
                " - " + DateFormat.format("dd.MM.yyyy", request.getDateEnd()).toString();

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
        dateInterval.setText(dateIntervalText);
        paymentMethod.setText(request.getPaymentMethod());

        specialTechniques.setMovementMethod(new ScrollingMovementMethod());

        if (request.isRateSent()) {
            rate.setBackgroundResource(R.drawable.button_disabled);
        }

        if (request.isCommentSent()) {
            comment.setBackgroundResource(R.drawable.button_disabled);
        }

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (request.isRateSent()) {
                    return;
                }

                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.openRateScreen(request);
                }
            }
        });
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (request.isCommentSent()) {
                    return;
                }

                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.openCommentScreen(request);
                }
            }
        });

        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        return view;
    }
}
