/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package com.luckcome.settings.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.luckcome.settings.R;
import com.luckcome.settings.Utils;

import java.util.Locale;

/**
 * DonutView represents a donut graph. It visualizes a certain percentage of fullness with a
 * corresponding label with the fullness on the inside (i.e. "50%" inside of the donut).
 */
public class DonutView extends View {
    private static final int TOP = -90;
    // From manual testing, this is the longest we can go without visual errors.
    private static final int LINE_CHARACTER_LIMIT = 10;
    private float mStrokeWidth;
    private double mPercent;
    private Paint mBackgroundCircle;
    private Paint mFilledArc;
    private TextPaint mTextPaint;
    private TextPaint mBigNumberPaint;
    private String mPercentString;
    private String mFullString;
    private boolean mShowPercentString = true;
    private int mMeterBackgroundColor;
    private int mMeterConsumedColor;

    public DonutView(Context context) {
        super(context);
    }

    public DonutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mMeterBackgroundColor = context.getColor(R.color.meter_background_color);
        mMeterConsumedColor = Utils.getDefaultColor(mContext, R.color.meter_consumed_color);
        boolean applyColorAccent = true;
        Resources resources = context.getResources();
        mStrokeWidth = resources.getDimension(R.dimen.storage_donut_thickness);

        if (attrs != null) {
            TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.DonutView);
            mMeterBackgroundColor = styledAttrs.getColor(R.styleable.DonutView_meterBackgroundColor,
                    mMeterBackgroundColor);
            mMeterConsumedColor = styledAttrs.getColor(R.styleable.DonutView_meterConsumedColor,
                    mMeterConsumedColor);
            applyColorAccent = styledAttrs.getBoolean(R.styleable.DonutView_applyColorAccent,
                    true);
            mShowPercentString = styledAttrs.getBoolean(R.styleable.DonutView_showPercentString,
                    true);
            mStrokeWidth = styledAttrs.getDimensionPixelSize(R.styleable.DonutView_thickness,
                    (int) mStrokeWidth);
            styledAttrs.recycle();
        }

        mBackgroundCircle = new Paint();
        mBackgroundCircle.setAntiAlias(true);
        mBackgroundCircle.setStrokeCap(Paint.Cap.BUTT);
        mBackgroundCircle.setStyle(Paint.Style.STROKE);
        mBackgroundCircle.setStrokeWidth(mStrokeWidth);
        mBackgroundCircle.setColor(mMeterBackgroundColor);

        mFilledArc = new Paint();
        mFilledArc.setAntiAlias(true);
        mFilledArc.setStrokeCap(Paint.Cap.BUTT);
        mFilledArc.setStyle(Paint.Style.STROKE);
        mFilledArc.setStrokeWidth(mStrokeWidth);
        mFilledArc.setColor(mMeterConsumedColor);

        if (applyColorAccent) {
            final ColorFilter mAccentColorFilter =
                    new PorterDuffColorFilter(
                            Utils.getColorAttr(context, android.R.attr.colorAccent),
                            PorterDuff.Mode.SRC_IN);
            mBackgroundCircle.setColorFilter(mAccentColorFilter);
            mFilledArc.setColorFilter(mAccentColorFilter);
        }

        final Locale locale = resources.getConfiguration().locale;
        final int layoutDirection = TextUtils.getLayoutDirectionFromLocale(locale);
        final int bidiFlags = (layoutDirection == LAYOUT_DIRECTION_LTR)
                ? Paint.BIDI_LTR
                : Paint.BIDI_RTL;

        mTextPaint = new TextPaint();
        mTextPaint.setColor(Utils.getColorAccent(getContext()));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(
                resources.getDimension(R.dimen.storage_donut_view_label_text_size));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setBidiFlags(bidiFlags);

        mBigNumberPaint = new TextPaint();
        mBigNumberPaint.setColor(Utils.getColorAccent(getContext()));
        mBigNumberPaint.setAntiAlias(true);
        mBigNumberPaint.setTextSize(
                resources.getDimension(R.dimen.storage_donut_view_percent_text_size));
        mBigNumberPaint.setTextAlign(Paint.Align.CENTER);
        mBigNumberPaint.setTypeface(Typeface.create(
                context.getString(com.android.internal.R.string.config_headlineFontFamily),
                Typeface.NORMAL));
        mBigNumberPaint.setBidiFlags(bidiFlags);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDonut(canvas);
        if (mShowPercentString) {
            drawInnerText(canvas);
        }
    }

    private void drawDonut(Canvas canvas) {
        canvas.drawArc(
                0 + mStrokeWidth,
                0 + mStrokeWidth,
                getWidth() - mStrokeWidth,
                getHeight() - mStrokeWidth,
                TOP,
                360,
                false,
                mBackgroundCircle);

        canvas.drawArc(
                0 + mStrokeWidth,
                0 + mStrokeWidth,
                getWidth() - mStrokeWidth,
                getHeight() - mStrokeWidth,
                TOP,
                (360 *  (float) mPercent),
                false,
                mFilledArc);
    }

    private void drawInnerText(Canvas canvas) {
        final float centerX = getWidth() / 2;
        final float centerY = getHeight() / 2;
        final float totalHeight = getTextHeight(mTextPaint) + getTextHeight(mBigNumberPaint);
        final float startY = centerY + totalHeight / 2;

        // The first line is the height of the bottom text + its descender above the bottom line.
        canvas.drawText(mPercentString, centerX,
                startY - getTextHeight(mTextPaint) - mBigNumberPaint.descent(),
                mBigNumberPaint);
        // The second line starts at the bottom + room for the descender.
        canvas.drawText(mFullString, centerX, startY - mTextPaint.descent(), mTextPaint);
    }

    /**
     * Set a percentage full to have the donut graph.
     */
    public void setPercentage(double percent) {
        mPercent = percent;
        mPercentString = Utils.formatPercentage(mPercent);
        mFullString = getContext().getString(R.string.storage_percent_full);
        if (mFullString.length() > LINE_CHARACTER_LIMIT) {
            mTextPaint.setTextSize(
                    getContext()
                            .getResources()
                            .getDimension(
                                    R.dimen.storage_donut_view_shrunken_label_text_size));
        }
        invalidate();
    }

    @ColorRes
    public int getMeterBackgroundColor() {
        return mMeterBackgroundColor;
    }

    public void setMeterBackgroundColor(@ColorRes int meterBackgroundColor) {
        mMeterBackgroundColor = meterBackgroundColor;
        mBackgroundCircle.setColor(meterBackgroundColor);
        invalidate();
    }

    @ColorRes
    public int getMeterConsumedColor() {
        return mMeterConsumedColor;
    }

    public void setMeterConsumedColor(@ColorRes int meterConsumedColor) {
        mMeterConsumedColor = meterConsumedColor;
        mFilledArc.setColor(meterConsumedColor);
        invalidate();
    }

    private float getTextHeight(TextPaint paint) {
        // Technically, this should be the cap height, but I can live with the descent - ascent.
        return paint.descent() - paint.ascent();
    }
}
