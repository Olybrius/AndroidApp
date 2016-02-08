package com.emn.jpichard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Joris on 22/01/2016.
 */
public class BookRecycledAdapter extends RecyclerView.Adapter<BookRecycledAdapter.ViewHolder> {

    private List<Book> mDataset;
    private Context context;
    private BooksFragment.OnDetailsBookListener listener;



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtPrice;
        public ImageView txtImage;

        public ViewHolder(View v) {
            super(v);
            txtImage = (ImageView) v.findViewById(R.id.imageImageView);
            txtName = (TextView) v.findViewById(R.id.nameTextView);
            txtPrice = (TextView) v.findViewById(R.id.priceTextView);
        }
    }

    public BookRecycledAdapter(Context context, List<Book> myBooks) {

        this.mDataset = myBooks;
        this.context = context;
        this.listener = (BooksFragment.OnDetailsBookListener) context;
    }

    @Override
    public BookRecycledAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_view_item_book, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BookRecycledAdapter.ViewHolder holder, final int position) {
        Glide.with(holder.itemView.getContext()).load(this.mDataset.get(position).getCover()).into(holder.txtImage);
        holder.txtName.setText(this.mDataset.get(position).getTitle());
        holder.txtPrice.setText(this.mDataset.get(position).getPrice() + "€");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDetailsBook(mDataset.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setBooks(List<Book> books) {
        this.mDataset = books;
    }
}

