package com.example.fitnessbunduruu.types;

public class Exercise {

    public enum type { CARDIO , STRENGTH }

    private type type;
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
    public Exercise(type type, String name, int easy, int medium, int hard) {

        this.type = type;
        this.name = name;

        difficulty_settings = new int[] { easy, medium, hard };

    }

    public String getName() {
        return this.name;
    }


    public type getType() {
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
