package com.example.a173301071_yeg;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AnaSayfa extends AppCompatActivity {

    TextView isim;
    TextView Id;
    String id;
    String ad;
    ListView listView;
    DatabaseYardimcisi yardimcisi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        listView = findViewById(R.id.listview);
        isim =findViewById(R.id.KarsilamaTxt);
        Id =findViewById(R.id.IdTxt);


        Intent al=getIntent();
        ad=al.getStringExtra("kullanici");
        isim.setText("kullanici:"+ad);

        yardimcisi=new DatabaseYardimcisi(AnaSayfa.this);
        id=yardimcisi.Idsiralama(ad);
        Id.setText("Id:"+id);

    }
    //Kayıt eklemek için kayıtekle ekranına yönlendiriliyor
    public void KayitEkle(View view)
    {

        Intent kayitekle=new Intent(AnaSayfa.this,KayitEkle.class);
        kayitekle.putExtra("id",id);
        startActivity(kayitekle);
    }
    //Profil düzenlemek için profilduzenle ekranına yönlendiriliyor
    public void ProfilDuzenle(View view)
    {
        Intent profilduzenle=new Intent(AnaSayfa.this,ProfilDuzenle.class);
        profilduzenle.putExtra("id",id);
        startActivity(profilduzenle);
    }
    //Eklenen Mülkler listeleniyor mülkün üstüne tıklanınca sil ve güncelle alert'i geliyor eğer güncelleye tıklarsa Mulkguncelle ekranına gidiyor
    public void Listele(View view)
    {
        yardimcisi=new DatabaseYardimcisi(AnaSayfa.this);
        final List<String> vVeriler= yardimcisi.VeriListele();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(AnaSayfa.this,android.R.layout.simple_list_item_1,android.R.id.text1,vVeriler);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                 AlertDialog dialog = new AlertDialog.Builder(AnaSayfa.this)
                        .setTitle("İşlem")
                        .setMessage("Bir İşlem Seçiniz")
                        .setPositiveButton("Güncelle", null)
                        .setNegativeButton("Sil", null)
                        .show();

                Button Btnguncelle = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Btnguncelle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    Intent GuncelleEkran=new Intent(AnaSayfa.this,MulkGuncelle.class);
                    GuncelleEkran.putExtra("id",id);
                    startActivity(GuncelleEkran);
                    }
                });
                Button  BtnSil= dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                BtnSil.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Evler evler=new Evler();
                        long a=yardimcisi.MülkSil(evler,id);
                        if (a>0) {
                            Toast.makeText(getApplicationContext(), "Mülk Başarıyla Silindi.."+id, Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "İşlem Yapmaya Yetkiniz Yok..", Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });
    }

    //Kullanıcı Silme butonu
    public void BtnSil(View view)
    {

        Kullanicilar kullanicilar=new Kullanicilar();
        Evler evler=new Evler();
        long a=yardimcisi.KullaniciSil(kullanicilar,id);
        //kullanıcıyı siliyorsak kullanınıcın eklediği müklerde silinmesi lazım
        yardimcisi.MülkSil(evler,id);
        if (a>0) {
            Toast.makeText(getApplicationContext(), "Kayıt Başarıyla Silindi.."+id, Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Kayıt Silinemedi..", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(getApplicationContext(), "Cıkış Yapıldı Tekrar Giriş Yapınız..", Toast.LENGTH_SHORT).show();
        Intent cikis=new Intent(AnaSayfa.this,MainActivity.class);
        startActivity(cikis);
    }

}