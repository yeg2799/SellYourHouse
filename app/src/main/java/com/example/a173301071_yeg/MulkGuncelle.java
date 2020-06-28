package com.example.a173301071_yeg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MulkGuncelle extends AppCompatActivity {
    EditText Baslik;
    EditText MülkTipi;
    EditText Adres;
    EditText Ucret;
    EditText OdaSayisi;
    EditText Kat;
    String id;
    TextView ID;
    DatabaseYardimcisi databaseYardimcisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulk_guncelle);

        ID=findViewById(R.id.id1);
        Baslik = findViewById(R.id.BaslikGiris);
        MülkTipi = findViewById(R.id.MülkTipiGiris);
        Adres = findViewById(R.id.AdresGiris);
        Ucret = findViewById(R.id.UcretGiris);
        OdaSayisi = findViewById(R.id.OdaGiris);
        Kat = findViewById(R.id.KatGiris);

        Intent al=getIntent();
        id =al.getStringExtra("id");
        ID.setText(id);

        databaseYardimcisi=new DatabaseYardimcisi(getApplicationContext());

        List<String> vVeriler= databaseYardimcisi.MülkKayitAl(id);
        Baslik.setText(vVeriler.get(0));
        MülkTipi.setText(vVeriler.get(1));
        Adres.setText(vVeriler.get(2));
        Ucret.setText(vVeriler.get(3));
        OdaSayisi.setText(vVeriler.get(4));
        Kat.setText(vVeriler.get(5));
    }
    public void BtnUpdate(View view) {


        String Baslikstr = Baslik.getText().toString();
        String Mülktipistr = MülkTipi.getText().toString();
        String Adresstr = Adres.getText().toString();
        String Ucretstr = Ucret.getText().toString();
        String Odasayisistr = OdaSayisi.getText().toString();
        String Katstr=Kat.getText().toString();



            DatabaseYardimcisi databaseYardimcisi = new DatabaseYardimcisi(getApplicationContext());
            Evler evler = new Evler();
            evler.setBaslik(Baslikstr);
            evler.setMülkTipi(Mülktipistr);
            evler.setAdres(Adresstr);
            evler.setUcret(Ucretstr);
            evler.setOdaSayisi(Odasayisistr);
            evler.setKat(Katstr);

            long a = databaseYardimcisi.MülkGüncelle(evler,id);

            if (a> 0)
            {
                Toast.makeText(getApplicationContext(), "Mülk Başarıyla Güncellendi..", Toast.LENGTH_SHORT).show();
                Baslik.setText("");
                MülkTipi.setText("");
                Adres.setText("");
                Ucret.setText("");
                OdaSayisi.setText("");
                Kat.setText("");
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Mülkü Güncelleme Yetkiniz yoktur..", Toast.LENGTH_LONG).show();
            }
        }
}