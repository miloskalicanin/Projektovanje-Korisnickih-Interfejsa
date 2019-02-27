package rs.etf.kupacandroid.ui;

import android.content.DialogInterface;
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
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.Utils;
import rs.etf.kupacandroid.asynctask.PayAsyncTask;
import rs.etf.kupacandroid.network.dto.RequestInfoDTO;
import rs.etf.kupacandroid.network.dto.UserDTO;

public class ActiveRequestScreen extends Fragment {
    private static final String TAG = ActiveRequestScreen.class.getSimpleName();

    public static ActiveRequestScreen newInstance(RequestInfoDTO request) {
        ActiveRequestScreen fragment = new ActiveRequestScreen();

        ActiveRequestScreen.request = request;

        return fragment;
    }

    private static RequestInfoDTO request;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_active_request, container, false);

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
        Button pay = view.findViewById(R.id.pay);

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

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (request.getDateBegin().after(new Date(System.currentTimeMillis()))) {
                    Toast.makeText(getActivity(), R.string.pay_not_possible_message, Toast.LENGTH_LONG).show();
                    return;
                }

                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity activity = (MainActivity) getActivity();
                        if (activity != null) {
                            new PayAsyncTask(activity, request).execute();
                        }
                    }
                };
                Utils.displayAlertDialog(getActivity(), getString(R.string.pay_dialog_title), getString(R.string.pay_dialog_messsage), getString(R.string.yes), getString(R.string.back), listener);
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
