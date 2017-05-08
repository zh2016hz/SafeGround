package com.gogoing.www.safeground;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gogoing.www.safeground.Utils.Constant;
import com.gogoing.www.safeground.Utils.SharePrefrenceUtil;
import com.gogoing.www.safeground.avtivity.PhoneSafeActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView horse;
    private final static String[] TITLES =
            new String[]{"手机防盗", "骚扰拦截", "软件管家", "进程管理", "流量统计", "手机杀毒", "缓存清理", "常用工具"};
    private final static String[] DESCS = new String[]{"远程定位手机", "全面拦截骚扰", "管理您的软件", "管理运行进程",
            "流量一目了然", "病毒无处藏身", "系统快如火箭", "工具大全"};
    private final static int[] ICONS = new int[]{R.drawable.sjfd, R.drawable.srlj, R.drawable.rjgj,
            R.drawable.jcgl, R.drawable.lltj, R.drawable.sjsd, R.drawable.hcql, R.drawable.cygj};
    private EditText pwd1;
    private EditText pwd2;
    private AlertDialog.Builder alert;
    private AlertDialog show;
    private AlertDialog show1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView b = (GridView) findViewById(R.id.bb);
        ImageView setting = (ImageView) findViewById(R.id.setting);
        horse = (ImageView) findViewById(R.id.horse);
        GridviewAdapter adapter = new GridviewAdapter();
        b.setAdapter(adapter);
        b.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case  0:
                        Phonesafe();
                        break;
                }
            }
        });
        loadAnim();
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });

    }
//手机防盗
    private void Phonesafe() {
        final String getpwd = SharePrefrenceUtil.getpwd(MainActivity.this, Constant.PHONESAFEPWD);
        final AlertDialog.Builder alert =  new AlertDialog.Builder(MainActivity.this);
        if(getpwd == null){
            View inflate = View.inflate(MainActivity.this, R.layout.alertpsw, null);
            alert.setView(inflate);
            TextView title = (TextView) inflate.findViewById(R.id.pwstitle);
            pwd1 = (EditText) inflate.findViewById(R.id.pwd1);
            pwd2 = (EditText) inflate.findViewById(R.id.pwd2);
            Button   comfirm= (Button) inflate.findViewById(R.id.comfirm);
            Button   cancel= (Button) inflate.findViewById(R.id.cancel);
            comfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String trim = pwd1.getText().toString().trim();
                    String trim1 = pwd2.getText().toString().trim();
                    if(trim.equals(trim1)){
                        startActivity(new Intent(MainActivity.this,PhoneSafeActivity.class));
                        SharePrefrenceUtil.putpwd(MainActivity.this, Constant.PHONESAFEPWD,trim);
                    }else if(trim ==null || trim1 == null) {
                        Toast.makeText(MainActivity.this,"密码不能为空！",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this,"两次输入密码不一致！！",Toast.LENGTH_LONG).show();
                    }

                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
            show = alert.show();
        }else {

            View inflate = View.inflate(MainActivity.this, R.layout.alertpsw2, null);
            alert.setView(inflate);
            pwd1 = (EditText) inflate.findViewById(R.id.pwd1);
            Button   comfirm= (Button) inflate.findViewById(R.id.comfirm);
            Button   cancel= (Button) inflate.findViewById(R.id.cancel);
            comfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String trim = pwd1.getText().toString().trim();

                    if(getpwd.equals(trim)){
                        startActivity(new Intent(MainActivity.this,PhoneSafeActivity.class));
                      show1.dismiss();
                    }else if(trim ==null ) {
                        Toast.makeText(MainActivity.this,"密码不能为空！",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this,"密码不对！！",Toast.LENGTH_LONG).show();
                    }

                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
            show1 = alert.show();
        }

    }

    // 加载动画
    private void loadAnim() {
        // Object target: 执行动画的对象
        // String propertyName : 具体的动画属性
        // float... values : 动画的值
        // 创建动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(horse, "rotationY", 0, 45, 90, 135);
        // 设置时常
        animator.setDuration(1500);
        // 设置重复次数
        animator.setRepeatCount(-1);
        // 设置重复播放方式
        animator.setRepeatMode(ValueAnimator.REVERSE);
        // 开始动画
        animator.start();

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(horse, "rotationX", 0, 45, 90, 135);
        animator2.setDuration(1500);
        animator2.setRepeatCount(-1);
        animator2.setRepeatMode(ValueAnimator.REVERSE);
        animator2.start();
        // 动画集合
        AnimatorSet set = new AnimatorSet();
        // 设置要播放的动画集合
        set.playTogether(animator, animator2);
        set.start();

    }

    private class GridviewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_main_grid, null);

            }
            ImageView icon = (ImageView) convertView.findViewById(R.id.item_main_grid_iv_icon);
            TextView desc = (TextView) convertView.findViewById(R.id.item_main_grid_tv_desc);
            TextView title = (TextView) convertView.findViewById(R.id.item_main_grid_tv_title);

            icon.setImageResource(ICONS[position]);
            desc.setText(DESCS[position]);
            title.setText(TITLES[position]);
            return convertView;
        }
    }
}
