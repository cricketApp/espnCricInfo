package com.mobileblog.user.mobileblogapp.cricket;

import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.mobileblog.user.mobileblogapp.R;
import com.mobileblog.user.mobileblogapp.football.CSVFile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ScoresActivity extends AppCompatActivity {

    private String t1,t2,t_id,t1_url,t2_url;
    private TextView txtName1,txtName2,txtDes;
    private ImageView image1,image2;
    private String url_score,score;
    private String url_summary;
    private TextView txt_summary_team1,txt_summary_team2;
    private Fragment fragment;
    private ArrayList<Batsman> firstTeamBat,secTeamBat;
    private ArrayList<Bowler> firstTeamBall,secTeamBall;
    private Bundle bundle = new Bundle();
    private Batsman batsmanInfo;
    private Bowler bowlerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        txtName1= findViewById(R.id.txt_team_name1);
        txtName2= findViewById(R.id.txt_team_name2);
        txtDes= findViewById(R.id.txt_des);
        image1 = findViewById(R.id.flag1);
        image2 = findViewById(R.id.flag2);
        txt_summary_team1= findViewById(R.id.txt_team1_name);
        txt_summary_team2= findViewById(R.id.txt_team2_name);

        firstTeamBat = new ArrayList<>();
        secTeamBat = new ArrayList<>();
        firstTeamBall = new ArrayList<>();
        secTeamBall = new ArrayList<>();

        batsmanInfo = new Batsman("Batsman","SR","4s","6s","Ball","Run","");
        firstTeamBat.add(batsmanInfo);
        secTeamBat.add(batsmanInfo);

        bowlerInfo = new Bowler("bowler","   0s","   M","   W","   R","econ","over");
        firstTeamBall.add(bowlerInfo);
        secTeamBall.add(bowlerInfo);


        t1 = getIntent().getStringExtra("team1");
        t2 = getIntent().getStringExtra("team2");
        t_id = getIntent().getStringExtra("match_id");

        url_score = "http://cricapi.com/api/cricketScore/?apikey=7bhQGSYMoAgENRQ1ZhiVctgVGcc2&unique_id="+t_id;

        url_summary = "http://cricapi.com/api/fantasySummary/?apikey=7bhQGSYMoAgENRQ1ZhiVctgVGcc2&unique_id="+t_id;

        getAllSummary(url_summary,firstTeamBat,secTeamBat,firstTeamBall,secTeamBall);

        getScore(url_score);

        getFlag();
        txtName1.setText(t1);
        txtName2.setText(t2);

        txt_summary_team1.setText(t1);
        txt_summary_team2.setText(t2);

        txt_summary_team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new SummaryFragment1();
                bundle.putParcelableArrayList("ftbat",firstTeamBat);
                bundle.putParcelableArrayList("stball",firstTeamBall);
                fragment.setArguments(bundle);
                replaceFragment(fragment);

            }
        });
        txt_summary_team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new SummaryFragment2();
                bundle.putParcelableArrayList("stbat",secTeamBat);
                bundle.putParcelableArrayList("ftbal",secTeamBall);
                fragment.setArguments(bundle);
                replaceFragment(fragment);

            }
        });


    }

    private void getScore(String url_score) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_score, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                     score = response.getString("score");
                     txtDes.setText(score);
                     //Toast.makeText(getApplicationContext(),score,Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue req1 = Volley.newRequestQueue(getApplicationContext());
        req1.add(request);
    }

    private void getFlag() {

        InputStream inputStream = getResources().openRawResource(R.raw.countries);
        CSVFile csvFile = new CSVFile(inputStream);
        final List<String[]> mylist = csvFile.read();

        for (int k =0;k< mylist.size();k++)
        {
            String[] tem = mylist.get(k);
            String check = tem[0].toString();
            if(t1.contains(check)){
                t1_url = tem[5].toString();

            }else if(t2.contains(check)){
                t2_url = tem[5].toString();
            }

        }
        if(t1_url != null || t2_url != null){
            Glide.with(getApplicationContext()).load(t1_url).into(image1);
            Glide.with(getApplicationContext()).load(t2_url).into(image2);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                image1.setImageDrawable(getDrawable(R.drawable.cric_def));
                image2.setImageDrawable(getDrawable(R.drawable.cric_def));
            }
        }
    }

    private void replaceFragment(Fragment fragment){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.score_summary_container,fragment);
        transaction.commit();
    }

    void getAllSummary(String url, final ArrayList<Batsman> firstTeamBat, final ArrayList<Batsman> secTeamBat, final ArrayList<Bowler> firstTeamBall, final ArrayList<Bowler> secTeamBall){

        final JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONObject main_object = response.getJSONObject("data");
                    JSONArray main_jsonArray = main_object.getJSONArray("batting");

                    for(int k = 0 ; k < main_jsonArray.length() ; k++){

                        JSONObject child_ob = (JSONObject) main_jsonArray.get(k);
                        String checkTeam = child_ob.getString("title");

                        //Toast.makeText(getApplicationContext(),checkTeam,Toast.LENGTH_SHORT).show();

                        JSONArray scoreArr = child_ob.getJSONArray("scores");

                        //all bats man scores
                    for(int i = 0 ; i < scoreArr.length() ; i++){
                        JSONObject batsmanObject = scoreArr.getJSONObject(i);

                        String batsman = batsmanObject.getString("batsman");
                        String sr = batsmanObject.getString("SR");
                        String fours = batsmanObject.getString("4s");
                        String sixs = batsmanObject.getString("6s");
                        String balls = batsmanObject.getString("B");
                        String runs = batsmanObject.getString("R");
                        String dismisals = batsmanObject.getString("dismissal-info");

                        //getting extra runs
                        if (i == scoreArr.length() - 1){
                            String details = batsmanObject.getString("detail");
                            batsman = batsman + " : "+details;
                        }

                        batsmanInfo = new Batsman(batsman,sr,fours,sixs,balls,runs,dismisals);

                        if(checkTeam.contains(t1)){
                            firstTeamBat.add(batsmanInfo);//first team batting list
                            //Toast.makeText(getApplicationContext(),checkTeam,Toast.LENGTH_SHORT).show();
                        }else{
                            secTeamBat.add(batsmanInfo);//second team batting list
                        }

                    }


                    }

                    JSONArray bowler_main_jsonArray = main_object.getJSONArray("bowling");

                    for(int k = 0 ; k < main_jsonArray.length() ; k++){

                        JSONObject child_ob = (JSONObject) bowler_main_jsonArray.get(k);
                        String checkTeamball = child_ob.getString("title");

                        JSONArray scoreArr = child_ob.getJSONArray("scores");

                        for(int i = 0 ; i < scoreArr.length() ; i++){
                            JSONObject batsmanObject = scoreArr.getJSONObject(i);

                            String bowler = batsmanObject.getString("bowler");
                            String econ = batsmanObject.getString("Econ");
                            String dots = batsmanObject.getString("0s");
                            String maiden = batsmanObject.getString("M");
                            String runs = batsmanObject.getString("R");
                            String wicket_number = batsmanObject.getString("W");
                            String over = batsmanObject.getString("O");

                            bowlerInfo = new Bowler(bowler,dots,maiden,wicket_number,runs,econ,over);

                            if(checkTeamball.contains(t1)){
                                firstTeamBall.add(bowlerInfo);
                                //Toast.makeText(getApplicationContext(),checkTeam,Toast.LENGTH_SHORT).show();
                            }else{
                                secTeamBall.add(bowlerInfo);
                            }

                        }


                    }

                    fragment = new SummaryFragment1();
                    bundle.putParcelableArrayList("ftbat",firstTeamBat);
                    bundle.putParcelableArrayList("stball",firstTeamBall);
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue req1 = Volley.newRequestQueue(getApplicationContext());
        req1.add(objectRequest);
    }

}
