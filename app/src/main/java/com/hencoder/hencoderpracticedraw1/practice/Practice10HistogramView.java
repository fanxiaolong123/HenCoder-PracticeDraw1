package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        // 画 x, y 轴
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        Path path = new Path();
        path.moveTo(100, 20);
        path.lineTo(100, 550);
        path.lineTo(970, 550);
        canvas.drawPath(path, mPaint);

        // 画直方形
        mPaint.setStyle(Paint.Style.FILL);

        int rectMargin = 20;
        int rectWidth = (970 - 100 - rectMargin * 8) / 7;

        for (int i = 0; i < 7; i++) {
            String text = "ABC";
            int top = 550;
            switch (i) {
                case 0:
                    top -= 5;
                    text = "Froyo";
                    break;
                case 1:
                    top -= 20;
                    text = "GB";
                    break;
                case 2:
                    text = "ICS";
                    top -= 20;
                    break;
                case 3:
                    text = "JB";
                    top -= 180;
                    break;
                case 4:
                    text = "KitKat";
                    top -= 300;
                    break;
                case 5:
                    text = "L";
                    top -= 400;
                    break;
                case 6:
                    text = "M";
                    top -= 150;
                    break;
            }
            int left = 100 + rectMargin * (i + 1) + rectWidth * i;
            int right = left + rectWidth;
            int bottom = 550;
            mPaint.setColor(Color.parseColor("#cc00ff00"));
            canvas.drawRect(new Rect(left, top, right, bottom), mPaint);

            // 画文字
            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(30);
            Rect bounds = new Rect();
            // 获取文字的宽
            mPaint.getTextBounds(text, 0, text.length(), bounds);
            int x = (int) (left + rectWidth / 2 - bounds.width() / 2);
            // 获取大写首字母的高
            mPaint.getTextBounds(text.substring(0, 1), 0, 1, bounds);
            int y = (int) (bottom + bounds.height());
            canvas.drawText(text, x, y, mPaint);

        }

        // 画底部标题
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(45);
        String titleText = "直方图";
        Rect bounds = new Rect();
        mPaint.getTextBounds(titleText, 0, titleText.length(), bounds);
        int x = getWidth() / 2 - bounds.width() / 2;
        int y = getHeight() - 30;
        canvas.drawText(titleText, x, y, mPaint);

    }
}
