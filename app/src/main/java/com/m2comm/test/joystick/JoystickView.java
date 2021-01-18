package com.m2comm.test.joystick;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class JoystickView extends View {
    Paint mBackgroundPaint = new Paint();
    Paint mJoystickPaint = new Paint();
    float mx = 100;
    float my = 100;

    //코드상으로 호출.
    public JoystickView(Context context) {
        this(context,null);
    }

    //xml로 할때.
    public JoystickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBackgroundPaint.setColor(Color.BLACK);
        mBackgroundPaint.setStyle(Paint.Style.STROKE);
        mJoystickPaint.setColor(Color.RED);
    }

    //View 모양 결정
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(100,100,100,mBackgroundPaint);
        canvas.drawCircle(mx,my,50,mJoystickPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch( event.getAction() ) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                final int historySize = event.getHistorySize();
                final int pointerCount = event.getPointerCount();

                for ( int h = 0; h < historySize; h++ ) {
                    for ( int p = 0 ; p < pointerCount; p++ ) {
                        mx = event.getHistoricalX(p,h);
                        my = event.getHistoricalY(p,h);
                        if ( Math.sqrt(Math.pow(100 - mx , 2) + Math.sqrt(Math.pow(100 - my , 2) )) > 50) {
                            return true;
                        }
                        invalidate();
                    }
                }


                break;

            case MotionEvent.ACTION_UP:
                mx = 100;
                my = 100;
                break;

        }

        //다시 onDraw실행.
        invalidate();
        return true;
    }

    //크기 결정
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //위치 결정.
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


}
