package com.example.biletiki2.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.biletiki2.R;
import com.example.biletiki2.data.dto.LockedSeats;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder> {

    private List<LockedSeats> list;

    public ShoppingCartAdapter(List<LockedSeats> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.locked_seats_row, viewGroup, false);
        return new ShoppingCartAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        LockedSeats lockedSeats = list.get(position);
        myViewHolder.rowTxt.setText(String.valueOf(lockedSeats.getRow()));
        myViewHolder.placeTxt.setText(lockedSeats.getSeat());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView rowTxt, placeTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rowTxt = itemView.findViewById(R.id.row_txt);
            placeTxt = itemView.findViewById(R.id.place_txt);
        }
    }
}
