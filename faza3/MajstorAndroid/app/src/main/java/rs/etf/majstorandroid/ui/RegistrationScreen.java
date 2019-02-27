package rs.etf.majstorandroid.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import rs.etf.majstorandroid.MainActivity;
import rs.etf.majstorandroid.R;
import rs.etf.majstorandroid.asynctask.RegisterAsyncTask;
import rs.etf.majstorandroid.network.MockDatabaseService;
import rs.etf.majstorandroid.network.dto.UserDTO;

public class RegistrationScreen extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) {
            return view;
        }

        final EditText inputFirstName = view.findViewById(R.id.input_first_name);
        final EditText inputLastName = view.findViewById(R.id.input_last_name);
        final EditText inputAddress = view.findViewById(R.id.input_address);
        final EditText inputPhoneNumber = view.findViewById(R.id.input_phone_number);
        final Spinner inputJob = view.findViewById(R.id.input_job);
        final EditText inputPrice = view.findViewById(R.id.input_price);
        final EditText inputSpecialTechniques = view.findViewById(R.id.input_special_techniques);
        final EditText inputUsername = view.findViewById(R.id.input_username);
        final EditText inputPassword = view.findViewById(R.id.input_password);
        Button register = view.findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = inputFirstName.getText().toString();
                String lastName = inputLastName.getText().toString();
                String address = inputAddress.getText().toString();
                String phoneNumber = inputPhoneNumber.getText().toString();
                String job = inputJob.getSelectedItem().toString();
                String price = inputPrice.getText().toString();
                String specialTechniques = inputSpecialTechniques.getText().toString();
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();

                List<String> st = new ArrayList<>(1);
                st.add(specialTechniques);

                try {
                    MainActivity activity = (MainActivity) getActivity();
                    if (activity != null) {
                        UserDTO userDTO = new UserDTO();
                        userDTO.setFirstName(firstName);
                        userDTO.setLastName(lastName);
                        userDTO.setAddress(address);
                        userDTO.setPhoneNumber(phoneNumber);
                        userDTO.setUsername(username);
                        userDTO.setPassword(password);
                        userDTO.setType(1);
                        userDTO.setPrice(Integer.parseInt(price));
                        userDTO.setJob(job);
                        userDTO.setSpecialTechniques(st);
                        new RegisterAsyncTask(activity, userDTO).execute();
                    }
                } catch (Exception e) {
                    // ignore
                }
            }
        });

        List<String> jobs = MockDatabaseService.getInstance().getJobs();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_spinner_dropdown_item, jobs);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputJob.setAdapter(adapter);

        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        return view;
    }
}
