package com.deepa.weather_app;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.net.ContentHandler;

public class WeatherSingleton {
    private static WeatherSingleton weatherSingleton;
    private RequestQueue requestQueue ;
    private static Context mCtx;
    private WeatherSingleton(Context context)
    {
        mCtx = context;
        requestQueue = getRequestQueue();
    }
    public RequestQueue getRequestQueue()
    {
        if (requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized WeatherSingleton getInstance(Context context)
    {
        if( weatherSingleton == null)
        {
            weatherSingleton = new WeatherSingleton(context);
        }
        return weatherSingleton;
    }
    public void addToRequestQue(Request request)
    {
        requestQueue.add(request);
    }
}
