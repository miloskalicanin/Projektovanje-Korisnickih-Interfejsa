package rs.etf.kupacandroid.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rs.etf.kupacandroid.R;


public class CommentItemHolderFactory implements ItemHolderFactory {

    @Override
    public RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case RecyclerItemType.ITEM:
                //inflate item row view
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .item_comment, parent, false);

                //return view holder
                return new ItemViewHolder(view);
            case RecyclerItemType.EMPTY:
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R
                        .layout.item_default_empty, parent, false));
            default:
                throw new IllegalArgumentException("View type is not supported, type:" + viewType);
        }
    }

    /**
     * Actual item view holder.
     */
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public final TextView date;
        public final TextView comment;

        /**
         * Instantiates a new Item view holder.
         *
         * @param view the view
         */
        public ItemViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.date);
            comment = view.findViewById(R.id.comment);
        }
    }

    /**
     * Empty label view holder.
     */
    public static class EmptyViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;

        /**
         * Instantiates a new Item view holder.
         *
         * @param view the view
         */
        public EmptyViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.fragment_default_empty_item_title);
        }
    }
}
