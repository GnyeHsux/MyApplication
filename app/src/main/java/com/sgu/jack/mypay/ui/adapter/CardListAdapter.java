package com.sgu.jack.mypay.ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sgu.jack.mypay.R;
import com.sgu.jack.mypay.model.AccountCard;
import com.sgu.jack.mypay.ui.mypayview.AccountCardView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：xushane on 2016/9/22
 * 邮箱：xushaneone@gmail.com
 */
public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder> {

    private ArrayList<AccountCard> mAccountCardsList;

    public CardListAdapter(ArrayList<AccountCard> accountCardsList) {
        this.mAccountCardsList = accountCardsList;
    }

    public void setData(ArrayList<AccountCard> accountCardsList) {
        this.mAccountCardsList = accountCardsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_account_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final AccountCard accountCard = mAccountCardsList.get(position);
//        int[] imgs = holder.mContext.getResources().getIntArray(R.array.account_card_img);
//        Log.i("adapter", imgs.toString());
        //TypedArrayUtils
        TypedArray ta = holder.mContext.getResources().obtainTypedArray(R.array.account_card_img);
        int[] imgs = new int[ta.length()];
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = ta.getResourceId(i, 0);
        }
        Log.i("adapter", imgs.toString());
        ta.recycle();

        String[] bgcolorS = holder.mContext.getResources().getStringArray(R.array.account_card_bg_color);

        holder.mAccountCardView.setBackgroundColor(Color.parseColor(bgcolorS[accountCard.getId()]));

        holder.mAccountCardView.setAccountImg(imgs[accountCard.getId()]);
        holder.mAccountCardView.setAccountDesc(accountCard.getAccountDesc());
        holder.mAccountCardView.setAccountName(accountCard.getAccountName(), accountCard.getAccountReMark());
        holder.mAccountCardView.setAccountBalance(accountCard.getAccountBalance());
        holder.mAccountCardView.setTodaySpend(accountCard.getTodaySpend());

        holder.mAccountCardView.setOnBottomTextClickListener(new AccountCardView.BottomTextClickListener() {
            @Override
            public void onTransactionClick() {
                Log.i("adapter", "Transaction - " + position);
            }

            @Override
            public void onModifyAccountClick() {
                Log.i("adapter", "Modify - " + position);
            }

            @Override
            public void onTransferClick() {
                Log.i("adapter", "Transfer - " + position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mAccountCardsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.accountcard)
        AccountCardView mAccountCardView;
        public Context mContext;

        public ViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }
    }
}
