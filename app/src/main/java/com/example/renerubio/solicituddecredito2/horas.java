package com.example.renerubio.solicituddecredito2;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.renerubio.solicituddecredito2.Utils.SessionManager;

import java.util.Calendar;

public class horas extends AppCompatActivity {
    EditText hora1,hora2,hora3,hora4;
    int a,b,f,d;
    String uh = "";
    String uh2 = "";
    String uh3 = "";
    String hm,hr,hr2,hr3;
    int minute;
    int hour;
    final Calendar c = Calendar.getInstance();
    public horas(){

    }
    public horas(EditText horasss,EditText horaa2,final Context ctxx){
        hr = horasss.getText().toString();
        hr2 = horaa2.getText().toString();
        if(hr.equals("")){
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
            if(!hr2.equals("")){
                String[] parts2 = hr2.split(":");
                uh2 = parts2[0];
                SessionManager.INSTANCE.setPreferences(ctxx, "Uh2", uh2);
            }
        }else{

            String[] parts = hr.split(":");
            hm = parts[1]; // 123
            uh = parts[0];
            if(!hr2.equals("")){
                String[] parts2 = hr2.split(":");
                uh2 = parts2[0];
                SessionManager.INSTANCE.setPreferences(ctxx, "Uh2", uh2);
            }

            SessionManager.INSTANCE.setPreferences(ctxx, "Hm", hm);
            SessionManager.INSTANCE.setPreferences(ctxx, "Uh", uh);

        }
    }
    public void Hora(final EditText et, final Context ctx) {
        String Min = SessionManager.INSTANCE.getPreferences(ctx, "Hm");
        String Hor = SessionManager.INSTANCE.getPreferences(ctx, "Uh");
        if(!Min.equals("") && !et.getText().toString().equals("")){
            hour = Integer.parseInt(Hor);
            minute = Integer.parseInt(Min);
        }

        TimePickerDialog recogerHora = new TimePickerDialog(ctx, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10) ? String.valueOf("0" + minute) : String.valueOf(minute);
               /* String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }*/
               if(!uh.equals(uh2)){
                   String Hor = SessionManager.INSTANCE.getPreferences(ctx, "Uh2");
                   int uh2int = Integer.parseInt(Hor);
                   if(hourOfDay <= uh2int){
                       Toast.makeText(ctx,"La Hora debe Ser Mayor a la anterior",Toast.LENGTH_LONG).show();
                   }else{
                       et.setText(hourOfDay + ":" + minutoFormateado);
                   }
               }else {
                et.setText(hourOfDay + ":" + minutoFormateado);}
            }
        }, hour, minute, false);
        recogerHora.show();
    }
}
