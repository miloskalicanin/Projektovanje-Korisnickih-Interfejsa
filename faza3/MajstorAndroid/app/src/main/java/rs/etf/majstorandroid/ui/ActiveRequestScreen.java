package rs.etf.majstorandroid.ui;

import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;
import java.util.List;

import rs.etf.majstorandroid.MainActivity;
import rs.etf.majstorandroid.R;
import rs.etf.majstorandroid.Utils;
import rs.etf.majstorandroid.asynctask.InvalidateRequestAsyncTask;
import rs.etf.majstorandroid.asynctask.RealizeRequestAsyncTask;
import rs.etf.majstorandroid.network.dto.RequestInfoDTO;
import rs.etf.majstorandroid.network.dto.UserDTO;

public class ActiveRequestScreen extends Fragment implements OnMapReadyCallback {
    private static final String TAG = ActiveRequestScreen.class.getSimpleName();

    public static ActiveRequestScreen newInstance(RequestInfoDTO request) {
        ActiveRequestScreen fragment = new ActiveRequestScreen();

        ActiveRequestScreen.request = request;

        return fragment;
    }

    private static RequestInfoDTO request;

    private MapView mMapView;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_active_request, container, false);

        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) {
            return view;
        }

        TextView firstName = view.findViewById(R.id.first_name);
        TextView lastName = view.findViewById(R.id.last_name);
        TextView address = view.findViewById(R.id.address);
        TextView phoneNumber = view.findViewById(R.id.phone_number);
        TextView dateInterval = view.findViewById(R.id.date_interval);
        TextView urgency = view.findViewById(R.id.ugrency);
        Button realize = view.findViewById(R.id.realize);
        Button invalidate = view.findViewById(R.id.invalidate);

        final UserDTO client = request.getClient();

        String dateIntervalText = DateFormat.format("dd.MM.yyyy", request.getDateBegin()).toString() +
                " - " + DateFormat.format("dd.MM.yyyy", request.getDateEnd()).toString();

        String urgencyText = getString(R.string.yes);
        if (!request.isUrgent()) {
            urgencyText = getString(R.string.no);
        }

        firstName.setText(client.getFirstName());
        lastName.setText(client.getLastName());
        address.setText(client.getAddress());
        phoneNumber.setText(client.getPhoneNumber());
        urgency.setText(urgencyText);
        dateInterval.setText(dateIntervalText);

        realize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!request.getDateEnd().before(new Date(System.currentTimeMillis()))) {
                    Toast.makeText(getActivity(), R.string.finish_request_not_possible_message, Toast.LENGTH_LONG).show();
                    return;
                }

                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity activity = (MainActivity) getActivity();
                        if (activity != null) {
                            new RealizeRequestAsyncTask(activity, request).execute();
                        }
                    }
                };
                Utils.displayAlertDialog(getActivity(), getString(R.string.realize_dialog_title), getString(R.string.realize_dialog_messsage), getString(R.string.yes), getString(R.string.back), listener);

            }
        });
        invalidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity activity = (MainActivity) getActivity();
                        if (activity != null) {
                            new InvalidateRequestAsyncTask(activity, request).execute();
                        }
                    }
                };
                Utils.displayAlertDialog(getActivity(), getString(R.string.invalidate_dialog_title), getString(R.string.invalidate_dialog_messsage), getString(R.string.yes), getString(R.string.back), listener);

            }
        });


        mMapView = view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume(); // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            Log.e(TAG, "Init map error", e);
        }
        mMapView.getMapAsync(this);


        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        return view;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // set home marker
        try {
            List<Address> addresses = new Geocoder(getActivity()).getFromLocationName(request.getRepairman().getAddress(), 1);
            if (addresses.size() > 0) {
                Double lat = addresses.get(0).getLatitude();
                Double lon = addresses.get(0).getLongitude();

                final LatLng home = new LatLng(lat, lon);

                googleMap.addMarker(new MarkerOptions()
                        .position(home)
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.ic_account_grey600_24dp))
                        .title(request.getRepairman().getAddress()));
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to find repairman address", e);
        }

        // set destination marker
        try {
            List<Address> addresses = new Geocoder(getActivity()).getFromLocationName(request.getClient().getAddress(), 1);
            if (addresses.size() > 0) {
                Double lat = addresses.get(0).getLatitude();
                Double lon = addresses.get(0).getLongitude();

                final LatLng user = new LatLng(lat, lon);

                googleMap.addMarker(new MarkerOptions()
                        .position(user)
                        .title(request.getClient().getAddress()));

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 15));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            } else {
                // move camera to Belgrade
                LatLng belgrade = new LatLng(44.787197, 20.457273);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(belgrade));
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to find client address", e);

            // move camera to Belgrade
            LatLng belgrade = new LatLng(44.787197, 20.457273);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(belgrade));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        }
    }
}
