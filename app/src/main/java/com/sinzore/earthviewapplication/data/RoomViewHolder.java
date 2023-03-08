package com.sinzore.earthviewapplication.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sinzore.earthviewapplication.R;

public class RoomViewHolder  extends RecyclerView.ViewHolder {
    TextView cusName, cusMetNumber, meterReading, dontBill;
    ImageView meterImage;

    public RoomViewHolder(@NonNull View itemView) {
        super(itemView);

        cusName = itemView.findViewById(R.id.cusName);
        cusMetNumber = itemView.findViewById(R.id.cusMetNumber);
        meterReading = itemView.findViewById(R.id.cusMetReading);
        dontBill = itemView.findViewById(R.id.dontBill);
        meterImage = itemView.findViewById(R.id.meterImage);
    }


    public void bind(Context context, String customerName, String customerNumber, double cusMeterReading,
                     Bitmap myImage) {


        cusName.setText("Customer Name:  "+customerName);
        cusMetNumber.setText("Customer Number:  "+customerNumber);
        meterReading.setText("Meter Reading:  " + cusMeterReading);

        Glide.with(context)
                .load(myImage)
                .into(meterImage);

    }

    static RoomViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_items, parent, false);
        return new RoomViewHolder(view);
    }
}
