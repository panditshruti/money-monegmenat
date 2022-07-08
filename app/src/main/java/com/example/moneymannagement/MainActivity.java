package com.example.moneymannagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    home home=new home();
    dashbord dashbord=new dashbord();
    history history=new history();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.nevigationbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,home).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,home).commit();
                        return true;
                    case  R.id.dashbord:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,dashbord).commit();
                        return true;
                    case  R.id.history:
                        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,history).commit();
                        return true;



                }



                return false;
            }
        });



    }
}