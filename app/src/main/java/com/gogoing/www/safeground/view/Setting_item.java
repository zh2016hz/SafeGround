package com.gogoing.www.safeground.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gogoing.www.safeground.R;

/**
 * Created by Administrator on 2017/5/7 0007.
 */

public class Setting_item extends RelativeLayout {

    private static final int STATE_UP = 0;
    private static final int STATE_DOWN = 2;
    private static final int STATE_MIDDLE = 1;
    private ImageView turnon_item;

    public Setting_item(Context context) {
        this(context,null);
    }

    public Setting_item(Context context, AttributeSet attrs) {
        super(context, attrs);
        //加载布局
        View.inflate(context, R.layout.setting_item_layout,this);
        //拿属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatingBar);
        String title = typedArray.getString(R.styleable.RatingBar_Text_define);
        Boolean  turnon = typedArray.getBoolean(R.styleable.RatingBar_Chext_define,false);
        int  backgroud = typedArray.getInt(R.styleable.RatingBar_Choise_define,0);
//      找ID
        TextView title_item = (TextView) findViewById(R.id.setting_item_title);
        turnon_item = (ImageView) findViewById(R.id.setting_item_icon);
//        赋值
        title_item.setText(title);
        turnon_item.setVisibility(turnon?View.VISIBLE:View.GONE);
        switch (backgroud){
            case STATE_UP:
                setBackgroundResource(R.drawable.item_back_top);
                break;
            case STATE_MIDDLE:
                setBackgroundResource(R.drawable.item_back_middle);
                break;
            case STATE_DOWN:
                setBackgroundResource(R.drawable.item_back_botton);
                break;
            default:
                break;
        }
        typedArray.recycle();
    }
    public void changestate(Boolean tag){
            turnon_item.setImageResource(tag?R.drawable.on:R.drawable.off);
    }

}
