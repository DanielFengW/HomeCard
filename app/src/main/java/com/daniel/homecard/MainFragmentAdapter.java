package com.daniel.homecard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.daniel.homecard.ui.CardLibFragment;
import com.daniel.homecard.ui.FemaleFragment;
import com.daniel.homecard.ui.MaleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 * Created by Daniel on 2018/3/27.
 */

public class MainFragmentAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList;

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new MaleFragment());
        mFragmentList.add(new FemaleFragment());
        mFragmentList.add(new CardLibFragment());

    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
