package com.example.kiwi.mypizzaclient;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String str;
    double dLat;
    double dLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        str = getIntent().getExtras().getString("LOCVAL");
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        String [] separated = str.split(",");
        dLat = Double.parseDouble(separated[0].trim());
        dLong = Double.parseDouble(separated[1].trim());
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
        mMap = googleMap;

        // Add a marker in the ubication and move the camera
        LatLng ubicacion = new LatLng(dLat, dLong);
        MarkerOptions marker = new MarkerOptions().position(ubicacion).title("Marker");
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pizzamoto));
        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
    }
}