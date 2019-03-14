package com.challenges.battleroyale;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class AdapterChallenges extends RecyclerView.Adapter<AdapterChallenges.ViewHolder>  {

    private ArrayList<ItemChallenges> items = new ArrayList<ItemChallenges>();
    private int width;
    public static int fragment_id;
    ProgressBar progress_bar;


    public AdapterChallenges(int width) {
        super();
        this.width = width;
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChallenges.ViewHolder holder, final int position) {
        final int pos = position;

            Glide.with(holder.itemView.getContext()).load(R.drawable.unlock)
                    .thumbnail(0.5f)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            progress_bar

                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {


                            return false;
                        }
                    })
                    .into(holder.image);

    }

    @NonNull
    @Override
    public AdapterChallenges.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_challenges_card, parent, false);

        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
        lp.height = width;
        view.setLayoutParams(lp);



        return viewHolder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private ProgressBar progress_bar;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            progress_bar =  itemView.findViewById(R.id.progress_bar);
        }
    }

    public  void addMessage(ItemChallenges item) {
        items.add(item);
        notifyItemChanged(items.size() - 1);
    }
}

