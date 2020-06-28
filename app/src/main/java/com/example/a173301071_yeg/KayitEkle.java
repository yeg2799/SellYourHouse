package com.example.a173301071_yeg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class KayitEkle extends AppCompatActivity {
    EditText Baslik;
    EditText MülkTipi;
    EditText Adres;
    EditText Ucret;
    EditText OdaSayisi;
    EditText Kat;
    String id;
    TextView ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ekle);
        ID=findViewById(R.id.id1);


        Intent al=getIntent();
        id =al.getStringExtra("id");
        ID.setText(id);
    }

    public void EvKayit(View view)
    {

                Baslik = findViewById(R.id.BaslikGiris);
                MülkTipi = findViewById(R.id.MülkTipiGiris);
                Adres = findViewById(R.id.AdresGiris);
                Ucret = findViewById(R.id.UcretGiris);
                OdaSayisi = findViewById(R.id.OdaGiris);
                Kat = findViewById(R.id.KatGiris);

                String IdStr=ID.getText().toString();
                String Baslikstr = Baslik.getText().toString();
                String MülkTipistr = MülkTipi.getText().toString();
                String Adresstr = Adres.getText().toString();
                String Ucretstr = Ucret.getText().toString();
                String Odasayisistr = OdaSayisi.getText().toString();
                String Katstr = Kat.getText().toString();


                DatabaseYardimcisi databaseYardimcisi = new DatabaseYardimcisi(getApplicationContext());
                Evler evler=new Evler();

                evler.setKullaniciId(IdStr);
                evler.setBaslik(Baslikstr);
                evler.setMülkTipi(MülkTipistr);
                evler.setAdres(Adresstr);
                evler.setUcret(Ucretstr);
                evler.setOdaSayisi(Odasayisistr);
                evler.setKat(Katstr);


                long id= databaseYardimcisi.EvEkle(evler);

            if (id>0) {
                Toast.makeText(getApplicationContext(), "Kayıt Başarıyla Oluşturuldu.."+IdStr, Toast.LENGTH_LONG).show();
                Baslik.setText("");
                MülkTipi.setText("");
                Adres.setText("");
                Ucret.setText("");
                OdaSayisi.setText("");
                Kat.setText("");

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Kayıt Başarısız..", Toast.LENGTH_LONG).show();
            }


    }

}