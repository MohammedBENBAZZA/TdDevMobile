package com.example.td6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private final List<Repo> mRepos;

    public RepoAdapter(List<Repo> Repos) {
        mRepos = Repos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View repoView=inflater.inflate(R.layout.item_repo,parent,false);
        return new ViewHolder(repoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     Repo repo= mRepos.get(position);

     TextView nameTextView = holder.nameTextView;
        nameTextView.setText(repo.getName());
     TextView urlTextView = holder.urlTextView;
        urlTextView.setText(repo.getHtml_url());
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView urlTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView= itemView.findViewById(R.id.textViewNameRepo);
            urlTextView= itemView.findViewById(R.id.textViewURLRepo);
        }
    }
}
