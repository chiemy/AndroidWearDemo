package com.chiemy.androidweardemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.CardFrame;
import android.support.wearable.view.CardScrollView;
import android.view.Gravity;

import com.chiemy.androidweardemo.R;

/**
 * Created by chiemy on 15/11/16.
 */
public class CardFrameActivity extends Activity{
    private float factor = 0.5f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_frame);

        final CardScrollView cardScrollView = (CardScrollView) findViewById(R.id.card_scroll_view);
        cardScrollView.setCardGravity(Gravity.BOTTOM);
        cardScrollView.setExpansionDirection(CardFrame.EXPAND_DOWN);
        cardScrollView.setExpansionEnabled(true);
        cardScrollView.setExpansionFactor(2);
    }
}
