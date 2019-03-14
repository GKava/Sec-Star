package com.challenges.battleroyale;


import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChallengesFragment extends Fragment {
    private int week_number;
    TextView tv;
    ImageView image1,image2,image3,image4,image5;
    ProgressBar spin_kit;
    public ChallengesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenges, container, false);
        tv = view.findViewById(R.id.tv);
        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        image3 = view.findViewById(R.id.image3);
        image4 = view.findViewById(R.id.image4);
        image5 = view.findViewById(R.id.image5);
        spin_kit = view.findViewById(R.id.spin_kit);

        Bundle bundle = getArguments();
        if (bundle != null) {
            week_number = bundle.getInt("week_number");
        }
        tv.setText("Week"+ week_number);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        //loadImgRes(week_number);

        double widthDouble = size.x;
        int widthInt = (int) widthDouble;


        image1.getLayoutParams().width = widthInt;
        image1.getLayoutParams().height = widthInt;
        image1.requestLayout();
        image2.getLayoutParams().width = widthInt;
        image2.getLayoutParams().height = widthInt;
        image2.requestLayout();
        image3.getLayoutParams().width = widthInt;
        image3.getLayoutParams().height = widthInt;
        image3.requestLayout();
        image4.getLayoutParams().width = widthInt;
        image4.getLayoutParams().height = widthInt;
        image4.requestLayout();
        image5.getLayoutParams().width = widthInt;
        image5.getLayoutParams().height = widthInt;
        image5.requestLayout();
        return view;



    }
    public void loadImgRes(int paramBundle) {
        FirebaseStorage storage = FirebaseStorage.getInstance("gs://fortnitestarschallenge.appspot.com");

        //сброс кеша при старте нового сезона
        if (paramBundle == 1) {
            loadImgRes(storage, "week1_1.jpg", image1);
            loadImgRes(storage, "week1_2.jpg", image2);
            loadImgRes(storage, "week1_3.jpg", image3);
            loadImgRes(storage, "week1_4.jpg", image4);
            loadImgRes(storage, "week1_5.jpg", image5);
        }
        if (paramBundle == 2) {
            loadImgRes(storage, "week2_1.jpg", image1);
            loadImgRes(storage, "week2_2.jpg", image2);
            loadImgRes(storage, "week2_3.jpg", image3);
            loadImgRes(storage, "week2_4.jpg", image4);
            loadImgRes(storage, "week2_5.jpg", image5);
        }
        if (paramBundle == 3) {
            loadImgRes(storage, "week3_1.jpg", image1);
            loadImgRes(storage, "week3_2.jpg", image2);
            loadImgRes(storage, "week3_3.jpg", image3);
            loadImgRes(storage, "week3_4.jpg", image4);
            loadImgRes(storage, "week3_5.jpg", image5);
        }
        if (paramBundle == 4) {
            loadImgRes(storage, "week4_1.jpg", image1);
            loadImgRes(storage, "week4_2.jpg", image2);
            loadImgRes(storage, "week4_3.jpg", image3);
            loadImgRes(storage, "week4_4.jpg", image4);
            loadImgRes(storage, "week4_5.jpg", image5);
        }
        if (paramBundle == 5) {
            loadImgRes(storage, "week5_1.jpg", image1);
            loadImgRes(storage, "week5_2.jpg", image2);
            loadImgRes(storage, "week5_3.jpg", image3);
            loadImgRes(storage, "week5_4.jpg", image4);
            loadImgRes(storage, "week5_5.jpg", image5);
        }
        if (paramBundle == 6) {
            loadImgRes(storage, "week6_1.jpg", image1);
            loadImgRes(storage, "week6_2.jpg", image2);
            loadImgRes(storage, "week6_3.jpg", image3);
            loadImgRes(storage, "week6_4.jpg", image4);
            loadImgRes(storage, "week6_5.jpg", image5);
        }
        if (paramBundle == 7) {
            loadImgRes(storage, "week7_1.jpg", image1);
            loadImgRes(storage, "week7_2.jpg", image2);
            loadImgRes(storage, "week7_3.jpg", image3);
            loadImgRes(storage, "week7_4.jpg", image4);
            loadImgRes(storage, "week7_5.jpg", image5);
        }
        if (paramBundle == 8) {
            loadImgRes(storage, "week8_1.jpg", image1);
            loadImgRes(storage, "week8_2.jpg", image2);
            loadImgRes(storage, "week8_3.jpg", image3);
            loadImgRes(storage, "week8_4.jpg", image4);
            loadImgRes(storage, "week8_5.jpg", image5);
        }
        if (paramBundle == 9) {
            loadImgRes(storage, "week9_1.jpg", image1);
            loadImgRes(storage, "week9_2.jpg", image2);
            loadImgRes(storage, "week9_3.jpg", image3);
            loadImgRes(storage, "week9_4.jpg", image4);
            loadImgRes(storage, "week9_5.jpg", image5);
        }
        if (paramBundle == 10) {
            loadImgRes(storage, "week10_1.jpg", image1);
            loadImgRes(storage, "week10_2.jpg", image2);
            loadImgRes(storage, "week10_3.jpg", image3);
            loadImgRes(storage, "week10_4.jpg", image4);
            loadImgRes(storage, "week10_5.jpg", image5);
        }
    }


    public void loadImgRes(FirebaseStorage storage, String imageName, final ImageView targetView) {
        StorageReference storageRef = storage.getReference().child(imageName);

        Glide.with(this /* context */)
                .load(storageRef)
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        spin_kit.setVisibility(ProgressBar.INVISIBLE);
//                        weekNumberTxt.setVisibility(View.VISIBLE);



                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                          spin_kit.setVisibility(ProgressBar.INVISIBLE);
//                        weekNumberTxt.setVisibility(View.VISIBLE);

                        return false;
                    }
                })
                .into(targetView);
    }

}
