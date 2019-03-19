package com.challenges.battleroyale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFragment extends Fragment {
    private LinearLayoutManager linearLayoutManager;
    private AdapterHelpMenu adapter;
    private RecyclerView recyclerView;


    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new AdapterHelpMenu();
        recyclerView.setAdapter(adapter);
        adapter.addAsk(new ItemHelp("I can't find secret stars? Why?", ""));
        adapter.addAsk(new ItemHelp("Ask", "otver"));
        adapter.addAsk(new ItemHelp("Askfffffffffffffffffffffffffffffffffffffffffffffff", "otvdddddddddddddddddddddddddddddder"));
        adapter.addAsk(new ItemHelp("Ask", "otver"));
        adapter.addAsk(new ItemHelp("Ask", "otvfffffffffffffffffffffffffffer"));





        return view;
    }

}
