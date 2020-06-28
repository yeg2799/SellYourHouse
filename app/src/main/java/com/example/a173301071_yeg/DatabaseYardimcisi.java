package com.example.a173301071_yeg;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DatabaseYardimcisi extends SQLiteOpenHelper {

    private static  final int Versiyon=5;
    private  static final String Veritabanı_Adi="Ev.db";

    //Kullanıcı Tablosu
    private  static final String Tablo_Adi="tbl_kullanici";
    private  static final String Sütun_Id="id";
    private  static final String Sütun_AdSoyad="Adsoyad";
    private  static final String Sütun_Mail="Email";
    private  static final String Sütun_Parola="Sifre";
    private  static final String Sütun_Tel="TelefonNo";


    //Mülk Tablosu
    private  static final String tbl_adi="tbl_evkayit";
    private  static final String sütunid="id";
    private  static final String sütun_kullaniciid="KullaniciId";
    private  static final String sütun_baslik="Baslik";
    private  static final String sütun_mülktipi="MülkTipi";
    private  static final String sütun_adres="Adres";
    private  static final String sütun_ucret="Ucret";
    private  static final String sütun_oda="OdaSayisi";
    private  static final String sütun_kat="Kat";


    SQLiteDatabase database;
    public  DatabaseYardimcisi(Context context)
    {
        super(context,Veritabanı_Adi,null,Versiyon);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sorgu1="CREATE TABLE "+ Tablo_Adi+"(id INTEGER PRIMARY KEY AUTOINCREMENT,Adsoyad TEXT NOT NULL,Email TEXT NOT NULL,Sifre TEXT NOT NULL,TelefonNo TEXT NOT NULL)";
        String sorgu2="CREATE TABLE "+ tbl_adi+"(id INTEGER PRIMARY KEY AUTOINCREMENT,KullaniciId TEXT NOT NULL,Baslik TEXT NOT NULL,MülkTipi TEXT NOT NULL,Adres TEXT NOT NULL,Ucret TEXT NOT NULL,OdaSayisi TEXT NOT NULL,Kat TEXT NOT NULL)";
        db.execSQL(sorgu1);
        db.execSQL(sorgu2);
        this.database=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sorgu1="DROP TABLE IF EXISTS "+Tablo_Adi;
        String sorgu2="DROP TABLE IF EXISTS "+tbl_adi;
        db.execSQL(sorgu1);
        db.execSQL(sorgu2);

    }
    //Kullanıcı Ekleme Fonksiyonu
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
    //Kullanıcı Güncelleme Fonksiyonu
   public long KullaniciUpdate(Kullanicilar kullanicilar,String id1)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Sütun_AdSoyad,kullanicilar.getAdSoyad());
        contentValues.put(Sütun_Mail,kullanicilar.getEmail());
        contentValues.put(Sütun_Parola,kullanicilar.getSifre());
        contentValues.put(Sütun_Tel,kullanicilar.getTelefonNo());

        long id= db.update(Tablo_Adi,contentValues,Sütun_Id+"=?",new String[]{String.valueOf(id1)});

        return id;
    }
    //Kullanıcı Silme Fonksiyonu
    public long KullaniciSil(Kullanicilar kullanicilar,String id1)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long id= db.delete(Tablo_Adi,Sütun_Id+"=?",new String[]{String.valueOf(id1)});
        return id;
    }

    //Mülk Silme Fonkisyonu
    public long MülkSil(Evler evler,String id1)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        long id= db.delete(tbl_adi,sütun_kullaniciid+"=?",new String[]{String.valueOf(id1)});
        return id;
    }

    //Mülk Ekleme Fonksiyonu
    public long EvEkle(Evler evler)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put(sütun_kullaniciid,evler.getKullaniciId());
        contentValues.put(sütun_baslik,evler.getBaslik());
        contentValues.put(sütun_mülktipi,evler.getMülkTipi());
        contentValues.put(sütun_adres,evler.getAdres());
        contentValues.put(sütun_ucret,evler.getUcret());
        contentValues.put(sütun_oda,evler.getOdaSayisi());
        contentValues.put(sütun_kat,evler.getKat());
        long id= db.insert(tbl_adi,null,contentValues);

        return id;
    }
    //Mülk Güncelleme Fonksiyonu
    public long MülkGüncelle(Evler evler,String id1)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(sütun_baslik,evler.getBaslik());
        contentValues.put(sütun_mülktipi,evler.getMülkTipi());
        contentValues.put(sütun_adres,evler.getAdres());
        contentValues.put(sütun_ucret,evler.getUcret());
        contentValues.put(sütun_oda,evler.getOdaSayisi());
        contentValues.put(sütun_kat,evler.getKat());

        long id= db.update(tbl_adi,contentValues,sütun_kullaniciid+"=?",new String[]{String.valueOf(id1)});

        return id;
    }

    //id alma
    public String Idsiralama(String KullaniciAd)
    {
        database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM "+Tablo_Adi,null);

        String cursorid,cursorkullanici;
        cursorid="0";
        if (cursor.moveToFirst())
        {
            do {
                cursorkullanici=cursor.getString(2);
                if (cursorkullanici.equals(KullaniciAd))
                {
                    cursorid=cursor.getString(0);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return  cursorid;
    }

    //Kullanıcı Kayıtlarını Database gönderme
    public List<String> KullaniciKayitAl(String id)
    {
        List<String> veriler=new ArrayList<String>();
        database=this.getReadableDatabase();
        String[] sütunlar={Sütun_AdSoyad,Sütun_Mail,Sütun_Parola,Sütun_Tel};
        Cursor cursor=database.query(Tablo_Adi,null,null,null,null,null,null);
        String cursorid;
        cursorid="0";
     if (cursor.moveToFirst())
        {
          do {
              cursorid=cursor.getString(cursor.getColumnIndex(Sütun_Id));
           if (cursorid.equals(id))
              {

                    veriler.add(cursor.getString(cursor.getColumnIndex(Sütun_AdSoyad)));
                    veriler.add(cursor.getString(cursor.getColumnIndex(Sütun_Mail)));
                    veriler.add(cursor.getString(cursor.getColumnIndex(Sütun_Parola)));
                    veriler.add(cursor.getString(cursor.getColumnIndex(Sütun_Tel)));

                break;
               }
           }

           while (cursor.moveToNext());
    }
     return veriler;
    }
    //Güncellemek istediğin Mülklerin Kayitları Alınıyor
    public List<String> MülkKayitAl(String id)
    {
        List<String> veriler=new ArrayList<String>();
        database=this.getReadableDatabase();
        String[] sütunlar={sütun_baslik,sütun_mülktipi,sütun_adres,sütun_ucret,sütun_oda,sütun_kat};
        Cursor cursor=database.query(tbl_adi,null,null,null,null,null,null);
        String cursorid;
        cursorid="0";
        if (cursor.moveToFirst())
        {
            do {
                cursorid=cursor.getString(cursor.getColumnIndex(sütun_kullaniciid));
                if (cursorid.equals(id))
                {

                    veriler.add(cursor.getString(cursor.getColumnIndex(sütun_baslik)));
                    veriler.add(cursor.getString(cursor.getColumnIndex(sütun_mülktipi)));
                    veriler.add(cursor.getString(cursor.getColumnIndex(sütun_adres)));
                    veriler.add(cursor.getString(cursor.getColumnIndex(sütun_ucret)));
                    veriler.add(cursor.getString(cursor.getColumnIndex(sütun_oda)));
                    veriler.add(cursor.getString(cursor.getColumnIndex(sütun_kat)));

                    break;
                }
            }

            while (cursor.moveToNext());
        }
        return veriler;
    }
    //Mülkleri Listeleme fonksiyonu
    public  List<String> VeriListele()
    {
        List<String> veriler=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        String[] sütunlar={sütun_baslik,sütun_adres,sütun_ucret,sütun_oda,sütun_kat};
        Cursor cursor=db.query(tbl_adi,sütunlar,null,null,null,null,null);


        while (cursor.moveToNext())
        {

                veriler.add(cursor.getString(cursor.getColumnIndex(sütun_baslik))+" Adres:"+cursor.getString(cursor.getColumnIndex(sütun_adres))+" Fiyat:"+cursor.getString(cursor.getColumnIndex(sütun_ucret))+"TL "+" Oda Sayisi:"+cursor.getString(cursor.getColumnIndex(sütun_oda))+" Kat Sayisi:"+cursor.getString(cursor.getColumnIndex(sütun_kat)));

        }
        return  veriler;
    }





    //Kullanıcı Kontrol Fonksiyonu
    public  String kullaniciadkontrol(String email)
    {
        database=this.getReadableDatabase();

        Cursor cursor=database.rawQuery("SELECT * FROM "+Tablo_Adi,null);
        String cursorkullanici;
        cursorkullanici="Bulunamadı";
        if (cursor.moveToFirst())
        {
            do {
                cursorkullanici=cursor.getString(2);
                if (cursorkullanici.equals(email))
                {
                    cursorkullanici="Hata";
                    break;
                }
                else
                {
                    cursorkullanici=cursor.getString(2);
                }
            }
            while(cursor.moveToNext());
        }

        return cursorkullanici;
    }
    //parolakontrol edilirken buraya gelir
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
