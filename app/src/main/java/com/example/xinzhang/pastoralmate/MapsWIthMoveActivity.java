package com.example.xinzhang.pastoralmate;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class MapsWIthMoveActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng location;
    MessageBestLocs locs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_with_move);
        Intent myIntent = getIntent();
        Bundle extras = myIntent.getExtras();
        location = new LatLng(extras.getDouble("latitude",0),extras.getDouble("longtitude",0));
        System.out.println("long "+location.longitude+",lat "+location.latitude);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private class AskForLocationsTask extends AsyncTask<Void, Void, MessageBestLocs> {
        @Override
        protected MessageBestLocs doInBackground(Void... voids) {
            Socket socket = null;
            try {
                DataInputStream in;
                DataOutputStream out;
                BufferedReader inreader;
                PrintWriter outwriter;
                System.out.println("opening socket");
                socket = new Socket("100.13.44.120", 2020);
                //socket = new Socket("54.200.222.165", 8000);
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                inreader = new BufferedReader( new InputStreamReader(in));
                outwriter = new PrintWriter(out, true);
                    // return current location
                Gson gson = new Gson();
                String json = gson.toJson(new MessageAskLocation("askLocations", new MessageLocation(location.longitude, location.latitude)));
                outwriter.println(json);
                outwriter.flush();
                outwriter.close();
                System.out.println("Done sending");

                try {
                    String receive_line = inreader.readLine();
                        System.out.print("receiving: " + receive_line);
                        locs = gson.fromJson(receive_line, MessageBestLocs.class);


                     //Following is what bad code
                     //create some returnedlocs.
//                   MessageReturnedLocation rl1 = new MessageReturnedLocation(143.2, -32, "Normal");
//                    MessageReturnedLocation rl2 = new MessageReturnedLocation(141.11, -34.11, "Good");
//                    ArrayList<MessageReturnedLocation> rls = new ArrayList<>();
//                    rls.add(rl1);
//                    rls.add(rl2);
//                    locs.setLocations(rls);
                System.out.println("locs done");
               } catch (IOException e){}
            }catch (UnknownHostException ue){
                System.out.println("unkownHost");
            }
            catch (IOException e){
                System.out.println("IO");
            } finally {
                if (socket != null) {
                    try  {
                        socket.close();
                    } catch (IOException e){

                    }
                }
            }
            return locs;
        }
        @Override
        protected void onPostExecute(MessageBestLocs mlocs) {
            System.out.println("got locs");
            ArrayList<MessageReturnedLocation> relocs;
//            if (mlocs != null){
//                relocs = mlocs.getLocations();
//
//            } else {
                MessageReturnedLocation rl1 = new MessageReturnedLocation(143.2, -32, "Normal");
                MessageReturnedLocation rl2 = new MessageReturnedLocation(141.11, -34.11, "Good");
                ArrayList<MessageReturnedLocation> rls = new ArrayList<>();
                rls.add(rl1);
                rls.add(rl2);
                relocs = rls;
           // }
            for (MessageReturnedLocation rl : relocs) {
                LatLng pos = new LatLng(rl.getY(), rl.getX());
                mMap.addMarker(new MarkerOptions().position(pos).title(rl.getZ())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                mMap.addCircle(new CircleOptions().center(pos).radius(100000).strokeColor(Color.GREEN));

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
        mMap.addMarker(new MarkerOptions().position(location).title("WARNING: LOW Q"));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(5));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        mMap.addCircle(new CircleOptions().center(location).radius(100000).strokeColor(Color.GREEN));
        new AskForLocationsTask().execute();
        MessageReturnedLocation rl1 = new MessageReturnedLocation(143.2, -32, "Normal");
        MessageReturnedLocation rl2 = new MessageReturnedLocation(141.11, -34.11, "Good");
        ArrayList<MessageReturnedLocation> relocs;
        ArrayList<MessageReturnedLocation> rls = new ArrayList<>();
        rls.add(rl1);
        rls.add(rl2);
        relocs = rls;
        // }
        for (MessageReturnedLocation rl : relocs) {
            LatLng pos = new LatLng(rl.getY(), rl.getX());
            mMap.addMarker(new MarkerOptions().position(pos).title(rl.getZ())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.addCircle(new CircleOptions().center(pos).radius(100000).strokeColor(Color.GREEN));

        }




    }
    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {

        super.onStop();
    }




}
