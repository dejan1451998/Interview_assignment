package com.dldev.interviewassignment;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Model> models;

    public Adapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_design, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Zoom.class);
                intent.putExtra("imageUrl", models.get(position).getUrlToImage());
                context.startActivity(intent);
            }
        });

        holder.headerTitle.setText(models.get(position).getTitle());
        holder.content.setText(models.get(position).getDescription());
        Glide.with(context).load(models.get(position).getUrlToImage()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView headerTitle, content;
        CardView cardView;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            headerTitle = itemView.findViewById(R.id.header_title);
            content = itemView.findViewById(R.id.content);
            img = itemView.findViewById(R.id.clickableImage);
            cardView = itemView.findViewById(R.id.cardView);


        }
    }


}
