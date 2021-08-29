package com.example.hotelakal.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelakal.R;
import com.example.hotelakal.adapters.HotelListAdapter;
import com.example.hotelakal.models.HotelModel;
import com.example.hotelakal.models.HotelSearchModel;
import com.example.hotelakal.viewmodels.HotelListViewModel;
import com.example.hotelakal.viewmodels.MainSharedViewModel;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public class HotelsListFragment extends Fragment implements ItemClickListener{

    View view;
    TextView headingTextView;
    ProgressBar progressBar;
    List<HotelModel> hotelListresponse;
    HotelListViewModel hotelListViewModel;
    MainSharedViewModel mainSharedViewModel;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_list_fragment,container,false);

        //object to access shared view model
        mainSharedViewModel = new ViewModelProvider(getActivity()).get(MainSharedViewModel.class);

        //object of fragment viewmodel
        hotelListViewModel = ViewModelProviders.of(this).get(HotelListViewModel.class);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //heading text view
        headingTextView = view.findViewById(R.id.heading_text_view);
        progressBar = view.findViewById(R.id.progress_bar);

        //fetching data from shared view model
        final HotelSearchModel[] temp = {new HotelSearchModel()};
        mainSharedViewModel.getHotelModelMutableLIveData().observe(getViewLifecycleOwner(),hotelSearchModel -> {
            temp[0] =hotelSearchModel;
            headingTextView.setText("Hi! "+temp[0].getGuestName()+"!\n"+
                    "Checkin Date: "+temp[0].getCheckin()+"\n"+
                    "Checkout Date: "+temp[0].getCheckout()+"\n"+
                    "Number of Guests: "+temp[0].getNumberOfGuests());
        });


        //fetching data from repository and updating in fragment
        hotelListViewModel.init();
        hotelListViewModel.getHotelRepository().observe(getViewLifecycleOwner(), hotelListModel -> {
            hotelListresponse = hotelListModel.getHotelsList();
            setupRecyclerView();
        });
    }

    //putting data to recycler view
    private void setupRecyclerView() {
        RecyclerView recyclerView = view.findViewById(R.id.hotel_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HotelListAdapter hotelListAdapter = new HotelListAdapter(getActivity(), hotelListresponse);
        recyclerView.setAdapter(hotelListAdapter);

        //Bind the click listener
        hotelListAdapter.setClickListener(this);
    }

    @Override
    public void onClick(View view, int position) {

        //getting hotel details selected by user selected by user
        HotelModel hotelListData = hotelListresponse.get(position);

        if(hotelListData.getAvailability().equals("true")){
            String hotelName = hotelListData.getHotelName();
            String price = hotelListData.getPrice();
            String availability = hotelListData.getAvailability();

            //using bundle to pass data to next view
            Bundle bundle = new Bundle();
            bundle.putString("hotel name", hotelName);
            bundle.putString("hotel price", price);
            bundle.putString("hotel availability", availability);

            HotelGuestDetailsFragment hotelGuestDetailsFragment = new HotelGuestDetailsFragment();
            hotelGuestDetailsFragment.setArguments(bundle);

            //replacing fragment in the activity
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.remove(HotelsListFragment.this);
            fragmentTransaction.replace(R.id.main_layout, hotelGuestDetailsFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();
        }else {
            Toast.makeText(getActivity(), "There is no availability in selected hotel", Toast.LENGTH_SHORT).show();
        }



    }

}
