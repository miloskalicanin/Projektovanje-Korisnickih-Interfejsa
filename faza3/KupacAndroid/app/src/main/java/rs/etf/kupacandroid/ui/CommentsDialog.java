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
import rs.etf.kupacandroid.network.dto.CommentDTO;
import rs.etf.kupacandroid.recycler.CommentItemHolderFactory;
import rs.etf.kupacandroid.recycler.ListRecyclerAdapter;
import rs.etf.kupacandroid.recycler.RecyclerViewBinder;

public class CommentsDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = CommentsDialog.class.getSimpleName();


    public static CommentsDialog newInstance(Activity activity, List<CommentDTO> comments) {
        CommentsDialog dialog = new CommentsDialog(activity);

        CommentsDialog.comments = comments;

        return dialog;
    }

    private static List<CommentDTO> comments;

    private WeakReference<Activity> activityWeakReference;

    private CommentsDialog(Activity activity) {
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
        title.setText(R.string.comments_title);

        TextView ok = findViewById(R.id.ok);
        ok.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.item_list_recycler_view);

        ListRecyclerAdapter<CommentDTO> recyclerAdapter = new ListRecyclerAdapter<>(new CommentItemHolderFactory(),
                viewBinder);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerAdapter.setList(comments);
        recyclerAdapter.notifyDataSetChanged();
    }

    private final RecyclerViewBinder<CommentDTO> viewBinder = new
            RecyclerViewBinder<CommentDTO>() {
                @Override
                public void onBindViewHolder(RecyclerView.ViewHolder holder,
                                             CommentDTO item, int position) {
                    if (holder instanceof CommentItemHolderFactory.ItemViewHolder) {
                        //show item
                        onBindItem((CommentItemHolderFactory.ItemViewHolder) holder, item, position);

                    } else if (holder instanceof CommentItemHolderFactory.EmptyViewHolder) {
                        //show empty view (no apps available for install)
                        CommentItemHolderFactory.EmptyViewHolder emptyViewHolder =
                                (CommentItemHolderFactory.EmptyViewHolder) holder;
                        onBindEmptyView(emptyViewHolder);
                    }
                }

            };

    private void onBindItem(final CommentItemHolderFactory.ItemViewHolder holder,
                            final CommentDTO item, final int position) {
        String formatedDate = DateFormat.format("dd.MM.yyyy", item.getDate()).toString();
        holder.date.setText(formatedDate);

        holder.comment.setText(item.getComment());
    }

    private void onBindEmptyView(CommentItemHolderFactory.EmptyViewHolder emptyViewHolder) {
        emptyViewHolder.title.setText(R.string.comments_empty_title);
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
