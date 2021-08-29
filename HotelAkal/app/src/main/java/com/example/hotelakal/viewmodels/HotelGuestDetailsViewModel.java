package com.example.hotelakal.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hotelakal.Repository.HotelRepository;
import com.example.hotelakal.models.GuestDetails;
import com.example.hotelakal.models.GuestListModel;

public class HotelGuestDetailsViewModel extends ViewModel {

    private MutableLiveData<GuestListModel> guestDetailsMutableLiveData;
    private HotelRepository hotelRepository;

    public void init(String n){
        if(guestDetailsMutableLiveData !=null)
            return;
        hotelRepository = new HotelRepository();
        guestDetailsMutableLiveData = hotelRepository.getGuests(Integer.parseInt(n));
    }

    public MutableLiveData<GuestListModel> getGuestRepository(){return guestDetailsMutableLiveData;}


}
