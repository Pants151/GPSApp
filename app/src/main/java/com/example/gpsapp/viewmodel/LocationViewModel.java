package com.example.gpsapp.viewmodel;

import android.app.Application;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.gpsapp.data.LocationRepository;

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
        repository.getLastLocation(location -> {
            if (location != null) {
                formatLocation(location);
            } else {
                locationText.setValue("No se pudo obtener la ubicación");
            }
        });
    }

    private void formatLocation(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        locationText.setValue("Latitud: " + lat + "\nLongitud: " + lon);
    }
}