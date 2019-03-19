package com.challenges.battleroyale;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.ViewHolder>  {

    private ArrayList<Item> items = new ArrayList<Item>();
    private int width;
    private OnImageClickListener onImageClickListener;

    public interface OnImageClickListener {
        void onImageClick(int position);
    }

    public AdapterMenu(int width) {
        super();
        this.width = width;
    }

    public void setListener(OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMenu.ViewHolder holder, final int position) {
        final int pos = position;
        final Item item = items.get(position);
        holder.week.setText(item.getWeek_name());
        holder.week_numbers.setText(item.getWeek_numbers());

        holder.itemView.setTag(pos);
        if (    item.isLocked()==false){
        Glide.with(holder.itemView.getContext()).load(R.drawable.lock)
                .thumbnail(0.5f)
                .into(holder.image);
        } else {
            Glide.with(holder.itemView.getContext()).load(R.drawable.unlock)
                    .thumbnail(0.5f)
                    .into(holder.image);
        }
    }

    @NonNull
    @Override
    public AdapterMenu.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_card, parent, false);

        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
        lp.height = width;
        view.setLayoutParams(lp);

        AdapterMenu.ViewHolder viewHolder = new AdapterMenu.ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onImageClickListener != null) {
                    onImageClickListener.onImageClick((Integer) view.getTag());
                }
            }
        });

        return viewHolder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView card;
        private ImageView image;
        private TextView week;
        private TextView week_numbers;

        public ViewHolder(View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            image = itemView.findViewById(R.id.image);
            week =  itemView.findViewById(R.id.week);
            week_numbers =  itemView.findViewById(R.id.week_numbers);
        }
    }

    public  void addMessage(Item item) {
        items.add(item);
        notifyItemChanged(items.size() - 1);
    }
}

