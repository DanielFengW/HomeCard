package com.daniel.homecard.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.daniel.homecard.R;
import com.daniel.homecard.module.CardBean;
import com.daniel.homecard.module.DatabaseHelper;

import java.util.List;

/**
 * 卡片库适配器
 * Created by Daniel on 2018/3/27.
 */

public class LibCardAdapter extends CardAdapter {
    public LibCardAdapter(List<CardBean> cardBeanList) {
        super(cardBeanList);
    }
    private AlertDialog mChooseDialog;

    @Override
    protected void onClickItem(CardBean cardBean) {
        showDialog(cardBean.getId());
    }

    private void showDialog(final int cardId) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                addToUserDb(which, cardId);
            }
        };
        mChooseDialog = new AlertDialog.Builder(mContext)
                .setTitle("分配给")
                .setSingleChoiceItems(R.array.target, -1, onClickListener)
                .setCancelable(true)
                .show();
    }

    private void addToUserDb(int which, int cardId) {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        switch (which) {
            case 0:
                databaseHelper.insertMaleCard(cardId);
                break;
            case 1:
                databaseHelper.insertFemaleCard(cardId);
                break;
        }

    }

}
