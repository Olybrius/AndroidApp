package com.emn.jpichard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Joris on 22/01/2016.
 */
public class LibraryActivity extends AppCompatActivity implements BooksFragment.OnDetailsBookListener {

    private View booksFragment;
    private View bookDetailsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        booksFragment = findViewById(R.id.fragment_books);
        bookDetailsFragment = findViewById(R.id.fragment_details);

        bookDetailsFragment.setVisibility(View.GONE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_books, new BooksFragment(), BooksFragment.class.getSimpleName())
                .commit();
    }

    public void onBackPressed() {
        super.onBackPressed();
        booksFragment.setVisibility(View.VISIBLE);
        bookDetailsFragment.setVisibility(View.GONE);
    }

    @Override
    public void onDetailsBook(Book book) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(DetailsFragment.BOOK, book);
        fragment.setArguments(args);

        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_details, fragment, DetailsFragment.class.getSimpleName())
                .addToBackStack(null)
                .commit();

        boolean landscape = getResources().getBoolean(R.bool.landscape);

        if (!landscape) {
            booksFragment.setVisibility(View.GONE);
            bookDetailsFragment.setVisibility(View.VISIBLE);
        }
        else {
            bookDetailsFragment.setVisibility(View.VISIBLE);
        }
    }
}

