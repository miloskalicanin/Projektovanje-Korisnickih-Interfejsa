package rs.etf.kupacandroid.ui;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.InputFilter;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.Utils;
import rs.etf.kupacandroid.asynctask.CreateRequestAsyncTask;
import rs.etf.kupacandroid.asynctask.SendRateAsyncTask;
import rs.etf.kupacandroid.network.MockDatabaseService;
import rs.etf.kupacandroid.network.dto.CreateRequestDTO;
import rs.etf.kupacandroid.network.dto.RequestInfoDTO;
import rs.etf.kupacandroid.network.dto.UserDTO;

public class RateScreen extends Fragment {
    private static final String TAG = RateScreen.class.getSimpleName();

    public static RateScreen newInstance(RequestInfoDTO request) {
        RateScreen fragment = new RateScreen();

        RateScreen.request = request;

        return fragment;
    }

    private static RequestInfoDTO request;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_rate, container, false);
        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) {
            return view;
        }

        final EditText inputRate = view.findViewById(R.id.input_rate);
        final Button sendRate = view.findViewById(R.id.send_rate);

        inputRate.setFilters(new InputFilter[]{ new InputFilterMinMax(1.0, 5.0)});

        sendRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.hideSoftKeyboard(getActivity());

                String rate = inputRate.getText().toString();

                try{
                    final double value = Double.parseDouble(rate);

                    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity activity = (MainActivity) getActivity();
                            if(activity != null) {
                                new SendRateAsyncTask(activity, request, value).execute();
                                Log.d(TAG, "Sending rate: " + value);
                            }
                        }
                    };
                    Utils.displayAlertDialog(getActivity(), getString(R.string.send_rate_dialog_title), getString(R.string.send_rate_dialog_message), getString(R.string.yes), getString(R.string.back), listener);
                } catch (Exception e) {

                }
            }
        });

        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        return view;
    }
}
