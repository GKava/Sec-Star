package com.challenges.battleroyale;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChallengesFragment extends Fragment {
    private int week_number;
    private int imageCount;
    private String season_storage;
    String seasonNumberConfig = "season8";
    private RecyclerView recyclerView;
    private AdapterChallenges adapter;
    private LinearLayoutManager verticalLinearLayoutManager;



    public ChallengesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenges, container, false);


        Bundle bundle = getArguments();
        if (bundle != null) {
            week_number = bundle.getInt("week_number");
        }

        recyclerView = view.findViewById(R.id.recycler);
        verticalLinearLayoutManager = new GridLayoutManager(getActivity(), 2);
        verticalLinearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(verticalLinearLayoutManager);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x/2;

        adapter = new AdapterChallenges(width);
        recyclerView.setAdapter(adapter);
        adapter.addMessage(new Item( week_number, imageCount, season_storage));



        return view;



    }

//    public void loadImgRes(int paramBundle) {
//        FirebaseStorage storage = FirebaseStorage.getInstance("gs://fortnitestarschallenge.appspot.com");
//
//        //сброс кеша при старте нового сезона
//        if (paramBundle == 1) {
//            loadImgRes(storage, "week1_1.jpg", image1);
//            loadImgRes(storage, "week1_2.jpg", image2);
//            loadImgRes(storage, "week1_3.jpg", image3);
//            loadImgRes(storage, "week1_4.jpg", image4);
//            loadImgRes(storage, "week1_5.jpg", image5);
//        }
//        if (paramBundle == 2) {
//            loadImgRes(storage, "week2_1.jpg", image1);
//            loadImgRes(storage, "week2_2.jpg", image2);
//            loadImgRes(storage, "week2_3.jpg", image3);
//            loadImgRes(storage, "week2_4.jpg", image4);
//            loadImgRes(storage, "week2_5.jpg", image5);
//        }
//        if (paramBundle == 3) {
//            loadImgRes(storage, "week3_1.jpg", image1);
//            loadImgRes(storage, "week3_2.jpg", image2);
//            loadImgRes(storage, "week3_3.jpg", image3);
//            loadImgRes(storage, "week3_4.jpg", image4);
//            loadImgRes(storage, "week3_5.jpg", image5);
//        }
//        if (paramBundle == 4) {
//            loadImgRes(storage, "week4_1.jpg", image1);
//            loadImgRes(storage, "week4_2.jpg", image2);
//            loadImgRes(storage, "week4_3.jpg", image3);
//            loadImgRes(storage, "week4_4.jpg", image4);
//            loadImgRes(storage, "week4_5.jpg", image5);
//        }
//        if (paramBundle == 5) {
//            loadImgRes(storage, "week5_1.jpg", image1);
//            loadImgRes(storage, "week5_2.jpg", image2);
//            loadImgRes(storage, "week5_3.jpg", image3);
//            loadImgRes(storage, "week5_4.jpg", image4);
//            loadImgRes(storage, "week5_5.jpg", image5);
//        }
//        if (paramBundle == 6) {
//            loadImgRes(storage, "week6_1.jpg", image1);
//            loadImgRes(storage, "week6_2.jpg", image2);
//            loadImgRes(storage, "week6_3.jpg", image3);
//            loadImgRes(storage, "week6_4.jpg", image4);
//            loadImgRes(storage, "week6_5.jpg", image5);
//        }
//        if (paramBundle == 7) {
//            loadImgRes(storage, "week7_1.jpg", image1);
//            loadImgRes(storage, "week7_2.jpg", image2);
//            loadImgRes(storage, "week7_3.jpg", image3);
//            loadImgRes(storage, "week7_4.jpg", image4);
//            loadImgRes(storage, "week7_5.jpg", image5);
//        }
//        if (paramBundle == 8) {
//            loadImgRes(storage, "week8_1.jpg", image1);
//            loadImgRes(storage, "week8_2.jpg", image2);
//            loadImgRes(storage, "week8_3.jpg", image3);
//            loadImgRes(storage, "week8_4.jpg", image4);
//            loadImgRes(storage, "week8_5.jpg", image5);
//        }
//        if (paramBundle == 9) {
//            loadImgRes(storage, "week9_1.jpg", image1);
//            loadImgRes(storage, "week9_2.jpg", image2);
//            loadImgRes(storage, "week9_3.jpg", image3);
//            loadImgRes(storage, "week9_4.jpg", image4);
//            loadImgRes(storage, "week9_5.jpg", image5);
//        }
//        if (paramBundle == 10) {
//            loadImgRes(storage, "week10_1.jpg", image1);
//            loadImgRes(storage, "week10_2.jpg", image2);
//            loadImgRes(storage, "week10_3.jpg", image3);
//            loadImgRes(storage, "week10_4.jpg", image4);
//            loadImgRes(storage, "week10_5.jpg", image5);
//        }
//    }
//
//
//    public void loadImgRes(FirebaseStorage storage, String imageName, final ImageView targetView) {
//        StorageReference storageRef = storage.getReference().child(seasonNumberConfig+imageName);
//
//        Glide.with(this /* context */)
//                .load(storageRef)
//                .addListener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//
//
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//
//
//                        return false;
//                    }
//                })
//                .into(targetView);
//    }

}
