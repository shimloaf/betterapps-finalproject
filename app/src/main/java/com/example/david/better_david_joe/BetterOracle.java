package com.example.david.better_david_joe;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Locale;

public class BetterOracle extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "ORACLELOG";
    private String sex = "";
    private String sexHold = "";
    private String country = "";
    private String countryHold = "";
    private String dateFormat = "";
    private String adjectiveDeity = "";
    int birthYear = 0;
    int year = 0;
    private static RequestQueue requestQueue;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView mDisplayDate;
    private int phase = -1;
    private Button submitButton;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private int question;
    private int taps = 0;
    private int life = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oracle_screen);
        submitButton = findViewById(R.id.submit);
        submitButton.setVisibility(View.GONE);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        mDisplayDate = findViewById(R.id.tvDate);
        spinner1.setVisibility(View.GONE);
        spinner2.setVisibility(View.GONE);
        spinner3.setVisibility(View.GONE);
        spinner4.setVisibility(View.GONE);
        mDisplayDate.setVisibility(View.GONE);


        Date now = new Date();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd",
                Locale.ENGLISH).format(now);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(BetterOracle.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.oracleSex));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdapter);

        spinner1.setOnItemSelectedListener(this);




        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(BetterOracle.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.oracleCountry));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter2);

        spinner2.setOnItemSelectedListener(this);




        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<>(BetterOracle.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.gods));
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(myAdapter3);

        spinner3.setOnItemSelectedListener(this);




        ArrayAdapter<String> myAdapter4 = new ArrayAdapter<>(BetterOracle.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.yesno));
        myAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(myAdapter4);

        spinner4.setOnItemSelectedListener(this);


        question = (int) (Math.floor(Math.random() * 4));


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(BetterOracle.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);

                dialog.getDatePicker().setMaxDate(new Date().getTime());
                try{
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                } catch(NullPointerException e) {
                    Log.d(TAG, "The Milk's gone bad.");
                }
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "" + (month + 1) + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
                birthYear = 2018 - year;
                phase = 3;
                submitButton.setVisibility(View.VISIBLE);
            }
        };


    }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            label:
            switch (parent.getId()) {
                case R.id.spinner1:
                    switch (position) {
                        case 0:
                            submitButton.setVisibility(View.GONE);
                            break;
                        case 1:
                            sexHold = "male";
                            phase = 1;
                            submitButton.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            sexHold = "female";
                            phase = 1;
                            submitButton.setVisibility(View.VISIBLE);
                            break;
                    }
                    break;
                case R.id.spinner2:
                    countryHold = getResources().getStringArray(R.array.oracleCountry)[position];
                    if (countryHold.equals("South Korea")) {
                        countryHold = "Rep%20of%20Korea";
                    } else if (countryHold.equals("Taiwan")) {
                        countryHold = "China";
                    } else if (countryHold.equals("The Netherlands")) {
                        countryHold = "The%20Netherlands";
                    } else if (countryHold.equals("United States")) {
                        countryHold = "United%20States";
                    } else if (countryHold.equals("United Kingdom")) {
                        countryHold = "United%20Kingdom";
                    } else if (countryHold.equals("Sri Lanka")) {
                        countryHold = "Sri%20Lanka";
                    } else if (countryHold.equals("Other")) {
                        countryHold = "United%20States";
                    } else if (countryHold.equals("Choose Country")) {
                        submitButton.setVisibility(View.GONE);
                        break;
                    }

                    phase = 2;
                    submitButton.setVisibility(View.VISIBLE);
                    break;
                case R.id.spinner3:

                    String deity = getResources().getStringArray(R.array.gods)[position];

                    switch (deity) {
                        case "Choose Deity":
                            submitButton.setVisibility(View.GONE);
                            break label;
                        case "Zeus":
                            adjectiveDeity = " a horrible ";
                            break;
                        case "Ishtar":
                            adjectiveDeity = " a peaceful ";
                            break;
                        case "Cthulhu":
                            adjectiveDeity = " a gruesome ";
                            break;
                        case "Talos":
                            adjectiveDeity = " a warrior's ";
                            break;
                        case "Loki":
                            adjectiveDeity = " a very interesting ";
                            break;
                        case "Anubis":
                            adjectiveDeity = " a proper ";
                            break;
                        case "Geoff Challen":
                            adjectiveDeity = " due to fatal checkstyle errors leading to your ";
                            break;
                        case "I serve no god.":
                            adjectiveDeity = " a very average ";
                            break;
                    }

                    phase = 4;
                    submitButton.setVisibility(View.VISIBLE);
                    break;
                case R.id.spinner4:
                    if (getResources().getStringArray(R.array.yesno)[position].equals("Choose Yes/No")) {
                        submitButton.setVisibility(View.GONE);
                        break;
                    }
                    phase = 5;
                    submitButton.setVisibility(View.VISIBLE);
                    break;
            }
        }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        submitButton.setVisibility(View.GONE);
    }

    public void changePhase(View view) {
        if (phase == 1) {
            sex = sexHold;
            TextView result = findViewById(R.id.oracleResult);
            result.setText(R.string.countryQuestion);
            spinner1.setVisibility(View.INVISIBLE);
            submitButton.setVisibility(View.GONE);
            spinner2.setVisibility(View.VISIBLE);
        } else if (phase == 2) {
            country = countryHold;
            spinner2.setVisibility(View.INVISIBLE);
            TextView result = findViewById(R.id.oracleResult);
            result.setText(R.string.ageQuestion);
            submitButton.setVisibility(View.GONE);
            mDisplayDate.setVisibility(View.VISIBLE);
        } else if (phase == 3) {
            year = birthYear;
            TextView result = findViewById(R.id.oracleResult);
            result.setText(R.string.godQuestion);
            spinner3.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.GONE);
            mDisplayDate.setVisibility(View.INVISIBLE);
        } else if (phase == 4) {

            String[] sQ = getResources().getStringArray(R.array.sillyQs);

            int choice = (int) Math.floor(Math.random()*8);

            String fullQuestion = getResources().getString(R.string.sillyQuestion) + "\n" + sQ[choice];


            spinner3.setVisibility(View.INVISIBLE);
            spinner4.setVisibility(View.VISIBLE);
            TextView result = findViewById(R.id.oracleResult);
            result.setText(fullQuestion);
            submitButton.setVisibility(View.GONE);
        } else if (phase == 5) {
            spinner4.setVisibility(View.INVISIBLE);
            submitButton.setVisibility(View.GONE);
            Log.d(TAG, "http://api.population.io:80/1.0/life-expectancy/" + "remaining/" + sex + "/" + country + "/" + dateFormat + "/" + birthYear + "y0m/");

            requestQueue = Volley.newRequestQueue(this);
            startAPICall();
        }
    }

    private void startAPICall() {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.population.io:80/1.0/life-expectancy/" + "remaining/" + sex + "/" + country + "/" + dateFormat + "/" + birthYear + "y0m/",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            try {
                                Log.d(TAG, "" + Float.valueOf(response.getString("remaining_life_expectancy")));
                                double trouble = (double) Float.valueOf(response.getString("remaining_life_expectancy"));
                                life = (int) trouble;
                                TextView result = findViewById(R.id.oracleResult);
                                String message = "According to statistics, you will die" + adjectiveDeity + "death in about " + life + " years.";
                                result.setText(message);
                                Log.d(TAG, "" + life);
                            } catch (JSONException e) {
                                Log.d(TAG, "huckle");
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

    }


    public void startOracle(View view) {

        if (taps == 2 && phase == -1) {
            TextView result = findViewById(R.id.oracleResult);
            result.setText(R.string.sexQuestion);
            spinner1.setVisibility(View.VISIBLE);
            phase = 0;
        } else {
            taps++;
        }
    }

    public void lifeSux (int setLife) {
        life = setLife;
    }
}
