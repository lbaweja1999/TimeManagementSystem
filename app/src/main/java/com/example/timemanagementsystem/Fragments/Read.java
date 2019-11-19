package com.example.timemanagementsystem.Fragments;


import android.database.Cursor;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timemanagementsystem.Adaptar.ActivityAdaptar;
import com.example.timemanagementsystem.Model.ActivityModel;
import com.example.timemanagementsystem.R;
import com.example.timemanagementsystem.database.MyDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Read extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ActivityModel> Al = new ArrayList<>();
    MyDatabase myDatabase;
    TextView textViewnodata;
    CardView cardView;
    String id, act, date, time, soloteam, des;

    ActivityModel activityModel;

    public Read() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read, container, false);

        recyclerView = view.findViewById(R.id.rv1);
        cardView = view.findViewById(R.id.card_view);

        myDatabase = new MyDatabase(getActivity());

        activityModel = new ActivityModel();

        Cursor readDta = myDatabase.getAllData();
        if (readDta.getCount() == 0) {
            View layouttoast = inflater.inflate(R.layout.toast_unhappy, (ViewGroup) view.findViewById(R.id.clayoutunhappy));
            ((TextView) layouttoast.findViewById(R.id.toastunhappy)).setText("NO ACTIVITY ADDED");


            Toast mytoast = new Toast(getActivity());
            mytoast.setView(layouttoast);
            mytoast.setDuration(Toast.LENGTH_SHORT);
            mytoast.show();
        }
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer();
        StringBuffer buffer3 = new StringBuffer();
        StringBuffer buffer4 = new StringBuffer();
        StringBuffer buffer5 = new StringBuffer();
        while (readDta.moveToNext()) {
            buffer.append(readDta.getString(0));
            buffer1.append(readDta.getString(1));
            buffer2.append(readDta.getString(2));
            buffer3.append(readDta.getString(3));
            buffer4.append(readDta.getString(4));
            buffer5.append(readDta.getString(5));



        }
        activityModel.setID(buffer.toString());
        activityModel.setActivity_type(buffer1.toString());
        activityModel.setDate(buffer2.toString());
        activityModel.setTime(buffer3.toString());
        activityModel.setSolo_team(buffer4.toString());
        activityModel.setDescription(buffer5.toString());
        Al.add(activityModel);

        ActivityAdaptar obj = new ActivityAdaptar(getActivity(), R.layout.mycard, Al);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(obj);


        return view;

    }
}