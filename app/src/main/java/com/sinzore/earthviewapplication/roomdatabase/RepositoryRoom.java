package com.sinzore.earthviewapplication.roomdatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RepositoryRoom {


    DaoRoom roomDao;

    public RepositoryRoom(Application application) {
        DatabaseRoom db = DatabaseRoom.getDatabase(application);
        roomDao = db.proDao();

    }


    LiveData<List<CustomerInfoModel>> getAllCustomerInfo(){


        return roomDao.getAllCustomerInfo();
    }


    void insertCustomerInfo(CustomerInfoModel customerInfoModel){

        DatabaseRoom.databaseWriteExecutor.execute(()->{
            roomDao.insertCustomerInfo(customerInfoModel);
        });
    }
}
