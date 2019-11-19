package com.example.timemanagementsystem.Fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.timemanagementsystem.R;
import com.example.timemanagementsystem.database.MyDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class Update extends Fragment implements AdapterView.OnItemSelectedListener {
    EditText ed1,ed2,ed3,ed4;
    Button button;
    Spinner spinner;
    String spinneritem;
    RadioGroup radioGroup;
    RadioButton radioButton,radioButton1;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    MyDatabase myDatabase;
    String rbvalue;
    int selectedId;
    final Calendar mycalendar = Calendar.getInstance();

    public Update() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update, container, false);
        button=view.findViewById(R.id.confirm1);
        myDatabase=new MyDatabase(getActivity());
        ed1=view.findViewById(R.id.edit_hours);
        ed2=(EditText)view.findViewById(R.id.edit_date);
        ed4=view.findViewById(R.id.edit_id);
        radioButton=view.findViewById(R.id.rb_solo);
        radioButton1=view.findViewById(R.id.rb_team);
        ed3=view.findViewById(R.id.edit_description);
        spinner=view.findViewById(R.id.spinner_activity);
        radioGroup=view.findViewById(R.id.rg_soloteam);
        spinner.setOnItemSelectedListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedId = radioGroup.getCheckedRadioButtonId();

                if (selectedId==R.id.rb_solo){
                    rbvalue="Solo";
                }
                else if (selectedId==R.id.rb_team){
                    rbvalue="Team";
                }
                boolean updateData=myDatabase.isUpdate(ed4.getText().toString(),spinneritem,ed2.getText().toString(),ed1.getText().toString(),rbvalue,ed3.getText().toString());

                if(updateData==true)
                {
                    View layouttoast = inflater.inflate(R.layout.toast_happy,(ViewGroup)view.findViewById(R.id.clayouthappy));
                    ((TextView) layouttoast.findViewById(R.id.toasthappy)).setText("Activity updated successfully.");




                    Toast mytoast = new Toast(getActivity());
                    mytoast.setView(layouttoast);
                    mytoast.setDuration(Toast.LENGTH_LONG);
                    mytoast.show();
                    ed4.setText(null);
                    ed1.setText(null);
                    ed2.setText(null);
                    ed3.setText(null);
                    spinner.setSelection(0);
                    radioGroup.clearCheck();


                }

                else
                {
                    View layouttoast = inflater.inflate(R.layout.toast_unhappy,(ViewGroup)view.findViewById(R.id.clayoutunhappy));
                    ((TextView) layouttoast.findViewById(R.id.toastunhappy)).setText("Please enter the ID of the activity");



                    Toast mytoast = new Toast(getActivity());
                    mytoast.setView(layouttoast);
                    mytoast.setDuration(Toast.LENGTH_LONG);
                    mytoast.show();
                }
            }
        });
        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), dateDialog, mycalendar.get(Calendar.YEAR), mycalendar.get(Calendar.MONTH), mycalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(),R.style.MyDatePickerDialogStyle, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String min=String.valueOf(selectedMinute);
                        if (min.length()<2){
                            min="0"+min;
                        }
                        else
                        {
                            min=min;
                        }
                        ed1.setText( selectedHour + ":" + min);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();


            }
        });


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinneritem=spinner.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    DatePickerDialog.OnDateSetListener dateDialog = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dateofMonth) {

            mycalendar.set(year, month, dateofMonth);
            String dateFormat= "dd/MM/YYYY";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
            ed2.setText(simpleDateFormat.format(mycalendar.getTime()));

        }
    };
}
