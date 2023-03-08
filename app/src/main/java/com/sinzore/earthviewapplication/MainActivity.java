package com.sinzore.earthviewapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.sinzore.earthviewapplication.customerinfor.CustomerMeterReading;
import com.sinzore.earthviewapplication.customerinfor.ViewCustomerInfo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    TextView btGetUnique, btAddData, btViewData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] a = {100,200,300,100,500,200};


        btGetUnique = findViewById(R.id.btGetUnique);
        btGetUnique.setOnClickListener(v ->{

            int[] resultA = checkForDuplicate(a);
            Toast.makeText(this, ""+ Arrays.toString(resultA), Toast.LENGTH_LONG).show();
        });
        btAddData = findViewById(R.id.btAddData);
        btAddData.setOnClickListener(v ->
                startActivity(new Intent(this, CustomerMeterReading.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK)));
        btViewData = findViewById(R.id.btViewData);
        btViewData.setOnClickListener(v ->
                startActivity(new Intent(this, ViewCustomerInfo.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK))
        );

    }

    @NonNull
    public static int[] checkForDuplicate(int[] a){

        Integer[] intArray = new Integer[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            intArray = Arrays.stream(a).boxed().toArray(Integer[]::new);
        }
        HashSet<Integer> set = new HashSet<>(Arrays.asList(intArray));

        // Convert the HashSet back to an array
        int[] result = new int[set.size()];
        int i = 0;
        for (int n : set) {
            result[i++] = n;
        }
        return  result;
    }

}