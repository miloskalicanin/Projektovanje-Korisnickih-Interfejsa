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
import rs.etf.majstorandroid.asynctask.EditUserDataAsyncTask;
import rs.etf.majstorandroid.network.MockDatabaseService;
import rs.etf.majstorandroid.network.dto.UserDTO;

public class EditUserDataScreen extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_edit_user_data, container, false);

        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) {
            return view;
        }
        UserDTO currentUser = MainActivity.getCurrentUser();

        final EditText inputFirstName = view.findViewById(R.id.input_first_name);
        final EditText inputLastName = view.findViewById(R.id.input_last_name);
        final EditText inputAddress = view.findViewById(R.id.input_address);
        final EditText inputPhoneNumber = view.findViewById(R.id.input_phone_number);
        final Spinner inputJob = view.findViewById(R.id.input_job);
        final EditText inputPrice = view.findViewById(R.id.input_price);
        final EditText inputSpecialTechniques = view.findViewById(R.id.input_special_techniques);
        Button edit = view.findViewById(R.id.edit);

        inputFirstName.setText(currentUser.getFirstName());
        inputLastName.setText(currentUser.getLastName());
        inputAddress.setText(currentUser.getAddress());
        inputPhoneNumber.setText(currentUser.getPhoneNumber());
        inputPrice.setText(currentUser.getPrice().toString());


        StringBuilder sb = new StringBuilder();
        try {
            List<String> techniques = currentUser.getSpecialTechniques();
            sb.append(techniques.get(0));
            for (int i = 1; i < techniques.size(); i++) {
                sb.append(", ").append(techniques.get(i));
            }
        } catch (Exception e) {
            // ignore
        }
        inputSpecialTechniques.setText(sb.toString());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = inputFirstName.getText().toString();
                String lastName = inputLastName.getText().toString();
                String address = inputAddress.getText().toString();
                String phoneNumber = inputPhoneNumber.getText().toString();
                String job = inputJob.getSelectedItem().toString();
                String price = inputPrice.getText().toString();
                String specialTechniques = inputSpecialTechniques.getText().toString();

                List<String> st = new ArrayList<>(1);
                st.add(specialTechniques);

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
                    userDTO.setPrice(Integer.parseInt(price));
                    userDTO.setJob(job);
                    userDTO.setSpecialTechniques(st);
                    new EditUserDataAsyncTask(activity, userDTO).execute();
                }
            }
        });

        List<String> jobs = MockDatabaseService.getInstance().getJobs();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_spinner_dropdown_item, jobs);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputJob.setAdapter(adapter);

        int i = 0;
        while (i < jobs.size()) {
            if (jobs.get(i).equals(currentUser.getJob())) {
                break;
            }
            i++;
        }
        inputJob.setSelection(i);

        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        return view;
    }
}
