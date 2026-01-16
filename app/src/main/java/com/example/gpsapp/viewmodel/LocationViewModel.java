package com.example.gpsapp.viewmodel;

import android.app.Application;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gpsapp.data.LocationRepository;
import com.example.gpsapp.model.LocationData;

public class LocationViewModel extends AndroidViewModel {

    private final LocationRepository repository;
    private final MutableLiveData<String> locationText = new MutableLiveData<>();

    // LiveData para pasar la ubicación al mapa
    private final MutableLiveData<Location> location = new MutableLiveData<>();

    public LocationViewModel(@NonNull Application application) {
        super(application);
        repository = new LocationRepository(application);
        locationText.setValue("Presiona el botón para obtener la ubicación");
    }

    public LiveData<String> getLocationText() {
        return locationText;
    }

    public LiveData<Location> getLocation() {
        return location;
    }

    public void fetchLocation() {
        repository.getCurrentLocation(loc -> {
            if (loc != null) {
                // Actualizamos el modelo de texto
                LocationData data = new LocationData(loc.getLatitude(), loc.getLongitude());
                locationText.setValue(data.toString());

                // Actualizamos el objeto Location para el mapa
                location.setValue(loc);
            } else {
                locationText.setValue("No se pudo obtener la ubicación actual");
            }
        });
    }
}