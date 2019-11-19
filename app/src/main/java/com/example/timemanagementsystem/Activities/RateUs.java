package com.example.timemanagementsystem.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.timemanagementsystem.R;

public class RateUs extends AppCompatActivity {
    EditText editTextfeedback;
    Button button;
    TextView textView;
    Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);
        textView=findViewById(R.id.tv_tool);
        editTextfeedback=findViewById(R.id.edit_desc);
        textView.setText("Feedback");
        toolbar=findViewById(R.id.mytool);
        button=findViewById(R.id.btn_rating);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RateUs.this, Profile.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedback=editTextfeedback.getText().toString();
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/email");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"lakshay17csu093@ncuindia.edu"});
                intent.putExtra(Intent.EXTRA_SUBJECT,"FEEDBACK FOR TMS");
                intent.putExtra(Intent.EXTRA_TEXT,feedback);
                startActivity(Intent.createChooser(intent,"EmailUs"));

            }
        });
    }
}
