package com.sinzore.earthviewapplication.customerinfor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sinzore.earthviewapplication.R;
import com.sinzore.earthviewapplication.roomdatabase.CustomerInfoModel;
import com.sinzore.earthviewapplication.roomdatabase.ViewModelRoom;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class CustomerMeterReading extends AppCompatActivity {

    private ViewModelRoom viewModelRoom;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private int my_position;
    private TextView bgMeterImage, bgProceed;

    private EditText edCustomerName, edCustomerNumber, edMeterReading;
    private ImageView ivMeterImage;
    private String encoded_img= "", getName = "", getNumber = "", getMetReading = "";
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_meter_reading);

        viewModelRoom = new ViewModelProvider(this).get(ViewModelRoom.class);
        progress = new ProgressDialog(this);

        edCustomerName = findViewById(R.id.edCustomerName);
        edCustomerNumber = findViewById(R.id.edCustomerNumber);
        edMeterReading = findViewById(R.id.edMeterReading);
        bgMeterImage = findViewById(R.id.btGetImage);
        bgProceed = findViewById(R.id.btProceed);
        ivMeterImage = findViewById(R.id.ivMeterImage);


        bgMeterImage.setOnClickListener(v ->{
            my_position = 1;
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                Toast.makeText(this, "Please Provide Camera Permission", Toast.LENGTH_SHORT).show();
            } else {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        bgProceed.setOnClickListener(v ->{

            CustomerInfoModel customerInfoModel = new CustomerInfoModel();
            getName = edCustomerName.getText().toString().trim();
            getNumber = edCustomerNumber.getText().toString().trim();
            getMetReading = edMeterReading.getText().toString().trim();


            if (Objects.equals(getName, "")) {
                Toast.makeText(this, "Customer Name is Required", Toast.LENGTH_SHORT).show();
            } else if (Objects.equals(getNumber, "")){
                Toast.makeText(this, "Customer Number is Required", Toast.LENGTH_SHORT).show();
            } else if (Objects.equals(getMetReading, "")){
                Toast.makeText(this, "Meter Reading is Required", Toast.LENGTH_SHORT).show();
            } else if (Objects.equals(encoded_img, "")){
                Toast.makeText(this, "Meter Image is Required", Toast.LENGTH_SHORT).show();
            } else {


                progress.setMessage("Please wait");
                progress.show();
                customerInfoModel.setCustomerName(getName);
                customerInfoModel.setCustomerNumber(getNumber);
                customerInfoModel.setMeterReading(getMetReading);
                customerInfoModel.setMeterImage(encoded_img);
                viewModelRoom.insertCustomerInfo(customerInfoModel);
                Toast.makeText(this, "Data added Successfully", Toast.LENGTH_LONG).show();
                progress.dismiss();
            }

        });


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivMeterImage.setImageBitmap(photo);
            ivMeterImage.setVisibility(View.VISIBLE);


//            txtLabelOne.setText(MessageFormat.format("{0} {1} {2}", timeStamp, pref.getSiteName(), address));
//            Bitmap bitmap = Bitmap.createBitmap(rl_image_one.getWidth(),
//                    rl_image_one.getHeight(), Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(bitmap);
//            rl_image_one.draw(canvas);
//            ic_image_one.setImageBitmap(bitmap);
//            ic_image_one.setVisibility(View.VISIBLE);
//            txtLabelOne.setText("");

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            encoded_img = Base64.encodeToString(byteArray, Base64.DEFAULT);

            Log.d("Base64Image", encoded_img);
        }
    }
}