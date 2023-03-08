package com.sinzore.earthviewapplication.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoRoom {

    @Query("SELECT * FROM t_customer_info")
    LiveData<List<CustomerInfoModel>> getAllCustomerInfo();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCustomerInfo(CustomerInfoModel customerInfoModel);

    @Query("DELETE FROM t_customer_info")
    void deleteAll();
}
