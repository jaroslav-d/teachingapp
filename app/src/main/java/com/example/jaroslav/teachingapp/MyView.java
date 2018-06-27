package com.example.jaroslav.teachingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.graphics.RectF;
import android.view.View;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by 808310 on 28.05.2018.
 */

public class MyView extends View {
    int val = 0;
    double axisY = 0;
    boolean firstCreate = true;
    Thread mThread;
    Handler mHandler;
    float var = 0;
    int twovar = 0;
    String touch = "default";
    String xyztext = "default";
    float actionDownX;
    float actionDownY;
    ClosedArray loopArray;

    MyView (Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF myRect = new RectF(10,10,200,380);
        RectF twoRect = new RectF(200,200,400,400);
        Paint myPaint = new Paint();
        Path myPath = new Path();
        Float fAxisX = new Float(Math.cos(axisY)*200);
        Float fAxisY = new Float(Math.sin(axisY)*200);
        myPath.addArc(myRect,0,val);
        myPath.addArc(twoRect,10,360);
        myPaint.setARGB(255, 255, 0, 0);
        myPath.offset(fAxisX+200,fAxisY+400);
        canvas.drawPath(myPath,myPaint);
        if (val < 360) {
            val++;
        }
        if (axisY > 24*Math.PI) {
            axisY = 0;
        }
        String text = "//" + var;
        canvas.drawText(text,10,10,myPaint);
        String xytext = "//" + twovar;
        canvas.drawText(xytext,10,20,myPaint);
        canvas.drawText(xyztext,10,30,myPaint);
        axisY = axisY + 0.08;
        if (firstCreate) {
            mThread = new Thread() {

                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            };
            mHandler = new Handler() {
                @Override
                public void publish(LogRecord record) {

                }

                @Override
                public void flush() {
                    post(mThread);
                }

                @Override
                public void close() throws SecurityException {

                }
            };
            loopArray = new ClosedArray(10);
            firstCreate = false;
        }

        myPaint.setARGB(255, 0, 0, 0);
        float[] pts = {
                10,120,50,90,
                10,120,50,150,
                50,90,50,150
        };
        for (int i = 0; i < 4; i++) {
            canvas.drawLines(pts,myPaint);
            canvas.rotate(90,90,120);
        }

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        Path triangle = new Path();
        triangle.setFillType(Path.FillType.EVEN_ODD);
        triangle.moveTo(10,120);
        triangle.lineTo(50,90);
        triangle.lineTo(50,150);
        triangle.lineTo(10,120);
        triangle.close();
        String arrow = touch;
        switch (arrow) {
            case "up":
                canvas.rotate(90,90,120);
                canvas.drawPath(triangle,paint);
                canvas.rotate(-90,90,120);
                break;
            case "down":
                canvas.rotate(-90,90,120);
                canvas.drawPath(triangle,paint);
                canvas.rotate(90,90,120);
                break;
            case "left":
                canvas.drawPath(triangle,paint);
                break;
            case "right":
                canvas.rotate(180,90,120);
                canvas.drawPath(triangle,paint);
                canvas.rotate(-180,90,120);
                break;
        }
        Picture myPicture = new Picture();
        canvas.drawPicture(myPicture);

        loopArray.add();
        loopArray.add();
        loopArray.add();
        loopArray.add();
        loopArray.up();
        loopArray.up();
        loopArray.add();
        loopArray.up();
        loopArray.up();
        loopArray.up();
        loopArray.add();
        loopArray.add();
        loopArray.up();
        loopArray.up();
        loopArray.up();
        loopArray.add();
        loopArray.add();
        loopArray.up();

        mHandler.flush();
        mThread.start();
    }

    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
    */
}
