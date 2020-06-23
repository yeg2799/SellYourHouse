package com.example.a173301071_yeg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class DatabaseYardimcisi extends SQLiteOpenHelper {

    private static  final int Versiyon=5;
    private  static final String Veritabanı_Adi="Ev.db";
    private  static final String Tablo_Adi="tbl_kullanici";
    private  static final String Sütun_Id="id";
    private  static final String Sütun_AdSoyad="Adsoyad";
    private  static final String Sütun_Mail="Email";
    private  static final String Sütun_Parola="Sifre";
    private  static final String Sütun_Tel="TelefonNo";

    SQLiteDatabase database;


    public  DatabaseYardimcisi(Context context)
    {
        super(context,Veritabanı_Adi,null,Versiyon);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+ Tablo_Adi+"(id INTEGER PRIMARY KEY AUTOINCREMENT,Adsoyad TEXT NOT NULL,Email TEXT NOT NULL,Sifre TEXT NOT NULL,TelefonNo TEXT NOT NULL)";
        db.execSQL(sql);
        this.database=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sorgu="DROP TABLE IF EXISTS "+Tablo_Adi;
        db.execSQL(sorgu);

    }
    public long kullaniciekle(Kullanicilar kullanicilar)
    {
       SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(Sütun_AdSoyad,kullanicilar.getAdSoyad());
        contentValues.put(Sütun_Mail,kullanicilar.getEmail());
        contentValues.put(Sütun_Parola,kullanicilar.getSifre());
        contentValues.put(Sütun_Tel,kullanicilar.getTelefonNo());

       long id= db.insert(Tablo_Adi,null,contentValues);

       return id;
    }
    public  String ParolaKontrol(String email)
    {
        database=this.getReadableDatabase();

        Cursor cursor=database.rawQuery("SELECT * FROM "+Tablo_Adi,null);
        String cursorkullanici,cursorparola;
        cursorparola="Bulunamadı";
        if (cursor.moveToFirst())
        {
            do {
                cursorkullanici=cursor.getString(2);
                if (cursorkullanici.equals(email))
                {
                    cursorparola=cursor.getString(3);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return cursorparola;
    }
    }
