package com.camel.camellibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.orhanobut.logger.Logger;
import org.camel.utilslibrary.PhoneUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.t(TAG).e("获取到的系统版本为：  " + PhoneUtils.getSystemVersion());
        Logger.t(TAG).e("获取内核版本：  " + PhoneUtils.getKernelVersion());
        Logger.t(TAG).e("获取基带版本：  " + PhoneUtils.getBaseBandVersion());
    }
}
