package com.daniel.homecard.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.daniel.homecard.module.CardBean;
import com.daniel.homecard.module.DatabaseHelper;

import java.util.List;

/**
 * 男神卡片适配器
 * Created by Daniel on 2018/3/27.
 */

public class MaleCardAdapter extends CardAdapter {
    public MaleCardAdapter(List<CardBean> cardBeanList) {
        super(cardBeanList);
    }

    @Override
    void showCount(TextView tvCount, int count) {
        tvCount.setVisibility(View.VISIBLE);
        tvCount.setText(String.valueOf(count));
    }

    @Override
    protected void onClickItem(final CardBean cardBean) {
        new AlertDialog.Builder(mContext).setTitle("确认消耗一张卡？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                consumeOnce(cardBean);
                notifyDataSetChanged();
            }
        }).show();
    }

    private void consumeOnce(CardBean cardBean) {
        if (cardBean.getCount() > 1) {
            cardBean.setCount(cardBean.getCount() - 1);
        } else {
            mCardBeanList.remove(cardBean);
        }
        new DatabaseHelper(mContext).consumeMaleCardOnce(cardBean.getId());
    }

}
