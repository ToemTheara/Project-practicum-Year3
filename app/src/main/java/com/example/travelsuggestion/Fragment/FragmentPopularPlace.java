package com.example.travelsuggestion.Fragment;

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
import com.example.travelsuggestion.Adapter.AdapterPopularPlaces;
import com.example.travelsuggestion.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class FragmentPopularPlace extends Fragment implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener{
    HashMap<String, String> HashMapForURL;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_place, container, false);

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

        final AdapterPopularPlaces adapterPopularPlaces = new AdapterPopularPlaces();
        recyclerView.setAdapter(adapterPopularPlaces);

        RequestQueue queue = Volley.newRequestQueue(getContext());

        //create request
        String url = "https://api.myjson.com/bins/pg3ic";
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
                adapterPopularPlaces.setNames(names);

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
               adapterPopularPlaces.setDescriptions(descriptions);
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
                adapterPopularPlaces.setImages(imageUrls);
                //locations
                String[] locations = new String[response.length()];
                for (int i = 0; i <= response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        locations[i] = jsonObject.getString("location");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
               adapterPopularPlaces.setLocation(locations);
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

        HashMapForURL.put("Slide1", "https://q-cf.bstatic.com/images/hotel/max1024x768/106/10687031.jpg");
        HashMapForURL.put("Slide2", "https://www.blacktomato.com/wp-content/uploads/2012/04/Cambodias-amazing-beaches.jpg");
        HashMapForURL.put("Slide3", "https://www.khmerangkortourguide.com/userfiles/9680dc3169f78813dd5a86e00e3e8ef5--thai-thai-thai-art(8).jpg");
        HashMapForURL.put("Slide4", "https://ak8.picdn.net/shutterstock/videos/19284568/thumb/1.jpg");
        HashMapForURL.put("Slide5", "https://media.tacdn.com/media/attractions-splice-spp-674x446/06/e0/5e/d0.jpg?fit=crop&w=320&h=140");
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
