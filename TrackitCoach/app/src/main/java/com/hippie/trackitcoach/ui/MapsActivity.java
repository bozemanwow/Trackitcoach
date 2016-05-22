package com.hippie.trackitcoach.ui;

import android.content.Context;
import android.content.IntentSender;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.hippie.trackitcoach.R;

import java.util.ArrayList;
import java.util.List;

/*
    wesley osborn
    8/14/2015
 */
//problem could be when initializing the locatin managr.requestLocationUdates(Provider, 1000L, 1f, (LocationListener) this);
//displays correct gps location and gMap view. Can zoom in & out. toast lat & long for accuracy. Needs testing for real time updates
//need to fix the updating. doesnt call onLocation change , possibly location source?


//implment so that it brings up this new activity page instead of GPS_MAIN_PAGE
public class MapsActivity extends FragmentActivity implements  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, LocationSource{
    private int count =0;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GoogleApiClient mGoogleApiClient;

    public static final String TAG = MapsActivity.class.getSimpleName();

    private LocationRequest mLocationRequest;

    private List<LatLng> myPoints;
    Polyline line;

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    // locationmanager not being used right now, need to set it in on create or updates?
    LocationManager locationManager;

    LocationListener locationListener;
    OnLocationChangedListener mListener;

    Location mylocation;
    Location lastloca;
    double currentLatitude;
    double currentLongitude;

    String locationProvider;
    String best;

    PolylineOptions mPolylineOptions;

    SupportMapFragment mFragment;

    LatLng latLng;
    OnMyLocationChangeListener bob;

    @Override
    //set my location true mgiht need to go into oncreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();




        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //locationManager.requestLocationUpdates(location.getProvider(), 10000, 100, (android.location.LocationListener) bob);//might crash
        // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, ); //crashes
        //build for gmaps api
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(5 * 1000)        // 60 seconds, in milliseconds
                .setFastestInterval(1000);
        //.setSmallestDisplacement(10); // 16ms = 60 fps

