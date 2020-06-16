package com.example.project1.viewCustom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;

import com.example.project1.R;
import com.example.project1.model.Category;
import com.example.project1.util.Util;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatTextView;

public class PercentageTextView extends AppCompatTextView {

    private List<Category> categoryList;
    private static final float STROKE_WIDTH = 10;

    public PercentageTextView(Context context) {
        super(context);
    }

    public PercentageTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentageTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float strokeWidth = Util.DpToPx(getContext(), STROKE_WIDTH);

        float top = strokeWidth;
        float left = strokeWidth;
        float bottom = canvas.getHeight() - strokeWidth;
        float right = canvas.getWidth() - strokeWidth;

        RectF rect = new RectF(left, top, right, bottom);
        Paint paint = new Paint();

        int color = getContext().getResources().getColor(R.color.BlueColor);

        paint.setColor(color);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(16);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);

        canvas.drawOval(rect, paint);

//        int foregorundColor = getContext().getResources().getColor(R.color.RedColor);
//        paint.setColor(foregorundColor);

//        String text = getText().toString();
//        int percentage = Integer.parseInt(text);
        int i = 0;
        float sweepAngle = 0;
        float startAngle = 270;
        int categorySum = 0;
        int color1 = getContext().getResources().getColor(R.color.BlackColor);
        int color2 = getContext().getResources().getColor(R.color.YellowColor);
        int color3 = getContext().getResources().getColor(R.color.PurpleColor);
        int color4 = getContext().getResources().getColor(R.color.RedColor);
        int color5 = getContext().getResources().getColor(R.color.GrayColor);
        int color6 = getContext().getResources().getColor(R.color.GreenColor);
        int color7 = getContext().getResources().getColor(R.color.DarkBlue);
        int colors[] = {color1, color2, color3, color4, color5,color6,color7};

        for (Category c : categoryList) {
            categorySum += c.getSum();
        }

        for (Category c : categoryList) {
            paint.setColor(colors[i++]);
//            Log.d("Cat","name is: "+c.getName());
            if (c.getSum() != 0) {
//                Log.d("Category: ","sum je: "+c.getSum());
                sweepAngle = ((float)c.getSum() / categorySum) * 360;
//                Log.d("Sweep: ","angle je: "+sweepAngle);
            } else {
                sweepAngle = 0;
            }

            canvas.drawArc(rect, startAngle, sweepAngle, false, paint);
            startAngle += sweepAngle;

        }


    }
}
