package rs.etf.kupacandroid.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.network.dto.RequestInfoDTO;
import rs.etf.kupacandroid.network.dto.UserDTO;
import rs.etf.kupacandroid.recycler.ListRecyclerAdapter;
import rs.etf.kupacandroid.recycler.RecyclerViewBinder;
import rs.etf.kupacandroid.recycler.RequestItemHolderFactory;

public class ActiveRequestsScreen extends Fragment {
    private static final String TAG = ActiveRequestsScreen.class.getSimpleName();

    public static ActiveRequestsScreen newInstance(List<RequestInfoDTO> requests) {
        ActiveRequestsScreen fragment = new ActiveRequestsScreen();

        ActiveRequestsScreen.requests = requests;

        return fragment;
    }

    private static List<RequestInfoDTO> requests;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_active_requests, container, false);

        TextView title = view.findViewById(R.id.title);
        RecyclerView recyclerView = view.findViewById(R.id.item_list_recycler_view);

        ListRecyclerAdapter<RequestInfoDTO> recyclerAdapter = new ListRecyclerAdapter<>(new RequestItemHolderFactory(),
                viewBinder);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        if (requests == null || requests.isEmpty()) {
            title.setVisibility(View.GONE);
        } else {
            title.setVisibility(View.VISIBLE);
        }

        recyclerAdapter.setList(requests);
        recyclerAdapter.notifyDataSetChanged();

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
                    activity.openActiveRequestScreen(item);
                }
            }
        });

        holder.icon.setVisibility(View.VISIBLE);
        holder.icon.setImageResource(R.drawable.ic_wrench_black_24dp);

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
        emptyViewHolder.title.setText(R.string.active_requests_empty_title);
    }

}
