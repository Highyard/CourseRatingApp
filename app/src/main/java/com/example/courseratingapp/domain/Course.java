package com.example.courseratingapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {

    private float currentOverallRating;
    private float newRating;

    public Course(){

    }

    public Course(float currentOverallRating, float newRating) {
        this.currentOverallRating = currentOverallRating;
        this.newRating = newRating;
    }

    protected Course(Parcel in) {
        currentOverallRating = in.readFloat();
        newRating = in.readFloat();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public float getCurrentOverallRating() {
        return currentOverallRating;
    }

    public void setCurrentOverallRating(float currentOverallRating) {
        this.currentOverallRating = currentOverallRating;
    }

    public float getNewRating() {
        return newRating;
    }

    public void setNewRating(float newRating) {
        this.newRating = newRating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(currentOverallRating);
        dest.writeFloat(newRating);
    }
}