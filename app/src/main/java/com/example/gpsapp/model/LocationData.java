package com.example.gpsapp.model;

public class LocationData {
    private double latitude;
    private double longitude;

    public LocationData(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Latitud: " + latitude + "\nLongitud: " + longitude;
    }
}