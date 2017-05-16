package adapter;

import android.abinbev.com.marveldojo.R;
import android.abinbev.com.marveldojo.model.Comic;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by fvillela on 5/15/17.
 */

public class ComicsListAdapter extends RecyclerView.Adapter<ComicsListAdapter.ViewHolder> {

    private String defaultImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6VT5fF0QLt1748mVMlM9RZUa8qMFkLe_6vB3H0MGqTujZl9hS";
    private ArrayList<Comic> comics;
    private final Context context;

    public ComicsListAdapter(Context context, ArrayList<Comic> comics) {
        this.comics = comics;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.context).inflate(R.layout.fragment_comic, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewTitle.setText(comics.get(position).getTitle());

        String imageUrl = this.defaultImageUrl;
        if (comics.get(position).getImages().size() > 0) {
            imageUrl = comics.get(position).getImages().get(0).getPath()+"."+comics.get(position).getImages().get(0).getExtension();
        }
        Picasso.with(context).load(imageUrl).into(holder.imageViewComic);
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle;
        ImageView imageViewComic;

        public ViewHolder(View view) {
            super(view);

            this.textViewTitle = (TextView) view.findViewById(R.id.comic_title);
            this.imageViewComic = (ImageView) view.findViewById(R.id.comic_image);
        }
    }
}
