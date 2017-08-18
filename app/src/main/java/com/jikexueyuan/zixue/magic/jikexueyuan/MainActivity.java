package com.jikexueyuan.zixue.magic.jikexueyuan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView mImage_cat;
    private ImageButton mImage_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImage_cat = (ImageView) findViewById(R.id.image_cat);
        mImage_Button = (ImageButton) findViewById(R.id.image_button);

        mImage_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "再点我报警了", Toast.LENGTH_SHORT).show();
            }
        });

        mImage_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "给你我的小心心", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
