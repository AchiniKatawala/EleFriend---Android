package com.example.achini.elefriend;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TimeFormatException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportPopupActivity extends Activity {

    TextView reported_date, reported_time;
    Spinner area_name, numberof_elephants;
    Button button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reportpopup);

//         set the popup display width and height

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int popupwidth = dm.widthPixels;
        int popupheight = dm.heightPixels;

        getWindow().setLayout((int) (popupwidth * .8), (int) (popupheight * .6));

//        get date and time

        Calendar calendar = Calendar.getInstance();
//        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatdate.format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.textViewDate);
        textViewDate.setText(currentDate);

        SimpleDateFormat format = new SimpleDateFormat("HH-mm");
        String currentTime = format.format(calendar.getTime());

        TextView textViewTime = findViewById(R.id.textViewTime);
        textViewTime.setText(currentTime);


//        get current phone number

//        TextView phonenumber = (TextView) findViewById(R.id.textViewPhoneNumber);
//        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        phonenumber.setText((CharSequence) telephonyManager.getLine1Number());



//        pop up spinner


        ArrayAdapter<CharSequence> adapterLocation;
        ArrayAdapter<CharSequence> adapterElephant;

        String[] location = {"A","B","C","D","E","F","G","H"};
        String[] elephnat = {"1", "2","3","4","5","6","more"};

        Spinner locationDrp =(Spinner)findViewById(R.id.spLocation);
        Spinner elephantDrp    =(Spinner)findViewById(R.id.spElephant);


        adapterLocation =    new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,location);
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationDrp.setAdapter(adapterLocation);

        // view the selected item

        locationDrp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(),adapterView.getItemAtPosition(i)+" is selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adapterElephant =     new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,elephnat);
        adapterElephant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        elephantDrp.setAdapter(adapterElephant);

        // view the selected item

        elephantDrp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(),adapterView.getItemAtPosition(i)+" is selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//closed the popup
//        button = (Button) findViewById(R.id.buttonReport);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });


        reported_date = (TextView) findViewById(R.id.textViewDate);
        reported_time = (TextView) findViewById(R.id.textViewTime);
        area_name = (Spinner) findViewById(R.id.spLocation);
        numberof_elephants = (Spinner) findViewById(R.id.spElephant);


    }

//    public void openMapsActivity(){
//        Intent intent = new Intent(this, MapsActivity.class);
//        startActivity(intent);
//getItemAtPosition(i)
//    }

    public void onReport(View view){

        String str_reported_date = reported_date.getText().toString();
        String str_reported_time = reported_time.getText().toString();
        String str_area_name = area_name.getSelectedItem().toString();
        String str_numberof_elephnats = numberof_elephants.getSelectedItem().toString();
        String type = "report";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_reported_date, str_reported_time, str_area_name, str_numberof_elephnats);

    }


}
