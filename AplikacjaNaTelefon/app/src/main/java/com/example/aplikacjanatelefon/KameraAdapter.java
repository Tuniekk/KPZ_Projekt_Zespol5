package com.example.aplikacjanatelefon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class KameraAdapter extends RecyclerView.Adapter<KameraHolder> {
    Context context;
    List<ParkingP_R> parkingiP_R;

    public KameraAdapter(Context context, List<ParkingP_R> parkingiP_R) {
        this.context = context;
        this.parkingiP_R = parkingiP_R;
    }

    @NonNull
    @Override
    public KameraHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KameraHolder(LayoutInflater.from(context).inflate(R.layout.kamera_na_liscie_widok,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull KameraHolder holder, int position) {
        holder.nazwaKamery.setText(parkingiP_R.get(position).getNazwaParkingu());
        holder.polozenieParkingu.setText(parkingiP_R.get(position).getPolozenieNaMapie());



    }

    @Override
    public int getItemCount() {
        return parkingiP_R.size();
    }


}
