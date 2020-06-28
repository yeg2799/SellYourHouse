package com.example.a173301071_yeg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.CharArrayWriter;

public class MainActivity extends AppCompatActivity {
    EditText kullaniciadi;
    EditText parola;
    DatabaseYardimcisi yardimci=new DatabaseYardimcisi(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //Kullanıcı Adı ve Parola Girince databaseden kontrol edip doğruysa anasayfaya gidiyor
    public void GirisYap(View view)
    {
        kullaniciadi=findViewById(R.id.kullanicigiris);
        String kullanicistr=kullaniciadi.getText().toString();

        parola=findViewById(R.id.parolagiris);
        String parolastr=parola.getText().toString();

        String parolaa=yardimci.ParolaKontrol(kullanicistr);
        if (parolastr.equals(parolaa))
        {

            Intent Giris=new Intent(MainActivity.this,AnaSayfa.class);
            Giris.putExtra("kullanici",kullanicistr);
            startActivity(Giris);

        }
        else
        {
            Toast.makeText(MainActivity.this,"Kullanıcı Adıyla Parola Eşleşmiyor Lütfen Kontrol Ediniz.",Toast.LENGTH_LONG).show();

        }
    }

    //Kayıt olma alanına gider
    public void Kayitol(View view)
    {
        Intent kayit=new Intent(MainActivity.this,KayitOlEkran.class);
        startActivity(kayit);

    }
}