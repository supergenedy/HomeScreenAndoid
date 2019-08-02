package com.restart.homescreen.Controller;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.restart.homescreen.Controller.MainFragment.HomeFragment;
import com.restart.homescreen.Controller.MainFragment.NotificationFragment;
import com.restart.homescreen.Controller.MainFragment.ProfileFragment;
import com.restart.homescreen.Controller.MainFragment.SearchFragment;
import com.restart.homescreen.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.imgIconHome)
    ImageView imgIconHome;
    @BindView(R.id.imgIconSearch)
    ImageView imgIconSearch;
    @BindView(R.id.imgIconPrize)
    ImageView imgIconPrize;
    @BindView(R.id.imgIconProfile)
    ImageView imgIconProfile;
    @BindView(R.id.bottomAppBar)
    RelativeLayout bottomAppBar;
    @BindView(R.id.backGroundForFloating)
    RelativeLayout backGroundForFloating;
    @BindView(R.id.floatBtnEvents)
    LinearLayout floatBtnEvents;
    @BindView(R.id.floatBtnAttractions)
    LinearLayout floatBtnAttractions;
    @BindView(R.id.floatBtnHotSpots)
    LinearLayout floatBtnHotSpots;
    @BindView(R.id.imgBellMan)
    ImageView imgBellMan;
    @BindView(R.id.floatBtnMap)
    LinearLayout floatBtnMap;
    @BindView(R.id.fabEvents)
    FloatingActionButton fabEvents;
    @BindView(R.id.fabAttractions)
    FloatingActionButton fabAttractions;
    @BindView(R.id.fabHotSpots)
    FloatingActionButton fabHotSpots;
    @BindView(R.id.fabMap)
    FloatingActionButton fabMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        HomeFragment homeFragment = new HomeFragment();
        loadFragment(homeFragment, "home");
    }

    @OnClick({R.id.imgIconHome, R.id.imgIconSearch, R.id.imgIconPrize, R.id.imgIconProfile, R.id.backGroundForFloating, R.id.floatBtnEvents, R.id.floatBtnAttractions, R.id.floatBtnHotSpots, R.id.imgBellMan, R.id.floatBtnMap})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgIconHome:
                HomeFragment homeFragment = new HomeFragment();
                loadFragment(homeFragment, "home");
                break;
            case R.id.imgIconSearch:
                SearchFragment searchFragment = new SearchFragment();
                loadFragment(searchFragment, "search");
                break;
            case R.id.imgIconPrize:
                NotificationFragment notificationFragment = new NotificationFragment();
                loadFragment(notificationFragment, "notification");
                break;
            case R.id.imgIconProfile:
                ProfileFragment profileFragment = new ProfileFragment();
                loadFragment(profileFragment, "profile");
                break;
            case R.id.imgBellMan:
                if (backGroundForFloating.getVisibility() == View.VISIBLE) {
                    hideFloatingButtons();
                } else {
                    showFloatingButtons();
                }
                break;
            case R.id.backGroundForFloating:
                hideFloatingButtons();
                break;
            case R.id.fabHotSpots:
                break;
            case R.id.fabAttractions:
                break;
            case R.id.fabEvents:
                break;
            case R.id.fabMap:
                break;
        }
    }

    private void loadFragment(Fragment fragment, String tag) {
        // load fragment
        changeNavBarSelection(tag);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment, tag);
        transaction.commit();
    }

    void changeNavBarSelection(String fragment) {
        switch (fragment) {
            case "home":
                imgIconHome.setImageResource(R.drawable.home_bottom_icon);
                imgIconSearch.setImageResource(R.drawable.search_grey_bottom_icon);
                imgIconPrize.setImageResource(R.drawable.notification_grey_bottom_icon);
                imgIconProfile.setImageResource(R.drawable.profile_grey_bottom_icon);
                break;
            case "search":
                imgIconHome.setImageResource(R.drawable.home_grey_bottom_icon);
                imgIconSearch.setImageResource(R.drawable.search_bottom_icon);
                imgIconPrize.setImageResource(R.drawable.notification_grey_bottom_icon);
                imgIconProfile.setImageResource(R.drawable.profile_grey_bottom_icon);
                break;
            case "notification":
                imgIconHome.setImageResource(R.drawable.home_grey_bottom_icon);
                imgIconSearch.setImageResource(R.drawable.search_grey_bottom_icon);
                imgIconPrize.setImageResource(R.drawable.notification_bottom_icon);
                imgIconProfile.setImageResource(R.drawable.profile_grey_bottom_icon);
                break;
            case "profile":
                imgIconHome.setImageResource(R.drawable.home_grey_bottom_icon);
                imgIconSearch.setImageResource(R.drawable.search_grey_bottom_icon);
                imgIconPrize.setImageResource(R.drawable.notification_grey_bottom_icon);
                imgIconProfile.setImageResource(R.drawable.profile_bottom_icon);
                break;
        }
    }


    void showFloatingButtons() {
        backGroundForFloating.setVisibility(View.VISIBLE);

        floatBtnHotSpots.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeIn)
                .duration(50)
                .repeat(0)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        floatBtnEvents.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.FadeIn)
                                .duration(50)
                                .repeat(0)
                                .onEnd(new YoYo.AnimatorCallback() {
                                    @Override
                                    public void call(Animator animator) {
                                        floatBtnAttractions.setVisibility(View.VISIBLE);
                                        YoYo.with(Techniques.FadeIn)
                                                .duration(50)
                                                .repeat(0)
                                                .onEnd(new YoYo.AnimatorCallback() {
                                                    @Override
                                                    public void call(Animator animator) {
                                                        floatBtnMap.setVisibility(View.VISIBLE);
                                                        YoYo.with(Techniques.FadeIn)
                                                                .duration(50)
                                                                .repeat(0)
                                                                .playOn(floatBtnMap);
                                                    }
                                                })
                                                .playOn(floatBtnAttractions);
                                    }
                                })
                                .playOn(floatBtnEvents);
                    }
                })
                .playOn(floatBtnHotSpots);
    }

    void hideFloatingButtons() {
        backGroundForFloating.setVisibility(View.GONE);

        YoYo.with(Techniques.FadeOut)
                .duration(50)
                .repeat(0)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        floatBtnMap.setVisibility(View.INVISIBLE);
                        YoYo.with(Techniques.FadeOut)
                                .duration(50)
                                .repeat(0)
                                .onEnd(new YoYo.AnimatorCallback() {
                                    @Override
                                    public void call(Animator animator) {
                                        floatBtnAttractions.setVisibility(View.INVISIBLE);
                                        YoYo.with(Techniques.FadeOut)
                                                .duration(50)
                                                .repeat(0)
                                                .onEnd(new YoYo.AnimatorCallback() {
                                                    @Override
                                                    public void call(Animator animator) {
                                                        floatBtnEvents.setVisibility(View.INVISIBLE);
                                                        YoYo.with(Techniques.FadeOut)
                                                                .duration(50)
                                                                .repeat(0)
                                                                .onEnd(new YoYo.AnimatorCallback() {
                                                                    @Override
                                                                    public void call(Animator animator) {
                                                                        floatBtnHotSpots.setVisibility(View.INVISIBLE);
                                                                    }
                                                                })
                                                                .playOn(floatBtnMap);
                                                    }
                                                })
                                                .playOn(floatBtnEvents);
                                    }
                                })
                                .playOn(floatBtnAttractions);
                    }
                })
                .playOn(floatBtnMap);

    }

}
