package com.owen.fragmentbasic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.owen.fragmentbasic.Fragment1;
import com.owen.fragmentbasic.Fragment2;
import com.owen.fragmentbasic.R;
import com.owen.fragmentbasic.utils.GenericUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;

public class ActivityA extends AppCompatActivity {

    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.button2)
    Button mButton2;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        ButterKnife.bind(this);

        mFragmentManager = getSupportFragmentManager();
    }

    @OnClick({R.id.button, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                Fragment fragment = new Fragment1();
                transaction.add(R.id.fragment_container, fragment);
                transaction.commit();
                break;
            case R.id.button2:
                FragmentTransaction transaction2 = mFragmentManager.beginTransaction();
                Fragment fragment2 = new Fragment2();
                transaction2.add(R.id.fragment_container, fragment2);
                transaction2.commit();
                break;
        }

        if (mFragmentManager.getFragments() != null) {
            /*if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(this, "FragmentManager.getFragments() = " + mFragmentManager.getFragments().size(), Toast.LENGTH_SHORT);
            mToast.show();*/
            GenericUtils.showToast("FragmentManager.getFragments() = " + mFragmentManager.getFragments().size());
        }
    }



}
