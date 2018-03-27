package com.daniel.homecard.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daniel.homecard.R;
import com.daniel.homecard.module.CardBean;

import java.util.List;

/**
 * 卡片适配器
 * Created by Daniel on 2018/3/27.
 */

public abstract class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    List<CardBean> mCardBeanList;
    Context mContext;

    public CardAdapter(List<CardBean> cardBeanList) {
        mCardBeanList = cardBeanList;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, final int position) {
        final CardBean cardBean = mCardBeanList.get(position);
        holder.mTvName.setText(cardBean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem(cardBean);
            }
        });
        showCount(holder.mTvCount, cardBean.getCount());
    }

    void showCount(TextView tvName, int count) {
        tvName.setVisibility(View.GONE);
    }

    protected abstract void onClickItem(CardBean cardBean);

    @Override
    public int getItemCount() {
        return mCardBeanList.size();
    }

    static class CardViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTvName;
        private final TextView mTvCount;

        CardViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tv_name);
            mTvCount = itemView.findViewById(R.id.tv_count);
        }
    }
}
