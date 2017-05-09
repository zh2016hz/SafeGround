package com.gogoing.www.safeground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gogoing.www.safeground.avtivity.PhoneSafeActivity;

public class GuideActivity3 extends AppCompatActivity {

    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide3);
        Button next = (Button) findViewById(R.id.next3);
        Button pre = (Button) findViewById(R.id.pre3);
        Button num= (Button) findViewById(R.id.butto3);
        content = (EditText) findViewById(R.id.edit3);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity3.this,GuideActivity4.class));
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity3.this,GuideActivity2.class));
            }
        });
        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = content.getText().toString().trim();

            }
        });
    }
}
