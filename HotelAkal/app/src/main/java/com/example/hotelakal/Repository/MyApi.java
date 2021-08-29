package com.example.hotelakal.Repository;

import com.example.hotelakal.models.Confirmation;
import com.example.hotelakal.models.HotelBookingModel;
import com.example.hotelakal.models.HotelListModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyApi {
   /* @GET("getListOfHotels")
    Call<HotelListModel> getHotelList(@Query("apiKey") String apiKey);
*/
   @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("getListOfHotels")
    Call<HotelListModel> getHotelList(@Header("Authorization") String authHeader);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("reservationConfirmation")
    Call<Confirmation> getConfirmation(@Header("Authorization") String authHeader, @Body HotelBookingModel hotelBookingModel);
}
