package com.example.a173301071_yeg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProfilDuzenle extends AppCompatActivity {

    EditText AdSoyad;
    EditText KullaniciAdi;
    EditText Sifre;
    EditText TelefonNo;
    String id1;
    TextView Id;
    Kullanicilar kullanicilar;
    DatabaseYardimcisi databaseYardimcisi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_duzenle);

        AdSoyad = findViewById(R.id.adsoyadgiris);
        KullaniciAdi = findViewById(R.id.kullaniciAdigiris);
        Sifre = findViewById(R.id.sifreGiris);
        TelefonNo = findViewById(R.id.telefonnogiris);
        Id=findViewById(R.id.Id1);

        Intent al=getIntent();
        id1=al.getStringExtra("id");
        Id.setText("Id:"+id1);


        databaseYardimcisi=new DatabaseYardimcisi(getApplicationContext());

        List<String> vVeriler= databaseYardimcisi.KullaniciKayitAl(id1);
        AdSoyad.setText(vVeriler.get(0));
        KullaniciAdi.setText(vVeriler.get(1));
        Sifre.setText(vVeriler.get(2));
        TelefonNo.setText(vVeriler.get(3));
    }

   public void BtnUpdate(View view) {


                 String Adsoyadstr = AdSoyad.getText().toString();
                 String KullaniciAdistr = KullaniciAdi.getText().toString();
                 String Sifrestr = Sifre.getText().toString();
                 String TelefonNostr = TelefonNo.getText().toString();



            DatabaseYardimcisi databaseYardimcisi = new DatabaseYardimcisi(getApplicationContext());
            kullanicilar = new Kullanicilar();

            kullanicilar.setAdSoyad(Adsoyadstr);
            kullanicilar.setEmail(KullaniciAdistr);
            kullanicilar.setSifre(Sifrestr);
            kullanicilar.setTelefonNo(TelefonNostr);

            long id = databaseYardimcisi.KullaniciUpdate(kullanicilar,id1);

            if (id > 0)
                 {
                Toast.makeText(getApplicationContext(), "Kayıt Başarıyla Güncellendi..", Toast.LENGTH_SHORT).show();
                     AdSoyad.setText("");
                     KullaniciAdi.setText("");
                     Sifre.setText("");
                     TelefonNo.setText("");
                 }
            else
                {
                Toast.makeText(getApplicationContext(), "Kayıt Güncellenemedi..", Toast.LENGTH_LONG).show();
                }

            Toast.makeText(getApplicationContext(), "Cıkış Yapıldı Tekrar Giriş Yapınız..", Toast.LENGTH_SHORT).show();
            Intent cikis=new Intent(ProfilDuzenle.this,MainActivity.class);
            startActivity(cikis);
        }
    }
