package com.example.timemanagementsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.timemanagementsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {

    ImageView imageView;
    TextView textName, textEmail,textView,textContact,textDesignation,textGender,textViewrate;
    FirebaseAuth mAuth;
    Button button;
    Toolbar toolbar;
    String contact,gender,designation;
    SharedPreferences sharedPreferences1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textView=findViewById(R.id.tv_tool);
        textView.setText("Profile");

        mAuth = FirebaseAuth.getInstance();

        imageView = findViewById(R.id.imageView);
        textName = findViewById(R.id.textViewName);
        textEmail = findViewById(R.id.textViewEmail);
        button=findViewById(R.id.btn_signout);
        textContact=findViewById(R.id.phoneno);
        textDesignation=findViewById(R.id.designation);
        textGender=findViewById(R.id.gender);
        textViewrate=findViewById(R.id.tv_rateus);
        toolbar=findViewById(R.id.mytool);

        FirebaseUser user = mAuth.getCurrentUser();
        sharedPreferences1 = getSharedPreferences("Info", Context.MODE_PRIVATE);
        contact = sharedPreferences1.getString("number", "");
        designation=sharedPreferences1.getString("designation","");
        gender=sharedPreferences1.getString("gender","");

        textGender.setText(gender);
        textDesignation.setText(designation);
        textContact.setText(contact);
        textViewrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this, RateUs.class));
            }
        });

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });


        Glide.with(this)
                .load(user.getPhotoUrl())
                .into(imageView);

        textName.setText(user.getDisplayName());
        textEmail.setText(user.getEmail());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               logout();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        //if the user is not logged in
        //opening the login activity
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this,R.style.MyDialogTheme);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Profile.this,GmailIntegration.class));
               mAuth.signOut();
                LayoutInflater li = getLayoutInflater();
                View layouttoast = li.inflate(R.layout.toast_sad,(ViewGroup)findViewById(R.id.clayoutsad));
                ((TextView) layouttoast.findViewById(R.id.toastsad)).setText("Logout Successfully");



                Toast mytoast = new Toast(Profile.this);
                mytoast.setView(layouttoast);
                mytoast.setDuration(Toast.LENGTH_LONG);
                mytoast.show();
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LayoutInflater li = getLayoutInflater();
                View layouttoast = li.inflate(R.layout.toast_happy,(ViewGroup)findViewById(R.id.clayouthappy));
                ((TextView) layouttoast.findViewById(R.id.toasthappy)).setText("Welcome back");



                Toast mytoast = new Toast(Profile.this);
                mytoast.setView(layouttoast);
                mytoast.setDuration(Toast.LENGTH_LONG);
                mytoast.show();
                finish();
                dialogInterface.cancel();

            }
        });
        builder.show();

    }
}