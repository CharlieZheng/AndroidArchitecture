package com.iousave.www.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.iousave.www.R;
import com.iousave.www.util.DisplayParams;

import java.util.regex.Pattern;

// Original name is CodeType
public class VerificationCodeEditText extends android.support.v7.widget.AppCompatEditText {

    //    private static final Pattern KEYCODE_PATTERN = Pattern.compile("KEYCODE_(\\d)");
    private static final Pattern KEYCODE_PATTERN = Pattern.compile("(\\d)");
    private static final int MAX_SIZE = 6;
    private final RectF rectF = new RectF();
    private final Context context;
    private final float RATIO_DEFAULT;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Callback callback;
    private int paintColor;

    public VerificationCodeEditText(Context context) {
        super(context);
        this.context = context;
        RATIO_DEFAULT = DisplayParams.getInstance(context).getWidthRatio();
        init(context);
    }

    public VerificationCodeEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        RATIO_DEFAULT = DisplayParams.getInstance(context).getWidthRatio();
        init(context);
    }

    public VerificationCodeEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        RATIO_DEFAULT = DisplayParams.getInstance(context).getWidthRatio();
        init(context);
    }

    private void init(final Context context) {
        setFocusable(true);
        setFocusableInTouchMode(true);
        paintColor = ContextCompat.getColor(context, R.color.btn_text);
        setFocusable(true);
        setEnabled(true);
        setClickable(true);
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                paintColor = ContextCompat.getColor(context, R.color.btn_text);
                if (getText().length() == MAX_SIZE) {
                    if (callback != null)
                        callback.onInputFinish(VerificationCodeEditText.this, getText().toString());
                } else if (getText().length() > MAX_SIZE) {
                    getText().delete(MAX_SIZE, getText().length());
                } else if (getText().length() == 0) {
                    paintColor = ContextCompat.getColor(context, R.color.room_number_enter_pressed);

                } else if (getText().length() < MAX_SIZE) {

                    if (callback != null)
                        callback.onInput(VerificationCodeEditText.this, s.toString().charAt(s.length() - 1));
                }

                postInvalidate();
            }
        };
        addTextChangedListener(watcher);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        float l = getPaddingLeft() + (getWidth() - getPaddingLeft() - getPaddingRight() - 240 * RATIO_DEFAULT) / 2f;
        float t = getPaddingTop() + (getHeight() - getPaddingTop() - getPaddingBottom() - 40 * RATIO_DEFAULT) / 2f;
        float r = getWidth() - getPaddingRight() - (getWidth() - getPaddingLeft() - getPaddingRight() - 240 * RATIO_DEFAULT) / 2f;
        float b = getHeight() - getPaddingBottom() - (getHeight() - getPaddingTop() - getPaddingBottom() - 40 * RATIO_DEFAULT) / 2f;
        rectF.set(l, t, r, b);
        paint.setStrokeWidth(RATIO_DEFAULT);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getPaintColor());
        canvas.drawRoundRect(rectF, 10 * RATIO_DEFAULT, 10 * RATIO_DEFAULT, paint);
        float itemWidth = 40 * RATIO_DEFAULT;
        for (int i = 1; i <= 5; i++) {
            canvas.drawLine(l + itemWidth * i, t, l + itemWidth * i, b, paint);
        }
        paint.setTextSize(30 * RATIO_DEFAULT);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < MAX_SIZE; i++) {
            if (i < getText().length()) {
                float textWidth = paint.measureText(String.valueOf(getText().charAt(i)));
                float x = l + itemWidth * i + (itemWidth - textWidth) / 2f;
                float y = getBaseLine((b + t) / 2f);
                canvas.drawText(String.valueOf(getText().charAt(i)), x, y, paint);
            }
        }
    }

    private int getPaintColor() {
        return paintColor;
    }

    private float getBaseLine(float textCenterY) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return textCenterY + (fontMetrics.descent - fontMetrics.ascent) / 2f - fontMetrics.descent;
    }

    public void clear() {
        getText().delete(0, getText().length());
        paintColor = ContextCompat.getColor(context, R.color.room_number_enter_pressed);
        if (callback != null) {
            callback.onInputEmpty(this);
        }

        invalidate();

    }


    public interface Callback {
        void onInput(VerificationCodeEditText verificationCodeEditText, char c);

        void onInputEmpty(VerificationCodeEditText verificationCodeEditText);

        void onInputFinish(VerificationCodeEditText verificationCodeEditText, String s);
    }
}
