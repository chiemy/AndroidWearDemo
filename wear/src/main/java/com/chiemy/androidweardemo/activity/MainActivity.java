package com.chiemy.androidweardemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.support.wearable.view.WearableListView;

import com.chiemy.androidweardemo.R;
import com.chiemy.androidweardemo.adapter.MyAdapter;

public class MainActivity extends Activity implements WearableListView.ClickListener{
    private static final String [] DATAS = {"CardActivity", "Send & Syn Data"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                WearableListView wearableLv = (WearableListView) stub.findViewById(R.id.wearable_lv);
                MyAdapter adapter = new MyAdapter(MainActivity.this, DATAS);
                wearableLv.setAdapter(adapter);
                wearableLv.setClickListener(MainActivity.this);
            }
        });
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        Integer position = (Integer) viewHolder.itemView.getTag();
        switch (position){
            case 0:
                Intent intent1 = new Intent(this, CardActivity.class);
                startActivity(intent1);
                break;
            case 1:
                Intent intent2 = new Intent(this, SendAndSyningDataDemo.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTopEmptyRegionClick() {

    }
}
