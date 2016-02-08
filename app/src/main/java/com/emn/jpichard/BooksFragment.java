package com.emn.jpichard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;

/**
 * Created by Joris on 31/01/2016.
 */
public class BooksFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private BookRecycledAdapter mAdapter;
    private Context context;
    private List<Book> books = new ArrayList<>();
    private OnDetailsBookListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new BookRecycledAdapter(this.context, books);

        mLayoutManager = new LinearLayoutManager(this.context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        loadBooks();

        return view;
    }

    protected void loadBooks() {

        Timber.plant(new Timber.DebugTree());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HenriPotierService service = retrofit.create(HenriPotierService.class);

        service.listBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Response<List<Book>> response, Retrofit retrofit) {
                List<Book> books = response.body();
                mAdapter.setBooks(books);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                Timber.e(t, "Failure !");
            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public interface OnDetailsBookListener {

        public void onDetailsBook(Book book);

    }
}
