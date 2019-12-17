package com.example.coffeeshop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeeshop.Adapter.AdapterPopularPlaces;
import com.squareup.picasso.Picasso;

public class MainDetailActivity extends AppCompatActivity {

    private AdapterPopularPlaces adapter;
    private String namePlace;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);

        Toolbar toolbar = findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getIncomingIntent();
        getSupportActionBar().setTitle(namePlace);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getIncomingIntent(){

        String namePlace = getIntent().getStringExtra("namePlace");
        String image = getIntent().getStringExtra("image");
        String description = getIntent().getStringExtra("description");
        String location = getIntent().getStringExtra("location");

        setData(namePlace, image, description, location);

    }
    private void setData(String namePlace,  String images, String descriptions, String types){
        TextView name = findViewById(R.id.txtDetailName);
        name.setText(namePlace);
        TextView description = findViewById(R.id.txtDetailDescription);
        description.setText(descriptions);

        TextView type = findViewById(R.id.txtDetailLocation);
        type.setText(types);

        ImageView image = findViewById(R.id.imgDetail);
        Picasso.with(this).load(images).into(image);

    }
}
