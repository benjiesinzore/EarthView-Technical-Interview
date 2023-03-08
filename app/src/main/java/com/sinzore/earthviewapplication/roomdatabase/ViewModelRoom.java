package com.sinzore.earthviewapplication.roomdatabase;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModelRoom extends AndroidViewModel {

    private final RepositoryRoom repository;

    public ViewModelRoom(Application application) {
        super(application);
        repository = new RepositoryRoom(application);
    }


    public LiveData<List<CustomerInfoModel>> getAllCustomerInfo(){
        return  repository.getAllCustomerInfo();
    }


    public void insertCustomerInfo(CustomerInfoModel customerInfoModel){
        repository.insertCustomerInfo(customerInfoModel);
    }


}
