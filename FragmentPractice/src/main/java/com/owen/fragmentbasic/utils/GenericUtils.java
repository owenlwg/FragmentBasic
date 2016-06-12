package com.owen.fragmentbasic.utils;

import android.content.Context;
import android.widget.Toast;

import com.owen.fragmentbasic.App;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
public class GenericUtils {

    private static Toast mToast;

    public static void showToast(String text) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(App.getInstance().getApplicationContext(), text, Toast.LENGTH_SHORT);
        mToast.show();
    }

}
