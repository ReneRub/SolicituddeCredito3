package com.example.renerubio.solicituddecredito2.Utils;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaCampos {

    public void Telefono(EditText NumeroTelefono){

        NumeroTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(NumeroTelefono.getText().toString().length() <10 && NumeroTelefono.getText().toString().length() >0){
                    NumeroTelefono.setError("Muy corto");
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }
    public void cp(EditText cp){

        cp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(cp.getText().toString().length() < 3 && cp.getText().toString().length() >0){
                    cp.setError("Muy corto");
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }
    public void email(EditText email,TextView tvEmail){
        email.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+") && s.length() > 0)
                {
                    tvEmail.setVisibility(View.VISIBLE);
                    tvEmail.setTextColor(Color.parseColor("#0174DF"));
                    tvEmail.setText("Email Válido");
                }else if(email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]++\\.[a-z]+") && s.length() > 0){
                    tvEmail.setVisibility(View.VISIBLE);
                    tvEmail.setTextColor(Color.parseColor("#0174DF"));
                    tvEmail.setText("Email Válido");
                }
                else
                {
                    tvEmail.setVisibility(View.VISIBLE);
                    tvEmail.setTextColor(Color.parseColor("#BF0811"));
                    tvEmail.setText("El Email no es válido");
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
// other stuffs
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
// other stuffs
            }
        });
    }
    public void rfc(EditText rfc,TextView tvrfc){
        rfc.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                //Tambien funciona****************************************************************
               /* String dato = rfc.getText().toString();
                String letras = "" ;
                String numeros = "";
                String homoclave ="";
               // Pattern pat = Pattern.compile("[a-zA-Z]");
                Pattern pat4 = Pattern.compile("^(([A-Z]|[a-z]|\\s){1})(([A-Z]|[a-z]){3})([0-9]{6})((([A-Z]|[a-z]|[0-9]){3}))");
                //Pattern pat2 = Pattern.compile("[a-zA-Z0-9]");
                //Pattern pat3 = Pattern.compile("[0-9]");

                if(dato.length() == 13){
                    //letras = dato.substring(0,4);
                    //numeros = dato.substring(4,10);
                   // homoclave = dato.substring(10,13);
                      Matcher mat4 = pat4.matcher(dato);
                   // Matcher mat = pat.matcher(letras);
                   // Matcher mat2 = pat2.matcher(homoclave);
                   // Matcher mat3 =pat3.matcher(numeros);
                    if(mat4.matches()){

                        tvrfc.setVisibility(View.VISIBLE);
                        tvrfc.setText("RFC Valido");
                    }
                    else
                    {
                        tvrfc.setVisibility(View.VISIBLE);
                        tvrfc.setText("El RFC no es valido");
                    }
                }else{
                    tvrfc.setVisibility(View.VISIBLE);
                    tvrfc.setText("El RFC no es valido");
                }*/
                //Tambien funciona****************************************************************
                //if (rfc.getText().toString().matches("^(([A-Z]|[a-z]|\\s){1})(([A-Z]|[a-z]){3})([0-9]{6})((([A-Z]|[a-z]|[0-9]){3}))"))
                if (rfc.getText().toString().matches("^(([A-Z]|[a-z]|\\s){1})(([A-Z]|[a-z]){3})([0-9]{6})"))
                {
                    tvrfc.setVisibility(View.VISIBLE);
                    tvrfc.setTextColor(Color.parseColor("#0174DF"));
                    tvrfc.setText("RFC Válido");
                }else if (rfc.getText().toString().matches("^(([A-Z]|[a-z]|\\s){1})(([A-Z]|[a-z]){3})([0-9]{6})((([A-Z]|[a-z]|[0-9]){3}))")){
                    tvrfc.setVisibility(View.VISIBLE);
                    tvrfc.setTextColor(Color.parseColor("#0174DF"));
                    tvrfc.setText("RFC Válido");
                }
                else
                {
                    tvrfc.setVisibility(View.VISIBLE);
                    tvrfc.setTextColor(Color.parseColor("#BF0811"));
                    rfc.setError("4 Letras, 6 Numeros, 3 L o N");
                    tvrfc.setText("El RFC no es válido");
                }
            }


            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
// other stuffs
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
// other stuffs
            }
        });

    }
    public void curp(EditText curp,TextView tvCurp){
        curp.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (curp.getText().toString().matches("([a-zA-Z0]{4})([0-9]{6})([a-zA-Z0-9]{8})") && s.length() > 0)
                {
                    tvCurp.setVisibility(View.VISIBLE);
                    tvCurp.setTextColor(Color.parseColor("#0174DF"));
                    tvCurp.setText("Curp Válido");
                }
                else
                {
                    tvCurp.setVisibility(View.VISIBLE);
                    tvCurp.setTextColor(Color.parseColor("#BF0811"));
                    curp.setError("4 Letras, 6 Numeros, 8 L o N");
                    tvCurp.setText("El Curp no es válido");
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
// other stuffs
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
// other stuffs
            }
        });
    }
}
