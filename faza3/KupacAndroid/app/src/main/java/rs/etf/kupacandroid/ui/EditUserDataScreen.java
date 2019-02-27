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
import rs.etf.kupacandroid.asynctask.EditUserDataAsyncTask;
import rs.etf.kupacandroid.network.dto.UserDTO;

public class EditUserDataScreen extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_edit_user_data, container, false);

        UserDTO currentUser = MainActivity.getCurrentUser();

        final EditText inputFirstName = view.findViewById(R.id.input_first_name);
        final EditText inputLastName = view.findViewById(R.id.input_last_name);
        final EditText inputAddress = view.findViewById(R.id.input_address);
        final EditText inputPhoneNumber = view.findViewById(R.id.input_phone_number);
        Button edit = view.findViewById(R.id.edit);

        inputFirstName.setText(currentUser.getFirstName());
        inputLastName.setText(currentUser.getLastName());
        inputAddress.setText(currentUser.getAddress());
        inputPhoneNumber.setText(currentUser.getPhoneNumber());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = inputFirstName.getText().toString();
                String lastName = inputLastName.getText().toString();
                String address = inputAddress.getText().toString();
                String phoneNumber = inputPhoneNumber.getText().toString();

                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    UserDTO currentUser = MainActivity.getCurrentUser();

                    UserDTO userDTO = new UserDTO();
                    userDTO.setFirstName(firstName);
                    userDTO.setLastName(lastName);
                    userDTO.setAddress(address);
                    userDTO.setPhoneNumber(phoneNumber);
                    userDTO.setUsername(currentUser.getUsername());
                    userDTO.setPassword(currentUser.getPassword());
                    userDTO.setType(currentUser.getType());
                    new EditUserDataAsyncTask(activity, userDTO).execute();
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
