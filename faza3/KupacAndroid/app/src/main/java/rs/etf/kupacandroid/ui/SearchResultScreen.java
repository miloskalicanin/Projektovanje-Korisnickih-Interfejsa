package rs.etf.kupacandroid.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rs.etf.kupacandroid.MainActivity;
import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.network.dto.UserDTO;
import rs.etf.kupacandroid.recycler.ListRecyclerAdapter;
import rs.etf.kupacandroid.recycler.RecyclerViewBinder;
import rs.etf.kupacandroid.recycler.SearchItemHolderFactory;

public class SearchResultScreen extends Fragment {
    private static final String TAG = SearchResultScreen.class.getSimpleName();

    public static SearchResultScreen newInstance(List<UserDTO> searchResult) {
        SearchResultScreen fragment = new SearchResultScreen();

        SearchResultScreen.searchResult = searchResult;

        return fragment;
    }

    private static List<UserDTO> searchResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_search_results, container, false);

        TextView title = view.findViewById(R.id.title);
        RecyclerView recyclerView = view.findViewById(R.id.item_list_recycler_view);

        ListRecyclerAdapter<UserDTO> recyclerAdapter = new ListRecyclerAdapter<>(new SearchItemHolderFactory(),
                viewBinder);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        if (searchResult == null || searchResult.isEmpty()) {
            title.setVisibility(View.GONE);
        } else {
            title.setVisibility(View.VISIBLE);
        }

        recyclerAdapter.setList(searchResult);
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

    private final RecyclerViewBinder<UserDTO> viewBinder = new
            RecyclerViewBinder<UserDTO>() {
                @Override
                public void onBindViewHolder(RecyclerView.ViewHolder holder,
                                             UserDTO item, int position) {
                    if (holder instanceof SearchItemHolderFactory.ItemViewHolder) {
                        //show item
                        onBindItem((SearchItemHolderFactory.ItemViewHolder) holder, item, position);

                    } else if (holder instanceof SearchItemHolderFactory.EmptyViewHolder) {
                        //show empty view (no apps available for install)
                        SearchItemHolderFactory.EmptyViewHolder emptyViewHolder =
                                (SearchItemHolderFactory.EmptyViewHolder) holder;
                        onBindEmptyView(emptyViewHolder);
                    }
                }

            };

    private void onBindItem(final SearchItemHolderFactory.ItemViewHolder holder,
                            final UserDTO item, final int position) {
        holder.holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.openRepairmanInfo(item);
                }
            }
        });

        Double rating = item.getRating();
        if (rating != null && rating > 4) {
            holder.icon.setVisibility(View.VISIBLE);
        } else {
            holder.icon.setVisibility(View.GONE);
        }

        String name = item.getFirstName() + " " + item.getLastName();
        holder.name.setText(name);

        if (rating != null) {
            holder.rating.setText(getString(R.string.rating_search_item, String.valueOf(rating)));
        } else {
            holder.rating.setVisibility(View.GONE);
        }
    }

    private void onBindEmptyView(SearchItemHolderFactory.EmptyViewHolder emptyViewHolder) {
        emptyViewHolder.title.setText(R.string.search_result_empty_title);
    }

}
