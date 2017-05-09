package com.gogoing.www.safeground.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public abstract  class GuideBaseAvtivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float rawX = e1.getRawX();
                float rawY = e1.getRawY();
                float raw2X = e2.getRawX();
                float raw2Y = e2.getRawY();
                    if(Math.abs(raw2X -rawX) >Math.abs(raw2Y -rawY)  ){
//                        左右滑动
                        if(raw2X -rawX >0){
//                            由华东
                            Boolean isfisrt = isfisrt();
                            if(isfisrt){

                            }
                        }else{

                        }
                    }


                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }
    public abstract Boolean isfisrt();
}
