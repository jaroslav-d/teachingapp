package com.example.jaroslav.teachingapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends Activity {

    MyView myView;

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myView = new MyView(this);
        setContentView(myView);
        myView.setOnTouchListener(myTouch);
        //myView.onTouchEvent(myTouch);
        //myView.setOnClickListener(myClick);
        //myView.setOnGenericMotionListener(myMotion);
    }

    private View.OnTouchListener myTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            myView.xyztext = String.valueOf(event.getActionMasked());
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    myView.var = event.getAxisValue(event.AXIS_Y,0);
                    myView.actionDownX = event.getAxisValue(event.AXIS_X,0);
                    myView.actionDownY = event.getAxisValue(event.AXIS_Y,0);
                    return true;


                case MotionEvent.ACTION_MOVE:

                    if (Math.abs(myView.actionDownY - event.getAxisValue(event.AXIS_Y)) >
                            Math.abs(myView.actionDownX - event.getAxisValue(event.AXIS_X))) {
                        if (myView.actionDownY - event.getAxisValue(event.AXIS_Y) < 0) {
                            myView.touch = "down"; break;
                        } else {
                            myView.touch = "up"; break;
                        }
                    } else {
                        if (myView.actionDownX - event.getAxisValue(event.AXIS_X) < 0) {
                            myView.touch = "right"; break;
                        } else {
                            myView.touch = "left"; break;
                        }
                    }
                    /*
                     */
                case MotionEvent.ACTION_UP:
                    /*
                    if (Math.abs(myView.actionDownY - event.getAxisValue(event.AXIS_Y)) >
                            Math.abs(myView.actionDownX - event.getAxisValue(event.AXIS_X))) {
                        if (myView.actionDownY - event.getAxisValue(event.AXIS_Y) < 0) {
                            myView.touch = "down"; break;
                        } else {
                            myView.touch = "up"; break;
                        }
                    } else {
                        if (myView.actionDownX - event.getAxisValue(event.AXIS_X) < 0) {
                            myView.touch = "right"; break;
                        } else {
                            myView.touch = "left"; break;
                        }
                    }
                    /*
                    */
                    myView.actionDownX = 0;
                    myView.actionDownY = 0;
                    if (myView.var - event.getAxisValue(event.AXIS_Y,0) < 0) {
                        myView.twovar++; return true;
                    } else {
                        myView.twovar--; return true;
                    }
            }
            return true;
        }
    };

    /*
    private View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            myView.twovar++;
        }
    };

    /*
    private View.OnGenericMotionListener myMotion = new View.OnGenericMotionListener() {
        @Override
        public boolean onGenericMotion(View v, MotionEvent event) {
            return false;
        }
    };
    /*
    */
}
