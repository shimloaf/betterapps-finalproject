package com.example.david.better_david_joe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BetterWeather extends AppCompatActivity {
    private static final String TAG = "WEATHERLOG";
    private static RequestQueue requestQueue;
    private static String weatherType = "A bit Challen Out Today";
    private static int temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.activity_weather_screen);


        TextView weatherReport = findViewById(R.id.weatherReport);

        //grab the longitude and latitude of the user

        float currentLatitude = 22.32f;
        float currentLongitude = 63.23f;

        startAPICall(currentLatitude, currentLongitude);

        weatherReport.setText(weatherType);

    }

    /**
     * Make an API call.
     *
     * @param latitude current latitude of the user
     * @param longitude current longitude of the user
     */
    void startAPICall(final float latitude, final float longitude) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://www.metaweather.com/api/location/search/?lattlong=" + latitude + "," + longitude,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {

                            String rawData = response.toString();

                            weatherType = rawData.substring(rawData.indexOf("weather_state_name"),
                                    rawData.indexOf(","));


                            Log.d(TAG, weatherType);

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

    }








}
