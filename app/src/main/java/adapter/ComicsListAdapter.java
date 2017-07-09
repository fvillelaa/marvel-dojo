package adapter;

import android.abinbev.com.marveldojo.R;
import android.abinbev.com.marveldojo.model.Comic;
import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import activities.ComicActivity;
import constants.Constants;

/**
 * Created by fvillela on 5/15/17.
 */

public class ComicsListAdapter extends RecyclerView.Adapter<ComicsListAdapter.ViewHolder>{

    private String defaultImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6VT5fF0QLt1748mVMlM9RZUa8qMFkLe_6vB3H0MGqTujZl9hS";
    private final Context context;
    private List<Comic> comics;

    public ComicsListAdapter(Context context, List<Comic> comics) {
        this.context = context;
        this.comics = comics;
    }

    @Override
    public int getItemCount() {
        return this.comics.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_comic,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Comic comic = comics.get(position);

        holder.comicTitle.setText(comic.getTitle());

        if (comic.getImages().size() > 0) {
            Picasso.with(context).load(comic.getImages().get(0).getImageUrl()).into(holder.comicImage);
        }
        else {
            Picasso.with(context).load(defaultImageUrl).into(holder.comicImage);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ComicActivity.class);
                intent.putExtra(Constants.intentExtraComicId, comic.getId());

                context.startActivity(intent);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView comicImage;
        TextView comicTitle;

        public ViewHolder(View view) {
            super(view);

            comicImage = (ImageView) view.findViewById(R.id.comic_image);
            comicTitle = (TextView) view.findViewById(R.id.comic_title);
        }
    }
}
