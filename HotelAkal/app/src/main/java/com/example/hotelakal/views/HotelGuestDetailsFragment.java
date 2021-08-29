package com.example.hotelakal.views;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelakal.R;
import com.example.hotelakal.Repository.HotelRepository;
import com.example.hotelakal.Repository.RetrofitService;
import com.example.hotelakal.adapters.GuestListAdapter;
import com.example.hotelakal.models.Confirmation;
import com.example.hotelakal.models.GuestDetails;
import com.example.hotelakal.models.GuestListModel;
import com.example.hotelakal.models.HotelBookingModel;
import com.example.hotelakal.viewmodels.HotelGuestDetailsViewModel;
import com.example.hotelakal.viewmodels.MainSharedViewModel;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelGuestDetailsFragment extends Fragment {

    View view;
    HotelRepository hotelRepository;
    MainSharedViewModel mainSharedViewModel;
    HotelGuestDetailsViewModel hotelGuestDetailsViewModel;
    List<GuestDetails> guestListresponse;
    GuestListAdapter guestListAdapter;
    ConfirmationFragment confirmationFragment = new ConfirmationFragment();
    String hotelName,hotelPrice,hotelAvailability,numberOfGuests,checkin,checkout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.hotel_guest_details_fragment, container, false);
        hotelGuestDetailsViewModel= ViewModelProviders.of(this).get(HotelGuestDetailsViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView hotelRecapTextView = view.findViewById(R.id.hotel_recap_text_view);

        hotelName = getArguments().getString("hotel name");
        hotelPrice = getArguments().getString("hotel price");
        hotelAvailability = getArguments().getString("hotel availability");
        Context context = getContext();
        SharedPreferences sp = context.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        numberOfGuests=sp.getString("numberOfGuests","");
        checkin=sp.getString("checkin","");
        checkout=sp.getString("checkout","");

        hotelRecapTextView.setText("You have selected " +hotelName+ ". The cost will be $ "+hotelPrice+ "number of Guests are "+ numberOfGuests +" and availability is " +hotelAvailability);

        hotelGuestDetailsViewModel.init(numberOfGuests);
        hotelGuestDetailsViewModel.getGuestRepository().observe(getViewLifecycleOwner(),guestListModel ->{
           guestListresponse=guestListModel.getGuestList();
           setupRecyclerView();
        });

        Button bookButton = view.findViewById(R.id.booking_button);

        bookButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                GuestListModel guestListModel=new GuestListModel();
                guestListModel.setGuestList(guestListAdapter.getGuestListData());



                HotelBookingModel hotelBookingModel = new HotelBookingModel();
                hotelBookingModel.setGuests_list(guestListModel.getGuestList());
                hotelBookingModel.setHotel_name(hotelName);
                hotelBookingModel.setCheckin(checkin);
                hotelBookingModel.setCheckout(checkout);

                Context context = getActivity();
                SharedPreferences sp = context.getSharedPreferences("myPref", Context.MODE_PRIVATE);


                TextView hotelRecapTextView = view.findViewById(R.id.hotel_recap_text_view);
                hotelRecapTextView.setText(hotelBookingModel.getHotel_name()+hotelBookingModel.getCheckin()+hotelBookingModel.getCheckout()+hotelBookingModel.getGuests_list().get(0).getGender());


                HotelRepository hotelRepository = new HotelRepository();
                Confirmation confirmation = hotelRepository.bookHotel(hotelBookingModel);


                SharedPreferences.Editor editor = sp.edit();
                editor.putString("confirmation", confirmation.getConfirmation_number());
                editor.commit();

                /*TextView hotelRecapTextView = view.findViewById(R.id.hotel_recap_text_view);
                hotelRecapTextView.setText(confirmation.getConfirmation_number());*/


                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_layout, confirmationFragment);
                fragmentTransaction.remove(HotelGuestDetailsFragment.this);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

    }

    private void setupRecyclerView(){
        RecyclerView recyclerView = view.findViewById(R.id.guest_details_recyclerView);
        recyclerView.setLayoutManager((new LinearLayoutManager(getActivity())));
        guestListAdapter = new GuestListAdapter(getActivity(),guestListresponse);
        recyclerView.setAdapter(guestListAdapter);

        //guestListAdapter.setClickListener(this);
    }

}