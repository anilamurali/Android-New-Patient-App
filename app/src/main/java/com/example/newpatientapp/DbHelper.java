package com.example.newpatientapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static  String Dbname="PatientApp.db";
    static String PatientTable="patient";
    static String col1="id";
    static String col2="Ptcode";
    static String col3="Ptname";
    static String col4="Address";
    static String col5="mobile";
    static String col6="drname";
    public DbHelper(Context context) { super(context, Dbname, null, 1); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="create table "+PatientTable+"("+col1+" integer primary key autoincrement,"
                +col2+" text,"+col3+" text,"+col4+" text,"+col5+" text,"+col6+" text)";
        sqLiteDatabase.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query="drop table if exists "+PatientTable;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);

    }
    public  boolean insertPatient(String pcode,String pname,String paddress,String pmoblie,String doctername)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        ContentValues content=new ContentValues();
        content.put(col2,pcode);
        content.put(col3,pname);
        content.put(col4,paddress);
        content.put(col5,pmoblie);
        content.put(col6,doctername);
        long status=sqLiteDatabase.insert(PatientTable,null,content);
        if (status==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
