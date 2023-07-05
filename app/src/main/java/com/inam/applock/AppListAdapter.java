package com.inam.applock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder> {
    List<AppInfo> appList;
    Context context;

    AppListAdapter(List<AppInfo> appInfo) {
        this.appList = appInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null){
            context = parent.getContext();
        }

        View view = LayoutInflater.from(context).inflate(R.layout.app_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.applogo.setImageDrawable(appList.get(position).appLogo);
        holder.appName.setText(appList.get(position).appName);
        if (appList.get(position).appStatus){
            holder.appStatus.setImageResource(R.drawable.ic_lock);
        } else{
            holder.appStatus.setImageResource(R.drawable.ic_lock_open);
        }

        //Setting click listener on the lock icon to change it to green and red
        int i = position;
        holder.appStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appList.get(i).appStatus){
                    holder.appStatus.setImageResource(R.drawable.ic_lock_open);
                    appList.get(i).appStatus = false;
                } else{
                    holder.appStatus.setImageResource(R.drawable.ic_lock);
                    appList.get(i).appStatus = true;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView applogo, appStatus;
        TextView appName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.app_name);
            applogo = itemView.findViewById(R.id.app_logo);
            appStatus = itemView.findViewById(R.id.app_status);
        }
    }
}
