// ExerciseGroup.java
// Data structure to manage a series of different exercises.

package com.example.fitnessbunduruu.types;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ExerciseGroup implements Parcelable {
    // Implement the Parcelable class so that we can pass ExerciseGroup object instances between activities.

    private String name;

    private ArrayList<Exercise> list;

    public ExerciseGroup(String name) {

        this.name = name;
        this.list = new ArrayList<Exercise>();

    }

    // BEGIN FUNCTIONS REQUIRED TO IMPLEMENT PARCELABLE.
    private ExerciseGroup(Parcel in) {
        // This constructor is called in the activity receiving the ExerciseGroup object.
        // Unpackage the data.

        name = in.readString();
        this.list = in.readArrayList(Exercise.class.getClassLoader());

    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        // This overloaded function is called in the activity sending the Exercise object.
        // Package the data for transport.

        out.writeString(this.name);
        out.writeList(this.list);
    }

    public static final Parcelable.Creator<ExerciseGroup> CREATOR
            = new Parcelable.Creator<ExerciseGroup>() {
        public ExerciseGroup createFromParcel(Parcel in) {
            return new ExerciseGroup(in);
        }

        public ExerciseGroup[] newArray(int size) {
            return new ExerciseGroup[size];
        }
    }; // END FUNCTIONS REQUIRED TO IMPLEMENT PARCELABLE.

    public void add(Exercise exercise) {
        this.list.add(exercise);
    }

    public void delete(int index) {
        this.list.remove(index);
    }

    public String getName() {
        return this.name;
    }

    public Exercise getExerciseAt(int position) {
        return this.list.get(position);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return this.list.size();
    }
}