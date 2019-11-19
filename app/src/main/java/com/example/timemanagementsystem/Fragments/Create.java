package com.example.timemanagementsystem.Fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
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
public class Create extends DialogFragment implements AdapterView.OnItemSelectedListener {
    EditText ed1,ed2,ed3;
    Button button,button1;
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

    public Create() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_create, container, false);
        button=view.findViewById(R.id.confirm);
        button1=view.findViewById(R.id.showact);
        myDatabase=new MyDatabase(getActivity());
        ed1=view.findViewById(R.id.edit_hours);
        ed2=(EditText)view.findViewById(R.id.edit_date);
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
                boolean isData=myDatabase.isInsert(spinneritem,ed2.getText().toString(),ed1.getText().toString(),rbvalue,ed3.getText().toString());

                if(spinneritem!=null && ed1.getText().toString()!=null&&ed2.getText().toString()!=null&&rbvalue!=null&&isData==true)
                {
                    View layouttoast = inflater.inflate(R.layout.toast_happy,(ViewGroup)view.findViewById(R.id.clayouthappy));
                    ((TextView) layouttoast.findViewById(R.id.toasthappy)).setText("Activity created successfully.");



                    Toast mytoast = new Toast(getActivity());
                    mytoast.setView(layouttoast);
                    mytoast.setDuration(Toast.LENGTH_LONG);
                    mytoast.show();
                    ed1.setText(null);
                    ed2.setText(null);
                    ed3.setText(null);
                    spinner.setSelection(0);
                    radioGroup.clearCheck();


                }

                else
                {
                    View layouttoast = inflater.inflate(R.layout.toast_unhappy,(ViewGroup)view.findViewById(R.id.clayoutunhappy));
                    ((TextView) layouttoast.findViewById(R.id.toastunhappy)).setText("Please fill in all the details.");



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
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor readDta = myDatabase.getAllData();
                if (readDta.getCount() == 0) {
                    View layouttoast = inflater.inflate(R.layout.toast_unhappy, (ViewGroup) view.findViewById(R.id.clayoutunhappy));
                    ((TextView) layouttoast.findViewById(R.id.toastunhappy)).setText("NO ACTIVITY ADDED");


                    Toast mytoast = new Toast(getActivity());
                    mytoast.setView(layouttoast);
                    mytoast.setDuration(Toast.LENGTH_SHORT);
                    mytoast.show();
                }
                else {

                    StringBuffer buffer=new StringBuffer();
                    while (readDta.moveToNext())
                    {
                        buffer.append("Activity "+readDta.getString(0)+"\n");
                        buffer.append("ID: "+readDta.getString(0)+"\n");
                        buffer.append("Activity Type: "+readDta.getString(1)+"\n");
                        buffer.append("Date: " +readDta.getString(2)+"\n");
                        buffer.append("Time: " +readDta.getString(3)+"\n");
                        buffer.append("Solo/Team: " +readDta.getString(4)+"\n");
                        buffer.append("Description: " +readDta.getString(5)+"\n\n");
                    }

                    showMessage("Your Activities: " ,buffer.toString());
                }
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
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity(),R.style.MyDialogTheme);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Go Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }
}
