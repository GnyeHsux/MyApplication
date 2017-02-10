package com.sgu.jack.mypay.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sgu.jack.mypay.R;
import com.sgu.jack.mypay.model.AccountCard;
import com.sgu.jack.mypay.ui.activity.CreateAccountCardActivity;
import com.sgu.jack.mypay.ui.adapter.CardListAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xusha on 2016/8/22.
 */
public class CardListFragment extends Fragment {
    @BindView(R.id.refresher)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private CardListAdapter mCardListAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<AccountCard> mData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_list, container, false);
        ButterKnife.bind(this, view);



        initData();
        mCardListAdapter = new CardListAdapter(mData);

        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mCardListAdapter);


        return view;
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mData.add(new AccountCard(0, "支付宝", "支付宝", "13580114480", "2080.33", "25.00"));
            mData.add(new AccountCard(1, "微信", "微信", "xsy", "2080.33", "25.00"));
            mData.add(new AccountCard(2, "现金", "现金", "小沈阳", "2080.33", "25.00"));
            mData.add(new AccountCard(3, "工商银行", "工商银行", "6212262005001483897", "5080.33", "25.00"));
        }
    }
}
