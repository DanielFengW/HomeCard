package com.daniel.homecard.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniel.homecard.R;
import com.daniel.homecard.module.CardBean;
import com.daniel.homecard.module.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 卡片库
 * Created by Daniel on 2018/3/27.
 */

public class CardLibFragment extends Fragment {

    private SQLiteDatabase mReadableDatabase;
    private List<CardBean> mCardList;
    private LibCardAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_card_lib, container, false);
        RecyclerView recyclerView = (RecyclerView) inflate;
        mCardList = new ArrayList<>();
        mAdapter = new LibCardAdapter(mCardList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());
        mReadableDatabase = databaseHelper.getReadableDatabase();
        queryCardList();

        return inflate;
    }

    private void queryCardList() {
        Cursor cursor = mReadableDatabase.query("Card", new String[]{"_id", "cardName", "cardDesc"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String desc = cursor.getString(2);
            mCardList.add(new CardBean(id, name, desc));
        }
        cursor.close();
        Log.i("database", mCardList.toString());
        mAdapter.notifyDataSetChanged();
        mReadableDatabase.close();
    }
}
