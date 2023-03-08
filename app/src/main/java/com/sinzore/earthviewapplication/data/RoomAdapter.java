package com.sinzore.earthviewapplication.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.sinzore.earthviewapplication.R;
import com.sinzore.earthviewapplication.roomdatabase.CustomerInfoModel;

public class RoomAdapter extends ListAdapter<CustomerInfoModel, RoomViewHolder> {

    private final Context context;
    private CustomerInfoModel model;


    public RoomAdapter(@NonNull DiffUtil.ItemCallback<CustomerInfoModel> diffCallback, Context context)
    {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RoomViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {

        CustomerInfoModel current = getItem(position);

        String base64String = current.getMeterImage();

        double myDouble = Double.parseDouble(current.getMeterReading());


        if (myDouble > 1){
            holder.dontBill.setVisibility(View.GONE);
        }

        byte[] decodedBytes = Base64.decode(base64String.substring(base64String.indexOf(",") + 1), Base64.DEFAULT);

        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

        holder.bind(context, current.getCustomerName(), current.getCustomerNumber(),
                myDouble, bitmap);

    }


    public static class ProductDiff extends DiffUtil.ItemCallback<CustomerInfoModel> {

        @Override
        public boolean areItemsTheSame(@NonNull CustomerInfoModel oldItem, @NonNull CustomerInfoModel newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CustomerInfoModel oldItem, @NonNull CustomerInfoModel newItem) {
            return oldItem.getCustomerNumber().equals(newItem.getCustomerNumber());
        }
    }
}



//class RsmStoreViewHolder extends RecyclerView.ViewHolder {
//
//    TextView cusName, cusMetNumber, meterReading, dontBill;
//    ImageView meterImage;
//
//    RsmStoreViewHolder(View itemView) {
//        super(itemView);
//
//        cusName = itemView.findViewById(R.id.cusName);
//        cusMetNumber = itemView.findViewById(R.id.cusMetNumber);
//        meterReading = itemView.findViewById(R.id.meterReading);
//        dontBill = itemView.findViewById(R.id.dontBill);
//        meterImage = itemView.findViewById(R.id.meterImage);
//
//
//    }
//}
