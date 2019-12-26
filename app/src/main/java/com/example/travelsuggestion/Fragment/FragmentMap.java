package com.example.travelsuggestion.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.travelsuggestion.R;
import com.squareup.picasso.Picasso;

public class FragmentMap extends Fragment {

    String imageUrl = "https://www.google.com/maps/d/thumbnail?mid=1_Bn3OdhX2Gym63bjoPx7H1NBJ8g";
    private Context context;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        ImageView imageView = view.findViewById(R.id.map);
        context = view.getContext();
        Picasso.with(context).load(imageUrl).into(imageView);
        return view;
    }

}


