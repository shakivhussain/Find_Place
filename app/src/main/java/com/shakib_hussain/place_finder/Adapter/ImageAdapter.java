package com.shakib_hussain.place_finder.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.shakib_hussain.place_finder.Activities.ListenerEdit;
import com.shakib_hussain.place_finder.R;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    Context context;
    ArrayList<Uri> images;

    ListenerEdit listener;


    public ImageAdapter(Context context, ArrayList<Uri> images, ListenerEdit listener) {
        this.context = context;
        this.images = images;

        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                listener.onItemClicked(viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri res = images.get(position);
        holder.image.setImageURI(res);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getAllActors(ArrayList<Uri> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        int i = 0;
        if (images.size() <= 6) {
            i = images.size();
        } else {

            Toast.makeText(context, "Image Limit Exceed", Toast.LENGTH_SHORT).show();
        }

        return i;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ShapeableImageView image;
        View v;
        FloatingActionButton fab;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.shapeableImageView);
            fab = itemView.findViewById(R.id.fab);
            v = itemView;

        }
    }

}

