package com.example.renerubio.solicituddecredito2.Utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;

public class Moneda {

    public void FormatoMoneda(final EditText etIngresosT){
        etIngresosT.setMovementMethod(null);

        etIngresosT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(current)){
                    etIngresosT.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    String formated = NumberFormat.getCurrencyInstance().format((parsed/100));

                    current = formated;
                    etIngresosT.setText(formated);
                    etIngresosT.setSelection(formated.length());

                    etIngresosT.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
