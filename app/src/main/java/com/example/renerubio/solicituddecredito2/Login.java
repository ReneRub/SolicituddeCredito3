package com.example.renerubio.solicituddecredito2;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.renerubio.solicituddecredito2.Utils.Alert;
import com.example.renerubio.solicituddecredito2.Utils.RestClient;
import com.example.renerubio.solicituddecredito2.Utils.RestResponse;
import com.example.renerubio.solicituddecredito2.Utils.SessionManager;
import com.example.renerubio.solicituddecredito2.Utils.SingletonVolley;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

import static com.android.volley.Request.Method.POST;

public class Login extends AppCompatActivity {

    private UserLoginTask mAuthTask = null;
    Button btnlogin;
    private String m_AuthorizationKey;
    private String Usuario_id;
    private String Sucursal_id;
    private String Localidad_id;
    EditText etusuario, etcontrasena;
    ArrayList<Integer> ListaU;
    private ACProgressFlower dialog;
    int bandera = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ListaU = new ArrayList<Integer>();
        etusuario = (EditText) findViewById(R.id.etUsuario);
        etcontrasena = (EditText) findViewById(R.id.etPassword);
        btnlogin = (Button) findViewById(R.id.btnLogin);
        String usuario= etusuario.getText().toString();
        String password= etcontrasena.getText().toString();

