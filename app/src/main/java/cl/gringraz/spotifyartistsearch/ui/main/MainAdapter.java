package cl.gringraz.spotifyartistsearch.ui.main;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.gringraz.spotifyartistsearch.R;
import cl.gringraz.spotifyartistsearch.data.model.Item;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> artists;

    @Inject
    public MainAdapter() {
        artists = new ArrayList<>();
    }

    public void setArtists(List<Item> artists){
        this.artists = artists;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = artists.get(position);
        ItemViewHolder itemHolder = (ItemViewHolder) holder;

        itemHolder.text_name.setText(item.name);
        if (item.genres.size() > 0) itemHolder.text_genre.setText(item.genres.get(0));
        if (item.images.size() > 0) Glide.with(itemHolder.itemView.getContext())
                .load(item.images.get(2).url).into(itemHolder.image);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.text_name)
        TextView  text_name;
        @BindView(R.id.text_genre)
        TextView  text_genre;
        @BindView(R.id.card_view)
        CardView  card_view;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
