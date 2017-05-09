package com.gogoing.www.safeground;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gogoing.www.safeground.Utils.Constant;
import com.gogoing.www.safeground.Utils.SharePrefrenceUtil;
import com.gogoing.www.safeground.avtivity.PhoneSafeActivity;
import com.gogoing.www.safeground.view.GuideBaseAvtivity;

public class GuideActivity2 extends GuideBaseAvtivity {

    private ImageView lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide2);
       Button next = (Button) findViewById(R.id.next2);
       Button pre = (Button) findViewById(R.id.pre2);
        RelativeLayout  bind = (RelativeLayout) findViewById(R.id.bind);
        lock = (ImageView) findViewById(R.id.bindicon);

        Boolean aBoolean1 = SharePrefrenceUtil.getBoolean(GuideActivity2.this, Constant.LUCKORUNLORCK, true);
        lock.setImageResource(aBoolean1?R.drawable.lock: R.drawable.unlock);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity2.this,GuideActivity3.class));
                overridePendingTransition(R.anim.next_in,R.anim.next_in2);
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity2.this,PhoneSafeActivity.class));
                overridePendingTransition(R.anim.pre_in,R.anim.pre_in1);
            }
        });
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean aBoolean = SharePrefrenceUtil.getBoolean(GuideActivity2.this, Constant.LUCKORUNLORCK, true);
                lock.setImageResource(!aBoolean?R.drawable.lock: R.drawable.unlock);
                SharePrefrenceUtil.putBoolean(GuideActivity2.this, Constant.LUCKORUNLORCK,!aBoolean);
            }
        });

    }

    @Override
    public Boolean isfisrt() {
        //跳转
        return true;
    }
}
