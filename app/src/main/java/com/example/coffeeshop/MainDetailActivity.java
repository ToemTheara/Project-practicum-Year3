package com.example.coffeeshop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeeshop.Adapter.AdapterDrinks;
import com.squareup.picasso.Picasso;

public class MainDetailActivity extends AppCompatActivity {

    private AdapterDrinks adapter;
    private String nameDrink;

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
        getSupportActionBar().setTitle(nameDrink);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getIncomingIntent(){

        nameDrink = getIntent().getStringExtra("nameDrink");
        String priceS = getIntent().getStringExtra("priceS");
        String priceM = getIntent().getStringExtra("priceM");
        String priceL = getIntent().getStringExtra("priceL");
        String image = getIntent().getStringExtra("image");
        String description = getIntent().getStringExtra("description");
        String type = getIntent().getStringExtra("type");

        setData(nameDrink, priceS, priceM, priceL, image, description, type);

    }
    private void setData(String nameDrink, String priceSs, String priceMs, String priceLs, String images, String descriptions, String types){
        TextView name = findViewById(R.id.txtDetailName);
        name.setText(nameDrink);

        TextView priceS = findViewById(R.id.txtDetailPriceS);
        priceS.setText(priceSs);

        TextView priceM = findViewById(R.id.txtDetailPriceM);
        priceM.setText(priceMs);

        TextView priceL = findViewById(R.id.txtDetailPriceL);
        priceL.setText(priceLs);

        TextView description = findViewById(R.id.txtDetailDescription);
        description.setText(descriptions);

        TextView type = findViewById(R.id.txtDetailType);
        type.setText(types);

        ImageView image = findViewById(R.id.imgDetail);
        Picasso.with(this).load(images).into(image);

    }
}
