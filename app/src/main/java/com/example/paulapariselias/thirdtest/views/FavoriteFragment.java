package com.example.paulapariselias.thirdtest.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paulapariselias.thirdtest.R;
import com.example.paulapariselias.thirdtest.adapters.PokemonsAdapter;
import com.example.paulapariselias.thirdtest.data.Nodes;


public class FavoriteFragment extends Fragment {


    private PokemonsAdapter adapter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newIntance(){
        return new FavoriteFragment();
    }


    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragmentRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new PokemonsAdapter(new Nodes().favorites(), getContext());
        recyclerView.setAdapter(adapter);
    }
}


