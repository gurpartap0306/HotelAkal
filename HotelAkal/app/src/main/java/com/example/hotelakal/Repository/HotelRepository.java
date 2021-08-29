package com.example.hotelakal.Repository;

import android.os.StrictMode;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.hotelakal.adapters.GuestListAdapter;
import com.example.hotelakal.models.Confirmation;
import com.example.hotelakal.models.GuestDetails;
import com.example.hotelakal.models.GuestListModel;
import com.example.hotelakal.models.HotelBookingModel;
import com.example.hotelakal.models.HotelListModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HotelRepository {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDbGllbnQifQ.Cil_27SNs24tvxLCU41v519pLl6Aij4tIdffOmBjBi0";

    public MutableLiveData<HotelListModel> getHotels(){
        MutableLiveData<HotelListModel> hotelData =new MutableLiveData<>();

        RetrofitService.getClient().getHotelList("Bearer "+ this.token).enqueue(new Callback<HotelListModel>() {
            @Override
            public void onResponse(Call<HotelListModel> call, Response<HotelListModel> response) {
               hotelData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<HotelListModel> call, Throwable t) {
                hotelData.setValue(null);
            }
        });
        return hotelData;
    }

    public MutableLiveData<GuestListModel> getGuests(int n) {
        MutableLiveData<GuestListModel> guestDetailsMutableLiveData = new MutableLiveData<>();
        GuestListModel guestListModel=new GuestListModel();
        List<GuestDetails> guestListDetails = new ArrayList<GuestDetails>();

        for(int i =0; i <n ;i++){
            GuestDetails guestDetails=new GuestDetails();
            guestListDetails.add(guestDetails);
        }
        guestListModel.setGuestList(guestListDetails);
        guestDetailsMutableLiveData.setValue(guestListModel);
        return guestDetailsMutableLiveData;
    }

    public Confirmation bookHotel(HotelBookingModel hotelBookingModel) {

        Confirmation confirmation=null;
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDbGllbnQifQ.Cil_27SNs24tvxLCU41v519pLl6Aij4tIdffOmBjBi0";
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Call<Confirmation> confirmationCall = RetrofitService.getClient().getConfirmation("Bearer " + token, hotelBookingModel);
            try {
                Response<Confirmation> response = confirmationCall.execute();
                confirmation = response.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return confirmation;
    }
}


