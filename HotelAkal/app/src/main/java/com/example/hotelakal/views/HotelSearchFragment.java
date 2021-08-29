package com.example.hotelakal.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.hotelakal.R;
import com.example.hotelakal.models.HotelSearchModel;
import com.example.hotelakal.viewmodels.MainSharedViewModel;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HotelSearchFragment extends Fragment {

    View view;
    MainSharedViewModel myViewModel;
    ConstraintLayout mainLayout;
    TextView titleTextView, searchTextConfirmationTextView;
    EditText guestsCountEditText, nameEditText;
    Button searchButton;
    DatePicker checkInDatePicker, checkOutDatePicker;
    String checkInDate, checkOutDate, numberOfGuests, guestName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hotel_search_layout,container,false);
        myViewModel=new ViewModelProvider(getActivity()).get(MainSharedViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //get reference to fields of layout
        mainLayout = view.findViewById(R.id.main_layout);
        titleTextView = view.findViewById(R.id.title_text_view);
        searchButton = view.findViewById(R.id.search_button);
        checkInDatePicker = view.findViewById(R.id.checkin_date_picker_view);
        checkOutDatePicker = view.findViewById(R.id.checkout_date_picker_view);
        guestsCountEditText = view.findViewById(R.id.guests_count_edit_text);
        nameEditText = view.findViewById(R.id.name_edit_text);

        //set Title Text
        titleTextView.setText(R.string.welcome_text);



        //Search Button click Listener
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //retrieved data from view
                checkInDate = getDateFromCalendar(checkInDatePicker);
                checkOutDate = getDateFromCalendar(checkOutDatePicker);
                numberOfGuests = guestsCountEditText.getText().toString();
                guestName = nameEditText.getText().toString();

                try {
                    Integer.parseInt(numberOfGuests);
                    //formatted data to update it in viewmodel
                    HotelSearchModel hotelSearchModel= new HotelSearchModel();
                    hotelSearchModel.setCheckin(checkInDate);
                    hotelSearchModel.setCheckout(checkOutDate);
                    hotelSearchModel.setNumberOfGuests(numberOfGuests);
                    hotelSearchModel.setGuestName(guestName);

                    //updated viewmodel
                    myViewModel.setHotelModelMutableLiveData(hotelSearchModel);

                    // set Fragment class Arguments
                    HotelsListFragment hotelsListFragment = new HotelsListFragment();

                    //persisted data in Shared preference to have access in future
                    Context context = getActivity();
                    SharedPreferences sp = context.getSharedPreferences("myPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("numberOfGuests",numberOfGuests);
                    editor.putString("checkin",checkInDate);
                    editor.putString("checkout",checkOutDate);
                    editor.commit();

                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main_layout, hotelsListFragment);
                    fragmentTransaction.remove(HotelSearchFragment.this);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Please select numeric value for number of guest", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    // Function to get the date object
    private String getDateFromCalendar(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = simpleDateFormat.format(calendar.getTime());

        return formattedDate;
    }
}
