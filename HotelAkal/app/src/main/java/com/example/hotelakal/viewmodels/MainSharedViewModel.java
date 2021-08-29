package com.example.hotelakal.viewmodels;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.hotelakal.models.HotelModel;
import com.example.hotelakal.models.HotelSearchModel;

import java.util.List;

public class MainSharedViewModel extends ViewModel {

    /*
    private SavedStateHandle savedStateHandle;

    public LiveData<HotelSearchModel> hotelSearchModelLiveData;

    public MainSharedViewModel(SavedStateHandle savedStateHandle){

        this.savedStateHandle=savedStateHandle;

        this.savedStateHandle = savedStateHandle;
        //hotelSearchModelLiveData=savedStateHandle.getLiveData("query");
        LiveData<HotelSearchModel> hotelSearchLiveData = savedStateHandle.getLiveData("query");
        hotelSearchModelLiveData = Transformations.switchMap(hotelSearchLiveData,query -> )

    }

    public void setHotelSearch(HotelSearchModel hotelSearchModel){
        this.savedStateHandle.set("query",hotelSearchModel);
    }

    public LiveData<HotelSearchModel> getHotelSearchModelLiveData() {
        return savedStateHandle.getLiveData("query");
    }

     */

    private final MutableLiveData<HotelSearchModel> hotelModelMutableLiveData= new MutableLiveData<HotelSearchModel>();

    public void setHotelModelMutableLiveData(HotelSearchModel hotelSearchModel){
        this.hotelModelMutableLiveData.setValue(hotelSearchModel);
    }

    public LiveData<HotelSearchModel> getHotelModelMutableLIveData(){
        return hotelModelMutableLiveData;
    }

}

