package com.chiemy.androidweardemo;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.util.Date;

/**
 * Created by chiemy on 15/11/24.
 */
public class DataItemsDemo extends ActionBarActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private static final String COUNT_KEY = "com.example.key.count";
    private static final String COUNT_KEY1 = "com.example.key.count1";

    private GoogleApiClient mGoogleApiClient;
    private TextView numTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataitems);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(Wearable.API)
                .build();

        numTv = (TextView) findViewById(R.id.tv_num);
        findViewById(R.id.btn_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseCounter();
            }
        });
    }

    private int count = 1;
    private void increaseCounter() {
        // 1.创建PutDataMapRequest对象，指定path
        PutDataMapRequest putDataMapReq = PutDataMapRequest.create("/count");
        // 2.获取DataMap
        final DataMap map = putDataMapReq.getDataMap();
        // 3.使用put…方法，设置键值。
        map.putLong("date", new Date().getTime());
        map.putInt(COUNT_KEY, count++);
        map.putString(COUNT_KEY1, "" + count++);
        // 4.调用PutDataMapRequest.asPutDataRequest()方法获取PutDataRequest对象
        PutDataRequest putDataReq = putDataMapReq.asPutDataRequest();
        putDataReq.setUrgent();
        // 5.调用DataApi.putDataItem()方法创建data item
        PendingResult pendingResult = Wearable.DataApi.putDataItem(mGoogleApiClient, putDataReq);
        pendingResult.setResultCallback(new ResultCallback() {
            @Override
            public void onResult(Result result) {
                if(result.getStatus().isSuccess()){
                    numTv.setText(count + "");
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("", "onStart");
        mGoogleApiClient.connect();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (mResolvingError) {
            // Already attempting to resolve an error.
            return;
        } else if (result.hasResolution()) {
            try {
                mResolvingError = true;
                result.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);
            } catch (IntentSender.SendIntentException e) {
                // There was an error with the resolution intent. Try again.
                mGoogleApiClient.connect();
            }
        } else {
            // Show dialog using GoogleApiAvailability.getErrorDialog()
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this, 0).show();
            mResolvingError = true;
        }

        switch(result.getErrorCode()){
            case ConnectionResult.SERVICE_MISSING:
                numTv.setText("请安装Google Play services");
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                numTv.setText("请升级Google Play services");
                break;
            default:
                numTv.setText(result.getErrorMessage());
                break;
        }
    }

    private static final int REQUEST_RESOLVE_ERROR = 1001;
    private boolean mResolvingError;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_RESOLVE_ERROR) {
            mResolvingError = false;
            if (resultCode == RESULT_OK) {
                // Make sure the app is not already connected or attempting to connect
                if (!mGoogleApiClient.isConnecting() &&
                        !mGoogleApiClient.isConnected()) {
                    mGoogleApiClient.connect();
                }
            }
        }
    }
}
