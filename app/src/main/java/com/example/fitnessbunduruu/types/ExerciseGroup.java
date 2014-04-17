package com.example.fitnessbunduruu.types;

import java.util.ArrayList;

public class ExerciseGroup {

    private String name;


    private final ArrayList<Exercise> list;

    public ExerciseGroup(String name) {

        this.name = name;
        this.list = new ArrayList<Exercise>();

    }

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
