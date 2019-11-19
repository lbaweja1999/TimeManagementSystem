package com.example.timemanagementsystem.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timemanagementsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Info extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
   RadioGroup radioGroup;
   TextView textView;
   RadioButton radioButton;
   EditText editText;
   String spinneritem;

    Spinner spinner;
   Button button;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        radioGroup=findViewById(R.id.rg_gender);
        editText=findViewById(R.id.edit_number);
        button=findViewById(R.id.btn_submit);
        textView=findViewById(R.id.tv_tool);
        textView.setText("Info");
        mAuth = FirebaseAuth.getInstance();
        spinner=findViewById(R.id.spinner_desg);
        spinner.setOnItemSelectedListener(this);
        sharedPreferences=getSharedPreferences("Info", Context.MODE_PRIVATE);

        boolean logged=sharedPreferences.getBoolean("isLogIn",false);//true

        if (logged) {


            startActivity(new Intent(Info.this, MainActivity.class));

        }



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int selectedId = radioGroup.getCheckedRadioButtonId();

                radioButton =  findViewById(selectedId);
                String gender= (String) radioButton.getText().toString();
                spinneritem=spinner.getSelectedItem().toString();
                String number=editText.getText().toString().trim();

                if (TextUtils.isEmpty(number) || number.length()<10){
                    editText.setError("Please enter a valid mobile number");
                    editText.requestFocus();
                    return;

                }
                editor=sharedPreferences.edit();
                editor.putString("gender",gender);
                editor.putString("number",number);
                editor.putString("designation",spinneritem);
                editor.putBoolean("isLogIn",true);
                editor.commit();
                Intent intent=new Intent(Info.this,MainActivity.class);
                startActivity(intent);
                FirebaseUser user = mAuth.getCurrentUser();
                LayoutInflater li=getLayoutInflater();
                View layouttoast = li.inflate(R.layout.toast_happy,(ViewGroup)findViewById(R.id.clayouthappy));
                ((TextView) layouttoast.findViewById(R.id.toasthappy)).setText("Welcome "+user.getDisplayName());



                Toast mytoast = new Toast(Info.this);
                mytoast.setView(layouttoast);
                mytoast.setDuration(Toast.LENGTH_LONG);
                mytoast.show();

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinneritem=spinner.getSelectedItem().toString();


        //((TextView) adapterView.getChildAt(0)).setTextColor(Color.parseColor("#E70D0D"));


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
