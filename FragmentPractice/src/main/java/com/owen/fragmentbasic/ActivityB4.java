package com.owen.fragmentbasic;

import android.os.Bundle;
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

public class ActivityB4 extends AppCompatActivity {


    @BindView(R.id.tv_isadded)
    TextView mTvIsadded;
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
        setContentView(R.layout.activity_b4);
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
     * button9:detach fragment3  button10:attach fragment3
     * button11 12:detach attach fragment2
     */
    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button9, R.id.button10, R.id.button11, R.id.button12})
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
            case R.id.button9:
                detachFragment(Fragment3.class.getSimpleName());
                break;
            case R.id.button10:
                attachFragment(Fragment3.class.getSimpleName());
                break;
            case R.id.button11:
                detachFragment(Fragment2.class.getSimpleName());
                break;
            case R.id.button12:
                attachFragment(Fragment2.class.getSimpleName());
                break;
        }

        displayFragments();
        displayBackStack();
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

    /**
     * 将Fragment移出container的add队列，并销毁fragment视图，FragmentManager.findFragmentByTag(tag)返回的此Fragment不为空
     */
    private void detachFragment(String tag) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragmentByTag = mFragmentManager.findFragmentByTag(tag);
        if (fragmentByTag != null) {
            transaction.detach(fragmentByTag);
            transaction.commit();
        }
    }

    /**
     * 创建Fragment视图并将Fragment加入container(Activity)的add队列
     */
    private void attachFragment(String tag) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        Fragment fragmentByTag = mFragmentManager.findFragmentByTag(tag);
        if (fragmentByTag != null) {
            transaction.attach(fragmentByTag);
            transaction.commit();
        }
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
        mTvFragments.invalidate();
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
        mTvBackstate.invalidate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragmentManager.removeOnBackStackChangedListener(mListener);
    }
}
