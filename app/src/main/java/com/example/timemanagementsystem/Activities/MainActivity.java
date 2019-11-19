package com.example.timemanagementsystem.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.example.timemanagementsystem.Fragments.Create;
import com.example.timemanagementsystem.Fragments.Delete;
import com.example.timemanagementsystem.Fragments.Read;
import com.example.timemanagementsystem.Fragments.Update;
import com.example.timemanagementsystem.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Fragment mContent;
    ImageView imageView;
    Toolbar toolbar;
    TextView textView;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.iv_profileimage);
        textView=findViewById(R.id.tv_tool);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        Glide.with(this)
                .load(user.getPhotoUrl())
                .into(imageView);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.drawer_layout,new Create());
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("MyNotification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });
         toolbar = findViewById(R.id.mytool);
         setSupportActionBar(toolbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bottom);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        textView.setText("Create");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.create:
                    textView.setText("Create");
                    FragmentManager fragmentManager=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.drawer_layout,new Create());
                    fragmentTransaction.commit();
                    fragmentTransaction.addToBackStack(null);

                    return true;
//                case R.id.read:
//                    textView.setText("Read");
//                    FragmentManager fragmentManager1=getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction1=fragmentManager1.beginTransaction();
//                    fragmentTransaction1.add(R.id.drawer_layout,new Read());
//                    fragmentTransaction1.commit();
//                    fragmentTransaction1.addToBackStack(null);
//                    return true;
                case R.id.update:
                    textView.setText("Update");
                    FragmentManager fragmentManager2=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction2=fragmentManager2.beginTransaction();
                    fragmentTransaction2.add(R.id.drawer_layout,new Update());
                    fragmentTransaction2.commit();
                    fragmentTransaction2.addToBackStack(null);
                    return true;
                case R.id.delete:
                    textView.setText("Delete");
                    FragmentManager fragmentManager3=getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction3=fragmentManager3.beginTransaction();
                    fragmentTransaction3.add(R.id.drawer_layout,new Delete());
                    fragmentTransaction3.commit();
                    fragmentTransaction3.addToBackStack(null);
                    return true;
            }
            return false;
        }
    };
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}