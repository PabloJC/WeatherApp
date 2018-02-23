package com.pabji.wheatherapp.presentation.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pabji.wheatherapp.R;
import com.pabji.wheatherapp.data.models.CityModel;
import com.pabji.wheatherapp.data.models.WeatherObservation;
import com.pabji.wheatherapp.presentation.utils.ThermometerAnimation;
import com.pabji.wheatherapp.presentation.ui.constants.IntentConstants;


public class DetailActivity extends AppCompatActivity implements DetailContract.View, OnMapReadyCallback {


    private GoogleMap mMap;

    private DetailPresenter mPresenter;
    private CityModel mCity;
    private ProgressBar thermometer;
    private TextView tvNoData, tvHumidity, tvClouds, tvThermometer;
    private LinearLayout llData;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, DetailActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mCity = intent.getParcelableExtra(IntentConstants.CITY);

        mPresenter = new DetailPresenter(this);
        mPresenter.getCityData(mCity);

        initView();
    }

    private void initView(){

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(mCity.getName());
        }

        llData = findViewById(R.id.llData);
        thermometer = findViewById(R.id.thermometer);
        tvNoData = findViewById(R.id.tv_noData);
        tvHumidity = findViewById(R.id.tv_humidity);
        tvClouds = findViewById(R.id.tv_clouds);
        tvThermometer = findViewById(R.id.tv_thermometer);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {

                if(mCity.hasLocation()){
                    final LatLng latLng = new LatLng(mCity.getLatitude(), mCity.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(mCity.getName()));
                }

                if(mCity.hasBounds()){
                    LatLng southWest = new LatLng(mCity.getSouth(),mCity.getWest());
                    LatLng northEast = new LatLng(mCity.getNorth(),mCity.getEast());
                    final LatLngBounds bounds = new LatLngBounds(southWest, northEast);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,0));
                }

        }});
    }

    @Override
    public void showError(String string) {

    }

    @Override
    public void showNoData() {
        tvNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(WeatherObservation result) {

        llData.setVisibility(View.VISIBLE);

        tvHumidity.setText(String.valueOf(result.getHumidity()));
        tvClouds.setText(result.getClouds());
        tvThermometer.setText(String.format(getString(R.string.degrees),result.getTemperature()));

        ThermometerAnimation anim = new ThermometerAnimation(thermometer, 0, result.getTemperature());
        anim.setDuration(1000);
        thermometer.startAnimation(anim);
    }
}

