package com.sgu.jack.mypay.model;

/**
 * 账户卡片
 * 作者：xushane on 2016/9/22
 * 邮箱：xushaneone@gmail.com
 */
public class AccountCard {
    private int mId;
    private String mAccountDesc;
    private String mAccountName;
    private String mAccountBalance;
    private String mTodaySpend;
    private String mAccountReMark;

    public AccountCard(
            int id,
            String accountDesc,
            String accountName,
            String accountReMark,
            String accountBalance,
            String todaySpend){
        this.mId = id;
        this.mAccountDesc = accountDesc;
        this.mAccountName = accountName;
        this.mAccountReMark = accountReMark;
        this.mAccountBalance = accountBalance;
        this.mTodaySpend = todaySpend;
    }

    public int getId(){
        return mId;
    }

    public String getAccountDesc(){
        return mAccountDesc;
    }

    public String getAccountName(){
        return  mAccountName;
    }
    public String getAccountReMark(){
        return mAccountReMark;
    }

    public String getAccountBalance(){
        return mAccountBalance;
    }

    public String getTodaySpend(){
        return mTodaySpend;
    }
}
