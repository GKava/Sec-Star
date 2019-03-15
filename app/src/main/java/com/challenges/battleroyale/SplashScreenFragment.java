package com.challenges.battleroyale;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashScreenFragment extends Fragment {


    Handler handler = new Handler();
    private TextView loading;
    String [] whatLoadString = {"We are looking for a star","Leroooooy","Deals 9 damage with pump Shotgun"};
    Random random = new Random();
    int rand = Integer.valueOf(random.nextInt(whatLoadString.length));
    String loadTxt = whatLoadString[rand];
    String nullLoadTxt = loadTxt;


    public SplashScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        loading = view.findViewById(R.id.loading);
        loading.setText(String.valueOf(loadTxt));

        startWork();
        return view;
    }



    public void startWork(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (hasConnection(getActivity())==true) {
                    Fragment fr = new MainFragment();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, fr)
                            .commit();
                }else {
                    startWork();
                    loadingAnimate(); // поменять текст, передаётся одно и тоже
                }

            }
//        },2500);
        },500);
    }

    public void loadingAnimate(){

        if (loadTxt.equals("      " + loadTxt + " . . .")){
            loading.setText(loadTxt);
            Toast toast = Toast.makeText(getActivity(), "Internet connection error :(", Toast.LENGTH_SHORT);toast.show();
            loadTxt= nullLoadTxt;
            return;
        }
        if (loadTxt.equals("    " + loadTxt + " . .")){
            loading.setText(loadTxt);
            loadTxt = "      " + loadTxt + " . . .";
            return;
        }

        if (loadTxt.equals("  " + loadTxt + " .")){
            loading.setText(loadTxt);
            loadTxt = "    " + loadTxt + " . .";
            return;
        }

        if (loadTxt.equals(loadTxt)){
            loading.setText(loadTxt);
            loadTxt = "  " + loadTxt + " .";
            return;
        }


       //бесконечные точки  потому что в параметр передается одно и тоже
//        if (loadTxt.equals(whatLoad)){
//            loading.setText(loadTxt);
//            loadTxt = "  "+whatLoad+" .";
//            return;
//        }
//        if (loadTxt.equals("    "+whatLoad+" . .")){
//            Toast toast = Toast.makeText(getActivity(), "Internet connection error :(", Toast.LENGTH_SHORT);toast.show();
//            loadTxt = "      "+whatLoad+" . . .";
//            loading.setText(loadTxt);
//            // loadTxt = whatLoad;
//            return;
//        }
//
//        if (loadTxt.equals("  "+whatLoad+" .")){
//            loadTxt = "    "+whatLoad+" . .";
//            return;
//        }
//
//
//        if (loadTxt.equals("      "+whatLoad+" . . .")){
//            loadTxt = whatLoad;
//            loading.setText(loadTxt);
//            return;
//        }
    }

//анимация со смещением текста влево
//    public void loadingAnimate(){
//        loading.setText(loadTxt);
//        loading.append(" .");
//        loadTxt = loading.getText().toString();
//
//
//
//        if (loading.getText().toString().equals("Loading . . .")){
//            Toast toast = Toast.makeText(getActivity(), "Internet connection error :(", Toast.LENGTH_SHORT);toast.show();
//            loadTxt="Loading";
//        }
//    }

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

//    @Override
//    public void onPause() {
//        super.onPause();
//        handler.removeCallbacks(null);
//
//    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        startWork();
//    }
}
