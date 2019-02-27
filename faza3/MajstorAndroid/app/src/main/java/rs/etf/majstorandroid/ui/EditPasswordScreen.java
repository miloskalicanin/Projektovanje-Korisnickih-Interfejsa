package rs.etf.majstorandroid.ui;

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
import android.widget.TextView;
import android.widget.Toast;

import rs.etf.majstorandroid.MainActivity;
import rs.etf.majstorandroid.R;
import rs.etf.majstorandroid.asynctask.EditPasswordAsyncTask;
import rs.etf.majstorandroid.network.dto.UserDTO;

public class EditPasswordScreen extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_edit_password, container, false);

        final UserDTO currentUser = MainActivity.getCurrentUser();
        final TextView username = view.findViewById(R.id.username);
        final EditText inputPassword = view.findViewById(R.id.input_password);
        final EditText inputNewPassword1 = view.findViewById(R.id.input_new_password1);
        final EditText inputNewPassword2 = view.findViewById(R.id.input_new_password2);
        Button edit = view.findViewById(R.id.edit);

        username.setText(currentUser.getUsername());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();

                String password = inputPassword.getText().toString();
                String newPassword1 = inputNewPassword1.getText().toString();
                String newPassword2 = inputNewPassword2.getText().toString();

                if (newPassword1.isEmpty() || newPassword2.isEmpty() || password.isEmpty()) {
                    Toast.makeText(activity, R.string.change_password_error_message_1, Toast.LENGTH_LONG).show();
                    return;
                }

                if (!newPassword1.equals(newPassword2)) {
                    Toast.makeText(activity, R.string.change_password_error_message_2, Toast.LENGTH_LONG).show();
                    return;
                }

                UserDTO userDTO = new UserDTO();
                userDTO.setFirstName(currentUser.getFirstName());
                userDTO.setLastName(currentUser.getLastName());
                userDTO.setPhoneNumber(currentUser.getPhoneNumber());
                userDTO.setAddress(currentUser.getAddress());
                userDTO.setUsername(currentUser.getUsername());
                userDTO.setPassword(currentUser.getPassword());
                userDTO.setType(currentUser.getType());

                if (activity != null) {
                    new EditPasswordAsyncTask(activity, userDTO, newPassword1).execute();
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