        myPoints =  new ArrayList<LatLng>();
        mMap.setOnMyLocationChangeListener(new OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {

                if (lastloca == null)
                {
                    lastloca = mylocation;
                }
                else
                {
                    float testloca = lastloca.distanceTo(location);

                    if (testloca >= 3){
                        onLocationChanged(location);
                    }
                    lastloca = mylocation;

                }


            }
        });


    }

    @Override//test code
    protected void onPause(){
        //need the super class of onpause method
        super.onPause();
        //if maps in online and working

        if (mGoogleApiClient.isConnected()){
            //get rid of current location and disconnect
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
            mGoogleApiClient.disconnect();
        }

        // locationManager.removeUpdates(locationListener);
        // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (android.location.LocationListener) this);
    }

    @Override
    protected void onResume() {
        //need the super class
        super.onResume();
        setUpMapIfNeeded();
        // locationManager.requestLocationUpdates(best, 10000,1, (android.location.LocationListener) locationListener);
        //connect back to the api for reuqest of location
        mGoogleApiClient.connect();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                // PolylineOptions poption = new PolylineOptions().add(latLng).width(5).color(Color.BLUE).geodesic(true);
                // mMap.addPolyline(poption);
                //onLocationChanged(latLng);
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //can place multiple markers
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));

    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "Location services connected");
        // updateUI();
        // Location location = LocationServices.......
        mylocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        //Use the request
        if (mylocation == null)
            //if there's no location, grab the location we are at right now
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        else{
            //handles lat & long
            onLocationChanged(mylocation);
            // handleNewLocation(location);

        }

    }
    /*
    private void initializeManager(){
        //get the manager
        this.locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);


        //define the criteria?
        Criteria criteria =  new Criteria();

        this.locationProvider = locationManager.getBestProvider(criteria, false);

        location = locationManager.getLastKnownLocation(locationProvider);

        mMap.setMyLocationEnabled(true);
        mMap.setMapType(mMap.MAP_TYPE_NORMAL);

        //init the location
        if (location !=null)
        {
            onLocationChanged(location);
        }
        //this toast here crashes program
        //Toast.makeText(getApplicationContext(), location.getLatitude() + " OnLocationChange " + location.getLongitude(), Toast.LENGTH_LONG).show();

    }
    */

    private void handleNewLocation(Location location){
        Log.v("Handle New Location", location.toString()+" Count "+String.valueOf(count++));

        // double currentLatitude = location.getLatitude();
        // double currentLongitude = location.getLongitude();
        //set the lat and long into specified data type
        latLng = new LatLng(currentLatitude, currentLongitude);

        //zoom parameter set to 14 // new map camera view from new updated location
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 50);
        //can add multiple markes, see above
      /*  MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("I am here!").draggable(true); */ //it adds a marker on every new location

        // mMap.setOnMarkerDragListener((GoogleMap.OnMarkerDragListener) this);
        mMap.setMyLocationEnabled(true);
        // mMap.addMarker(options);

        //standard map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(update);

        //animated map
        mMap.animateCamera(update);
        //change the number to zoom in and out for start of map
        mMap.animateCamera(CameraUpdateFactory.zoomTo(50));

        //mess around with polylines
        latLng = new LatLng(currentLatitude, currentLongitude);
        myPoints.add(latLng);

        redrawLine();



       /*
        mPolylineOptions =  new PolylineOptions();
        mPolylineOptions.add(latLng);
        mPolylineOptions.add(new LatLng(location.getLatitude(), location.getLongitude()));
       // mPolylineOptions.color(Color.BLUE).width(10);
        mMap.addPolyline(mPolylineOptions);
        */

    }
    //called by handleNew LOcation
    private void redrawLine(){
        // mMap.clear();

        PolylineOptions options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        for (int i = 0; i < myPoints.size(); i++){
            LatLng points = myPoints.get(i);
            options.add(points);
        }
        // mMap.addMarker();
        // mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        line = mMap.addPolyline(options);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please Reconnect");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //check to see if there is in fact a solid connection
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }




    @Override
    public void onLocationChanged(Location location) { //does this handle real time updates? testing required -wesley
        //handles lat & ong updates
        //   locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 0, (android.location.LocationListener) this);
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        latLng = new LatLng(currentLatitude, currentLongitude);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 40);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(update);
        mMap.setMyLocationEnabled(true);
        //animated map
        mMap.animateCamera(update);
        LatLng myLatLang = new LatLng(currentLatitude, currentLongitude);
        myPoints.add(myLatLang);
        PolylineOptions options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        for (int i = 0; i < myPoints.size(); i++){
            LatLng points = myPoints.get(i);
            options.add(points);
        }
        // mMap.addMarker();
        // mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        // line = mMap.addPolyline(options);

        /*
        if (mListener != null)
        {
            mListener.onLocationChanged(location);
        }
        */
        //marked out of here, being hand;ed in handleNewLocation
        /*
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
        this.mMap.moveCamera(center);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        this.mMap.animateCamera(zoom);
        Toast.makeText(getApplicationContext(), location.getLatitude() + " OnLocationChange " + location.getLongitude(), Toast.LENGTH_LONG).show();
       // mMap.clear();
*/
//
        //handleNewLocation(location);
        mMap.addPolyline(options);
        Toast.makeText(getApplicationContext(), location.getLatitude() + " OnLocationChange " + location.getLongitude(), Toast.LENGTH_LONG).show();




    }


    //might need to be moved around; not being used right now
    @Override
    public void activate(OnLocationChangedListener listener) {
        this.mListener = listener;
        // LocationProvider gpsProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
        if (locationManager != null)
        {
            locationManager.requestLocationUpdates(/*gpsProvider.getName()*/LocationManager.GPS_PROVIDER, 60000, 0, (android.location.LocationListener) this); //crash
            // LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);//crash
        }
        /*
        LocationProvider networkProvider = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
        if (networkProvider != null)
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000* 3, 0, (android.location.LocationListener) this);
        }
        */
    }
    //not being used right now
    @Override
    public void deactivate() {
        //  mListener = null;
        locationManager.removeUpdates((android.location.LocationListener) this);
    }

//*/

}
