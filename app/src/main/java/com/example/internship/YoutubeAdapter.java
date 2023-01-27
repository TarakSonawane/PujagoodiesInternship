package com.example.internship;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internship.databinding.YoutubelistBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class YoutubeAdapter extends  RecyclerView.Adapter<YoutubeAdapter.ViewHolder> {

    int flag;
    Context context;
    ArrayList<String> videoid;
    ArrayList<String> thumbnail;
    ArrayList<String> videotitle;

    public YoutubeAdapter(ArrayList<String> videoid,ArrayList<String> thumbnail,ArrayList<String> videotitle,int flag,Context context)
    {
        this.thumbnail=thumbnail;
        this.videotitle=videotitle;
        this.videoid=videoid;
        this.flag=flag;
        this.context=context;


    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        YoutubelistBinding view = YoutubelistBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String title = videotitle.get(position);
        Picasso.get().load(thumbnail.get(position)).into(holder.binding.thumbnaill);

        if(flag==1) {

            @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = context.getResources().getDrawable(R.drawable.livebg);
            holder.binding.getRoot().setBackground(drawable);
        }
        if(flag==0)
        {
            @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = context.getResources().getDrawable(R.drawable.videobg);
            holder.binding.getRoot().setBackground(drawable);

        }

        holder.binding.titlee.setText(title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),YoutubePlayerActivity.class);

                intent.putExtra("videoID",videoid.get(position));

                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return videoid.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final YoutubelistBinding binding;

        public ViewHolder(YoutubelistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
