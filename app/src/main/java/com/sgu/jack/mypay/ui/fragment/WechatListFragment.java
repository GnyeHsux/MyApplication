package com.sgu.jack.mypay.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sgu.jack.mypay.R;
import com.sgu.jack.mypay.ui.mypayview.AccountCardView;

/**
 * Created by xusha on 2016/8/22.
 */
public class WechatListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wechat_list, container, false);
        AccountCardView cardView = (AccountCardView) view.findViewById(R.id.accountcard);
        cardView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        cardView.setAccountDesc("支付宝");
        cardView.setAccountName("支付宝","13580114480");
        cardView.setAccountBalance("12.08");
        cardView.setTodaySpend("33.25");
        cardView.setAccountImg(R.drawable.ic_action_dashboard);
        cardView.setOnBottomTextClickListener(new AccountCardView.BottomTextClickListener() {
            @Override
            public void onTransactionClick() {
                Toast.makeText(getActivity(),"Transaction",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onModifyAccountClick() {
                Toast.makeText(getActivity(),"modify",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransferClick() {
                Toast.makeText(getActivity(),"Transfer",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
