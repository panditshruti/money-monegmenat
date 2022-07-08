package com.example.moneymannagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moneymannagement.Modalclass.Modal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class home extends Fragment {
    Button addbutton;
    EditText incomeedittext;
    EditText expenceicomeedittext;

    Button withdrolbtn;
   CheckBox business,office,moneywithdrowl,otherincome;
   TextView todayincome,monthincome,todayexpencetextview,mounthexpencetextview;
   CheckBox homeexpence ,businessexpence,moneyexpence,otherexpence;


    DatabaseReference databaseReferencetoday,databaseReferencemonth,databaseReferencetodayexpence,databaseReferencemonthexpence;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        incomeedittext=view.findViewById(R.id.incomeedittext);
        expenceicomeedittext=view.findViewById(R.id.expence);
        addbutton=view.findViewById(R.id.addbotton);

        withdrolbtn= view.findViewById(R.id.withdrowlbtn);


// income chekbox
        business=view.findViewById(R.id.Business);
        office=view.findViewById(R.id.office);
        moneywithdrowl=view.findViewById(R.id.moneywithdrol);
        otherincome=view.findViewById(R.id.other);

        //expence
        homeexpence=view.findViewById(R.id.home);
        businessexpence=view.findViewById(R.id.business);
        moneyexpence=view.findViewById(R.id.money);
        otherexpence=view.findViewById(R.id.otherexpence);

        todayexpencetextview=view.findViewById(R.id.todayexpencetextview);
        mounthexpencetextview=view.findViewById(R.id.mothexpencetextview);


        todayincome=view.findViewById(R.id.todayincome);
        monthincome=view.findViewById(R.id.monthincome);
        Date date= new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String todaydate=format.format(date);
        databaseReferencetoday=FirebaseDatabase.getInstance().getReference().child("todayincome");

        databaseReferencemonth=FirebaseDatabase.getInstance().getReference().child("mounth");
        databaseReferencetodayexpence=FirebaseDatabase.getInstance().getReference().child("todayexpence");

        databaseReferencemonthexpence=FirebaseDatabase.getInstance().getReference().child("mounthexpence");



        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///dateworkin

                Date date= new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                String todaydate=format.format(date);

                SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm:ss:a");
                String time=timeformat.format(date);


                Modal modal=new Modal();

                modal.setAmount(incomeedittext.getText().toString());
                modal.setTodaydate(todaydate);
                modal.setTime(time);

                if(business.isChecked()){
                    modal.setType("business");
                }
                 if(office.isChecked()){
                    modal.setType("office");
                }
                 if(moneywithdrowl.isChecked()){
                    modal.setType("moneywithdrowl");
                }
                 if(otherincome.isChecked()){
                    modal.setType("other");
                }




                String id = databaseReferencetoday.push().getKey();
                databaseReferencetoday.child(id).setValue(modal);


                String id2 = databaseReferencemonth.push().getKey();
                databaseReferencemonth.child(id2).setValue(modal);



            }
        });

        // expence code
        withdrolbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///dateworkin

                Date date= new Date();
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                String todaydate=format.format(date);

                SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm:ss:a");
                String time=timeformat.format(date);


                Modal modal=new Modal();

                modal.setAmount(expenceicomeedittext.getText().toString());
                modal.setTodaydate(todaydate);
                modal.setTime(time);

                if(homeexpence.isChecked()){
                    modal.setType("homeexpence");
                }
                if(businessexpence.isChecked()){
                    modal.setType("businessexpence");
                }
                if(moneyexpence.isChecked()){
                    modal.setType("moneyexpence");
                }
                if(otherexpence.isChecked()){
                    modal.setType("otherexpence");
                }


                String id = databaseReferencetodayexpence.push().getKey();
                databaseReferencetodayexpence.child(id).setValue(modal);


                String id2 = databaseReferencemonthexpence.push().getKey();
                databaseReferencemonthexpence.child(id2).setValue(modal);



            }
        });



        databaseReferencetoday.orderByChild("todaydate").equalTo(todaydate).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               int sum =0;
               for (DataSnapshot data : dataSnapshot.getChildren()){

                   Map<String, Object> map = ( Map<String, Object>) data.getValue();
                   Object todayamount = map.get("amount");
                   int todayamountvalue = Integer.parseInt(String.valueOf(todayamount));
                   sum += todayamountvalue;
                   todayincome.setText("₹ "+ sum);

               }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        databaseReferencemonth.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int sum =0;
                for (DataSnapshot data : dataSnapshot.getChildren()){

                    Map<String, Object> map = ( Map<String, Object>) data.getValue();
                    Object todayamount = map.get("amount");
                    int todayamountvalue = Integer.parseInt(String.valueOf(todayamount));
                    sum += todayamountvalue;
                    monthincome.setText("₹ "+ sum);


                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        //expnce

        databaseReferencemonthexpence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int sum =0;
                for (DataSnapshot data : dataSnapshot.getChildren()){

                    Map<String, Object> map = ( Map<String, Object>) data.getValue();
                    Object todayamount = map.get("amount");
                    int todayamountvalue = Integer.parseInt(String.valueOf(todayamount));
                    sum += todayamountvalue;
                    mounthexpencetextview.setText("₹ "+ sum);


                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        databaseReferencetodayexpence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int sum =0;
                for (DataSnapshot data : dataSnapshot.getChildren()){

                    Map<String, Object> map = ( Map<String, Object>) data.getValue();
                    Object todayamount = map.get("amount");
                    int todayamountvalue = Integer.parseInt(String.valueOf(todayamount));
                    sum += todayamountvalue;
                    todayexpencetextview.setText("₹ "+ sum);


                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

return view;

    }
}