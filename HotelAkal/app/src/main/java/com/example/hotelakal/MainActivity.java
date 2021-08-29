package com.example.hotelakal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.hotelakal.viewmodels.MainSharedViewModel;
import com.example.hotelakal.views.HotelSearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MainSharedViewModel myViewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(this.getApplication(),this)).get(MainSharedViewModel.class);
        MainSharedViewModel myViewModel = new ViewModelProvider(this).get(MainSharedViewModel.class);

        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frame_layout,new HotelSearchFragment());

        fragmentTransaction.commit();

    }
}