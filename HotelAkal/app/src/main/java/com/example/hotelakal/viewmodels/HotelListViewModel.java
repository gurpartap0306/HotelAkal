package com.example.hotelakal.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hotelakal.Repository.HotelRepository;
import com.example.hotelakal.models.HotelListModel;

public class HotelListViewModel extends ViewModel {

    private MutableLiveData<HotelListModel> mutableLiveData;
    private HotelRepository hotelRepository;

    public void init(){
        if(mutableLiveData !=null){
            return;
        }
        hotelRepository = new HotelRepository();
        mutableLiveData = hotelRepository.getHotels();
    }
    public MutableLiveData<HotelListModel> getHotelRepository(){
        return mutableLiveData;
    }

}
