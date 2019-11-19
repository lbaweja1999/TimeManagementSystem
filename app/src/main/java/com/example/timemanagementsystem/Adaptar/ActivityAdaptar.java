package com.example.timemanagementsystem.Adaptar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timemanagementsystem.Model.ActivityModel;
import com.example.timemanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityAdaptar extends RecyclerView.Adapter<ActivityAdaptar.MyHolder> {
    Activity obj;
    int res;
    ArrayList<ActivityModel> arrayList=new ArrayList<>();

    public ActivityAdaptar(Activity obj,int res,ArrayList<ActivityModel> arrayList) {
        this.obj=obj;
        this.res=res;
        this.arrayList=arrayList;
    }
    @Override
    public ActivityAdaptar.MyHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(obj).inflate(res,parent,false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(ActivityAdaptar.MyHolder holder, int position) {

        ActivityModel activityModel=arrayList.get(position);
        holder.textViewid.setText(activityModel.getID());
        holder.textViewact.setText(activityModel.getActivity_type());
        holder.textViewdate.setText(activityModel.getDate());
        holder.textViewtime.setText(activityModel.getTime());
        holder.textViewsoloteam.setText(activityModel.getSolo_team());
        holder.textViewdesc.setText(activityModel.getDescription());
        holder.textViewactivity.append(" "+activityModel.getID());

    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textViewid,textViewact,textViewdate,textViewtime,textViewsoloteam,textViewdesc,textViewactivity;
        public MyHolder( View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.card_view);
            textViewid=itemView.findViewById(R.id.tv_idtext);
            textViewact=itemView.findViewById(R.id.tv_activitytext);
            textViewdate=itemView.findViewById(R.id.tv_datetext);
            textViewtime=itemView.findViewById(R.id.tv_timetext);
            textViewsoloteam=itemView.findViewById(R.id.tv_soloteamtext);
            textViewdesc=itemView.findViewById(R.id.tv_actdestext);
            textViewactivity=itemView.findViewById(R.id.activity);


        }
    }
}
