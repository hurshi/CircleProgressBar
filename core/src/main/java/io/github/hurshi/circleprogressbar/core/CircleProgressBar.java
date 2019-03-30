package io.github.hurshi.circleprogressbar.core;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class CircleProgressBar extends AppCompatImageView {
    private int processHighlightColor;
    private float processHighlightStrokeWidth;
    private int processBackgroundColor;
    private float processBackgroundWidth;
    private float pauseSizePercent;

    private int max;
    private int progress;
    private int offsetStartAngle;

    private Paint paint = new Paint();
    private RectF highlightRect;
    private RectF backgroundRect;
    private RectF pauseRect;

    private int processGravity;
    private boolean roundCorner;
    private boolean showImg = false;


    public CircleProgressBar(Context context) {
        this(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        processHighlightColor = typedArray.getColor(R.styleable.CircleProgressBar_circle_foreground_color, Color.RED);
        processHighlightStrokeWidth = typedArray.getDimension(R.styleable.CircleProgressBar_circle_foreground_width, 5);
        processBackgroundColor = typedArray.getColor(R.styleable.CircleProgressBar_circle_background_color, Color.GRAY);
        processBackgroundWidth = typedArray.getDimension(R.styleable.CircleProgressBar_circle_background_width, 5);
        pauseSizePercent = typedArray.getFloat(R.styleable.CircleProgressBar_circle_pause_size_percent, 0f);
        max = typedArray.getInteger(R.styleable.CircleProgressBar_circle_max, 100);
        progress = typedArray.getInt(R.styleable.CircleProgressBar_circle_process, 0);
        offsetStartAngle = typedArray.getInt(R.styleable.CircleProgressBar_circle_offset_start_angle, 0);
        processGravity = typedArray.getInteger(R.styleable.CircleProgressBar_circle_gravity, Gravity.CENTER);
        roundCorner = typedArray.getBoolean(R.styleable.CircleProgressBar_circle_round_corner, true);

        typedArray.recycle();

        paint.setAntiAlias(true);
        paint.setStyle(Style.STROKE);
        setRoundCorner(roundCorner);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (showImg) {
            super.onDraw(canvas);
            return;
        }
        tryInitOval();

        paint.setStyle(Style.STROKE);

        //背景图
        paint.setStrokeWidth(processBackgroundWidth);
        paint.setColor(processBackgroundColor);
        canvas.drawOval(backgroundRect, paint);

        //画圆弧,圆环的进度
        paint.setStrokeWidth(processHighlightStrokeWidth);
        paint.setColor(processHighlightColor);
        canvas.drawArc(highlightRect, -90.0f + offsetStartAngle, 360.0f * progress / max, false, paint);

        if (null != pauseRect) {
            paint.setStyle(Style.FILL);
            canvas.drawRect(pauseRect, paint);
        }

    }

    private void tryInitOval() {
        if (null == highlightRect || null == backgroundRect) {
            highlightRect = new RectF();
            backgroundRect = new RectF();

            float foregroundRadius;
            float backgroundRadius;
            float size = Math.min(getWidth() - getPaddingLeft() - getPaddingRight(), getHeight() - getPaddingTop() - getPaddingBottom());

            switch (processGravity) {
                case Gravity.INNER: {
                    backgroundRadius = (size - processBackgroundWidth) / 2;
                    foregroundRadius = backgroundRadius - (processBackgroundWidth + processHighlightStrokeWidth) / 2;
                    break;
                }
                case Gravity.OUTTER: {
                    foregroundRadius = (size - processHighlightStrokeWidth) / 2;
                    backgroundRadius = foregroundRadius - (processBackgroundWidth + processHighlightStrokeWidth) / 2;
                    break;
                }
                default: {//Gravity.CENTER
                    float baseRadius = (size - Math.max(processHighlightStrokeWidth, processBackgroundWidth)) / 2;
                    foregroundRadius = baseRadius;
                    backgroundRadius = baseRadius;
                }
            }
            highlightRect.set(getPivotX() - foregroundRadius, getPivotY() - foregroundRadius, getPivotX() + foregroundRadius, getPivotY() + foregroundRadius);
            backgroundRect.set(getPivotX() - backgroundRadius, getPivotY() - backgroundRadius, getPivotX() + backgroundRadius, getPivotY() + backgroundRadius);

            if (pauseSizePercent > 0) {
                pauseRect = new RectF();
                float halfPauseWidth = (size * pauseSizePercent) / 2;
                pauseRect.set(getPivotX() - halfPauseWidth, getPivotY() - halfPauseWidth, getPivotX() + halfPauseWidth, getPivotY() + halfPauseWidth);
            }
        }
    }

    public int getMax() {
        return max;
    }

    public synchronized void setMax(int max) {
        if (max < 0) throw new IllegalArgumentException("max not less than 0");
        this.max = max;
    }

    public int getProgress() {
        return progress;
    }

    public synchronized void setProgress(int progress) {
        if (showImg) showImg = false;
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        this.progress = progress;
        postInvalidate(); // 能在非UI线程刷新
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        this.showImg = true;
        super.setImageBitmap(bm);
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        this.showImg = true;
        super.setImageDrawable(drawable);
    }

    @Override
    public void setImageResource(int resId) {
        this.showImg = true;
        super.setImageResource(resId);
    }

    public void setProcessHighlightColor(int processHighlightColor) {
        this.processHighlightColor = processHighlightColor;
    }

    public void setProcessHighlightStrokeWidth(float processHighlightStrokeWidth) {
        this.processHighlightStrokeWidth = processHighlightStrokeWidth;
    }

    public void setProcessBackgroundColor(int processBackgroundColor) {
        this.processBackgroundColor = processBackgroundColor;
    }

    public void setProcessBackgroundWidth(float processBackgroundWidth) {
        this.processBackgroundWidth = processBackgroundWidth;
    }

    public void setProcessGravity(int processGravity) {
        this.processGravity = processGravity;
    }

    public void setRoundCorner(boolean roundCorner) {
        this.roundCorner = roundCorner;
        if (roundCorner) {
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeJoin(Paint.Join.ROUND);
        } else {
            paint.setStrokeCap(Paint.Cap.BUTT);
            paint.setStrokeJoin(Paint.Join.MITER);
        }
    }

    public void setOffsetStartAngle(int value) {
        this.offsetStartAngle = value;
    }

    public class Gravity {
        public static final int CENTER = 0;
        public static final int INNER = 1;
        public static final int OUTTER = 2;
    }

}
