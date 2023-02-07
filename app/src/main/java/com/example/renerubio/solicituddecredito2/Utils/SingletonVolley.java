package com.example.renerubio.solicituddecredito2.Utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonVolley {



    private static SingletonVolley instanciaVolley;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private SingletonVolley(Context context) {
        mCtx = context;
        requestQueue = getRequestQueue();

    }

    public static synchronized SingletonVolley getInstanciaVolley(Context context) {

        if (instanciaVolley == null) {
            instanciaVolley = new SingletonVolley(context);
        }

        return instanciaVolley;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }


}
