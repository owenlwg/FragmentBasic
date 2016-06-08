package com.owen.fragmentbasic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.owen.fragmentbasic.utils.GenericUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * popBackStack
 */
public class ActivityB2 extends AppCompatActivity {


    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2);
        ButterKnife.bind(this);

        mFragmentManager = getSupportFragmentManager();
    }

    /**
     * button6 backToFragment2_0, button7 backToFragment2_INCLUSIVE
     */
    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Fragment fragment1 = new Fragment1();
                addFragment(fragment1, fragment1.getClass().getSimpleName());
                break;
            case R.id.button2:
                Fragment fragment2 = new Fragment2();
                addFragment(fragment2, Fragment2.class.getSimpleName());
                break;
            case R.id.button3:
                Fragment fragment3 = new Fragment3();
                addFragment(fragment3, Fragment3.class.getSimpleName());
                break;
            case R.id.button4:
                Fragment fragment4 = new Fragment4();
                addFragment(fragment4, Fragment4.class.getSimpleName());
                break;
            case R.id.button5:
                mFragmentManager.popBackStack();
                break;
            case R.id.button6:
                mFragmentManager.popBackStack(Fragment2.class.getSimpleName(), 0);
                break;
            case R.id.button7:
                mFragmentManager.popBackStack(Fragment2.class.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
        }
    }

    private void addFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
        GenericUtils.showToast("add fragment " + tag);
    }
}
