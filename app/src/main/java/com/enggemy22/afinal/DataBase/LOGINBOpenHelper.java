package com.enggemy22.afinal.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class LOGINDBOpenHelper extends SQLiteOpenHelper {
    final static String DATABASE_NAME="login.db";
    final static int DATABASE_VERSION=1;
    protected final static String TABLE01_CREATE="CREATE TABLE \""+LOGINContract.TABLE_NAME_01+"\"(\""+LOGINContract.COLUM_FULL_NAME+
            "\"TEXT NOT NULL,\""+LOGINContract.COLUM_USER_NAME+"\"TEXT NOT NULL PRIMARY KEY,\""+LOGINContract.COLUM_PASSWORD+
            "\"TEXT NOT NULL,\""+LOGINContract.COLUM_NUMBER+"\"INTEGER NOT NULL,\""+LOGINContract.COLUM_GENDER+"\"TEXT NOT NULL)";

    public LOGINDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE01_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LOGINContract.TABLE_NAME_01);
        onCreate(sqLiteDatabase);
    }
}


