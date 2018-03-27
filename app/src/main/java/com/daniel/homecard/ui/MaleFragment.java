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
 * Male
 * Created by Daniel on 2018/3/27.
 */

public class MaleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        List<CardBean> maleCardList = databaseHelper.getMaleCardList();
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_male, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MaleCardAdapter(maleCardList));
        return recyclerView;
    }
}
