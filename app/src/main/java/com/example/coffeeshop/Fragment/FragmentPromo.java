package com.example.coffeeshop.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.coffeeshop.R;
import com.squareup.picasso.Picasso;

public class FragmentPromo extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promo, container, false);
        Context context;
        String promo = "https://serving.photos.photobox.com/104617372305e9626b2bc9d8f8538d34efb1a0e10f88e24d03012a0e765ec83063b9f8a3.jpg";
        String promo1 = "https://serving.photos.photobox.com/07031262c4b0d34d8a1881a41dd370bf8593151ddc6d5cd2172c97d4c2f4b971de2ea194.jpg";
        ImageView imageView1 = view.findViewById(R.id.promo);
        context = view.getContext();
        Picasso.with(context).load(promo).into(imageView1);
        ImageView imageView2 = view.findViewById(R.id.promo1);
        context = view.getContext();
        Picasso.with(context).load(promo1).into(imageView2);
        return view;
    }
}
