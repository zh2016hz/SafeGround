package com.gogoing.www.safeground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gogoing.www.safeground.Utils.Constant;
import com.gogoing.www.safeground.Utils.SharePrefrenceUtil;

public class GuideActivity5 extends AppCompatActivity {

    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide5);
        Button next = (Button) findViewById(R.id.next5);
        Button pre = (Button) findViewById(R.id.pre5);
        checkBox = (CheckBox) findViewById(R.id.chect5);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity5.this,GuideActivity5.class));
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity5.this,GuideActivity3.class));
            }
        });


    }
}
