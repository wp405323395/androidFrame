package com.wangpan.school.templateapp.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ping.greendao.gen.DaoMaster;

public class MySqliteOpenHelper extends DaoMaster.OpenHelper {
    public MySqliteOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MySqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        db.execSQL("drop table "+ "CONTACTS_PERSON");
        db.execSQL("drop table "+ "CONTACTS_GROUP");
        db.execSQL("drop table "+ "DIALOG");
        db.execSQL("drop table "+ "DIALOG_TO_PERSON");
        db.execSQL("drop table "+ "MESSAGE_INFO");
        onCreate(db);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }
}
