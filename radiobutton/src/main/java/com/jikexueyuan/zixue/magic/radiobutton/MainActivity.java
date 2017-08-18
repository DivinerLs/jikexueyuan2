package com.jikexueyuan.zixue.magic.radiobutton;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RadioGroup mRadioGroup;
    private RadioButton mRadio_very_spicy,mRadio_spicy,mRedio_lttle_spicy,mRadio_no_spicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        int buttonId = mRadioGroup.getCheckedRadioButtonId();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                String text = "";

                switch (checkedId){
                    case R.id.radio_very_spicy:
                        text = "变态辣";
                        break;

                    case R.id.radio_spicy:
                        text = "中辣";
                        break;

                    case R.id.radio_lttle_spicy:
                        text = "微辣";
                        break;

                    case R.id.radio_no_spicy:
                        text = "不要辣";
                        break;

                    default:
                        break;
                }
                Log.d(TAG,text);
            }
        });

        mRadioGroup.check(R.id.radio_no_spicy);//默认选中 [方式1]
        mRadio_no_spicy.setChecked(true);//默认选中 [方式2]
    }

    public void init() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadio_very_spicy = (RadioButton) findViewById(R.id.radio_very_spicy);
        mRadio_spicy = (RadioButton) findViewById(R.id.radio_spicy);
        mRedio_lttle_spicy = (RadioButton) findViewById(R.id.radio_lttle_spicy);
        mRadio_no_spicy = (RadioButton) findViewById(R.id.radio_no_spicy);
    }
}
