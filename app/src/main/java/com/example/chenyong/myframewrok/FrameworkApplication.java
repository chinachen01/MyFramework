package com.example.chenyong.myframewrok;

import android.app.Application;
import android.content.Context;

/**
 * Created by focus on 16/8/19.
 */
public class FrameworkApplication extends Application {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

}
