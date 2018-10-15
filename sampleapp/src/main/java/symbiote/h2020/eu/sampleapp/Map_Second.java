package symbiote.h2020.eu.sampleapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Map_Second extends AppCompatActivity implements OnMapReadyCallback {

    SharedPreferences sharedPrefs;
    Gson gson = new Gson();


    ArrayList<Float> nitrogenLimassol = new ArrayList<Float>();
    ArrayList<Float> sulphurLimassol = new ArrayList<Float>();
    ArrayList<Float> ozoneLimassol = new ArrayList<Float>();
    ArrayList<Float> pm10Limassol = new ArrayList<Float>();
    ArrayList<Float> pm25Limassol = new ArrayList<Float>();


    @Override
    public void onMapReady(GoogleMap googleMap) {

        Toast.makeText(this, "Ο χάρτης είναι έτοιμος", Toast.LENGTH_SHORT).show();

        mMap = googleMap;

        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng limassol = new LatLng(34.680307, 33.04332);
        googleMap.addMarker(new MarkerOptions().position(limassol)
                .snippet("Δείτε περισσότερα")
                .title("Λεμεσός"));

        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(34.680307,
                        33.043273));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(10);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);



        ///if (nitrogenVienna.get(0) > 17) {

        /*LatLng vienna = new LatLng(48.208174, 16.373819);
        googleMap.addMarker(new MarkerOptions().position(vienna)
                .title("Βιέννη")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .snippet("Δείτε περισσότερα"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(vienna));*/


        ///} else {

            /*LatLng vienna = new LatLng(48.208174, 16.373819);
            googleMap.addMarker(new MarkerOptions().position(vienna)
                    .title("Βιέννη")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                    .snippet("Τιμή AQI: " + nitrogenVienna.get(0) + "\n Τιή"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(vienna));

        }*/


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                if (marker.getTitle().equals("Λεμεσός")) {
                    marker.showInfoWindow();
                } else if (marker.getTitle().equals("Ζάγκρεμπ")) {
                    marker.showInfoWindow();
                } else if (marker.getTitle().equals("Βιέννη")) {
                    marker.showInfoWindow();
                }

                return false;
            }
        });


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent1 = new Intent(Map_Second.this, MainActivity.class);
                String title = marker.getTitle();
                System.out.println(title);
                if (title.equals("Λεμεσός")) {

                    startActivity(new Intent(Map_Second.this, Limassol.class));

                }

            }
        });


    }//end of method

    private static final String TAG = "Map";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map__second);

        System.out.println("Welcome to MAP SECOND");

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(Map_Second.this);
        Type type = new TypeToken<List<String>>() {
        }.getType();



        //Data - Limassol
        String json2 = sharedPrefs.getString("array_limassol_nitrogen", "");
        List<String> arrayList2 = gson.fromJson(json2, type);
        //System.out.println("Length is: "+arrayList2.size());

        for (int counter = 0; counter < arrayList2.size(); counter++) {

            String value = arrayList2.get(counter);
            if (value.contains(",")) {
                value = value.replace(",", ".");
            }
            float final_float = Float.valueOf(value);
            String val = String.format("%.2f", final_float);
            if (val.contains(",")) {
                val = value.replace(",", ".");
            }
            float final_float_total = Float.valueOf(val);
            nitrogenLimassol.add(final_float_total);

        }


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //getLocationPermission();


        /*if (isServicesOk()) {
            init();
        }*/

    }


    public boolean isServicesOk() {

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Map_Second.this);

        if (available == ConnectionResult.SUCCESS) {
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {

            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Map_Second.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {

            System.out.println("TOTAL ERROR");
        }

        return false;
    }


    private void initMap() {
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(Map_Second.this);

    }


    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }


}
