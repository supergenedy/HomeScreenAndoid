package com.restart.homescreen.Controller.MainFragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.restart.homescreen.Helper.ErrorHandeler;
import com.restart.homescreen.Model.DataItem;
import com.restart.homescreen.Network.ApiCall;
import com.restart.homescreen.Network.ApiListener;
import com.restart.homescreen.R;
import com.restart.homescreen.View.Adapters.ItemAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    @BindView(R.id.btnHotSpotAll)
    TextView btnHotSpotAll;
    @BindView(R.id.recyclerHotSpots)
    RecyclerView recyclerHotSpots;
    @BindView(R.id.progressHotSpots)
    ProgressBar progressHotSpots;
    @BindView(R.id.btnEventsAll)
    TextView btnEventsAll;
    @BindView(R.id.recyclerEvents)
    RecyclerView recyclerEvents;
    @BindView(R.id.progressEvents)
    ProgressBar progressEvents;
    @BindView(R.id.btnAttractionsAll)
    TextView btnAttractionsAll;
    @BindView(R.id.recyclerAttractions)
    RecyclerView recyclerAttractions;
    @BindView(R.id.progressAttractions)
    ProgressBar progressAttractions;
    @BindView(R.id.txtLocations)
    TextView txtLocations;
    @BindView(R.id.btnNotification)
    ImageView btnNotification;
    @BindView(R.id.btnCalender)
    ImageView btnCalender;
    @BindView(R.id.topBar)
    LinearLayout topBar;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.btnFilterSearch)
    ImageView btnFilterSearch;

    private ArrayList<DataItem> hotSpotItems = new ArrayList<>();
    private ArrayList<DataItem> eventItems = new ArrayList<>();
    private ArrayList<DataItem> attractionItems = new ArrayList<>();

    private ItemAdapter hotSpotAdapter;
    private ItemAdapter eventsAdapter;
    private ItemAdapter attractionsAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        recyclerHotSpots.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerAttractions.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerEvents.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        hotSpotAdapter = new ItemAdapter(hotSpotItems);
        recyclerHotSpots.setAdapter(hotSpotAdapter);

        eventsAdapter = new ItemAdapter(eventItems);
        recyclerEvents.setAdapter(eventsAdapter);

        attractionsAdapter = new ItemAdapter(attractionItems);
        recyclerAttractions.setAdapter(attractionsAdapter);

        getHomeData();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        getHomeData();
    }

    private void getHomeData() {
        ApiCall.getHomeData(new ApiListener.ResponseObjectListener() {
            @Override
            public void onSuccessObjectResponse(JSONObject response) {
                if (response != null) {
                    Log.e("DataRes", response.toString());

                    if (response.optString("status_code").equalsIgnoreCase("200")) {

                        //Get HotSpots Data and notify hotSpotsAdapter
                        if (response.optJSONObject("data").optJSONArray("hot_spots").length() > 0) {
                            JSONArray array = response.optJSONObject("data").optJSONArray("hot_spots");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.optJSONObject(i);
                                hotSpotItems.add(new DataItem(object.optString("name")
                                        , object.optJSONArray("categories").optJSONObject(0).optString("name")
                                        , String.valueOf(object.optJSONArray("photos").opt(0))));
                            }
                            progressHotSpots.setVisibility(View.GONE);
                            hotSpotAdapter.notifyDataSetChanged();
                        }

                        //Get Events Data and notify eventsAdapter
                        if (response.optJSONObject("data").optJSONArray("events").length() > 0) {
                            JSONArray array = response.optJSONObject("data").optJSONArray("events");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.optJSONObject(i);
                                eventItems.add(new DataItem(object.optString("name")
                                        , object.optJSONArray("categories").optJSONObject(0).optString("name")
                                        , String.valueOf(object.optJSONArray("photos").opt(0))));
                            }
                            progressEvents.setVisibility(View.GONE);
                            eventsAdapter.notifyDataSetChanged();

                        }

                        //Get Attractions Data and notify attractionsAdapter
                        if (response.optJSONObject("data").optJSONArray("attractions").length() > 0) {
                            JSONArray array = response.optJSONObject("data").optJSONArray("attractions");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.optJSONObject(i);
                                attractionItems.add(new DataItem(object.optString("name")
                                        , object.optJSONArray("categories").optJSONObject(0).optString("name")
                                        , String.valueOf(object.optJSONArray("photos").opt(0))));
                            }
                            progressAttractions.setVisibility(View.GONE);
                            attractionsAdapter.notifyDataSetChanged();
                        }

                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressHotSpots.setVisibility(View.GONE);
                progressAttractions.setVisibility(View.GONE);
                progressEvents.setVisibility(View.GONE);

                new ErrorHandeler().handel(getActivity(), error);
            }
        });
    }

}
