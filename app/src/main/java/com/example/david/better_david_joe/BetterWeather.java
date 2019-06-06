package com.example.david.better_david_joe;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BetterWeather extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
    private static final String TAG = "WEATHER_LOG";
    private static final int REQUEST_LOCATION_CODE = 1;
    private static RequestQueue requestQueue;
    private String weatherType = "A bit Challen Out Today";
    private int temperature;
    private static int earth_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);

        float currentLatitude;
        float currentLongitude;

        if ((ContextCompat.checkSelfPermission(BetterWeather.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
                || (ContextCompat.checkSelfPermission(BetterWeather.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(BetterWeather.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_CODE);

        } else {

            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            //grab the longitude and latitude of the user
            currentLongitude = (float) location.getLongitude();
            currentLatitude = (float) location.getLatitude();

            Log.d(TAG, currentLatitude + ", " + currentLongitude);

            setContentView(R.layout.activity_weather_screen);
            startAPICall(currentLatitude, currentLongitude);


        }
    }

    /**
     * Make an API call.
     *
     * @param latitude current latitude of the user
     * @param longitude current longitude of the user
     */
    void startAPICall(final float latitude, final float longitude) {

        //first API call for where on earth id
        try {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    "https://www.metaweather.com/api/location/search/?lattlong=" + latitude + "," + longitude,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(final JSONArray response) {

                            try {
                                JSONObject firstLocation = response.getJSONObject(0);
                                earth_id = firstLocation.getInt("woeid");
                                //second API call for weather data
                                try {
                                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                            Request.Method.GET,
                                            "https://www.metaweather.com/api/location/" + earth_id + "/",
                                            null,
                                            new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(final JSONObject response) {

                                                    try {
                                                        JSONArray weather = response.getJSONArray("consolidated_weather");


                                                        weatherType = weather.getJSONObject(0).getString("weather_state_abbr");
                                                        temperature = weather.getJSONObject(0).getInt("the_temp");

                                                        initializeLayout();

                                                    } catch(JSONException e){
                                                        Log.d(TAG, e.toString());
                                                    }


                                                }
                                            }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(final VolleyError error) {
                                            Log.w(TAG, error.toString());
                                        }
                                    });
                                    requestQueue.add(jsonObjectRequest);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            } catch (JSONException e) {
                                Log.d(TAG, e.toString());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w(TAG, error.toString());
                }
            });
            requestQueue.add(jsonArrayRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void initializeLayout() {
        TextView weatherReport = findViewById(R.id.weatherReport);
        ImageView weatherBackground = findViewById(R.id.weatherBackdrop);

        boolean hot = temperature > 0;

        boolean wet = !weatherType.equals("c") && !weatherType.equals("lc")
                && !weatherType.equals("hc");

        String geoffChallen;

        if (hot && wet) {
            geoffChallen = getString(R.string.hotWet);
            weatherBackground.setImageResource(R.drawable.hot_and_wet_background);
        } else if (!hot && wet) {
            geoffChallen = getString(R.string.coldWet);
            weatherBackground.setImageResource(R.drawable.cold_and_wet_background);
        } else if (hot) {
            geoffChallen = getString(R.string.hotString);
            weatherBackground.setImageResource(R.drawable.hot_background);
        } else {
            geoffChallen = getString(R.string.coldString);
            weatherBackground.setImageResource(R.drawable.cold_background);
        }

        weatherReport.setText(geoffChallen);

    }






}
