package com.wangpan.school.templateapp.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.TextUtils;

import com.ping.greendao.gen.DaoMaster;
import com.ping.greendao.gen.DaoSession;
import com.wangpan.school.templateapp.BuildConfig;

import java.io.File;

public class Dbinitia {
    public static String dbName = "im.db";
    public static String sessionId = "234234234234444";
    private static DaoSession daoSession;
    private static Dbinitia dbinitia;
    private static Context context;
    private Dbinitia(){
        setupDatabase();
    }
    public static DaoSession getDaoInstant(Context contxt){
        if(dbinitia == null || daoSession == null) {
            synchronized (Dbinitia.class) {
                context = contxt;
                dbinitia = new Dbinitia();
            }
        }
        return daoSession;
    }

    /**
     * 配置数据库
     */
    private void setupDatabase(){
        MySqliteOpenHelper helper = null;
        if(BuildConfig.DEBUG) {
            String dbname = getDBName();
            helper = new MySqliteOpenHelper(context, dbname, null);
        } else {
            helper = new MySqliteOpenHelper(context, sessionId+dbName, null);
        }
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private String getDBName() {
        String dbDir = getSDPath()+"/"+"dbstorage";
        if (TextUtils.isEmpty(getSDPath())){
            return context.getDatabasePath(sessionId+dbName).getAbsolutePath();
        }
        File baseFile = new File(dbDir);
        if (!baseFile.exists()){
            baseFile.mkdirs();
        }
        File db = new File(baseFile,sessionId+dbName);
        return db.getAbsolutePath();
    }
    public String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);//判断sd卡是否存在
        if(sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }

}
