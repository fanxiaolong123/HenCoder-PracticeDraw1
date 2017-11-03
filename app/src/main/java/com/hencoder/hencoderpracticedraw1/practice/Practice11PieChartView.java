package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {

    private Paint mPaint;

    private int count = 6;
    // 半径
    float ratio = 250;
    // 第一个圆距离左边的距离
    float oval1Left = 150;

    // 第二个圆的圆心坐标
    float ratioX = ratio + oval1Left + 15;
    float ratioY = ratio + 15;

    float line1 = 20;
    float line2 = 40;

    float namePadding = 10;

    RectF oval1 = new RectF(oval1Left, 0, oval1Left + ratio * 2, ratio * 2);
    RectF oval2 = new RectF(oval1Left + 10, 10, oval1Left + ratio * 2 + 10, ratio * 2 + 10);

    int[] colors = new int[]{Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.GRAY, Color
            .CYAN};


    static List<Float> angles;
    static List<String> names;

    static {
        angles = new ArrayList<>();
        angles.add(60f);
        angles.add(10f);
        angles.add(5f);
        angles.add(120f);
        angles.add(60f);
        angles.add(105f);

        names = new ArrayList<>();
        names.add("Lollipop");
        names.add("Marshmallow");
        names.add("Froyo");
        names.add("Glingerbread");
        names.add("Ice Cream Sandwich");
        names.add("Jelly Bean");
        names.add("KitKat");

    }

    private float mMaxAngle;
    private Path mPath;


    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // 把最大值放在第一位
        mMaxAngle = angles.get(0);
        for (int i = 0; i < angles.size() - 1; i++) {
            mMaxAngle = mMaxAngle > angles.get(i + 1) ? mMaxAngle : angles.get(i + 1);
        }
        if (mMaxAngle != angles.get(0)) {
            angles.remove(angles.indexOf(mMaxAngle));
            angles.add(0, mMaxAngle);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        // 画饼图

        float startAngle = -180;
        float sweepAngle = 0;

        for (int i = 0; i < angles.size(); i++) {
            // 扇形的角度
            sweepAngle = angles.get(i);

            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(colors[i]);

            // 标注线起点坐标
            float x;
            float y;

            // 标注线起点的角度
            float lineAngle = startAngle + (sweepAngle - 3) / 2;

            if (i == 0) {

                x = (float) ((ratio + oval1Left) + ratio * Math.cos(lineAngle * Math.PI / 180));
                y = (float) (ratio + ratio * Math.sin(lineAngle * Math.PI / 180));

                canvas.drawArc(oval1, startAngle, sweepAngle - 3, true, mPaint);
            } else {

                x = (float) ((ratio + oval1Left + 10) + ratio * Math.cos(lineAngle * Math.PI /
                        180));
                y = (float) ((ratio + 10) + ratio * Math.sin(lineAngle * Math.PI / 180));

                canvas.drawArc(oval2, startAngle, sweepAngle - 3, true, mPaint);
            }

            /**
             * 画标注的线和文字
             */
            // 画标注线
            mPaint.setColor(Color.WHITE);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(2);
            // 标注线的起点
            mPath = new Path();
            mPath.moveTo(x, y);

            float lineToX;
            float lineToY;
            float moveX = sweepAngle * 130 / 360 + line2;

            // 标注字
            float nameX;

            String name = names.get(i);
            Rect bounds = new Rect();
            mPaint.getTextBounds(name, 0, name.length(), bounds);

            if (x < ratioX && y < ratioY) {
                moveX = 0 - moveX;
                lineToX = x - line1;
                lineToY = y - line1;
                nameX = lineToX + moveX - namePadding - bounds.width();
            } else if (x > ratioX && y < ratioY) {
                lineToX = x + line1;
                lineToY = y - line1;
                nameX = lineToX + moveX + namePadding;
            } else if (x > ratioX && y > ratioY) {
                lineToX = x + line1;
                lineToY = y + line1;
                nameX = lineToX + moveX + namePadding;
            } else {
                moveX = 0 - moveX;
                lineToX = x - line1;
                lineToY = y + line1;
                nameX = lineToX + moveX - namePadding - bounds.width();
            }
            mPath.lineTo(lineToX, lineToY);
            mPath.lineTo(lineToX + moveX, lineToY);
            canvas.drawPath(mPath, mPaint);

            // 画标注的文字
            mPaint.setColor(Color.WHITE);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(1);
            mPaint.setTextSize(30);
//            mPaint.setTypeface(Typeface.DEFAULT_BOLD);
            canvas.drawText(name, nameX, lineToY, mPaint);
            startAngle += sweepAngle;
        }

        // 画大标题
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(50);
        String title = "圆饼图";
        Rect bounds = new Rect();
        mPaint.getTextBounds(title, 0, title.length(), bounds);
        canvas.drawText(title, getWidth() / 2 - bounds.width() / 2, getHeight() - 20, mPaint);

    }
}
