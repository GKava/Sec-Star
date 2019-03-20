package com.challenges.battleroyale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.ImageView;

import static com.challenges.battleroyale.MainActivity.APP_PREFERENCES;
import static com.challenges.battleroyale.MainActivity.IMAGE_COUNT_WEEK1;
import static com.challenges.battleroyale.MainActivity.IMAGE_COUNT_WEEK2;
import static com.challenges.battleroyale.MainActivity.IMAGE_COUNT_WEEK3;
import static com.challenges.battleroyale.MainActivity.IMAGE_COUNT_WEEK4;
import static com.challenges.battleroyale.MainActivity.IMAGE_COUNT_WEEK5;
import static com.challenges.battleroyale.MainActivity.IMAGE_COUNT_WEEK6;
import static com.challenges.battleroyale.MainActivity.IMAGE_COUNT_WEEK7;
import static com.challenges.battleroyale.MainActivity.IMAGE_COUNT_WEEK8;
import static com.challenges.battleroyale.MainActivity.IMAGE_COUNT_WEEK9;
import static com.challenges.battleroyale.MainActivity.IMAGE_COUNT_WEEK10;
import static com.challenges.battleroyale.MainActivity.SEASON_STORAGE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChallengesFragment extends Fragment implements View.OnClickListener{
    private int week_number;
    private ImageView rate,share,help;
    private String season_storage;
    private RecyclerView recyclerView;
    private AdapterChallenges adapter;
    private LinearLayoutManager verticalLinearLayoutManager;
    private SharedPreferences mSettings;
    private long imageCount;
    private long imageCountWeek1,imageCountWeek2,imageCountWeek3,imageCountWeek4,imageCountWeek5,imageCountWeek6,imageCountWeek7,imageCountWeek8,imageCountWeek9,imageCountWeek10;


    public ChallengesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenges, container, false);
        mSettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        share = view.findViewById(R.id.share);
        rate = view.findViewById(R.id.rate);
        help = view.findViewById(R.id.help);
        share.setOnClickListener(this);
        rate.setOnClickListener(this);
        help.setOnClickListener(this);

        Bundle bundle = getArguments();
        if (bundle != null) {
            week_number = bundle.getInt("week_number");
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

        if (mSettings.contains(SEASON_STORAGE)) {
            season_storage = mSettings.getString(SEASON_STORAGE, "");
        }


        // получаем  imageCount из CFG в зависимости от недели
        if (mSettings.contains(IMAGE_COUNT_WEEK1)) {
            imageCountWeek1 = mSettings.getLong(IMAGE_COUNT_WEEK1, 0);
        }
        if (mSettings.contains(IMAGE_COUNT_WEEK2)) {
            imageCountWeek2 = mSettings.getLong(IMAGE_COUNT_WEEK2, 0);
        }
        if (mSettings.contains(IMAGE_COUNT_WEEK3)) {
            imageCountWeek3 = mSettings.getLong(IMAGE_COUNT_WEEK3, 0);
        }
        if (mSettings.contains(IMAGE_COUNT_WEEK4)) {
            imageCountWeek4 = mSettings.getLong(IMAGE_COUNT_WEEK4, 0);
        }
        if (mSettings.contains(IMAGE_COUNT_WEEK5)) {
            imageCountWeek5 = mSettings.getLong(IMAGE_COUNT_WEEK5, 0);
        }
        if (mSettings.contains(IMAGE_COUNT_WEEK6)) {
            imageCountWeek6 = mSettings.getLong(IMAGE_COUNT_WEEK6, 0);
        }
        if (mSettings.contains(IMAGE_COUNT_WEEK7)) {
            imageCountWeek7 = mSettings.getLong(IMAGE_COUNT_WEEK7, 0);
        }
        if (mSettings.contains(IMAGE_COUNT_WEEK8)) {
            imageCountWeek8 = mSettings.getLong(IMAGE_COUNT_WEEK8, 0);
        }
        if (mSettings.contains(IMAGE_COUNT_WEEK9)) {
            imageCountWeek9 = mSettings.getLong(IMAGE_COUNT_WEEK9, 0);
        }
        if (mSettings.contains(IMAGE_COUNT_WEEK10)) {
            imageCountWeek10 = mSettings.getLong(IMAGE_COUNT_WEEK10, 0);
        }

        if (week_number==1){
            imageCount = imageCountWeek1;
        }else if (week_number==2){
            imageCount = imageCountWeek2;
        }else if (week_number==3){
            imageCount = imageCountWeek3;
        }else if (week_number==4){
            imageCount = imageCountWeek4;
        }else if (week_number==5){
            imageCount = imageCountWeek5;
        }else if (week_number==6){
            imageCount = imageCountWeek6;
        }else if (week_number==7){
            imageCount = imageCountWeek7;
        }else if (week_number==8){
            imageCount = imageCountWeek8;
        }else if (week_number==9){
            imageCount = imageCountWeek9;
        }else if (week_number==10){
            imageCount = imageCountWeek10;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String weekNum = String.valueOf(week_number);

                if (imageCount==1) {
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_1", season_storage));
                }
                if (imageCount==2) {
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_1", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_2", season_storage));
                }
                if (imageCount==3) {
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_1", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_2", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_3", season_storage));
                }
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
                if (imageCount==8) {
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_1", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_2", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_3", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_4", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_5", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_7", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_8", season_storage));
                }
                if (imageCount==9) {
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_1", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_2", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_3", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_4", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_5", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_7", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_8", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_9", season_storage));
                }
                if (imageCount==10) {
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_1", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_2", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_3", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_4", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_5", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_7", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_8", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_9", season_storage));
                    adapter.addMessage(new ItemChallenges("week"+weekNum+"_10", season_storage));
                }
            }
        },0);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.help:
                Fragment fr = new HelpFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fr)
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.rate:
                createRateDialog("Rate it","Like the app?\nYou can help us and evaluate the app");
                break;
            case R.id.share:
                int applicationNameId = getContext().getApplicationInfo().labelRes;
                final String appPackageName = getContext().getPackageName();
                String text = getString(R.string.install_this_application, appPackageName);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, getContext().getString(applicationNameId));
                sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,"Share link:"));
                break;
        }
    }

    private void createRateDialog(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setNegativeButton("Close ✖",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                    }
                });
        builder.setPositiveButton("Rate it ★",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Uri address = Uri.parse("https://play.google.com/store/apps/details?id=battleroyale.challenges.com.secretstars");
                        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
                        startActivity(openlinkIntent);
                    }
                });
        builder.show();
    }

}
