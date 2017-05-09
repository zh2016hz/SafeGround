package com.gogoing.www.safeground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gogoing.www.safeground.Utils.Constant;
import com.gogoing.www.safeground.Utils.SharePrefrenceUtil;
import com.gogoing.www.safeground.avtivity.PhoneSafeActivity;

public class GuideActivity4 extends AppCompatActivity {

    private ImageView active_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide4);
        Button next = (Button) findViewById(R.id.next4);
        Button pre = (Button) findViewById(R.id.pre4);
        RelativeLayout bind = (RelativeLayout) findViewById(R.id.active);
        active_icon = (ImageView) findViewById(R.id.active_icon);

        Boolean aBoolean1 = SharePrefrenceUtil.getBoolean(GuideActivity4.this, Constant.ACTIVESTATU, true);
        active_icon.setImageResource(aBoolean1?R.drawable.admin_activated: R.drawable.admin_inactivated);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity4.this,GuideActivity5.class));
                overridePendingTransition(R.anim.next_in,R.anim.next_in2);
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity4.this,GuideActivity3.class));
                overridePendingTransition(R.anim.pre_in,R.anim.pre_in1);
            }
        });
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean aBoolean = SharePrefrenceUtil.getBoolean(GuideActivity4.this, Constant.ACTIVESTATU, true);
                active_icon.setImageResource(!aBoolean?R.drawable.admin_activated: R.drawable.admin_inactivated);
                SharePrefrenceUtil.putBoolean(GuideActivity4.this, Constant.ACTIVESTATU,!aBoolean);
            }
        });

    }
    }

