package me.arun.livedataviewmodel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import me.arun.livedataviewmodel.R;
import me.arun.livedataviewmodel.model.User;
import timber.log.Timber;

public class MainUserAdapter extends RecyclerView.Adapter<MainUserAdapter.ViewHolder> {

    private List<User> userList;
    private Context context;
    String TAG="MainUserAdapter";
    public MainUserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public MainUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_user, parent, false);
           return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        User user = userList.get(position);
        holder.tvFirstName.setText(user.getFirstName());
        holder.tvLastName.setText(user.getLastName());
        holder.tvAge.setText(user.getAge());

    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"Users are : " + userList.size());
        return userList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFirstName,tvLastName,tvAge;
        ViewHolder(View itemView) {
            super(itemView);
            tvAge=(itemView).findViewById(R.id.tvAge);
            tvLastName=(itemView).findViewById(R.id.tvLastName);
            tvFirstName=(itemView).findViewById(R.id.tvFirstName);
        }
    }
}