package com.owen.fragmentbasic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivityB extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);
        ButterKnife.bind(this);

        mFragmentManager = getSupportFragmentManager();
    }


    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                startActivity(new Intent(this, ActivityB1.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, ActivityB2.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, ActivityB3.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this, ActivityB4.class));
                break;
            case R.id.button5:
                break;
        }
    }

}
