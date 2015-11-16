package com.chiemy.androidweardemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chiemy.androidweardemo.R;

/**
 * Created by chiemy on 15/11/16.
 */
public class CardActivity extends Activity {
    private static final String []DATAS = {"CardFragmentActivity", "CardFrameActivity"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        WearableListView listView = (WearableListView) findViewById(R.id.wearable_lv);
        listView.setAdapter(new MyAdapter(this));
        listView.setClickListener(new WearableListView.ClickListener() {
            @Override
            public void onClick(WearableListView.ViewHolder viewHolder) {
                int position = (Integer)viewHolder.itemView.getTag();
                try {
                    Class<?> c = Class.forName("com.chiemy.androidweardemo.activity." + DATAS[position]);
                    Intent intent = new Intent(CardActivity.this, c);
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTopEmptyRegionClick() {

            }
        });
    }

    private static class MyAdapter extends WearableListView.Adapter{
        private LayoutInflater inflater;
        public MyAdapter(Context context){
            inflater = LayoutInflater.from(context);
        }

        private static class ItemViewHolder extends WearableListView.ViewHolder{

            private TextView nameTv;
            public ItemViewHolder(View itemView) {
                super(itemView);
                nameTv = (TextView) itemView.findViewById(R.id.name);
            }
        }

        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ItemViewHolder(inflater.inflate(R.layout.layout_wearable_card_list_item, null));
        }

        @Override
        public void onBindViewHolder(WearableListView.ViewHolder holder, int position) {
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
            viewHolder.nameTv.setText(DATAS[position]);
            viewHolder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
            return DATAS.length;
        }
    }
}
