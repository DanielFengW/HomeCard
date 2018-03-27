package com.daniel.homecard.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.daniel.homecard.MainFragmentAdapter;
import com.daniel.homecard.R;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {
    private ViewPager mViewPager;
    private BottomNavigationView mNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigation = findViewById(R.id.navigation);
        mNavigation.setOnNavigationItemSelectedListener(this);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mNavigation.setSelectedItemId(R.id.male);
                break;
            case 1:
                mNavigation.setSelectedItemId(R.id.female);
                break;
            case 2:
                mNavigation.setSelectedItemId(R.id.card_lib);
                break;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.male:
                mViewPager.setCurrentItem(0);
                return true;
            case R.id.female:
                mViewPager.setCurrentItem(1);
                return true;
            case R.id.card_lib:
                mViewPager.setCurrentItem(2);
                return true;
        }
        return false;
    }
}
