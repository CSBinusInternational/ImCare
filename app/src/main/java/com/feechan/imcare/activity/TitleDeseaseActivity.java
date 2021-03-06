package com.feechan.imcare.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.feechan.imcare.R;
import com.feechan.imcare.entity.Penyakit;
import com.feechan.imcare.global.AppHelper;

public class TitleDeseaseActivity extends AppCompatActivity {

    ImageView penyakitImageView;
    Button detailInformasiButton,artikelButton,videoButton,infoRumahSakitButton;
    Penyakit penyakit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_desease);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        penyakit = (Penyakit) getIntent().getSerializableExtra("penyakit");
        Log.d("care","selected penyakit >> "+penyakit.getNmpenyakit());

        penyakitImageView = (ImageView) findViewById(R.id.penyakitImageView);

        detailInformasiButton = (Button) findViewById(R.id.detailInformasiButton);
        artikelButton = (Button) findViewById(R.id.artikelButton);
        videoButton = (Button) findViewById(R.id.videoButton);
        infoRumahSakitButton = (Button) findViewById(R.id.infoRumahSakitButton);

        detailInformasiButton.setText(detailInformasiButton.getText()+" "+penyakit.getNmpenyakit());
        infoRumahSakitButton.setText(infoRumahSakitButton.getText()+" "+penyakit.getNmpenyakit());
        AppHelper.setImageFromUrl(penyakitImageView,penyakit.getImage_url(),this);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.detailInformasiButton:
                        detailInformasiClicked();
                        break;
                    case R.id.artikelButton:
                        artikelClicked();
                        break;
                    case R.id.videoButton:
                        videoClicked();
                        break;
                    case R.id.infoRumahSakitButton:
                        infoRumahSakitClicked();
                        break;
                    default:
                        Log.d("care","button not defined");
                        break;
                }
            }
        };
        detailInformasiButton.setOnClickListener(click);
        artikelButton.setOnClickListener(click);
        videoButton.setOnClickListener(click);
        infoRumahSakitButton.setOnClickListener(click);
    }

    private void detailInformasiClicked(){
        Intent intent = new Intent(this, DetailDiseaseActivity.class);
        intent.putExtra("penyakit",penyakit);
        startActivity(intent);
    }

    private void artikelClicked(){
        Intent intent = new Intent(this, ListArtikelActivity.class);
        intent.putExtra("penyakit",penyakit);
        startActivity(intent);
    }

    private void videoClicked(){
        Intent intent = new Intent(this, ListVideoActivity.class);
        intent.putExtra("penyakit",penyakit);
        startActivity(intent);
    }

    private void infoRumahSakitClicked(){
        Intent intent = new Intent(this, ListHospitalDiseaseActivity.class);
        intent.putExtra("kdpenyakit",penyakit.getKdpenyakit());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
}
