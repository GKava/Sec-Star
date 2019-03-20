package com.challenges.battleroyale;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.codemybrainsout.ratingdialog.RatingDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String WEEK1= "week1";
    public static final String WEEK2= "week2";
    public static final String WEEK3= "week3";
    public static final String WEEK4= "week4";
    public static final String WEEK5= "week5";
    public static final String WEEK6= "week6";
    public static final String WEEK7= "week7";
    public static final String WEEK8= "week8";
    public static final String WEEK9= "week9";
    public static final String WEEK10= "week10";

    public static final String WEEK1TXT= "week1txt";
    public static final String WEEK2TXT= "week2txt";
    public static final String WEEK3TXT= "week3txt";
    public static final String WEEK4TXT= "week4txt";
    public static final String WEEK5TXT= "week5txt";
    public static final String WEEK6TXT= "week6txt";
    public static final String WEEK7TXT= "week7txt";
    public static final String WEEK8TXT= "week8txt";
    public static final String WEEK9TXT= "week9txt";
    public static final String WEEK10TXT= "week10txt";

    public static final String IMAGE_COUNT_WEEK1= "image_count_week1";
    public static final String IMAGE_COUNT_WEEK2= "image_count_week2";
    public static final String IMAGE_COUNT_WEEK3= "image_count_week3";
    public static final String IMAGE_COUNT_WEEK4= "image_count_week4";
    public static final String IMAGE_COUNT_WEEK5= "image_count_week5";
    public static final String IMAGE_COUNT_WEEK6= "image_count_week6";
    public static final String IMAGE_COUNT_WEEK7= "image_count_week7";
    public static final String IMAGE_COUNT_WEEK8= "image_count_week8";
    public static final String IMAGE_COUNT_WEEK9= "image_count_week9";
    public static final String IMAGE_COUNT_WEEK10= "image_count_week0";

    public static final String SEASON_NAME= "season_name";
    public static final String SEASON_STORAGE= "storage_path";

    FirebaseRemoteConfig mFirebaseRemoteConfig;
    InterstitialAd mInterstitialAd;
    FragmentManager fragmentManager;
    AdView mAdView;
    Handler handler = new Handler();
    private Runnable dialogshow = new Runnable() {
        @Override
        public void run() {
            rating();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FirebaseApp.initializeApp(this);
        mSettings = this.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        mFirebaseRemoteConfig.setDefaults(R.xml.rem_cfg);
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().
                setDeveloperModeEnabled(BuildConfig.DEBUG).build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/4411468910");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        fragmentManager = getSupportFragmentManager();
        if (findViewById(R.id.fragment_container)!=null){
            if (savedInstanceState!=null){
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new SplashFragment()).commit();
        }
    }

    public void rating (){

        final RatingDialog ratingDialog = new RatingDialog.Builder(MainActivity.this)
                .session(2)
                .threshold(5)
                .onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener() {
                    @Override
                    public void onFormSubmitted(String feedback) {
                        String mailto = "mailto:vercong1@gmail.com" +
                                "?subject=" + Uri.encode(getString(R.string.feedback)) +
                                "&body=" + Uri.encode(feedback);

                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                        emailIntent.setData(Uri.parse(mailto));
                        try {
                            startActivity(emailIntent);
                        } catch (ActivityNotFoundException ex) {
                            Toast.makeText(MainActivity.this, ":(", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).build();

        ratingDialog.show();
    }

    public  void showAd(@NonNull final Runnable runnable) {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            SharedPreferences preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            int cnt = preferences.getInt(getString(R.string.show_ad_counter_settings_key), 0);
            if (cnt >= 5) {
                preferences.edit().putInt(getString(R.string.show_ad_counter_settings_key), 0).apply();

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
                            mInterstitialAd.loadAd(new AdRequest.Builder().build());
                        }
                        runnable.run();
                    }

                });
                mInterstitialAd.show();
            } else {
                preferences.edit().putInt(getString(R.string.show_ad_counter_settings_key), cnt + 1).apply();
                runnable.run();
            }

        } else {
            runnable.run();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdView.resume();
        handler.postDelayed(dialogshow, 20000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdView.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdView.pause();
        handler.removeCallbacks(dialogshow);
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseRemoteConfig.fetch(0).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    mFirebaseRemoteConfig.activateFetched();

                    boolean week1,week2,week3,week4,week5,week6,week7,week8,week9,week10;
                    String week1txt,week2txt,week3txt,week4txt,week5txt,week6txt,week7txt,week8txt,week9txt,week10txt,seasonStorage,season_name;
                    long imageCountWeek1,imageCountWeek2,imageCountWeek3,imageCountWeek4,imageCountWeek5,imageCountWeek6,imageCountWeek7,imageCountWeek8,imageCountWeek9,imageCountWeek10;

                    week1 = mFirebaseRemoteConfig.getBoolean("week1");
                    week2 = mFirebaseRemoteConfig.getBoolean("week2");
                    week3 = mFirebaseRemoteConfig.getBoolean("week3");
                    week4 = mFirebaseRemoteConfig.getBoolean("week4");
                    week5 = mFirebaseRemoteConfig.getBoolean("week5");
                    week6 = mFirebaseRemoteConfig.getBoolean("week6");
                    week7 = mFirebaseRemoteConfig.getBoolean("week7");
                    week8 = mFirebaseRemoteConfig.getBoolean("week8");
                    week9 = mFirebaseRemoteConfig.getBoolean("week9");
                    week10 = mFirebaseRemoteConfig.getBoolean("week10");

                    week1txt = mFirebaseRemoteConfig.getString("week1txt");
                    week2txt = mFirebaseRemoteConfig.getString("week2txt");
                    week3txt = mFirebaseRemoteConfig.getString("week3txt");
                    week4txt = mFirebaseRemoteConfig.getString("week4txt");
                    week5txt = mFirebaseRemoteConfig.getString("week5txt");
                    week6txt = mFirebaseRemoteConfig.getString("week6txt");
                    week7txt = mFirebaseRemoteConfig.getString("week7txt");
                    week8txt = mFirebaseRemoteConfig.getString("week8txt");
                    week9txt = mFirebaseRemoteConfig.getString("week9txt");
                    week10txt = mFirebaseRemoteConfig.getString("week10txt");

                    imageCountWeek1 =  mFirebaseRemoteConfig.getLong("image_count_week1");
                    imageCountWeek2 =  mFirebaseRemoteConfig.getLong("image_count_week2");
                    imageCountWeek3 =  mFirebaseRemoteConfig.getLong("image_count_week3");
                    imageCountWeek4 =  mFirebaseRemoteConfig.getLong("image_count_week4");
                    imageCountWeek5 =  mFirebaseRemoteConfig.getLong("image_count_week5");
                    imageCountWeek6 =  mFirebaseRemoteConfig.getLong("image_count_week6");
                    imageCountWeek7 =  mFirebaseRemoteConfig.getLong("image_count_week7");
                    imageCountWeek8 =  mFirebaseRemoteConfig.getLong("image_count_week8");
                    imageCountWeek9 =  mFirebaseRemoteConfig.getLong("image_count_week9");
                    imageCountWeek10 =  mFirebaseRemoteConfig.getLong("image_count_week10");

                    seasonStorage = mFirebaseRemoteConfig.getString("storage_path");

                    season_name = mFirebaseRemoteConfig.getString("season_name");

                    SharedPreferences.Editor editor = mSettings.edit();

                    editor.putBoolean(WEEK1, week1);
                    editor.putBoolean(WEEK2, week2);
                    editor.putBoolean(WEEK3, week3);
                    editor.putBoolean(WEEK4, week4);
                    editor.putBoolean(WEEK5, week5);
                    editor.putBoolean(WEEK6, week6);
                    editor.putBoolean(WEEK7, week7);
                    editor.putBoolean(WEEK8, week8);
                    editor.putBoolean(WEEK9, week9);
                    editor.putBoolean(WEEK10, week10);

                    editor.putString(WEEK1TXT, week1txt);
                    editor.putString(WEEK2TXT, week2txt);
                    editor.putString(WEEK3TXT, week3txt);
                    editor.putString(WEEK4TXT, week4txt);
                    editor.putString(WEEK5TXT, week5txt);
                    editor.putString(WEEK6TXT, week6txt);
                    editor.putString(WEEK7TXT, week7txt);
                    editor.putString(WEEK8TXT, week8txt);
                    editor.putString(WEEK9TXT, week9txt);
                    editor.putString(WEEK10TXT, week10txt);

                    editor.putLong(IMAGE_COUNT_WEEK1, imageCountWeek1);
                    editor.putLong(IMAGE_COUNT_WEEK2, imageCountWeek2);
                    editor.putLong(IMAGE_COUNT_WEEK3, imageCountWeek3);
                    editor.putLong(IMAGE_COUNT_WEEK4, imageCountWeek4);
                    editor.putLong(IMAGE_COUNT_WEEK5, imageCountWeek5);
                    editor.putLong(IMAGE_COUNT_WEEK6, imageCountWeek6);
                    editor.putLong(IMAGE_COUNT_WEEK7, imageCountWeek7);
                    editor.putLong(IMAGE_COUNT_WEEK8, imageCountWeek8);
                    editor.putLong(IMAGE_COUNT_WEEK9, imageCountWeek9);
                    editor.putLong(IMAGE_COUNT_WEEK10, imageCountWeek10);

                    editor.putString(SEASON_STORAGE, seasonStorage);
                    editor.putString(SEASON_NAME, season_name);

                    editor.apply();
                }
            }
        });
        mFirebaseRemoteConfig.fetch().addOnCanceledListener(this, new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(MainActivity.this,getString(R.string.internet_error),LENGTH_SHORT).show();
            }
        });
        mFirebaseRemoteConfig.fetch().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

               openMainMenu();
               // запускать в хендлере?
                // Здесь можно обработать переход когда произойдет подгрузка из конфига
                 Toast.makeText(MainActivity.this,"The application has been successfully updated!",LENGTH_SHORT).show();
            }
        });
    }

    public void openMainMenu(){
        if (hasConnection(MainActivity.this)==true) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Fragment fr = new MainFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fr)
//                      .addToBackStack(null)
                            .commit();
                }
                },0);

        }else {
            Toast.makeText(MainActivity.this,getString(R.string.internet_error),LENGTH_SHORT).show();
            onStart();
        }

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
}
