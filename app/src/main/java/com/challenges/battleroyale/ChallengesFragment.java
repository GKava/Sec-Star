package com.challenges.battleroyale;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.challenges.battleroyale.MainActivity.APP_PREFERENCES;
import static com.challenges.battleroyale.MainActivity.IMAGE_COUNT_WEEK1;
import static com.challenges.battleroyale.MainActivity.IMAGE_COUNT_WEEK2;
import static com.challenges.battleroyale.MainActivity.IMAGE_COUNT_WEEK3;
import static com.challenges.battleroyale.MainActivity.SEASON_STORAGE;
import static com.challenges.battleroyale.MainActivity.WEEK1;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChallengesFragment extends Fragment {
    private int week_number;
    private String season_storage;
    private String seasonStorage;
    private RecyclerView recyclerView;
    private AdapterChallenges adapter;
    private LinearLayoutManager verticalLinearLayoutManager;
    private SharedPreferences mSettings;
    private long imageCount;
    private long imageCountWeek1,imageCountWeek2,imageCountWeek3;


    public ChallengesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenges, container, false);
        mSettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        Bundle bundle = getArguments();
        if (bundle != null) {
            week_number = bundle.getInt("week_number");
        //    seasonStorage = bundle.getString("season_storage");
        }

        recyclerView = view.findViewById(R.id.recycler);
        verticalLinearLayoutManager = new GridLayoutManager(getActivity(), 1);
        verticalLinearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        adapter = new AdapterChallenges(width);
        recyclerView.setAdapter(adapter);


        // получаем  imageCount из CFG в зависимости от недели

        if (week_number==1){
            imageCount = imageCountWeek1;
        }else if (week_number==2){
            imageCount = imageCountWeek2;
        }else if (week_number==3){
            imageCount = imageCountWeek3;
        }
        //сделать 10 недель во всех класса с COUNT

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String weekNum = String.valueOf(week_number);

//        if (imageCount==3) {
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_1", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_2", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_3", season_storage));
//        }
        if (imageCount==4) {
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_1", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_2", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_3", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_4", season_storage));
        }
        if (imageCount==5) {
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_1", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_2", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_3", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_4", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_5", season_storage));
        }
        if (imageCount==6) {
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_1", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_2", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_3", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_4", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_5", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_6", season_storage));
        }
        if (imageCount==7) {
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_1", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_2", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_3", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_4", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_5", season_storage));
            adapter.addMessage(new ItemChallenges("week"+weekNum+"_7", season_storage));
        }
            }
        },0);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mSettings.contains(WEEK1)) {
            imageCountWeek1 = mSettings.getLong(IMAGE_COUNT_WEEK1, 0);
        }
        if (mSettings.contains(WEEK1)) {
            imageCountWeek2 = mSettings.getLong(IMAGE_COUNT_WEEK2, 0);
        }
        if (mSettings.contains(WEEK1)) {
            imageCountWeek3 = mSettings.getLong(IMAGE_COUNT_WEEK3, 0);
        }
        if (mSettings.contains(SEASON_STORAGE)) {
            season_storage = mSettings.getString(SEASON_STORAGE, "");
        }
    }
}
