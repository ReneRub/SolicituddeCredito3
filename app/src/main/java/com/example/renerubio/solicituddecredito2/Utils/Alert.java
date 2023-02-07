package com.example.renerubio.solicituddecredito2.Utils;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.renerubio.solicituddecredito2.R;
import com.example.renerubio.solicituddecredito2.AlertDialogActivity;

public class Alert {
    public enum TYPE {
        OK, OKCANCEL
    }

    public static final Alert INSTANCE = new Alert();

    protected Alert() {
    }
    public void AlertMessage(Context context, String message) {
        AlertMessage(context, context.getString(R.string.app_name), message, TYPE.OK);
    }

    public void AlertMessage(Context context, String title, String message, TYPE type) {
        Intent intent = new Intent(context, AlertDialogActivity.class);
        Bundle b = new Bundle();
        Bundle bout = new Bundle();
        b.putString("Title", title);
        b.putString("Message", message);
        b.putString("Type", type.toString());
        intent.putExtras(b);
        context.startActivity(intent);
    }
}
