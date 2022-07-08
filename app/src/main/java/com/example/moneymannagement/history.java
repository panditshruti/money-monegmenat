package com.example.moneymannagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.moneymannagement.Modalclass.Modal;
import com.example.moneymannagement.databinding.FragmentHistoryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class history extends Fragment {
    FragmentHistoryBinding binding;

    DatabaseReference databaseReferencemonth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHistoryBinding.inflate(getLayoutInflater());





        binding.recycleview.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        binding.recycleview.setLayoutManager(layoutManager);

        databaseReferencemonth=FirebaseDatabase.getInstance().getReference().child("mounth");
       ArrayList<Modal>list = new ArrayList<>();

       Addapter addapter= new Addapter(list,getContext());
       binding.recycleview.setAdapter(addapter);


        databaseReferencemonth.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Modal modal = dataSnapshot.getValue(Modal.class);
                    list.add(modal);
                }
                addapter.notifyDataSetChanged();
                binding.recycleview.scrollToPosition(list.size()-1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        // Inflate the layout for this fragment
        return binding.getRoot();







    }
}