package com.example.timemanagementsystem.Model;

public class ActivityModel {
    private String ID;
    private String Activity_type;
    private String Date;
    private String Time;
    private String Solo_team;
    private String Description;


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getActivity_type() {
        return Activity_type;
    }

    public void setActivity_type(String activity_type) {
        Activity_type = activity_type;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getSolo_team() {
        return Solo_team;
    }

    public void setSolo_team(String solo_team) {
        Solo_team = solo_team;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
