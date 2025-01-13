package com.example.aplikacjanatelefon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MojeKameryActivity extends AppCompatActivity implements KameraViewInterface{

    List<ParkingP_R> parkingiP_R = new ArrayList<ParkingP_R>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_moje_kamery);

        // Dodawanie kolejnych parkingów Park+Ride do listy wyświetlanej
        parkingiP_R.add(new ParkingP_R("P+R Stokłosy","https://www.wtp.waw.pl/parkingi/parking-pr-metro-stoklosy/","52.15394,21.03757"));
        parkingiP_R.add(new ParkingP_R("P+R Metro Ursynów","https://www.wtp.waw.pl/parkingi/parking-pr-metro-ursynow/","52.16259,21.02742"));
        parkingiP_R.add(new ParkingP_R("P+R Metro Wilanowska","https://www.wtp.waw.pl/parkingi/parking-pr-metro-wilanowska/","52.18005,21.02476"));
        parkingiP_R.add(new ParkingP_R("P+R Połczyńska","https://www.wtp.waw.pl/parkingi/parking-pr-polczynska/","52.22222,21.92240"));

        RecyclerView recyclerView = findViewById(R.id.recycleViewKameryMK);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new KameraAdapter(getApplicationContext(),parkingiP_R,this));
        Button buttonPowrot = findViewById(R.id.buttonPowrotMK);

        buttonPowrot.setOnClickListener(v->{
            super.onBackPressed();
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,KameraActivity.class);

        // Przesyłanie url strony danego parkingu do kolejnego okna
        intent.putExtra("URL_STRONY",parkingiP_R.get(position).getUrlStrony());
        startActivity(intent);
    }
}