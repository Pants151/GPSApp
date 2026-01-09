package com.example.gpsapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.gpsapp.viewmodel.LocationViewModel;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_CODE = 100;

    private TextView txtLocation;
    private Button btnGetLocation;

    private LocationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtLocation = findViewById(R.id.txtLocation);
        btnGetLocation = findViewById(R.id.btnGetLocation);

        // Inicializar ViewModel
        viewModel = new ViewModelProvider(this).get(LocationViewModel.class);

        // Observar cambios
        viewModel.getLocationText().observe(this, text -> {
            txtLocation.setText(text);
        });

        btnGetLocation.setOnClickListener(v -> checkPermissionAndGetLocation());
    }

    private void checkPermissionAndGetLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_CODE);
        } else {
            viewModel.fetchLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.fetchLocation();
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}