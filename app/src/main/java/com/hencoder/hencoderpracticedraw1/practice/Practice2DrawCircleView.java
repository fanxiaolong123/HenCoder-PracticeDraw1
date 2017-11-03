package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;

import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {

    private Paint mPaint;

    public Practice2DrawCircleView(Context context) {
        this(context, null);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
        // 1. 实心圆
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(350, 155, 150, mPaint);

        // 2. 空心圆
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        canvas.drawCircle(700, 155, 150, mPaint);

        // 3. 蓝色实心圆
        mPaint.setColor(Color.parseColor("#aa0000ff"));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(350, 505, 150, mPaint);

        // 4. 线宽为 20 的空心圆
        mPaint.setColor(Color.argb(255, 0, 0, 0));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dip2px(20));
        canvas.drawCircle(700, 505, 150, mPaint);
    }


    public int dip2px(int dpValue){
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }
}
