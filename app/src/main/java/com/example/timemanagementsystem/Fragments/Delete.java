package com.example.timemanagementsystem.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timemanagementsystem.R;
import com.example.timemanagementsystem.database.MyDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class Delete extends Fragment {
EditText editText;
Button button;
MyDatabase myDatabase;
    public Delete() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_delete, container, false);
        editText=view.findViewById(R.id.edit_id1);
        button=view.findViewById(R.id.confirm2);
        myDatabase=new MyDatabase(getActivity());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int deldata=myDatabase.deleteData(editText.getText().toString());
                if (deldata>0){
                    View layouttoast = inflater.inflate(R.layout.toast_happy,(ViewGroup)view.findViewById(R.id.clayouthappy));
                    ((TextView) layouttoast.findViewById(R.id.toasthappy)).setText("Activity deleted successfully.");



                    Toast mytoast = new Toast(getActivity());
                    mytoast.setView(layouttoast);
                    mytoast.setDuration(Toast.LENGTH_LONG);
                    mytoast.show();
                    editText.setText(null);
                }
                else {
//                    View layouttoast = inflater.inflate(R.layout.toast_unhappy,(ViewGroup)view.findViewById(R.id.clayoutunhappy));
//                    ((TextView) layouttoast.findViewById(R.id.toastunhappy)).setText("Please enter the ID of the \n\t\t\t\t\t\t\t\t\t\t\t\t\t activity.");
//
//
//
//                    Toast mytoast = new Toast(getActivity());
//                    mytoast.setView(layouttoast);
//                    mytoast.setDuration(Toast.LENGTH_LONG);
//                    mytoast.show();
                    editText.setError("ID not found");

                }

            }
        });
        return view;
    }

}
