package com.juanite.canvas_joseluisespejo_juanhermo.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
public class StarsRating extends View {

    private int numStars = 5;
    private int rating = 0;
    private boolean[] filledStars;

    private Paint paint;
    private int starSize;

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
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.YELLOW);

        // Tamaño de la estrella es 1/10 del ancho de la pantalla
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        starSize = screenWidth / 10;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int spaceBetweenStars = getWidth() / (numStars + 1);

        for (int i = 0; i < numStars; i++) {
            int starX = (i + 1) * spaceBetweenStars - starSize / 2;
            int starY = getHeight() / 2 - starSize / 2;
            Path starPath = createStarPath(starX, starY, starSize);
            if (filledStars[i]) {
                canvas.drawPath(starPath, paint);
            } else {
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawPath(starPath, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float touchX = event.getX();

            // Calcular el espacio entre estrellas y la estrella seleccionada
            int spaceBetweenStars = getWidth() / (numStars + 1);
            int selectedStar = (int) (touchX / spaceBetweenStars);

            // Verificar si el toque está dentro de una estrella no coloreada
            if (selectedStar >= 0 && selectedStar < numStars && !filledStars[selectedStar]) {
                // Colorear la estrella seleccionada y las de su izquierda
                for (int i = selectedStar; i >= 0; i--) {
                    filledStars[i] = true;
                }
                invalidate();
                return true;
            }
        }

        return super.onTouchEvent(event);
    }



    private Path createStarPath(float startX, float startY, float size) {
        Path path = new Path();

        float halfWidth = size / 2;
        float internalAngle = 144;
        float externalAngle = 72;

        path.moveTo(startX + halfWidth, startY);

        for (int i = 1; i <= 5; i++) {
            float angle = (float) (Math.PI / 180 * (i * internalAngle));
            path.lineTo(startX + halfWidth + (float) Math.sin(angle) * halfWidth, startY + halfWidth - (float) Math.cos(angle) * halfWidth);
            angle = (float) (Math.PI / 180 * (i * internalAngle + externalAngle));
            path.lineTo(startX + halfWidth + (float) Math.sin(angle) * halfWidth / 2, startY + halfWidth - (float) Math.cos(angle) * halfWidth / 2);
        }

        path.close();

        return path;
    }
}

