package com.example.xinzhang.pastoralmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {
    LatLng melbourne = new LatLng(-34.6375, 144.3639);
    double longtitude = melbourne.longitude;
    double latitude = melbourne.latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText eLong = (EditText) findViewById(R.id.editLong);
        EditText eLat = (EditText) findViewById(R.id.editLat);
        eLong.setText(Double.toString(longtitude));
        eLat.setText(Double.toString(latitude));

    }
    public void onButton2Clicked(View v){
        EditText eLong = (EditText) findViewById(R.id.editLong);
        EditText eLat = (EditText) findViewById(R.id.editLat);
        Double dLong = Double.parseDouble(eLong.getText().toString());
        Double dLat = Double.parseDouble(eLat.getText().toString());
        System.out.print("dLong"+dLong);
        System.out.print("dLat" + dLat);
        Intent i = new Intent(getApplicationContext(), MapsWIthMoveActivity.class);
        i.putExtra("latitude", dLat);
        i.putExtra("longtitude", dLong);
        startActivity(i);
        finish();

    }


    public void onButton3Clicked(View v){
        // select default
        EditText eLong = (EditText) findViewById(R.id.editLong);
        EditText eLat = (EditText) findViewById(R.id.editLat);
        eLong.setText(Double.toString(longtitude));
        eLat.setText(Double.toString(latitude));

    }


}
