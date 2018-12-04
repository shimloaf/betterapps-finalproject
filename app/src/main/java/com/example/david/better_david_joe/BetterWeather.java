package com.example.david.better_david_joe;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BetterWeather extends AppCompatActivity {
    private static final String TAG = "WEATHERLOG";
    private static RequestQueue requestQueue;
    private String weatherType = "A bit Challen Out Today";
    private int temperature;
    private static int woeid;
    private boolean hot;
    private boolean wet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        float currentLatitude = 22.32f;
        float currentLongitude = 63.23f;


        try {
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            //grab the longitude and latitude of the user
            currentLongitude = (float) location.getLongitude();
            currentLatitude = (float) location.getLatitude();

            startAPICall(currentLatitude, currentLongitude);

        } catch(SecurityException e){

            Intent appBrowser = new Intent(BetterWeather.this, BetterApp.class);
            startActivity(appBrowser);

            Context context = getApplicationContext();
            CharSequence text = "Location access required!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();



        }

    }

    /**
     * Make an API call.
     *
     * @param latitude current latitude of the user
     * @param longitude current longitude of the user
     */
    void startAPICall(final float latitude, final float longitude) {

        //first API call for woeid
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
                                woeid = firstLocation.getInt("woeid");

                                //second API call for weather data
                                try {
                                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                                            Request.Method.GET,
                                            "https://www.metaweather.com/api/location/" + woeid + "/",
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
        String theReport = weatherType + " and " + temperature;
        setContentView(R.layout.activity_weather_screen);
        TextView weatherReport = findViewById(R.id.weatherReport);

        hot = temperature > 25;

        wet = !weatherType.equals("c") && !weatherType.equals("lc")
                && !weatherType.equals("hc");

        if (hot && wet) {
            weatherReport.setText(getString(R.string.hotWet));
        } else if (!hot && wet) {
            weatherReport.setText(getString(R.string.coldWet));
        } else if (hot) {
            weatherReport.setText(getString(R.string.hotString));
        } else {
            weatherReport.setText(getString(R.string.coldString));
        }



    }






}
