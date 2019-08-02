package com.restart.homescreen.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.restart.homescreen.Model.DataItem;
import com.restart.homescreen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ahmed El Genedy on 02-Aug-19.
 * Copyright (C) 2019 SuperGenedy. supergenedy@gmail.com All Rights Reserved.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {


    private ArrayList<DataItem> arrayItems;

    public ItemAdapter(ArrayList<DataItem> arrayItems) {
        this.arrayItems = arrayItems;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemImage)
        ImageView itemImage;
        @BindView(R.id.itemTitle)
        TextView itemTitle;
        @BindView(R.id.itemSubtitle)
        TextView itemSubtitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataItem item = arrayItems.get(position);

        holder.itemTitle.setText(item.getItemName());
        holder.itemSubtitle.setText(item.getItemCatName());
        Picasso.get()
                .load(item.getImageURL())
                .into(holder.itemImage);

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayItems.size();
    }

}
