package com.example.reproject;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    ArrayList<Item> items;

    public Adapter(ArrayList<Item> items)
    {
        this.items = items;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Item item = items.get(position);
        holder.img.setImageResource(item.getAvatar());
        holder.name.setText(item.getName());
        holder.rate.setText(String.valueOf(item.getRating()));
        holder.calo.setText(String.valueOf(item.getCalo()));

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
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView name, rate, calo;

        public ViewHolder(View itemview)
        {
            super(itemview);
            img = itemview.findViewById(R.id.imageView5);
            name = itemview.findViewById(R.id.textView5);
            rate = itemview.findViewById(R.id.textView6);
            calo = itemview.findViewById(R.id.textView7);
        }
    }
}
