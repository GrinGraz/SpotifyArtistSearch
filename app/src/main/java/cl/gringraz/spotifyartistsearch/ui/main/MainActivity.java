package cl.gringraz.spotifyartistsearch.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.gringraz.spotifyartistsearch.R;
import cl.gringraz.spotifyartistsearch.data.model.Artists;
import cl.gringraz.spotifyartistsearch.data.model.Item;
import cl.gringraz.spotifyartistsearch.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements MainMvpView, SearchView.OnQueryTextListener {

    @Inject MainPresenter mMainPresenter;
    @Inject MainAdapter mMainAdapter;

    @BindView(R.id.textView) TextView textView;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    private Artists artists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPresenter.attachView(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMainAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
        MenuItem searchMenuItem = menu.findItem( R.id.action_search );
        searchMenuItem.expandActionView();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchArtist(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void searchArtist(String artistName) {
        textView.setText(R.string.searching);
        mMainPresenter.searchArtistByName(artistName);
    }

    @Override
    public void onSearchOk(Artists artists) {
        this.artists = artists;
        if (artists.items.isEmpty()) {
            textView.setText(R.string.empty_response);
            toggleVisibility(true);
        } else {
            toggleVisibility(false);
            mMainAdapter.setArtists(artists.items);
        }
    }

    @Override
    public void onError() {
        toggleVisibility(true);
        textView.setText(R.string.search_error);
    }

    public void toggleVisibility(boolean hideResults){
        if (hideResults){
            mRecyclerView.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("data", artists);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        artists = savedInstanceState.getParcelable("data");
        if (artists != null) {
            mMainAdapter.setArtists(artists.items);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }
}