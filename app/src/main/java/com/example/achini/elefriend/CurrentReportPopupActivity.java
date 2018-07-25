package com.example.achini.elefriend;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentReportPopupActivity extends AppCompatActivity {


    TextView reported_date, reported_time, latitude, longitude;
    Spinner numberof_elephants;
    static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_current_reportpopup);


        //         set the popup display width and height

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int popupwidth = dm.widthPixels;
        int popupheight = dm.heightPixels;

        getWindow().setLayout((int) (popupwidth * .8), (int) (popupheight * .6));

        //        get date and time

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatdate.format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.textViewDateCurrent);
        textViewDate.setText(currentDate);

        SimpleDateFormat format = new SimpleDateFormat("HH-mm");
        String currentTime = format.format(calendar.getTime());

        TextView textViewTime = findViewById(R.id.textViewTimeCurrent);
        textViewTime.setText(currentTime);

        //get spinner

        ArrayAdapter<CharSequence> adapterElephantCurrent;

        String[] elephantCurrent = {"1", "2", "3", "4", "5", "6", "more"};
        Spinner elephantDrpCurrent = (Spinner) findViewById(R.id.spElephantCurrent);

        adapterElephantCurrent = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, elephantCurrent);
        adapterElephantCurrent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        elephantDrpCurrent.setAdapter(adapterElephantCurrent);

        // view the selected item

        elephantDrpCurrent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), adapterView.getItemAtPosition(i) + " is selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        reported_date = (TextView) findViewById(R.id.textViewDateCurrent);
        reported_time = (TextView) findViewById(R.id.textViewTimeCurrent);
        latitude = (TextView) findViewById(R.id.tLatitude);
        longitude = (TextView) findViewById(R.id.tLongitude);
        numberof_elephants = (Spinner) findViewById(R.id.spElephantCurrent);

        //get Current Location

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getCurrentLocation();


    }

    void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

        }
        else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null){
                double lati = location.getLatitude();
                double longi = location.getLongitude();

                ((EditText)findViewById(R.id.tLatitude)).setText(""+lati);
                ((EditText)findViewById(R.id.tLongitude)).setText(""+longi);
            }
            else {
                ((EditText)findViewById(R.id.tLatitude)).setText("No latitude");
                ((EditText)findViewById(R.id.tLongitude)).setText("No longitude");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permission, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permission, grantResults);

        switch (requestCode){
            case REQUEST_LOCATION:
                getCurrentLocation();
                break;
        }
    }

    public void onReport(View view){

        String str_reported_dateCurrent = reported_date.getText().toString();
        String str_reported_timeCurrent = reported_time.getText().toString();
        String str_latitudeCurrent = latitude.getText().toString();
        String str_longitudeCurrent = longitude.getText().toString();
        String str_numberof_elephnatsCurrent = numberof_elephants.getSelectedItem().toString();
        String type = "reportCurrent";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_reported_dateCurrent, str_reported_timeCurrent, str_latitudeCurrent, str_longitudeCurrent, str_numberof_elephnatsCurrent);
    }

}
