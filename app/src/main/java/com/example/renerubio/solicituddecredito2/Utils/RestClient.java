package com.example.renerubio.solicituddecredito2.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class RestClient {
    public static final RestClient INSTANCE = new RestClient();

    protected RestClient() {
    }

    private static byte[] readBytes(InputStream inputStream) {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        int len;
        byte[] buffer = new byte[bufferSize];

        try {
            while ((len = inputStream.read(buffer)) != -1) {
                byteBuffer.write(buffer, 0, len);
            }
        } catch (Exception e) {
        }
        return byteBuffer.toByteArray();
    }

    public RestResponse consumeService(Context context, String uri, Map<String, String> header, String method, String data) {
        URL url;
        HttpURLConnection request = null;
        RestResponse response = null;
        try {
            url = new URL(uri);
            if(isConnected(context)) {
                Log.d("RestResponse", "CONECTADO");
                request = (HttpURLConnection) url.openConnection();
                request.setRequestMethod(method);
                request.setConnectTimeout(300000);
                request.setReadTimeout(300000);
                if(header != null && !header.isEmpty()) {
                    for (Map.Entry<String, String> pairs : header.entrySet()) {
                        String key = pairs.getKey();
                        String value = pairs.getValue();
                        request.setRequestProperty(key, value);
                    }
                }
                if(method.toUpperCase().equals("POST") || method.toUpperCase().equals("PUT")) {
                    byte[] outputInBytes = data.getBytes("UTF-8");
                    OutputStream os = request.getOutputStream();
                    os.write(outputInBytes);
                    os.close();
                }
                InputStream is;
                if (199 < request.getResponseCode()  && request.getResponseCode() < 300) {
                    is = new BufferedInputStream(request.getInputStream());
                } else {
                    is = new BufferedInputStream(request.getErrorStream());
                }
                byte[] buffer = readBytes(is);
                response = new RestResponse(request.getResponseCode(), buffer);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if(request != null) {
                request.disconnect();
            }
        }
        return response;
    }

    public boolean isConnected(Context context) {
        boolean connected = false;
        ConnectivityManager connMgr;
        connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            connected=true;
        }
        return connected;
    }
}
