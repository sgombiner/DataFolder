package com.example.datafolder;

import java.io.Serializable;
import java.util.ArrayList;

class music {
    final private String name;
    private float interactions;
    final private String released;

    private static ArrayList<music> allData = new ArrayList<>();

    public music(String name, float interactions, String released) {
        this.name = name;
        this.interactions = interactions;
        this.released = released;
        allData.add(this);
    }

    public static ArrayList<music> getAllData() {
        return allData;
    }

    public static void setAllData(ArrayList<music> allData) {
        music.allData = allData;
    }

    public void setInteractions(float interactions) {
        this.interactions = interactions;
    }

    public String getName() {
        return name;
    }

    public float getInteractions() {
        return interactions;
    }

    public String getReleased() {
        return released;
    }

    void describeSelf() {
        System.out.println(getName() + " is on youtube with " + getInteractions() + " billion views. It was uploaded on " + getReleased() + ".");
    }
}

