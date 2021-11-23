package com.example.td5;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private final List<Contact> mContacts;

    public ContactAdapter(List<Contact> mContacts) {
        this.mContacts = mContacts;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
            public TextView firstNameTextView;
            public TextView lastNameTextView;
            public ImageView imageView;

        public ViewHolder( View itemView) {
            super(itemView);
            firstNameTextView = (TextView) itemView.findViewById(R.id.firstName);
            lastNameTextView = (TextView)  itemView.findViewById(R.id.lastName);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.item_contact,parent,false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Contact contact=mContacts.get(position);
        TextView firstNameTextView=holder.firstNameTextView;
        firstNameTextView.setText(contact.getPrenom());

        TextView lastNameTextView=holder.lastNameTextView;
        lastNameTextView.setText(contact.getNom());

        ImageView imageView = holder.imageView;
        Glide.with(context).load(mContacts.get(position).getImageUrl()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}
