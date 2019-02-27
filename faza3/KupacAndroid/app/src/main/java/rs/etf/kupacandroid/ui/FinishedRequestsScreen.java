package rs.etf.kupacandroid.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.network.MockDatabaseService;
import rs.etf.kupacandroid.network.dto.RequestInfoDTO;
import rs.etf.kupacandroid.network.dto.UserDTO;
import rs.etf.kupacandroid.recycler.ListRecyclerAdapter;
import rs.etf.kupacandroid.recycler.RecyclerViewBinder;
import rs.etf.kupacandroid.recycler.RequestItemHolderFactory;

public class FinishedRequestsScreen extends Fragment {
    private static final String TAG = FinishedRequestsScreen.class.getSimpleName();

    public static FinishedRequestsScreen newInstance(List<RequestInfoDTO> requests) {
        FinishedRequestsScreen fragment = new FinishedRequestsScreen();

        FinishedRequestsScreen.requests = requests;

        return fragment;
    }

    private static List<RequestInfoDTO> requests;

    private ListRecyclerAdapter<RequestInfoDTO> recyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_finished_requests, container, false);

        TextView title = view.findViewById(R.id.title);
        LinearLayout inputJobLayout = view.findViewById(R.id.job_choose_layout);
        Spinner inputJob = view.findViewById(R.id.input_job);
        RecyclerView recyclerView = view.findViewById(R.id.item_list_recycler_view);

        recyclerAdapter = new ListRecyclerAdapter<>(new RequestItemHolderFactory(),
                viewBinder);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        if (requests == null || requests.isEmpty()) {
            title.setVisibility(View.GONE);
            inputJobLayout.setVisibility(View.GONE);
        } else {
            title.setVisibility(View.VISIBLE);
            inputJobLayout.setVisibility(View.VISIBLE);
        }

        recyclerAdapter.setList(requests);
        recyclerAdapter.notifyDataSetChanged();

        final List<String> jobs = MockDatabaseService.getInstance().getJobs();
        jobs.add(0, "sve");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, jobs);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputJob.setAdapter(adapter);

        inputJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                filter(jobs.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
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

    private final RecyclerViewBinder<RequestInfoDTO> viewBinder = new
            RecyclerViewBinder<RequestInfoDTO>() {
                @Override
                public void onBindViewHolder(RecyclerView.ViewHolder holder,
                                             RequestInfoDTO item, int position) {
                    if (holder instanceof RequestItemHolderFactory.ItemViewHolder) {
                        //show item
                        onBindItem((RequestItemHolderFactory.ItemViewHolder) holder, item, position);

                    } else if (holder instanceof RequestItemHolderFactory.EmptyViewHolder) {
                        //show empty view (no apps available for install)
                        RequestItemHolderFactory.EmptyViewHolder emptyViewHolder =
                                (RequestItemHolderFactory.EmptyViewHolder) holder;
                        onBindEmptyView(emptyViewHolder);
                    }
                }

            };

    private void onBindItem(final RequestItemHolderFactory.ItemViewHolder holder,
                            final RequestInfoDTO item, final int position) {
        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.openFinishedRequestScreen(item);
                }
            }
        });

        holder.icon.setVisibility(View.VISIBLE);
        holder.icon.setImageResource(R.drawable.ic_briefcase_check_black_24dp);

        UserDTO repairman = item.getRepairman();

        String title = repairman.getFirstName() + " " + repairman.getLastName() + " (" + repairman.getJob() + ")";
        holder.title.setText(title);

        Date dateBegin = item.getDateBegin();
        if (dateBegin != null) {
            String formattedDate = DateFormat.format("dd.MM.yyyy", dateBegin).toString();
            holder.description.setText(getString(R.string.date_begin_active_request_item, formattedDate));
        } else {
            holder.description.setVisibility(View.GONE);
        }
    }

    private void onBindEmptyView(RequestItemHolderFactory.EmptyViewHolder emptyViewHolder) {
        emptyViewHolder.title.setText(R.string.finished_requests_empty_title);
    }

    private void filter(String job) {
        Log.d(TAG, "Selected " + job);
        if ("sve".equals(job)) {
            recyclerAdapter.setList(requests);
            recyclerAdapter.notifyDataSetChanged();
            return;
        }

        List<RequestInfoDTO> filtered = new LinkedList<>();
        for (RequestInfoDTO request : requests) {
            if (request.getRepairman().getJob().equals(job)) {
                filtered.add(request);
            }
        }

        Collections.sort(filtered, new Comparator<RequestInfoDTO>() {
            @Override
            public int compare(RequestInfoDTO request1, RequestInfoDTO request2) {
                if (request1 == null) {
                    return -1;
                } else if (request2 == null) {
                    return 1;
                }
                Date d1 = request1.getDateBegin();
                Date d2 = request2.getDateBegin();
                return d1.compareTo(d2);
            }
        });
        Collections.reverse(filtered);

        recyclerAdapter.setList(filtered);
        recyclerAdapter.notifyDataSetChanged();
    }
}
