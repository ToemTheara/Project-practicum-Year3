package com.example.coffeeshop.Adapter;

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

import com.example.coffeeshop.MainDetailActivity;
import com.example.coffeeshop.R;
import com.squareup.picasso.Picasso;

public class AdapterDrinks extends RecyclerView.Adapter<AdapterDrinks.DrawerViewHolder> {

    private Context context;
    private String[] names;
    private String[] priceSs;
    private String[] priceMs;
    private String[] priceLs;
    private String[] images;
    private String[] descriptions;
    private String[] types;

    public void setNames(String[] names) {
        this.names = names;
        notifyDataSetChanged();
    }

    public void setPriceSs(String[] priceSs) {
        this.priceSs = priceSs;
        notifyDataSetChanged();
    }

    public void setPriceMs(String[] priceMs) {
        this.priceMs = priceMs;
        notifyDataSetChanged();
    }

    public void setPriceLs(String[] priceLs) {
        this.priceLs = priceLs;
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

    public void setTypes(String[] types) {
        this.types = types;
    }

    @NonNull
    @Override
    public AdapterDrinks.DrawerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.viewholder_drinks, viewGroup, false);
        AdapterDrinks.DrawerViewHolder viewHolder = new AdapterDrinks.DrawerViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDrinks.DrawerViewHolder drawerViewHolder, final int i) {
        String nameDrink = names[i];
        String priceS = priceSs[i];
        String priceM = priceMs[i];
        String priceL = priceLs[i];
        String image = images[i];
        String description = descriptions[i];
        String type = types[i];

        drawerViewHolder.txtName.setText(nameDrink);
        drawerViewHolder.txtDescription.setText(description);
        drawerViewHolder.txtPriceS.setText(priceS);
        drawerViewHolder.txtType.setText(type);
        context = drawerViewHolder.imgUrl.getContext();
        Picasso.with(context).load(image).into(drawerViewHolder.imgUrl);

        drawerViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainDetailActivity.class);
                intent.putExtra("nameDrink", names[i]);
                intent.putExtra("priceS", priceSs[i]);
                intent.putExtra("priceM", priceMs[i]);
                intent.putExtra("priceL", priceLs[i]);
                intent.putExtra("image", images[i]);
                intent.putExtra("description", descriptions[i]);
                intent.putExtra("type", types[i]);
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
        private TextView txtDescription;
        private TextView txtPriceS;
        private TextView txtType;
        private ImageView imgUrl;
        private CardView cardView;

        public DrawerViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtNameDrink);
            imgUrl = itemView.findViewById(R.id.imgDrink);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtPriceS = itemView.findViewById(R.id.txtPriceS);
            txtType = itemView.findViewById(R.id.txtTypes);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
