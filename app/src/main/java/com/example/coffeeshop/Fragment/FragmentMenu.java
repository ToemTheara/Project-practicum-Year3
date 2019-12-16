package com.example.coffeeshop.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.coffeeshop.Adapter.AdapterDrinks;
import com.example.coffeeshop.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class FragmentMenu extends Fragment implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener{
    HashMap<String, String> HashMapForURL;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        SliderLayout sliderLayout = view.findViewById(R.id.slider);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMain);

        AddImagesUrlOnline();

        for (String name : HashMapForURL.keySet()) {

            TextSliderView textSliderView = new TextSliderView(getContext());

            textSliderView
                    .description(name)
                    .image(HashMapForURL.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra", name);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(5000);
        sliderLayout.addOnPageChangeListener(
                this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        final AdapterDrinks adapterHotDrinks = new AdapterDrinks();
        recyclerView.setAdapter(adapterHotDrinks);

        RequestQueue queue = Volley.newRequestQueue(getContext());

        //create request
        String url = "https://api.myjson.com/bins/lq5vz";
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //names
                String[] names = new String[response.length()];
                for (int i = 0; i <= response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        names[i] = jsonObject.getString("name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterHotDrinks.setNames(names);
                //price S
                String[] priceSs = new String[response.length()];
                for (int i = 0; i <= response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        priceSs[i] = jsonObject.getString("priceS");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterHotDrinks.setPriceSs(priceSs);
                //price M
                String[] priceMs = new String[response.length()];
                for (int i = 0; i <= response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        priceMs[i] = jsonObject.getString("priceM");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterHotDrinks.setPriceMs(priceMs);
                //price L
                String[] priceLs = new String[response.length()];
                for (int i = 0; i <= response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        priceLs[i] = jsonObject.getString("priceL");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterHotDrinks.setPriceLs(priceLs);
                //description
                String[] descriptions = new String[response.length()];
                for (int i = 0; i <= response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        descriptions[i] = jsonObject.getString("description");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterHotDrinks.setDescriptions(descriptions);
                //image urls
                String[] imageUrls = new String[response.length()];
                for (int i = 0; i <= response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        imageUrls[i] = jsonObject.getString("image");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterHotDrinks.setImages(imageUrls);
                //types
                String[] types = new String[response.length()];
                for (int i = 0; i <= response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        types[i] = jsonObject.getString("type");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterHotDrinks.setTypes(types);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("bug", "load data error" + error.getMessage());
            }
        });
        queue.add(request);

        return view;
    }
    public void AddImagesUrlOnline() {

        HashMapForURL = new HashMap<>();

        HashMapForURL.put("Slide1", "https://ak1.picdn.net/shutterstock/videos/925081/thumb/1.jpg");
        HashMapForURL.put("Slide2", "https://png.pngtree.com/thumb_back/fw800/back_pic/02/65/13/095787272316695.jpg");
        HashMapForURL.put("Slide3", "https://i.ytimg.com/vi/G0vzdaALJ7s/maxresdefault.jpg");
        HashMapForURL.put("Slide4", "https://ae01.alicdn.com/kf/HTB1vJHfc2jM8KJjSZFNq6zQjFXay/wallpaper-hand-painted-European-and-American-coffee-cup-coffee-shop-coffee-beans-English-background-wall-wallpaper.jpg");
        HashMapForURL.put("Slide5", "https://www.nespresso.com/shared_res/mosaic_freehtml/images/b2b/home/hero-banner-background-1.jpg");
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
