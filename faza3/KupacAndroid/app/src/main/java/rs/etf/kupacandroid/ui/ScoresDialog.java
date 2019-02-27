package rs.etf.kupacandroid.ui;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import rs.etf.kupacandroid.R;
import rs.etf.kupacandroid.network.dto.ScoreDTO;
import rs.etf.kupacandroid.recycler.ListRecyclerAdapter;
import rs.etf.kupacandroid.recycler.RecyclerViewBinder;
import rs.etf.kupacandroid.recycler.ScoreItemHolderFactory;

public class ScoresDialog extends Dialog implements android.view.View.OnClickListener {
    private static final String TAG = ScoresDialog.class.getSimpleName();


    public static ScoresDialog newInstance(Activity activity, List<ScoreDTO> scores) {
        ScoresDialog dialog = new ScoresDialog(activity);

        ScoresDialog.scores = scores;

        return dialog;
    }

    private static List<ScoreDTO> scores;

    private WeakReference<Activity> activityWeakReference;

    private ScoresDialog(Activity activity) {
        super(activity);
        activityWeakReference = new WeakReference<>(activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        Activity activity = activityWeakReference.get();
        if (activity == null) {
            return;
        }

        TextView title = findViewById(R.id.title);
        title.setText(R.string.scores_title);

        TextView ok = findViewById(R.id.ok);
        ok.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.item_list_recycler_view);

        ListRecyclerAdapter<ScoreDTO> recyclerAdapter = new ListRecyclerAdapter<>(new ScoreItemHolderFactory(),
                viewBinder);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.setList(scores);
        recyclerAdapter.notifyDataSetChanged();
    }

    private final RecyclerViewBinder<ScoreDTO> viewBinder = new
            RecyclerViewBinder<ScoreDTO>() {
                @Override
                public void onBindViewHolder(RecyclerView.ViewHolder holder,
                                             ScoreDTO item, int position) {
                    if (holder instanceof ScoreItemHolderFactory.ItemViewHolder) {
                        //show item
                        onBindItem((ScoreItemHolderFactory.ItemViewHolder) holder, item, position);

                    } else if (holder instanceof ScoreItemHolderFactory.EmptyViewHolder) {
                        //show empty view (no apps available for install)
                        ScoreItemHolderFactory.EmptyViewHolder emptyViewHolder =
                                (ScoreItemHolderFactory.EmptyViewHolder) holder;
                        onBindEmptyView(emptyViewHolder);
                    }
                }

            };

    private void onBindItem(final ScoreItemHolderFactory.ItemViewHolder holder,
                            final ScoreDTO item, final int position) {
        String formatedDate = DateFormat.format("dd.MM.yyyy", item.getDate()).toString();
        holder.date.setText(formatedDate);

        holder.score.setText(String.valueOf(item.getScore()));
    }

    private void onBindEmptyView(ScoreItemHolderFactory.EmptyViewHolder emptyViewHolder) {
        emptyViewHolder.title.setText(R.string.scores_empty_title);
    }

    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.ok:
                dismiss();
                break;
            default:
                break;
        }*/
        dismiss();
    }
}
