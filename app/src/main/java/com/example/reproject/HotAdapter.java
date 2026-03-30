package com.example.reproject;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.HotViewHolder> {

    ArrayList<Item> hotitem;

    public HotAdapter(ArrayList<Item> hotitem) {
        this.hotitem = hotitem;
    }

    public HotViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotitem, parent, false);
        return new HotViewHolder(view);
    }

    public void onBindViewHolder(HotViewHolder holder, int position) {
        Item item = hotitem.get(position);
        holder.img.setImageResource(item.getAvatar());

        holder.itemView.setOnClickListener(v -> {
            Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.itemexpand);
            dialog.getWindow().setBackgroundDrawable(
                    new ColorDrawable(Color.TRANSPARENT)
            );

            ImageView img = dialog.findViewById(R.id.imageView14);
            TextView calo = dialog.findViewById(R.id.textView9);
            TextView protein = dialog.findViewById(R.id.textView12);
            TextView carbs = dialog.findViewById(R.id.textView15);
            TextView fats = dialog.findViewById(R.id.textView18);
            ImageView heart = dialog.findViewById(R.id.imageView15);

            img.setImageResource(item.getAvatar());
            calo.setText(String.valueOf(item.getCalo()));
            protein.setText(String.valueOf(item.getProtein()));
            carbs.setText(String.valueOf(item.getCarbs()));
            fats.setText(String.valueOf(item.getFats()));

            heart.setSelected(item.isFavorite());
            heart.setOnClickListener(v1 -> {
                boolean state = !item.isFavorite();
                item.setFavorite(state);
                heart.setSelected(state);
            });

            dialog.show();
        });
    }

    public int getItemCount()
    {
        return hotitem.size();
    }

    public static class HotViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;

        public HotViewHolder(View itemview)
        {
            super(itemview);
            img = itemview.findViewById(R.id.imageView33);
        }
    }
}
