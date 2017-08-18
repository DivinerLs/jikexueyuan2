package com.jikexueyuan.zixue.magic.framelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mErrorView;
    private FrameLayout mContentView;
    private Button btn_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContentView = (FrameLayout) findViewById(R.id.fm_contentPanel);
        mErrorView = (LinearLayout) findViewById(R.id.fm_error_view);
        btn_test = (Button) findViewById(R.id.btn_test);

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //一开始将ErrorView隐藏掉
                mErrorView.setVisibility(View.VISIBLE);
            }
        });


    }



    //Resume:重现开始
    @Override
    protected void onResume() {
        super.onResume();
        //网络的加载  Loading...
        //mErrorView.setVisibility(View.VISIBLE);
    }
}
