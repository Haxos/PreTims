package com.tims.pretims;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.tims.pretims.ItemFragment.OnListFragmentInteractionListener;
import com.tims.pretims.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Dish> dishies;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<Dish> items, OnListFragmentInteractionListener listener) {
        dishies = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.dish = dishies.get(position);
        holder.nameDish_textView.setText(dishies.get(position).getName());
        holder.descriptionDish_textView.setText(dishies.get(position).getDescription());
        holder.favorite_checkBox.setChecked(false);
        holder.imageDish_imageView.setImageBitmap(dishies.get(position).getImage());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.dish);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nameDish_textView;
        public final TextView descriptionDish_textView;
        public final CheckBox favorite_checkBox;
        public final ImageView imageDish_imageView;
        public Dish dish;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            nameDish_textView = (TextView) view.findViewById(R.id.nameDish_textView);
            descriptionDish_textView = (TextView) view.findViewById(R.id.descriptionDish_textView);
            favorite_checkBox = (CheckBox) view.findViewById(R.id.favorite_checkBox);
            imageDish_imageView = (ImageView) view.findViewById(R.id.imageDish_imageView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + descriptionDish_textView.getText() + "'";
        }
    }
}
