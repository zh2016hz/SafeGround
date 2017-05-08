package com.gogoing.www.safeground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gogoing.www.safeground.Utils.Constant;
import com.gogoing.www.safeground.Utils.SharePrefrenceUtil;
import com.gogoing.www.safeground.view.Setting_item;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private Setting_item autorefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initData();



    }

    private void initData() {
        SharePrefrenceUtil.getBoolean(this,Constant.AUTOUPDATE);
        autorefresh.changestate(SharePrefrenceUtil.getBoolean(this,Constant.AUTOUPDATE));
    }

    private void initView() {
        autorefresh = (Setting_item) findViewById(R.id.autorefresh);
        Setting_item   stopin = (Setting_item) findViewById(R.id.stopin);
        Setting_item   locationshow = (Setting_item) findViewById(R.id.locatinonshow);
        Setting_item   locationstyle = (Setting_item) findViewById(R.id.loactionstylesetting);
        autorefresh.setOnClickListener(this);
        stopin.setOnClickListener(this);
        locationshow.setOnClickListener(this);
        locationstyle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.autorefresh:
                Boolean aBoolean = SharePrefrenceUtil.getBoolean(this, Constant.AUTOUPDATE);
                autorefresh.changestate(!aBoolean);
                SharePrefrenceUtil.putBoolean(this,Constant.AUTOUPDATE,!aBoolean);

                break;
            case R.id.stopin:
                Toast.makeText(this,"2",Toast.LENGTH_LONG).show();
                break;
            case R.id.locatinonshow:
                Toast.makeText(this,"3",Toast.LENGTH_LONG).show();
                break;
            case R.id.loactionstylesetting:
                Toast.makeText(this,"4",Toast.LENGTH_LONG).show();
                break;
        }

    }
}
