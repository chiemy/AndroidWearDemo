package com.chiemy.androidweardemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.chiemy.androidweardemo.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.Wearable;

/**
 * Created by chiemy on 15/11/24.
 */
public class ListenData extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener , DataApi.DataListener{

    private static final String COUNT_KEY = "com.example.key.count";
    private static final String COUNT_KEY1 = "com.example.key.count1";

    private GoogleApiClient mGoogleApiClient;

    private TextView numTv;
    private TextView flowTv;
    private StringBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_data);
        builder = new StringBuilder();
        numTv = (TextView) findViewById(R.id.tv_num);
        flowTv = (TextView) findViewById(R.id.tv_flow);
        flowTv.setMovementMethod(ScrollingMovementMethod.getInstance());

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }


    @Override
    protected void onStop() {
        super.onStop();
        Wearable.DataApi.removeListener(mGoogleApiClient, this);
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        print("onConnected()");
        Wearable.DataApi.addListener(mGoogleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        print("onConnectionFailed() " + connectionResult.getErrorMessage());
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        print("onDataChanged()");
        for (DataEvent event : dataEventBuffer) {
            if (event.getType() == DataEvent.TYPE_CHANGED) {
                // DataItem changed
                DataItem item = event.getDataItem();
                if (item.getUri().getPath().compareTo("/count") == 0) {
                    DataMap dataMap = DataMapItem.fromDataItem(item).getDataMap();
                    updateCount(dataMap.getInt(COUNT_KEY), dataMap.getString(COUNT_KEY1));
                }
            } else if (event.getType() == DataEvent.TYPE_DELETED) {
                // DataItem deleted
            }
        }
    }

    private void updateCount(int c, String s) {
        numTv.setText("" + c + ", s = " + s);
        print("updateCount = " + c);
    }

    private void print(String append){
        builder.append(append + "\n");
        flowTv.setText(builder.toString());
    }
}
