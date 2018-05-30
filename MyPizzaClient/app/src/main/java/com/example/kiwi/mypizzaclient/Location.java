package com.example.kiwi.mypizzaclient;

/**
 * Created by kiwi_ on 18/1/2018.
 */

public class Location {

    private int ID;
    private double LATITUD;
    private double LONGITUD;

    public Location() {}

    public int getId() {
        return ID;
    }

    public double getLatitud() {
        return LATITUD;
    }

    public double getLongitud() {
        return LONGITUD;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public void setLatitud(double latitud) {
        this.LATITUD = latitud;
    }

    public void setLongitud(double longitud) {
        this.LONGITUD = longitud;
    }
}
