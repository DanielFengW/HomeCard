package com.daniel.homecard.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniel.homecard.R;
import com.daniel.homecard.module.CardBean;
import com.daniel.homecard.module.DatabaseHelper;

import java.util.List;

/**
 * 女神卡片列表
 * Created by Daniel on 2018/3/27.
 */

public class FemaleFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_female, container, false);
        loadData();
        return mRecyclerView;
    }

    private void loadData() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        List<CardBean> femaleCardList = databaseHelper.getFemaleCardList();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new FemaleCardAdapter(femaleCardList));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getContext() != null)
            loadData();
    }
}
