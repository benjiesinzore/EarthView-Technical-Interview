package com.sinzore.earthviewapplication.customerinfor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sinzore.earthviewapplication.R;
import com.sinzore.earthviewapplication.data.RoomAdapter;
import com.sinzore.earthviewapplication.roomdatabase.ViewModelRoom;

public class ViewCustomerInfo extends AppCompatActivity {

    private RoomAdapter roomAdapter;
    RecyclerView myRecycleView;
    ViewModelRoom viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer_info);

        //Cached Data
       myRecycleView = findViewById(R.id.myRecycleView);
       roomAdapter = new RoomAdapter(new RoomAdapter.ProductDiff(), this);
       myRecycleView.setAdapter(roomAdapter);
       myRecycleView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(ViewModelRoom.class);
        viewModel.getAllCustomerInfo().observe(this, roomAdapter::submitList);
    }
}