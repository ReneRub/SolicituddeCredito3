package com.example.renerubio.solicituddecredito2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlertDialogActivity extends Activity implements View.OnClickListener {
    Button ok_btn;
    TextView txt_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle b = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertdialog_ok);
        this.setFinishOnTouchOutside(false);
        txt_message = (TextView) findViewById(R.id.message_txt_id);
        txt_message.setText(b.getString("Message"));
        ok_btn = (Button) findViewById(R.id.ok_btn_id);
        ok_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ok_btn_id:
                this.finish();
                break;
        }

    }
}
