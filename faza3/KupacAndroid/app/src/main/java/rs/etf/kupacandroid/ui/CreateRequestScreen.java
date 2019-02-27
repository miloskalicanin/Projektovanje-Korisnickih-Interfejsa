package rs.etf.kupacandroid.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.asynctask.CreateRequestAsyncTask;
import rs.etf.kupacandroid.network.MockDatabaseService;
import rs.etf.kupacandroid.network.dto.CreateRequestDTO;
import rs.etf.kupacandroid.network.dto.UserDTO;

public class CreateRequestScreen extends Fragment {
    private static final String TAG = CreateRequestScreen.class.getSimpleName();

    public static CreateRequestScreen newInstance(UserDTO repairman) {
        CreateRequestScreen fragment = new CreateRequestScreen();

        CreateRequestScreen.repairman = repairman;

        return fragment;
    }

    private static UserDTO repairman;

    private Date dateBegin = null;
    private Date dateEnd = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_create_request, container, false);
        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) {
            return view;
        }

        final Spinner inputPayment = view.findViewById(R.id.input_payment);
        final TextView dateBeginTextView = view.findViewById(R.id.date_begin);
        final Button dateBeginEdit = view.findViewById(R.id.date_begin_edit);
        final TextView dateEndTextView = view.findViewById(R.id.date_end);
        final Button dateEndEdit = view.findViewById(R.id.date_end_edit);
        final Button sendRequest = view.findViewById(R.id.send_request);

        dateBeginEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        Calendar c = Calendar.getInstance();
                        c.set(selectedYear, selectedMonth, selectedDay, 0, 0);
                        dateBegin = c.getTime();
                        String formatedDate = DateFormat.format("dd.MM.yyyy", dateBegin).toString();
                        dateBeginTextView.setText(formatedDate);
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), listener, year, month, day);
                if (dateEnd != null) {
                    dialog.getDatePicker().setMaxDate(dateEnd.getTime());
                }
                dialog.show();
            }
        });
        dateEndEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        Calendar c = Calendar.getInstance();
                        c.set(selectedYear, selectedMonth, selectedDay, 0, 0);
                        dateEnd = c.getTime();
                        String formatedDate = DateFormat.format("dd.MM.yyyy", dateEnd).toString();
                        dateEndTextView.setText(formatedDate);
                    }
                };
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), listener, year, month, day);
                if (dateBegin != null) {
                    dialog.getDatePicker().setMinDate(dateBegin.getTime());
                }
                dialog.show();
            }
        });

        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String payment = inputPayment.getSelectedItem().toString();
                sendRequest(dateBegin, dateEnd, payment);
            }
        });

        List<String> paymentMethods = MockDatabaseService.getInstance().getPaymentMethods();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_spinner_dropdown_item, paymentMethods);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputPayment.setAdapter(adapter);

        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        return view;
    }

    private void sendRequest(Date dateBegin, Date dateEnd, String paymentMethod) {
        if (dateBegin == null || dateEnd == null || paymentMethod == null || paymentMethod.isEmpty()) {
            Toast.makeText(getActivity(), R.string.create_request_fill_data_error, Toast.LENGTH_LONG).show();
            return;
        }

        CreateRequestDTO createRequestDTO = new CreateRequestDTO();
        createRequestDTO.setClientUsername(MainActivity.getCurrentUser().getUsername());
        createRequestDTO.setRepairmanUsername(repairman.getUsername());
        createRequestDTO.setDateBegin(dateBegin);
        createRequestDTO.setDateEnd(dateEnd);
        createRequestDTO.setPaymentMethod(paymentMethod);

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            new CreateRequestAsyncTask(mainActivity, createRequestDTO).execute();
        }
    }
}
