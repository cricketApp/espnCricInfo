package com.mobileblog.user.mobileblogapp.cricket;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.mobileblog.user.mobileblogapp.R;
import com.mobileblog.user.mobileblogapp.football.Parameters;

public class CricActivity extends AppCompatActivity {

    private BottomNavigationView cricBottomNav;
    private Fragment cricMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cric);

        final ViewGroup mContainer = (ViewGroup) findViewById(android.R.id.content).getRootView();
        final Typeface tf = Typeface.createFromAsset(getAssets(), "Comfortaa-Regular.ttf");
        Parameters.setAppFont(mContainer, tf);

        cricMainFragment = new cricHomeFragment();

        replaceFragment(cricMainFragment);


        cricBottomNav = findViewById(R.id.bottom_navigation_bar);


        cricBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.cric_matches_item:
                        cricMainFragment = new cricHomeFragment();
                        replaceFragment(cricMainFragment);
                        break;

                    case R.id.new_match_item:
                        cricMainFragment = new NewMatchFragment();
                        replaceFragment(cricMainFragment);
                        break;

                    case R.id.search_palyer_item:
                        cricMainFragment = new NewsCricketFragment();
                        replaceFragment(cricMainFragment);
                        break;

                }
                return true;
            }
        });


    }

    private void replaceFragment(Fragment fragment){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.cric_container,fragment);
        transaction.commit();
    }

}
