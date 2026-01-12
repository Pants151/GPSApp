package com.example.gpsapp.viewmodel;

import android.app.Application;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gpsapp.data.LocationRepository;
import com.example.gpsapp.model.LocationData; // Importamos el modelo

public class LocationViewModel extends AndroidViewModel {

    private final LocationRepository repository;
    private final MutableLiveData<String> locationText = new MutableLiveData<>();

    public LocationViewModel(@NonNull Application application) {
        super(application);
        repository = new LocationRepository(application);
        locationText.setValue("Presiona el botón para obtener la ubicación");
    }

    public LiveData<String> getLocationText() {
        return locationText;
    }

    public void fetchLocation() {
        repository.getCurrentLocation(location -> {
            if (location != null) {
                // Usamos el modelo LocationData
                LocationData data = new LocationData(location.getLatitude(), location.getLongitude());
                locationText.setValue(data.toString());
            } else {
                locationText.setValue("No se pudo obtener la ubicación actual");
            }
        });
    }
}