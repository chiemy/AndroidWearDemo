package com.chiemy.androidweardemo.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;

import com.chiemy.androidweardemo.R;

/**
 * Created by chiemy on 15/11/16.
 */
public class CardFragmentActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_fragment);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        CardFragment cardFragment = CardFragment.create("This is Title", "Description\nThis is a CardFragment demo!!");
        transaction.add(R.id.frame_layout, cardFragment).commit();
    }
}
