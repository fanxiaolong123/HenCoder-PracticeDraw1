package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形

        RectF oval = new RectF(getWidth() / 2 - 200, getHeight() / 2 - 100, getWidth() / 2 + 200,
                getHeight() / 2 + 100);

        // 弧线
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, -180, 60, false, mPaint);

        // 弧形
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(oval, 15, 150, false, mPaint);

        // 扇形
        canvas.drawArc(oval, -15, -95, true, mPaint);
    }
}
