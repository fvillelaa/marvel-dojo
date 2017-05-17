package adapter;

import android.abinbev.com.marveldojo.R;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fvillela on 5/15/17.
 */

public class ComicsListAdapter extends RecyclerView.Adapter<ComicsListAdapter.ViewHolder> {

    private String defaultImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6VT5fF0QLt1748mVMlM9RZUa8qMFkLe_6vB3H0MGqTujZl9hS";
    private final Context context;

    public ComicsListAdapter(Context context) {
        this.context = context;
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

    }


    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View view) {
            super(view);
        }
    }
}
