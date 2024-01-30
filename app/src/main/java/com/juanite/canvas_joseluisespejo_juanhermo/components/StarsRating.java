package com.juanite.canvas_joseluisespejo_juanhermo.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class StarsRating extends View {

    private int numStars = 5;
    private int rating = 0;
    private boolean[] filledStars;

    private Paint paint;

    public StarsRating(Context context) {
        super(context);
        init();
    }

    public StarsRating(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        filledStars = new boolean[numStars];
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int starWidth = getWidth() / numStars;

        for (int i = 0; i < numStars; i++) {
            Path starPath = createStarPath(i * starWidth, 0, starWidth, getHeight());
            if (filledStars[i]) {
                canvas.drawPath(starPath, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float touchX = event.getX();
            int selectedStar = (int) (touchX / (getWidth() / numStars));

            for (int i = 0; i <= selectedStar; i++) {
                filledStars[i] = true;
            }

            rating = selectedStar + 1;
            invalidate();
        }

        return super.onTouchEvent(event);
    }

    private Path createStarPath(float startX, float startY, float width, float height) {
        Path path = new Path();

        path.moveTo(startX + width / 2, startY);
        path.lineTo(startX + width, startY + height);
        path.lineTo(startX, startY + height);
        path.close();
        return path;
    }
}
