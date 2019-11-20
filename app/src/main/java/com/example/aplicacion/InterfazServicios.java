package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InterfazServicios extends AppCompatActivity {
    DatabaseHelper db;
    TextView TNumControlServicios,TIDServicios;
    Button mButtonVerSolicitud;
    Integer id,numcontrol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_servicios);

        db = new DatabaseHelper(this);

        Intent intent = getIntent();

        final String NumControl2 = intent.getExtras().getString("NumControlServicios");

       mButtonVerSolicitud =(Button)findViewById(R.id.mButtonVerSolicitudSer);
       TNumControlServicios = (TextView)findViewById(R.id.TNumControlServicios);
       TIDServicios = (TextView)findViewById(R.id.TIDServicios);

        Cursor cursor = db.getDataServicios(NumControl2);

        while (cursor.moveToNext()) {
             numcontrol = cursor.getInt(0);
             id = cursor.getInt(1);
        }
        TIDServicios.setText(String.valueOf(id));
        TNumControlServicios.setText(String.valueOf(numcontrol));

        mButtonVerSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moving = new Intent(InterfazServicios.this,VerListaServicios.class);
               moving.putExtra("NumControlSer",NumControl2);
                startActivity(moving);
            }
        });
    }

    }

