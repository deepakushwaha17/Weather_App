package com.deepa.weather_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText city;
    Button btn;
    TextView result;
    WeatherSingleton weatherSingletons;

    String baseURL = "https://api.openweathermap.org/data/2.5/weather?q=";
    String API = "&appid=8b8597fa3d716d108909c1d4230e910e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        city= findViewById(R.id.getCity);
        result = findViewById(R.id.result);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myURL = baseURL + city.getText().toString()+API;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.i("JSON", "JSON" + jsonObject);

                        try{
                            String info = jsonObject.getString("weather");
                            JSONArray ar = new JSONArray(info);

                            for(int i = 0; i < ar.length(); i++)
                            {
                                JSONObject jobj = ar.getJSONObject(i);
                                String myweather = jobj.getString("main");
                                result.setText(myweather);
                                Log.i("ID","ID" + jobj.getString("id"));
                                Log.i("MAIN","MAIN" + jobj.getString("main"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                 new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error","Something went wrong"+ error);
                    }
                }
                );
                weatherSingletons.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
            }
        });

    }
}