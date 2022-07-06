package com.example.aplicacion;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.aplicacion.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    TextView tvLatitud, tvLongitud, tvDireccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,},1000);

        }else {
            locationStart();
        }
    }

    private void locationStart(){
        LocationManager mlocManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);

        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!gpsEnabled){
            Intent intentgps = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intentgps);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,(LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,(LocationListener) Local);
        tvLatitud.setText("Localización GPS");
        tvDireccion.setText("");

    }

    public void setLocation(Location loc) {

        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    tvDireccion.setText(DirCalle.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class Localizacion implements LocationListener {
        MapsActivity mainActivity;

        public MapsActivity getMainActivity() {
            return mainActivity;
        }

        public void setMainActivity(MapsActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            loc.getLatitude();
            loc.getLongitude();
            tvLatitud.setText(String.valueOf(loc.getLatitude()));
            tvLongitud.setText(String.valueOf(loc.getLongitude()));

            this.mainActivity.setLocation(loc);

        }
        @Override
        public void onProviderDisabled(String provider){

            tvLatitud.setText("GPS Desactivado");

        }

        @Override
        public void onProviderEnabled(String provider){

            tvLatitud.setText("GPS Activado");

        }
        @Override
        public void onStatusChanged(String provider, int status,Bundle extras){
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }

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
        mMap = googleMap;

        LatLng Unach00 = new LatLng(-36.63892977884602, -71.99611065863944);
        LatLng Unach01 = new LatLng(-36.6383328048202, -71.99573867292989);
        LatLng Unach02 = new LatLng(-36.63759918143771, -71.99704867239205);
        LatLng Unach03 = new LatLng(-36.637783034359465, -71.99663150595447);
        LatLng Unach04 = new LatLng(-36.63726380118998, -71.99760598795181);
        LatLng Unach05 = new LatLng(-36.6374820113665, -71.99544846027882);
        LatLng Unach06 = new LatLng(-36.63679421430649, -71.99624468851982);
        LatLng Unach07 = new LatLng(-36.63676638736424, -71.99658369605608);
        LatLng Unach08 = new LatLng(-36.63691732167533, -71.99540138073398);
        LatLng Unach09 = new LatLng(-36.63700356971585, -71.9942101084721);
        LatLng Unach10 = new LatLng(-36.636601078034, -71.99529389752237);
        LatLng Unach11 = new LatLng(-36.63614827237859, -71.99473856759579);
        LatLng Unach12 = new LatLng(-36.63561640201059, -71.99684344715425);
        LatLng Unach13 = new LatLng(-36.63626327088338, -71.99752417416106);
        LatLng Unach14 = new LatLng(-36.636708888503094, -71.99749730335814);
        LatLng Unach15 = new LatLng(-36.63759292873308, -71.99845569532823);
        LatLng Unach16 = new LatLng(-36.638764446092225, -71.99525806978319);
        LatLng Unach17 = new LatLng(-36.63770792509058, -71.9948102230682);
        LatLng Unach18 = new LatLng(-36.638498520414075, -71.99335919972374);
        LatLng Unach19 = new LatLng(-36.636407018796476, -71.99277699899426);
        LatLng Unach20 = new LatLng(-36.63732699899973, -71.99928869020385);
        LatLng Unach21 = new LatLng(-36.636989195040925, -71.99996941721062);
        LatLng Unach22 = new LatLng(-36.63704669369171, -71.99857213545985);
        LatLng Unach23 = new LatLng(-36.636845448226246, -71.99850943691976);
        LatLng Unach24 = new LatLng(-36.636687326420336, -71.99840195370817);
        LatLng Unach25 = new LatLng(-36.63650045477678, -71.99816907341636);



        mMap.addMarker(new MarkerOptions().position(Unach00).title("porteria"));
        mMap.addMarker(new MarkerOptions().position(Unach01).title("Templo unach"));
        mMap.addMarker(new MarkerOptions().position(Unach02).title("rectoria"));
        mMap.addMarker(new MarkerOptions().position(Unach03).title("biblioteca"));
        mMap.addMarker(new MarkerOptions().position(Unach04).title("patio de las banderas"));
        mMap.addMarker(new MarkerOptions().position(Unach05).title("DTI"));
        mMap.addMarker(new MarkerOptions().position(Unach06).title("FAE"));
        mMap.addMarker(new MarkerOptions().position(Unach07).title("VDE"));
        mMap.addMarker(new MarkerOptions().position(Unach08).title("FACS"));
        mMap.addMarker(new MarkerOptions().position(Unach09).title("Recidencias damas"));
        mMap.addMarker(new MarkerOptions().position(Unach10).title("Facultad de teologia"));
        mMap.addMarker(new MarkerOptions().position(Unach11).title("comedor institucional"));
        mMap.addMarker(new MarkerOptions().position(Unach12).title("Recidencias varones"));
        mMap.addMarker(new MarkerOptions().position(Unach13).title("instituto"));
        mMap.addMarker(new MarkerOptions().position(Unach14).title("aulas b"));
        mMap.addMarker(new MarkerOptions().position(Unach15).title("salon estudiantil"));
        mMap.addMarker(new MarkerOptions().position(Unach16).title("Colegio adventista"));
        mMap.addMarker(new MarkerOptions().position(Unach17).title("instituto de musica"));
        mMap.addMarker(new MarkerOptions().position(Unach18).title("campo deportivo"));
        mMap.addMarker(new MarkerOptions().position(Unach19).title("ddc/radio"));
        mMap.addMarker(new MarkerOptions().position(Unach20).title("Gym unach"));
        mMap.addMarker(new MarkerOptions().position(Unach21).title("Gimnasio institucional"));
        mMap.addMarker(new MarkerOptions().position(Unach22).title("recursos humanos"));
        mMap.addMarker(new MarkerOptions().position(Unach23).title("centro de estudio"));
        mMap.addMarker(new MarkerOptions().position(Unach24).title("direccion de vinculacion con el medio"));
        mMap.addMarker(new MarkerOptions().position(Unach25).title("FAIN"));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(Unach00));
        mMap.setMinZoomPreference(10.0f);
        mMap.setMaxZoomPreference(50.0f);
    }
}