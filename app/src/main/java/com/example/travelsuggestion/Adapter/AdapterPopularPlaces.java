package com.example.travelsuggestion.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travelsuggestion.MainDetailActivity;
import com.example.travelsuggestion.R;
import com.squareup.picasso.Picasso;

public class AdapterPopularPlaces extends RecyclerView.Adapter<AdapterPopularPlaces.DrawerViewHolder> {

    private Context context;
    private String[] names;
    private String[] images;
    private String[] descriptions;
    private String[] locations;

    public void setNames(String[] names) {
        this.names = names;
        notifyDataSetChanged();
    }


    public void setImages(String[] images) {
        this.images = images;
        notifyDataSetChanged();
    }

    public void setDescriptions(String[] descriptions) {
        this.descriptions = descriptions;
        notifyDataSetChanged();
    }

    public void setLocation(String[] locations) {
        this.locations = locations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterPopularPlaces.DrawerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.viewholder_places, viewGroup, false);
        AdapterPopularPlaces.DrawerViewHolder viewHolder = new AdapterPopularPlaces.DrawerViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPopularPlaces.DrawerViewHolder drawerViewHolder, final int i) {
        String namePlaces = names[i];
        String image = images[i];
        String location = locations[i];

        drawerViewHolder.txtName.setText(namePlaces);

        drawerViewHolder.txtName.setText(namePlaces);
        drawerViewHolder.txtLocation.setText(location);
        context = drawerViewHolder.imgUrl.getContext();
        Picasso.with(context).load(image).into(drawerViewHolder.imgUrl);

        drawerViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainDetailActivity.class);
                intent.putExtra("namePlace", names[i]);
                intent.putExtra("image", images[i]);
                intent.putExtra("description", descriptions[i]);
                intent.putExtra("location", locations[i]);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (names == null){
            return 0;
        }else {
            return names.length;
        }
    }

    public class DrawerViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtLocation;
        private ImageView imgUrl;
        private CardView cardView;

        public DrawerViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtNamePlace);
            imgUrl = itemView.findViewById(R.id.imgPlace);
            txtLocation = itemView.findViewById(R.id.txtLocationVH);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
