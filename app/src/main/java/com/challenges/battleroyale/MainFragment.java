package com.challenges.battleroyale;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;
import static com.challenges.battleroyale.MainActivity.APP_PREFERENCES;
import static com.challenges.battleroyale.MainActivity.SEASON_STORAGE;
import static com.challenges.battleroyale.MainActivity.WEEK1;
import static com.challenges.battleroyale.MainActivity.WEEK10;
import static com.challenges.battleroyale.MainActivity.WEEK10TXT;
import static com.challenges.battleroyale.MainActivity.WEEK1TXT;
import static com.challenges.battleroyale.MainActivity.WEEK2;
import static com.challenges.battleroyale.MainActivity.WEEK2TXT;
import static com.challenges.battleroyale.MainActivity.WEEK3;
import static com.challenges.battleroyale.MainActivity.WEEK3TXT;
import static com.challenges.battleroyale.MainActivity.WEEK4;
import static com.challenges.battleroyale.MainActivity.WEEK4TXT;
import static com.challenges.battleroyale.MainActivity.WEEK5;
import static com.challenges.battleroyale.MainActivity.WEEK5TXT;
import static com.challenges.battleroyale.MainActivity.WEEK6;
import static com.challenges.battleroyale.MainActivity.WEEK6TXT;
import static com.challenges.battleroyale.MainActivity.WEEK7;
import static com.challenges.battleroyale.MainActivity.WEEK7TXT;
import static com.challenges.battleroyale.MainActivity.WEEK8;
import static com.challenges.battleroyale.MainActivity.WEEK8TXT;
import static com.challenges.battleroyale.MainActivity.WEEK9;
import static com.challenges.battleroyale.MainActivity.WEEK9TXT;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment  implements AdapterMenu.OnImageClickListener {
    private RecyclerView recyclerView;
    private LinearLayoutManager verticalLinearLayoutManager;
    private AdapterMenu adapter;
    private SharedPreferences mSettings;
    private boolean week1,week2,week3,week4,week5,week6,week7,week8,week9,week10 ;
    private String week1txt,week2txt,week3txt,week4txt,week5txt,week6txt,week7txt,week8txt,week9txt,week10txt,seasonStorage;
    public MainFragment() {// Required empty public constructor
    }

    @Override
    public void onImageClick(int position) {
      //  viewAds();

        if (hasConnection(getActivity()) == true ) {
        Fragment fr = new ChallengesFragment();
        Bundle args = new Bundle();
        args.putInt("week_number", position+1);
        args.putString("season_storage", seasonStorage);
        args.putString("season_storage", seasonStorage);
        fr.setArguments(args);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fr)
                .addToBackStack(null)
                .commit();
        } else {
            Toast.makeText(getContext(),"Internet connection error :(",LENGTH_SHORT).show();
        }


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mSettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        recyclerView = view.findViewById(R.id.recycler);
        verticalLinearLayoutManager = new GridLayoutManager(getActivity(), 2);
        verticalLinearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/2;

        adapter = new AdapterMenu(width);
        recyclerView.setAdapter(adapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

        adapter.addMessage(new Item( week1txt, week1));
        adapter.addMessage(new Item( week2txt, week2));
        adapter.addMessage(new Item( week3txt, week3));
        adapter.addMessage(new Item( week4txt, week4));
        adapter.addMessage(new Item( week5txt, week5));
        adapter.addMessage(new Item( week6txt, week6));
        adapter.addMessage(new Item( week7txt, week7));
        adapter.addMessage(new Item( week8txt, week8));
        adapter.addMessage(new Item( week9txt, week9));
        adapter.addMessage(new Item( week10txt, week10));
            }
        },0);

        return view;
    }

    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }

    @Override
    public void onPause() {
        if (adapter != null) {
            adapter.setListener(null);
        }
        super.onPause();
    }



    @Override
    public void onStart() {
        super.onStart();

        if (mSettings.contains(WEEK1)) {
            week1 = mSettings.getBoolean(WEEK1, false);
        }
        if (mSettings.contains(WEEK2)) {
            week2 = mSettings.getBoolean(WEEK2, false);
        }
        if (mSettings.contains(WEEK3)) {
            week3 = mSettings.getBoolean(WEEK3, false);
        }
        if (mSettings.contains(WEEK4)) {
            week4 = mSettings.getBoolean(WEEK4, false);
        }
        if (mSettings.contains(WEEK5)) {
            week5 = mSettings.getBoolean(WEEK5, false);
        }
        if (mSettings.contains(WEEK6)) {
            week6 = mSettings.getBoolean(WEEK6, false);
        }
        if (mSettings.contains(WEEK7)) {
            week7 = mSettings.getBoolean(WEEK7, false);
        }
        if (mSettings.contains(WEEK8)) {
            week8 = mSettings.getBoolean(WEEK8, false);
        }
        if (mSettings.contains(WEEK9)) {
            week9 = mSettings.getBoolean(WEEK9, false);
        }
        if (mSettings.contains(WEEK10)) {
            week10 = mSettings.getBoolean(WEEK10, false);
        }

        if (mSettings.contains(WEEK1TXT)) {
            week1txt = mSettings.getString(WEEK1TXT, "");
        }
        if (mSettings.contains(WEEK2TXT)) {
            week2txt = mSettings.getString(WEEK2TXT, "");
        }
        if (mSettings.contains(WEEK3TXT)) {
            week3txt = mSettings.getString(WEEK3TXT, "");
        }
        if (mSettings.contains(WEEK4TXT)) {
            week4txt = mSettings.getString(WEEK4TXT, "");
        }
        if (mSettings.contains(WEEK5TXT)) {
            week5txt = mSettings.getString(WEEK5TXT, "");
        }
        if (mSettings.contains(WEEK6TXT)) {
            week6txt = mSettings.getString(WEEK6TXT, "");
        }
        if (mSettings.contains(WEEK7TXT)) {
            week7txt = mSettings.getString(WEEK7TXT, "");
        }
        if (mSettings.contains(WEEK8TXT)) {
            week8txt = mSettings.getString(WEEK8TXT, "");
        }
        if (mSettings.contains(WEEK9TXT)) {
            week9txt = mSettings.getString(WEEK9TXT, "");
        }
        if (mSettings.contains(WEEK10TXT)) {
            week10txt = mSettings.getString(WEEK10TXT, "");
        }
        if (mSettings.contains(WEEK10TXT)) {
            week10txt = mSettings.getString(WEEK10TXT, "");
        }


        if (mSettings.contains(SEASON_STORAGE)) {
            seasonStorage = mSettings.getString(SEASON_STORAGE, "");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.setListener(this);
        }
    }
}