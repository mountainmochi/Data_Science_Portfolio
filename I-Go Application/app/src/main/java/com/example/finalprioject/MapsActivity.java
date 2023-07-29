package com.example.finalprioject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    // checklist button
    private Button checklistButton;
    // map variable to use
    private GoogleMap mMap;
    // for logs
    private static final String TAG = "MapsActivity";
    /**
     * @param mLocationGranted - boolean to check if app can use user's location
     * @param LOCATION_REQUEST_CODE - used for location permissions
     * @param mFusedLocationProviderClient - used to gain user's info
     */
    private boolean mLocationGranted = false;
    private static final int LOCATION_REQUEST_CODE = 1234;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate : creating the map");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLocationPermission();

        /**
         * makes the button appear when app is opened
         * if the the button is clicked, will call the open checklist method
         */
        checklistButton = findViewById(R.id.ChecklistID);
        checklistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "openCheckList : checklist will cover the map");
                openCheckList();
            }
        });
    }

    /**
     * method that will open the scrolling activity/ checklist
     */
    public void openCheckList() {
        Intent intent = new Intent(this, ScrollingActivity.class);
        startActivity(intent);
    }
    /**
     * Creates pop-up asking if the app can use the user's location in order to gain their current location
     */
    private void getLocationPermission() {
        Log.d(TAG, "Getting the user's permission to use location on the map");
        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        // permission checks
        if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationGranted = true;
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        } else {
            ActivityCompat.requestPermissions(this, permission, LOCATION_REQUEST_CODE);
        }
    }

    /**
     * @param requestCode - request code
     * @param permissions - permissions we are asking for
     * @param grantResults - access grant results
     *  Used to request permissions from the user
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "Requesting the user's permission to use location on the map");
        mLocationGranted = false;
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                        mMap.getUiSettings().setMyLocationButtonEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "Permission was nor granted!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     *  Used to get coordinates of the user and center the map to their current location
     *  Checks to see if the app is able to find the user's location as well
     */
    private void getCurrentLocation() {
        Log.d(TAG, "Getting the user's current location on the map.");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationGranted) {
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: location found on the map");
                            Location current = (Location) task.getResult();
                            assert current != null;
                            LatLng currentLocation = new LatLng(current.getLatitude(), current.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15f));
                        } else {
                            Log.d(TAG, "onComplete: location not found on the map");
                            Toast.makeText(MapsActivity.this, "Location not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.d(TAG, "getting location; security exception" + e.getMessage());
        }
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
        Log.d(TAG, "onMapReady : manipulating the map");
        mMap = googleMap;
        // if location is given/granted, then this method will retrieve the user's location
        if (mLocationGranted) {
            getCurrentLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            // these will create the blue dot indicating the user's location
            // ... and will create the centering button
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
        /**
         * markers for each location
         */
        // Arboretum
        LatLng arboretum = new LatLng(40.0938, -88.2163);
        mMap.addMarker(new MarkerOptions().position(arboretum).title("University of Illinois Arboretum"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(arboretum));

        // Alma! M!A!T!E!R!
        LatLng alma = new LatLng(40.1099, -88.2284);
        mMap.addMarker(new MarkerOptions().position(alma).title("Alma Mater"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(alma));

        // Foellinger Auditorium
        LatLng foellinger = new LatLng(40.1059, -88.2273);
        mMap.addMarker(new MarkerOptions().position(foellinger).title("Foellinger Auditorium"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(foellinger));

        // Grainger Engineering Library
        LatLng grainger = new LatLng(40.1125, -88.2269);
        mMap.addMarker(new MarkerOptions().position(grainger).title("Grainger Engineering Library Information Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(grainger));

        // Hallene Gateway
        LatLng hallene = new LatLng(40.1087, -88.2198);
        mMap.addMarker(new MarkerOptions().position(hallene).title("Hallene Gateway"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hallene));

        // Illini Union
        LatLng illini = new LatLng(40.1092, -88.2272);
        mMap.addMarker(new MarkerOptions().position(illini).title("Illini Union"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(illini));

        // Krannert Center for the Performing Arts
        LatLng krannert = new LatLng(40.1080, -88.2225);
        mMap.addMarker(new MarkerOptions().position(krannert).title("Krannert Center for the Performing Arts"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(krannert));

        // Memorial Stadium
        LatLng memorial = new LatLng(40.0992, -88.2360);
        mMap.addMarker(new MarkerOptions().position(memorial).title("Memorial Stadium"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(memorial));

        // National Center for Supercomputing Applications
        LatLng national = new LatLng(40.1149, -88.2249);
        mMap.addMarker(new MarkerOptions().position(national).title("National Center for Supercomputing Applications"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(national));

        // Thomas M. Siebel Center for Computer Science
        LatLng thomas = new LatLng(40.1138, -88.2249);
        mMap.addMarker(new MarkerOptions().position(thomas).title("Thomas M. Siebel Center for Computer Science"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(thomas));

        // Altgeld Hall
        LatLng altgeld = new LatLng(40.1093, -88.2284);
        mMap.addMarker(new MarkerOptions().position(altgeld).title("Altgeld Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(altgeld));

        // Armory
        LatLng armory = new LatLng(40.1048, -88.2320);
        mMap.addMarker(new MarkerOptions().position(armory).title("Armory"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(armory));

        // Astronomical
        LatLng astronomical = new LatLng(40.1052, -88.2260);
        mMap.addMarker(new MarkerOptions().position(astronomical).title("Astronomical Observatory"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(astronomical));

        // ARC
        LatLng arc = new LatLng(40.1012, -88.2361);
        mMap.addMarker(new MarkerOptions().position(arc).title("Activities and Recreation Center (ARC)"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(arc));

        // Ice Arena
        LatLng ice = new LatLng(40.1058, -88.2325);
        mMap.addMarker(new MarkerOptions().position(ice).title("UI Ice Arena"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ice));

        // Bookstore
        LatLng bookstore = new LatLng(40.1083, -88.2292);
        mMap.addMarker(new MarkerOptions().position(bookstore).title("Illini Union Bookstore"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bookstore));

        // Bell Tower 1111231312312312313123
        LatLng bell = new LatLng(40.1028, -88.2272);
        mMap.addMarker(new MarkerOptions().position(bell).title("McFarland Bell Tower"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bell));

        // Krannert Art Museum and Kinkead Pavilion
        LatLng krannertArt = new LatLng(40.1019, -88.2317);
        mMap.addMarker(new MarkerOptions().position(krannertArt).title("Krannert Art Museum and Kinkead Pavilion"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(krannertArt));

        // Lincoln
        LatLng lincoln = new LatLng(40.1066, -88.2282);
        mMap.addMarker(new MarkerOptions().position(lincoln).title("Lincoln Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lincoln));

        // Morrow
        LatLng morrow = new LatLng(40.1043, -88.2261);
        mMap.addMarker(new MarkerOptions().position(morrow).title("Morrow Plots"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(morrow));

        // Round Dairy Barns
        LatLng round = new LatLng(40.09604, -88.22473);
        mMap.addMarker(new MarkerOptions().position(round).title("Round Dairy Barns"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(round));

        // State Farm
        LatLng state = new LatLng(40.0962, -88.2359);
        mMap.addMarker(new MarkerOptions().position(state).title("State Farm Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(state));

        // Spurlock
        LatLng spurlock = new LatLng(40.1076, -88.2209);
        mMap.addMarker(new MarkerOptions().position(spurlock).title("Spurlock Museum"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(spurlock));

        // University Library
        LatLng univLibrary = new LatLng(40.1047, -88.2290);
        mMap.addMarker(new MarkerOptions().position(univLibrary).title("University Library"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(univLibrary));

        // Beckman
        LatLng beckman = new LatLng(40.1158, -88.2271);
        mMap.addMarker(new MarkerOptions().position(beckman).title("Beckman Institute for Advanced Science and Technology"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(beckman));
    }
}