        //En caso de que ya se haya logueado
        Thread background = new Thread() {
            public void run() {

                try {
                    //String user = SessionManager.INSTANCE.getPreferences(Login.this, "_user");
                    //String pwd = SessionManager.INSTANCE.getPreferences(Login.this, "_pwd");

                    String user = SessionManager.INSTANCE.getPreferences(Login.this, "_user");
                    String pwd = SessionManager.INSTANCE.getPreferences(Login.this, "_pwd");
                    Intent intent;
                    if(user != null && pwd != null) {
                        if (!user.isEmpty() && !pwd.isEmpty()) {
                            intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        background.start();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //EnviarPost();
                //UserLogin();
                attemptLogin();
                    //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   // startActivity(intent);

            }
        });
    }
    private void attemptLogin() {
    if (mAuthTask != null) {
        return;
    }

    // Store values at the time of the login attempt.
    String usuario = etusuario.getText().toString();
    String password = etcontrasena.getText().toString();
        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(usuario)) {
            Toast.makeText(Login.this,"El usuario no puede estar vacio",Toast.LENGTH_SHORT).show();
            focusView = etusuario;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this,"La contraseña no puede estar vacia",Toast.LENGTH_SHORT).show();
            focusView = etcontrasena;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            if (RestClient.INSTANCE.isConnected(Login.this)) {
                mAuthTask = new UserLoginTask(usuario, password);
                mAuthTask.execute((Void) null);
            } else {
                Toast.makeText(Login.this, "Fallo la conexion", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void UserLogin(){
        try {
            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            String ip = getString(R.string.ip);
            String URL =ip +"login";
            JSONObject jsonBody = new JSONObject();
            String usu= etusuario.getText().toString();
            String pas= etcontrasena.getText().toString();
            jsonBody.put("user", usu);
            jsonBody.put("pwd", pas);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
                /* @Override
                 protected Response<String> parseNetworkResponse(NetworkResponse response) {
                     String responseString = "";
                     if (response != null) {
                         responseString = String.valueOf(response.statusCode);
                         // can get more details such as response.headers
                     }
                     return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                 }*/
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String jsonString = new String(response.data);
                    try {
                        int mStatusCode = response.statusCode;
                        if(mStatusCode == 200 || mStatusCode == 202) {
                            m_AuthorizationKey = jsonString;
                            Agregar(jsonString);
                            //Log.i("VOLLEY", response);
                            //JSONObject jsonResponse = new JSONObject(jsonString);
                            //Toast.makeText(MainActivity.this,""+ jsonString,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            //finish();
                            //System.exit(0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return super.parseNetworkResponse(response);
                }
            };
            //requestQueue.add(stringRequest);
            SingletonVolley.getInstanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void Agregar(String resp){
        try{
            // OutputStreamWriter dato = new OutputStreamWriter(openFileOutput("token.txt", Context.MODE_PRIVATE));
            // dato.write("Token " + resp);
            //dato.close();
            SessionManager.INSTANCE.setPreferences(Login.this, "_authorizationkey", resp);
            SessionManager.INSTANCE.setPreferences(Login.this, "_user", etusuario.getText().toString());
            SessionManager.INSTANCE.setPreferences(Login.this, "_pwd", etcontrasena.getText().toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //LOGIN de pruebaaaaaaa*****************************************************************************************************
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String m_User;
        private final String m_Password;
        private String m_AuthorizationKey;

        UserLoginTask(String usuario, String password) {
            m_User = usuario;
            m_Password = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean logged = false;

            try {
                JSONObject data = new JSONObject();
                data.put("usuario", m_User);
                data.put("password", m_Password);
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-type", "application/json charset=utf-8");

                RestResponse response = RestClient.INSTANCE.consumeService(getApplicationContext(), getString(R.string.ip) + "loginn", headers, "POST", data.toString());
                //RestResponse response = RestClient.INSTANCE.consumeService(getApplicationContext(), getString(R.string.ure_verificationservice) + "login", headers, "POST", data.toString());
                if (response != null) {
                    if(199 < response.getHttpStatus() && response.getHttpStatus() < 300) {
                        Log.d("Splash", "status: " + response.getHttpStatus());
                        Log.d("Splash", "content: " + new String(response.getContent()));
                        JSONObject json = new JSONObject(new String(response.getContent()));
                        JSONArray jsonSeguimientos = json.getJSONArray("Login");
                        JSONObject json1 = jsonSeguimientos.getJSONObject(0);
                        Usuario_id = String.valueOf(json1.getInt("Usuario_id"));
                        Sucursal_id = String.valueOf(json1.getInt("Sucursal_id")) ;
                        Localidad_id = String.valueOf(json1.getInt("Localidad_id")) ;
                        //m_AuthorizationKey = json.getString("Authorization");
                        logged = true;
                    }else {
                        bandera = 1;
                       // Alert.INSTANCE.AlertMessage(Login.this, "Datos incorrectos");
                    }
                } else {
                    bandera = 2;
                    Toast.makeText(Login.this,"Usuario incorrecto",Toast.LENGTH_LONG).show();
                    //Alert.INSTANCE.AlertMessage(Login.this, "No se pudo obtener respuesta");
                }
            } catch (Exception e) {
                bandera = 3;
                //e.printStackTrace();
                //Alert.INSTANCE.AlertMessage(Login.this, "No se pudo obtener respuesta");
            }
            return logged;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            Carga();
            if (success) {
                //SessionManager.INSTANCE.setPreferences(Login.this, "_authorizationkey", m_AuthorizationKey);
                SessionManager.INSTANCE.setPreferences(Login.this, "_pwd", m_Password);
                SessionManager.INSTANCE.setPreferences(Login.this, "_user", m_User);
                SessionManager.INSTANCE.setPreferences(Login.this, "_userid", Usuario_id);
                SessionManager.INSTANCE.setPreferences(Login.this, "_sucid", Sucursal_id);
                SessionManager.INSTANCE.setPreferences(Login.this, "_locid", Localidad_id);

                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                switch (bandera){
                    case 1:
                        dialog.dismiss();
                        Toast.makeText(Login.this,"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        dialog.dismiss();
                        Toast.makeText(Login.this,"Algo salio mal intenta de nuevo",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        dialog.dismiss();
                        Toast.makeText(Login.this,"Fallo comunicacion con servidor",Toast.LENGTH_SHORT).show();
                        break;
                }
                //dialog.dismiss();
                //Toast.makeText(Login.this,"Usuario o contraseña incorrecto",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Carga();
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
    public void Carga(){
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Un momento")
                .fadeColor(Color.DKGRAY).build();
        dialog.show();
    }

    //***********************************************************************************************************
    /*private void EnviarPost() {

        try {
            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            String ip = getString(R.string.ip);
            String URL =ip +"/apiRest/login";
            JSONObject jsonBody = new JSONObject();
            String usu= etusuario.getText().toString();
            String pas= etcontrasena.getText().toString();
            jsonBody.put("usuario", usu);
            jsonBody.put("contrasena", pas);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Log.i("VOLLEY", response);
                    //Toast.makeText(Login.this,"datos :"+ response, Toast.LENGTH_LONG).show();
                    Toast.makeText(Login.this,"Bienvenido :"+ etusuario.getText().toString(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                    Toast.makeText(Login.this,"Usuario incorrecto",Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }
            };

            //requestQueue.add(stringRequest);
            SingletonVolley.getInstanciaVolley(getApplicationContext()).addToRequestQueue(stringRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }







       /* String url = "http://10.0.1.231:8080/apiRest/login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String usu= user.getText().toString();
                String pas= pass.getText().toString();
                params.put("usuario",usu);
                params.put("contrasena",pas);
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/
    //}
}


