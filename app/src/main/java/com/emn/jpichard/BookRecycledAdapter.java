package com.emn.jpichard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Joris on 22/01/2016.
 */
public class BookRecycledAdapter extends RecyclerView.Adapter<BookRecycledAdapter.ViewHolder> {

    private List<Book> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView price;
        public ImageView image;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.imageImageView);
            name = (TextView) v.findViewById(R.id.nameTextView);
            price = (TextView) v.findViewById(R.id.priceTextView);
        }
    }

    public BookRecycledAdapter(List<Book> myBooks) {
        mDataset = myBooks;
    }

    @Override
    public BookRecycledAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_view_item_book, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BookRecycledAdapter.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(this.mDataset.get(position).getCover()).into(holder.image);
        holder.name.setText(this.mDataset.get(position).getTitle());
        holder.price.setText(this.mDataset.get(position).getPrice() + "€");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

