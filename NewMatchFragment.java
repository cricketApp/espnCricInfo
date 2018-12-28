package com.mobileblog.user.mobileblogapp.cricket;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mobileblog.user.mobileblogapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class NewMatchFragment extends Fragment {


    private RecyclerView newMatchRecycler;
    private NewMatchAdapter adapter;

    private String url = "http://cricapi.com/api/matchCalendar/?apikey=7bhQGSYMoAgENRQ1ZhiVctgVGcc2";
    private ArrayList<NewMatch> matches;
    public NewMatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_new_match, container, false);

        newMatchRecycler = v.findViewById(R.id.new_match_recycler);
        newMatchRecycler.setHasFixedSize(true);
        newMatchRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        matches = new ArrayList<>();

        getAllFutureMatch(url,matches);


        return v;
    }

    private void getAllFutureMatch(String url, final ArrayList<NewMatch> matches) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray = null;

                try {
                    jsonArray = response.getJSONArray("data");


                    for(int i = 0 ; i < jsonArray.length() ; i++){

                        JSONObject object = jsonArray.getJSONObject(i);

                        String name = object.getString("name");
                        String date = object.getString("date");

                        matches.add(new NewMatch(name,date));

                    }

                    adapter = new NewMatchAdapter(matches);
                    newMatchRecycler.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue req1 = Volley.newRequestQueue(getActivity());
        req1.add(request);

    }


}
