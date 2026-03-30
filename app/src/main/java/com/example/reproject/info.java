package com.example.reproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            findViewById(R.id.imageView31).setOnClickListener(v -> {
                Intent intent = new Intent(info.this, MainActivity.class);
                startActivity(intent);
            });

            findViewById(R.id.imageView8).setOnClickListener(v -> {
                Intent intent = new Intent(info.this, findingredient.class);
                startActivity(intent);
            });

            ConstraintLayout breakfast = findViewById(R.id.constraintLayout9);
            ConstraintLayout lunch = findViewById(R.id.constraintLayout10);
            ConstraintLayout dinner = findViewById(R.id.constrainlayout11);

            ImageView breakfastar = findViewById(R.id.breakfastarrow);
            ImageView lunchar = findViewById(R.id.imageView28);
            ImageView dinnerar = findViewById(R.id.imageView30);

            ConstraintLayout breakfastex = findViewById(R.id.breakfast);
            ConstraintLayout lunchex = findViewById(R.id.lunch);
            ConstraintLayout dinnerex = findViewById(R.id.dinner);

            breakfast.setOnClickListener(v -> toggle(breakfastex, breakfastar));
            lunch.setOnClickListener(v -> toggle(lunchex, lunchar));
            dinner.setOnClickListener(v -> toggle(dinnerex, dinnerar));
        }
    private void toggle(View content, ImageView arrow){
        if(content.getVisibility() == View.GONE)
        {
            //closeAll();

            content.setVisibility(View.VISIBLE);

            arrow.setImageResource(R.drawable.ic_round_arrow_back_ios__1_);
        }
        else
        {
            content.setVisibility(View.GONE);

            arrow.setImageResource(R.drawable.ic_round_arrow_back_ios);
        }
    }

    private void closeAll(){
        ConstraintLayout breakfastex = findViewById(R.id.breakfast);
        ConstraintLayout lunchex = findViewById(R.id.lunch);
        ConstraintLayout dinnerex = findViewById(R.id.dinner);

        breakfastex.setVisibility(View.GONE);
        lunchex.setVisibility(View.GONE);
        dinnerex.setVisibility(View.GONE);
    }
}