package com.chiemy.androidweardemo;

import android.content.Context;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by chiemy on 15/11/16.
 */
public class WearableCardListItemLayout extends LinearLayout implements WearableListView.OnCenterProximityListener{
    private TextView nameTv;

    public WearableCardListItemLayout(Context context) {
        this(context, null);
    }

    public WearableCardListItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WearableCardListItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        nameTv = (TextView) findViewById(R.id.name);
    }

    @Override
    public void onCenterPosition(boolean b) {
        nameTv.setAlpha(1);
    }

    @Override
    public void onNonCenterPosition(boolean b) {
        nameTv.setAlpha(0.4f);
    }
}
