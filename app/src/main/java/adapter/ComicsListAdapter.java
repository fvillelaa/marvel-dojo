package adapter;

import android.abinbev.com.marveldojo.R;
import android.abinbev.com.marveldojo.model.Comic;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fvillela on 5/15/17.
 */

public class ComicsListAdapter extends RecyclerView.Adapter<ComicsListAdapter.ViewHolder> {

    private String defaultImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6VT5fF0QLt1748mVMlM9RZUa8qMFkLe_6vB3H0MGqTujZl9hS";
    private final Context context;
    private List<Comic> comics;

    public ComicsListAdapter(Context context, List<Comic> comics) {
        this.context = context;
        this.comics = comics;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.context).inflate(R.layout.fragment_comic,
                parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comic comic = comics.get(position);

        holder.comicTitle.setText(comic.getTitle());
        Picasso.with(context).load(comic.getImages().get(0).getImageUrl()).into(holder.comicImage);

    }


    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView comicImage;
        TextView comicTitle;

        public ViewHolder(View view) {
            super(view);

            //view.setId();
            comicImage = (ImageView) view.findViewById(R.id.comic_image);
            comicTitle = (TextView) view.findViewById(R.id.comic_title);


        }
    }

    public void updateList(List<Comic> list) {

        comics = list;

        notifyDataSetChanged();
    }
}
