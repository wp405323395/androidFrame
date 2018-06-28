package com.wangpan.school.templateapp;

import android.app.Application;
import com.ping.greendao.gen.DaoSession;
import com.wangpan.school.templateapp.greendao.Dbinitia;

public class MyApplication extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        daoSession = Dbinitia.getDaoInstant(this);
    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }

}
