package com.example.gpsapp.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationRepository {

    private final FusedLocationProviderClient fusedLocationClient;

    public LocationRepository(Context context) {
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    @SuppressLint("MissingPermission")
    public void getLastLocation(OnSuccessListener<Location> onSuccessListener) {
        fusedLocationClient.getLastLocation().addOnSuccessListener(onSuccessListener);
    }
}