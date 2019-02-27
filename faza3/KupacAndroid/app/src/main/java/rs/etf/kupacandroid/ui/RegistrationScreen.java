package rs.etf.kupacandroid.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.asynctask.RegisterAsyncTask;
import rs.etf.kupacandroid.network.dto.UserDTO;

public class RegistrationScreen extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        final EditText inputFirstName = view.findViewById(R.id.input_first_name);
        final EditText inputLastName = view.findViewById(R.id.input_last_name);
        final EditText inputAddress = view.findViewById(R.id.input_address);
        final EditText inputPhoneNumber = view.findViewById(R.id.input_phone_number);
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
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();

                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setFirstName(firstName);
                    userDTO.setLastName(lastName);
                    userDTO.setAddress(address);
                    userDTO.setPhoneNumber(phoneNumber);
                    userDTO.setUsername(username);
                    userDTO.setPassword(password);
                    new RegisterAsyncTask(activity, userDTO).execute();
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
