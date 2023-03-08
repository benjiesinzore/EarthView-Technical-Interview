package com.sinzore.earthviewapplication.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "t_customer_info")
public class CustomerInfoModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "customerName")
    private String customerName;

    @ColumnInfo(name = "customerNumber")
    private String customerNumber;

    @ColumnInfo(name = "meterReading")
    private String meterReading;

    @ColumnInfo(name = "meterImage")
    private String meterImage;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(String meterReading) {
        this.meterReading = meterReading;
    }

    public String getMeterImage() {
        return meterImage;
    }

    public void setMeterImage(String meterImage) {
        this.meterImage = meterImage;
    }
}
