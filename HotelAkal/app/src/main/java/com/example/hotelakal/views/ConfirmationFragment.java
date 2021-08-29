package com.example.hotelakal.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.hotelakal.R;

import org.jetbrains.annotations.NotNull;

public class ConfirmationFragment extends Fragment {

    TextView textView;
    View view;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.hotel_confirmation_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Context context = getContext();
        SharedPreferences sp = context.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        String confirmation_number=sp.getString("confirmation","");

        textView = view.findViewById(R.id.confirmation_number);
        textView.setText("Your confirmation number is "+confirmation_number);
    }
}
