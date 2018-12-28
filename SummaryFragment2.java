package com.mobileblog.user.mobileblogapp.cricket;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mobileblog.user.mobileblogapp.R;

import java.util.ArrayList;

public class SummaryFragment2 extends Fragment {

    private RecyclerView team_bat_Recycler,team_ball_Recycler;
    private BatsmanAdapter adapterBat;
    private BowlerAdapter adapterBall;


    public SummaryFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_summary_fragment2, container, false);

        team_bat_Recycler = v.findViewById(R.id.team_one_recycler_bats);
        team_bat_Recycler.setHasFixedSize(true);
        team_bat_Recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        team_ball_Recycler = v.findViewById(R.id.team_two_recycler_balls);
        team_ball_Recycler.setHasFixedSize(true);
        team_ball_Recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        Bundle extras = getArguments();
        ArrayList<Bowler> ftbal  = extras.getParcelableArrayList("ftbal");
        ArrayList<Batsman> stbat  = extras.getParcelableArrayList("stbat");

//        String name = al21.get(0).getBatsmanName();
//
//        Toast.makeText(getActivity(),name+"",Toast.LENGTH_LONG).show();

        adapterBat = new BatsmanAdapter(stbat);
        adapterBall = new BowlerAdapter(ftbal);
        team_bat_Recycler.setAdapter(adapterBat);
        team_ball_Recycler.setAdapter(adapterBall);

        return v;
    }

}
