package com.example.aplikacjanatelefon;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class KameraNaszaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_kamera_nasza);

        String urlStrony = getIntent().getStringExtra("URL_STRONY");
        String polozenieNaMapie = getIntent().getStringExtra("POLOZENIE_NA_MAPIE");
        String nazwaParkingu = getIntent().getStringExtra("NAZWA_PARKINGU");

        Button buttonPowrot = findViewById(R.id.buttonPowrotKN);
        Button buttonPokazNaMapie = findViewById(R.id.buttonPokazNaMapieKN);

        buttonPowrot.setOnClickListener(v->{
            super.onBackPressed();
            finish();
        });

        buttonPokazNaMapie.setOnClickListener(v -> {
            String urlMapy = "http://maps.google.com/maps?q="+ polozenieNaMapie +"("+ nazwaParkingu + ")&iwloc=A&hl=es";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(urlMapy));
            Intent chooser = Intent.createChooser(intent,"Launch Map");
            startActivity(chooser);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}