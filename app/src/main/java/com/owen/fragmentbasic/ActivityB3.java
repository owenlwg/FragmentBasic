package com.owen.fragmentbasic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.owen.fragmentbasic.utils.GenericUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * show() hide()和addToBackStack
 */
public class ActivityB3 extends AppCompatActivity {


    @BindView(R.id.tv_fragments)
    TextView mTvFragments;
    @BindView(R.id.tv_backstack)
    TextView mTvBackstate;

    private FragmentManager mFragmentManager;
    private StringBuilder mString;
    FragmentManager.OnBackStackChangedListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b3);
        ButterKnife.bind(this);

        mFragmentManager = getSupportFragmentManager();
        mListener = new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                displayBackStack();
                displayFragments();
            }
        };
        mFragmentManager.addOnBackStackChangedListener(mListener);
    }

    /**
     * button1-4 addFragment
     * button5:hide fragment3  button6:show fragment3
     * button7:hide fragment2  button8:show fragment2
     */
    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8})
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
                hideFragment(Fragment3.class.getSimpleName());
                break;
            case R.id.button6:
                showFragment(Fragment3.class.getSimpleName());
                break;
            case R.id.button7:
                hideFragment(Fragment2.class.getSimpleName());
                break;
            case R.id.button8:
                showFragment(Fragment2.class.getSimpleName());
                break;
        }

        displayFragments();
        displayBackStack();
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

    private void displayBackStack() {
        int count = mFragmentManager.getBackStackEntryCount();
        if (count == 0) {
            return;
        }
        mString = new StringBuilder();
        mString.append("回退栈内容:\n");
        mString.append("count:" + count + "\n");
        for (int i = 0; i < count; i++) {
            FragmentManager.BackStackEntry entry = mFragmentManager.getBackStackEntryAt(i);
            if (entry == null) continue;
            mString.append(entry.getName());
            mString.append("\n");
        }

        mTvBackstate.setText(mString);
    }

    private void addFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragmentByTag = mFragmentManager.findFragmentByTag(tag);
        if (fragmentByTag == null) {
            transaction.add(R.id.fragment_container, fragment, tag);
            transaction.addToBackStack(tag);
            transaction.commit();
            GenericUtils.showToast("add fragment " + tag);
        }
    }

    private void showFragment(String tag) {
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.show(fragment);
            transaction.commit();
        }
    }

    private void hideFragment(String tag) {
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.hide(fragment);
            transaction.commit();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragmentManager.removeOnBackStackChangedListener(mListener);
    }
}
