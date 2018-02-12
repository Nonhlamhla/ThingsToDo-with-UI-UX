package com.example.admin.thingstodo.BottomNavigation;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.admin.thingstodo.Music.MusicActivity;
import com.example.admin.thingstodo.Music.MusicAdmin;
import com.example.admin.thingstodo.UserAuth.Profile;
import com.example.admin.thingstodo.R;
import com.example.admin.thingstodo.Wine.WineActivity;
import com.example.admin.thingstodo.Wine.WineAdmin;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View view;
    ImageButton layout_wine, layout_music, layout_outdoor, layout_stage, layout_food, layout_indoor, layout_fitness, layout_travel;





    public HomeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);


        layout_wine = view.findViewById(R.id.layout_wine);
        layout_music = view.findViewById(R.id.layout_music);
        layout_outdoor = view.findViewById(R.id.layout_outdoor);
        layout_stage = view.findViewById(R.id.layout_stage);
        layout_food = view.findViewById(R.id.layout_food);
        layout_indoor = view.findViewById(R.id.layout_indoor);
        layout_fitness = view.findViewById(R.id.layout_fitness);
        layout_travel = view.findViewById(R.id.layout_travel);


        layout_wine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WineActivity.class);
                startActivity(intent);
            }
        });

        layout_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MusicActivity.class);
                startActivity(intent);
            }
        });

        layout_outdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Profile.class);
                startActivity(i);
            }
        });

        layout_stage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Profile.class);
                startActivity(intent);
            }
        });

        layout_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Profile.class);
                startActivity(intent);
            }
        });

        layout_indoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Profile.class);
                startActivity(intent);
            }
        });

        layout_fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Profile.class);
                startActivity(intent);
            }
        });

        layout_travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Profile.class);
                startActivity(intent);
            }
        });

 return view;
}
}
