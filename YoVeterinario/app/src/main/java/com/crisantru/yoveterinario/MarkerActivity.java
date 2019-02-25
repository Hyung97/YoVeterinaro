package com.crisantru.yoveterinario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MarkerActivity extends AppCompatActivity {

    private Button btn_registerC;
    private Button btn_showC;
    private Button btn_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_marker);

        btn_registerC = (Button) findViewById(R.id.btn_addClinica);
        btn_showC = (Button) findViewById(R.id.btn_showC);
        btn_delete = (Button) findViewById(R.id.btn_delete);

        btn_registerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarkerActivity.this, RegisterClinic.class);
                startActivity(intent);
            }
        });

        btn_showC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarkerActivity.this, ClinicListContent.class);
                startActivity(intent);

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarkerActivity.this, DeleteClinic.class);
                startActivity(intent);

            }
        });
    }
}
