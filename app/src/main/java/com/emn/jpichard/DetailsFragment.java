package com.emn.jpichard;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emn.jpichard.model.Book;

/**
 * Created by Joris on 31/01/2016.
 */
public class DetailsFragment extends Fragment {

    public static final String BOOK = "book";
    //private ImageView imgDetailsCover;
    private TextView txtDetailsTitle;
    private TextView txtDetailsPrice;
    private TextView txtDetailsIsbn;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Book book = this.getArguments().getParcelable(BOOK);
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        //imgDetailsCover = (ImageView) view.findViewById(R.id.imgDetailsCover);
        txtDetailsTitle = (TextView) view.findViewById(R.id.detailsTitle);
        txtDetailsPrice = (TextView) view.findViewById(R.id.detailsPrice);
        txtDetailsIsbn = (TextView) view.findViewById(R.id.detailsIsbn);

        txtDetailsTitle.setText(book.getTitle());
        txtDetailsPrice.setText(book.getPrice() + "€");
        txtDetailsIsbn.setText(book.getIsbn());

        // download cover then set it
        /*Picasso.with(getContext())
                .load(book.getCover())
                .resize(300, 300)
                .centerCrop()
                .into(imgDetailsCover);*/

        return view;
    }


}
