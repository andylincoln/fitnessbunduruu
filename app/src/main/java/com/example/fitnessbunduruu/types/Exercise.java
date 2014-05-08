// Exercise.java
// Data structure to hold characteristics of a particular exercise.

package com.example.fitnessbunduruu.types;

import android.os.Parcel;
import android.os.Parcelable;

public class Exercise implements Parcelable{
    // Implement the Parcelable class so we can pass Exercise object instances between activities.

    public enum workoutType { CARDIO , STRENGTH }

    private workoutType type;
    private String name;

    private int[] difficulty_settings;

    /**
     *
     * @param type
     * @param name
     * @param easy
     * @param medium
     * @param hard
     */
    public Exercise(workoutType type, String name, int easy, int medium, int hard) {

        this.type = type;
        this.name = name;

        difficulty_settings = new int[] { easy, medium, hard };
    }

    // BEGIN FUNCTIONS REQUIRED TO IMPLEMENT PARCELABLE.
    private Exercise(Parcel in) {
        // This constructor is called in the activity receiving the Exercise object.
        // Unpackage the data.
        this.name = in.readString();

    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        // This overloaded function is called in the activity sending the Exercise object.
        // Package the data for transport.
        out.writeString(this.name);
    }


    public static final Parcelable.Creator<Exercise> CREATOR
            = new Parcelable.Creator<Exercise>() {
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    }; // END FUNCTIONS REQUIRED TO IMPLEMENT PARCELABLE.

    public String getName() {
        return this.name;
    }

    public workoutType getType() {
        return this.type;
    }

    public int getEasyLength() {
        return this.difficulty_settings[0];
    }

    public int getMediumLength() {
        return this.difficulty_settings[1];
    }

    public int getHardLength() {
        return this.difficulty_settings[2];
    }

    public void setEasyLength(int newLength) {
        this.difficulty_settings[0] = newLength;
    }

    public void setMediumLength(int newLength) {
        this.difficulty_settings[1] = newLength;
    }

    public void setHardLength(int newLength) {
        this.difficulty_settings[2] = newLength;
    }
}