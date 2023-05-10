package com.moutamid.ewastemanagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.moutamid.ewastemanagement.databinding.ActivityMapDetailBinding;
import com.moutamid.ewastemanagement.models.AddBusinessModel;
import com.moutamid.ewastemanagement.models.ClusterMarker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapDetailActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private ActivityMapDetailBinding binding;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    double currentLat, currentLng = 0;
    private Marker currentMarker = null;
    private static final int REQUEST_LOCATION = 1;
    private ClusterManager<ClusterMarker> mClusterManager;
    private MyClusterManagerRenderer mClusterManagerRenderer;
    private ArrayList<ClusterMarker> mClusterMarkers = new ArrayList<>();
    private LatLngBounds mMapBoundary;
    private String loc = "";
    private Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        checkInternetAndGPSConnection();

        loc = getIntent().getStringExtra("loc");

        geocoder = new Geocoder(MapDetailActivity.this);

        binding.backbtn.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            bulidGoogleApiClient();

        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.CALL_PHONE}, REQUEST_LOCATION);
            showGPSDialogBox();
        }

    }

    private void showGPSDialogBox() {
        LocationManager enable_gps = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!enable_gps.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder gps = new AlertDialog.Builder(this);
            gps.setMessage("Turn on GPS to find Location").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MapDetailActivity.this, "No location updation without GPS", Toast.LENGTH_SHORT).show();
                }
            }).show();
        }
    }

    private void checkInternetAndGPSConnection() {
        ConnectivityManager connect = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connect.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (!connect.isActiveNetworkMetered() && !info.isConnected()) {
            AlertDialog.Builder internet = new AlertDialog.Builder(MapDetailActivity.this);
            internet.setMessage("Turn on Internet to see Business location")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                            startActivity(intent);
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MapDetailActivity.this, "No route description without Internet", Toast.LENGTH_SHORT).show();
                        }
                    }).show();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                bulidGoogleApiClient();
                // Toast.makeText(MainScreen.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(MainScreen.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected synchronized void bulidGoogleApiClient() {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        client.connect();

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

        // Add a marker in Sydney and move the camera
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        //  bulidGoogleApiClient();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mMap.setMyLocationEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Toast.makeText(this, "" + currentLat, Toast.LENGTH_SHORT).show();
        if (loc.equals("address")){
            mMap.setOnMapClickListener(this);
        }else {
            getMyMarker();
            addMapMarkers();
        }

    }

    private void getMyMarker() {

        if(mClusterManager == null){
            mClusterManager = new ClusterManager<ClusterMarker>(this, mMap);
        }
        if(mClusterManagerRenderer == null){
            mClusterManagerRenderer = new MyClusterManagerRenderer(
                    this,
                    mMap,
                    mClusterManager
            );
            mClusterManager.setRenderer(mClusterManagerRenderer);
        }

        AddBusinessModel model = getIntent().getParcelableExtra("map");
        String avatar = model.getImage();

        ClusterMarker newClusterMarker = new ClusterMarker(
                new LatLng(model.getLat(), model.getLng()),
                model.getBusinessName(),
                "snippet",
                avatar
        );
        mClusterManager.addItem(newClusterMarker);
        mClusterMarkers.add(newClusterMarker);
        mClusterManager.cluster();
        setCameraView(model);
    }


    private void addMapMarkers(){
        if(mClusterManager == null){
            mClusterManager = new ClusterManager<ClusterMarker>(this, mMap);
        }
        if(mClusterManagerRenderer == null){
            mClusterManagerRenderer = new MyClusterManagerRenderer(
                    this,
                    mMap,
                    mClusterManager
            );
            mClusterManager.setRenderer(mClusterManagerRenderer);
        }

        AddBusinessModel model = getIntent().getParcelableExtra("map");
            String avatar = model.getImage();

            ClusterMarker newClusterMarker = new ClusterMarker(
                    new LatLng(model.getLat(), model.getLng()),
                    model.getBusinessName(),
                    "snippet",
                    avatar
            );
            mClusterManager.addItem(newClusterMarker);
            mClusterMarkers.add(newClusterMarker);

        mClusterManager.cluster();
        setCameraViews();
    }

    private void setCameraViews() {
        AddBusinessModel addBusinessModel = getIntent().getParcelableExtra("map");

        // Set a boundary to start
        double bottomBoundary = addBusinessModel.getLat() - .1;
        double leftBoundary = addBusinessModel.getLng() - .1;
        double topBoundary = addBusinessModel.getLat() + .1;
        double rightBoundary = addBusinessModel.getLng() + .1;

        mMapBoundary = new LatLngBounds(
                new LatLng(bottomBoundary, leftBoundary),
                new LatLng(topBoundary, rightBoundary)
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(mMapBoundary, 0));
    }

    private void setCameraView(AddBusinessModel addBusinessModel) {

        // Set a boundary to start
        double bottomBoundary = addBusinessModel.getLat() - .1;
        double leftBoundary = addBusinessModel.getLng() - .1;
        double topBoundary = addBusinessModel.getLat() + .1;
        double rightBoundary = addBusinessModel.getLng() + .1;

        mMapBoundary = new LatLngBounds(
                new LatLng(bottomBoundary, leftBoundary),
                new LatLng(topBoundary, rightBoundary)
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(mMapBoundary, 0));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new com.google.android.gms.location.LocationRequest();
        locationRequest.setInterval(100);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(com.google.android.gms.location.LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this::onLocationChanged);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        currentLat = location.getLatitude();
        currentLng = location.getLongitude();

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("lat",currentLat);
        hashMap.put("lng",currentLng);

    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        Log.d("marker",""+latLng.latitude);
        if (currentMarker != null){
            currentMarker.remove();
        }
        drawMarkers(latLng);
    }

    private void drawMarkers(LatLng latLng) {
        try {
            List<Address> addresseslist = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);

            if (addresseslist.size() > 0){
                Address address = addresseslist.get(0);
                String streetAdr = address.getAddressLine(0);
                currentMarker = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                        .title(streetAdr));
                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("meeting_lat",latLng.latitude);
                hashMap.put("meeting_lng",latLng.longitude);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}