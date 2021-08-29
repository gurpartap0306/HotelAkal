package com.example.hotelakal.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;
import com.example.hotelakal.R;


import androidx.annotation.NonNull;

import com.example.hotelakal.models.GuestDetails;
import com.example.hotelakal.views.ItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GuestListAdapter extends RecyclerView.Adapter<GuestListAdapter.ViewHolder> {

    private List<GuestDetails> guestListData;
    private LayoutInflater layoutInflater;
    private ItemClickListener clickListener;


    public GuestListAdapter(Context context, List<GuestDetails> guestListData){
        this.layoutInflater = LayoutInflater.from(context);
        this.guestListData = guestListData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotel_guest_details_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String guest_name=guestListData.get(position).getGuest_name();
        String gender=guestListData.get(position).getGender();

        //holder.guestName.setText(guest_name);
        //holder.gender.setText(gender);
        //GuestDetails guestDetails = guestListData.get(holder.getAdapterPosition());

        holder.guestName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                guestListData.get(holder.getAdapterPosition()).setGuest_name(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.gender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                guestListData.get(holder.getAdapterPosition()).setGender(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public int getItemCount() {
        if (guestListData != null) {
            return guestListData.size();
        } else {
            return 0;
        }
    }

    public List<GuestDetails> getGuestListData() {
        return guestListData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        EditText guestName, gender;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            guestName = itemView.findViewById(R.id.guest_name_text_view);
            gender = itemView.findViewById(R.id.guest_gender_text_view);
            //send=itemView.findViewById(R.id.send);

        }

        public void bindData(final GuestDetails guestDetails){
            guestName.setText(guestDetails.getGuest_name());
            gender.setText(guestDetails.getGender());
            /*
            guestName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    guestDetails.setGuest_name(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            gender.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    guestDetails.setGender(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });*/

        }

        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onClick(view, getAbsoluteAdapterPosition());
        }
    }
}
