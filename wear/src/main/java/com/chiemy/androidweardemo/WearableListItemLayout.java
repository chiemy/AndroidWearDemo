package com.chiemy.androidweardemo;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by chiemy on 15/11/16.
 */
public class WearableListItemLayout extends LinearLayout implements WearableListView.OnCenterProximityListener{
    private ImageView iconIv;
    private TextView nameTv;
    private int mFadedCircleColor, mChosenCircleColor;
    public WearableListItemLayout(Context context) {
        this(context, null);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WearableListItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFadedCircleColor = getResources().getColor(R.color.grey);
        mChosenCircleColor = getResources().getColor(R.color.blue);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        iconIv = (ImageView) findViewById(R.id.circle);
        nameTv = (TextView) findViewById(R.id.name);
    }

    @Override
    public void onCenterPosition(boolean b) {
        nameTv.setAlpha(1f);
        ((GradientDrawable) iconIv.getDrawable()).setColor(mChosenCircleColor);
    }

    @Override
    public void onNonCenterPosition(boolean b) {
        ((GradientDrawable) iconIv.getDrawable()).setColor(mFadedCircleColor);
        nameTv.setAlpha(0.4f);
    }
}
