package com.owen.fragmentbasic;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.owen.fragmentbasic.utils.GenericUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * add() remove() replace
 */
public class ActivityB1 extends AppCompatActivity {

    @BindView(R.id.tv_fragments)
    TextView mTvFragments;

    private FragmentManager mFragmentManager;
    private StringBuilder mString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1);
        ButterKnife.bind(this);

        mFragmentManager = getSupportFragmentManager();
    }

    /**
     * add fragment1, add fragment2, remove fragment2, replace with fragment2
     */
    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Fragment fragment1 = new Fragment1();
                addFragment(fragment1, Fragment1.class.getSimpleName());
                break;
            case R.id.button2:
                Fragment fragment2 = new Fragment2();
                addFragment(fragment2, Fragment2.class.getSimpleName());
                break;
            case R.id.button3:
                removeFragment(Fragment2.class.getSimpleName());
                break;
            case R.id.button4:
                replaceFragment();
                break;
        }

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                displayFragments();
            }
        });

    }

    private void addFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment, tag);
        transaction.commit();
        GenericUtils.showToast("add fragment " + tag);
    }

    private void removeFragment(String tag) {
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }

    private void replaceFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragment2 = new Fragment2();
        transaction.replace(R.id.fragment_container, fragment2, Fragment2.class.getSimpleName());
        transaction.commit();
    }

    private void displayFragments() {
        List<Fragment> fragments = mFragmentManager.getFragments();
        if (fragments == null || fragments.size() == 0) {
            return;
        }
        mString = new StringBuilder();
        mString.append("FragmentManager.getFragments():\n");
        mString.append("size:" + fragments.size() + "\n");

        for ( Fragment fragment : fragments) {
            if (fragment == null) continue;
            mString.append(fragment.getClass().getSimpleName());
            mString.append("\n");
        }

        mTvFragments.setText(mString);
    }

    Handler mHandler = new Handler();
}
