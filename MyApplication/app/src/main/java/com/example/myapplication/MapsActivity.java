package com.example.myapplication;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener{

    private GoogleMap mMap;
    private Location location;
    private LocationManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria c = new Criteria();
        String provider = lm.getBestProvider(c, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Toast.makeText(this, "Location" + location, Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        if (location != null) {
            onLocationChanged(location);
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 1000, this);
    }

    @Override
    public void onLocationChanged(Location location) {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Toast.makeText(this, ""+latitude+"\n"+longitude, Toast.LENGTH_SHORT).show();
            // Add a marker in Sydney and move the camera
            Geocoder geo = new Geocoder(this, Locale.getDefault());
            String address="";
            try {
                List<Address> ad = geo.getFromLocation(latitude, longitude, 1);
                Address add = ad.get(0);
                address = add.getCountryName() + "\n" + add.getAdminArea() + "\n" + add.getLocality();
            }
            catch (Exception e) {
            }
            LatLng sydney = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(sydney).title("This is me"+address));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(30));
            Toast.makeText(this, ""+address, Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}