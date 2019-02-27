package rs.etf.kupacandroid.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import rs.etf.kupacandroid.R;


public class RequestItemHolderFactory implements ItemHolderFactory {

    @Override
    public RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case RecyclerItemType.ITEM:
                //inflate item row view
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .item_search_result, parent, false);

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

        public final LinearLayout holder;
        public final ImageView icon;
        public final TextView title;
        public final TextView description;

        /**
         * Instantiates a new Item view holder.
         *
         * @param view the view
         */
        public ItemViewHolder(View view) {
            super(view);

            holder = view.findViewById(R.id.holder);
            icon = view.findViewById(R.id.icon);
            title = view.findViewById(R.id.name);
            description = view.findViewById(R.id.rating);
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
