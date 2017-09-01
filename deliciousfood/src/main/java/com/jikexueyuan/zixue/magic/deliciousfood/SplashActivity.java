package com.jikexueyuan.zixue.magic.deliciousfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.iv_bg_splash)
    ImageView ivBgSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startMainActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivBgSplash.setAnimation(animation);

    }

    private void startMainActivity() {
        //开启主界面
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //关闭引导界面
        finish();
        //动态设置界面转场效果
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}
