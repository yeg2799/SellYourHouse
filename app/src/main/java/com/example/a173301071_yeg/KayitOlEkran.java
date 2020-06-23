package com.example.a173301071_yeg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class KayitOlEkran extends AppCompatActivity {

    EditText AdSoyad;
    EditText KullaniciAdi;
    EditText Sifre;
    EditText SifreTekrar;
    EditText TelefonNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol_ekran);
    }

    //Veritabanına Kayıt Edilecek
    public void kayitol(View view) {


            AdSoyad = findViewById(R.id.adsoyadgiris);
            KullaniciAdi = findViewById(R.id.KullaniciAdigiris);
            Sifre = findViewById(R.id.SifreGiris);
            SifreTekrar = findViewById(R.id.sifretekrargiris);
            TelefonNo = findViewById(R.id.telefonnogiris);

            String Adsoyadstr = AdSoyad.getText().toString();
            String KullaniciAdistr = KullaniciAdi.getText().toString();
            String Sifrestr = Sifre.getText().toString();
            String SifreTekrarstr = SifreTekrar.getText().toString();
            String TelefonNostr = TelefonNo.getText().toString();

            if (!Sifrestr.equals(SifreTekrarstr)) {
                Toast.makeText(KayitOlEkran.this, "Parolalar Eşleşmiyor Kontrol Ediniz.", Toast.LENGTH_LONG).show();

            }
            else    {

                DatabaseYardimcisi databaseYardimcisi = new DatabaseYardimcisi(getApplicationContext());
                Kullanicilar kullanicilar = new Kullanicilar();
                kullanicilar.setAdSoyad(Adsoyadstr);
                kullanicilar.setEmail(KullaniciAdistr);
                kullanicilar.setSifre(Sifrestr);
                kullanicilar.setTelefonNo(TelefonNostr);

                long id= databaseYardimcisi.kullaniciekle(kullanicilar);
                if (id>0) {
                    Toast.makeText(getApplicationContext(), "Kayıt Başarıyla Oluşturuldu.."+id, Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Kayıt Başarısız..", Toast.LENGTH_LONG).show();
                }
            }






    }
}
