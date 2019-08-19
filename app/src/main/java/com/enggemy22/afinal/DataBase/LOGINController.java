package com.enggemy22.afinal.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class LOGINController {
    LOGINDBOpenHelper helper;
    SQLiteDatabase database;

    public LOGINController(Context context) {
        helper=new LOGINDBOpenHelper(context);
    }
    public long insertAccount(String fullName, String UserName, String password, long number,String gender )
    {
        ContentValues values= new ContentValues();
        values.put(LOGINContract.COLUM_FULL_NAME,fullName);
        values.put(LOGINContract.COLUM_USER_NAME,UserName);
        values.put(LOGINContract.COLUM_PASSWORD,password);
        values.put(LOGINContract.COLUM_NUMBER,number);
        values.put(LOGINContract.COLUM_GENDER,gender);
        long m = database.insert(LOGINContract.TABLE_NAME_01,null,values);
        return m;
    }

    public long updateAccount(String userName,String fullName, String password, int number)
    {
        ContentValues values= new ContentValues();
        values.put(LOGINContract.COLUM_FULL_NAME,fullName);
        values.put(LOGINContract.COLUM_PASSWORD,password);
        values.put(LOGINContract.COLUM_NUMBER,number);
        long x= database.update(LOGINContract.TABLE_NAME_01,values,LOGINContract.COLUM_USER_NAME+"=?",new String[]{userName});
        return x;
    }

    public void delete(String userName)
    {
        database.delete(LOGINContract.TABLE_NAME_01,LOGINContract.COLUM_USER_NAME+"=?",new String[]{userName});

    }
    public boolean validation(String user,String pass){
        boolean flag=false;
        Cursor cursor=database.query(LOGINContract.TABLE_NAME_01,new String[]{"user_name","password"},null,
                null,null,null,null);
        while (cursor.moveToNext()){
            String USER=cursor.getString(0);
            String PASS=cursor.getString(1);
            if (user.equals(USER)&&pass.equals(PASS)){
                flag=true;
            }
        }
        return flag;
    }
    public AccountClass getAccount(String user){
        AccountClass account=new AccountClass();
        Cursor cursor=database.query(LOGINContract.TABLE_NAME_01,new String[]{LOGINContract.COLUM_FULL_NAME,LOGINContract.COLUM_PASSWORD
                        ,LOGINContract.COLUM_NUMBER,LOGINContract.COLUM_GENDER},LOGINContract.COLUM_USER_NAME+"=?",
                new String[]{user},null,null,null);
        cursor.moveToNext();
        if(cursor!=null) {
            account.setFullName(cursor.getString(0));
            account.setPassword(cursor.getString(1));
            account.setNumber(cursor.getInt(2));
            account.setGender(cursor.getString(3));
        }
        return account;

    }

    public void openDB(){
        database=helper.getWritableDatabase();
    }
    public void closeDB(){
        helper.close();
    }
}

