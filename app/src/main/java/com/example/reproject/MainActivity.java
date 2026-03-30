package com.example.reproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Rect;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ConstraintLayout tabCanh = findViewById(R.id.canh);
        ConstraintLayout tabChien = findViewById(R.id.chien);
        ConstraintLayout tabNuoc = findViewById(R.id.nuoc);
        ConstraintLayout tabXao = findViewById(R.id.xao);

        ConstraintLayout[] constraintLayout = {tabCanh, tabChien, tabNuoc, tabXao};

        for (ConstraintLayout ctn : constraintLayout) {
            ctn.setOnClickListener(v -> {
                for (ConstraintLayout ctn2 : constraintLayout) {
                    ctn2.setSelected(false);
                }
                v.setSelected(true);
            });
        }

        RecyclerView rclview = findViewById(R.id.recyclerView);
        RecyclerView rclviewhot = findViewById(R.id.recyclerView2);
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Item> hotitem = new ArrayList<>();
        items.add(new Item(R.drawable.image_4__1_, "Bánh chuối yến mạch", 4.8, 275, 15, 32, 6, false));
        items.add(new Item(R.drawable.image_5__2_, "Canh bí tôm", 4.8, 275, 15, 32, 6, false));
        items.add(new Item(R.drawable.image_5__1_, "Cá hồi áp chảo", 4.8, 275, 15, 32, 6, false));
        items.add(new Item(R.drawable.image_5__3_, "Ức gà cuộn măng tây", 4.8, 275, 15, 32, 6, false));
        items.add(new Item(R.drawable.image_4__3_, "Tôm xào rau", 4.8, 275, 15, 32, 6, true));
        items.add(new Item(R.drawable.image_4__4_, "Nước ép healthy", 4.8, 275, 15, 32, 6, true));

        for (Item item : items) {
            if (item.isHot()) {
                hotitem.add(item);
            }
        }
        rclviewhot.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rclviewhot.setAdapter(new HotAdapter(hotitem));

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rclview.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter(items);
        rclview.setAdapter(adapter);

        findViewById(R.id.imageView10).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, findingredient.class);
            startActivity(intent);
        });

        findViewById(R.id.imageView9).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, info.class);
            startActivity(intent);
        });

        findViewById(R.id.editTextText).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, find.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }
}