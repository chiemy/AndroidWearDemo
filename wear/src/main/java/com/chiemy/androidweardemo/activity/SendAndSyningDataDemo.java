package com.chiemy.androidweardemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;

import com.chiemy.androidweardemo.R;
import com.chiemy.androidweardemo.adapter.MyAdapter;

/**
 * 通讯示例
 * Created by chiemy on 15/11/24.
 */
public class SendAndSyningDataDemo extends Activity implements WearableListView.ClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_syning_data);
        WearableListView wearableLv = (WearableListView) findViewById(R.id.wearable_lv);
        String [] datas = {"ListenData"};
        MyAdapter adapter = new MyAdapter(SendAndSyningDataDemo.this, datas);
        wearableLv.setAdapter(adapter);
        wearableLv.setClickListener(SendAndSyningDataDemo.this);
    }

    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        Integer position = (Integer) viewHolder.itemView.getTag();
        switch (position){
            case 0:
                Intent intent = new Intent(this, ListenData.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTopEmptyRegionClick() {
    }
}
