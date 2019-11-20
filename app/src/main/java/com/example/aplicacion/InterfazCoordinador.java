package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InterfazCoordinador extends AppCompatActivity {
    DatabaseHelper db;
    Button mButtonVerSolicitud;
    TextView TID,TNumControlCoordinador,TCarreraCoordinada;
   Integer numcontrol,id,idcarrera;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_coordinador);

        db = new DatabaseHelper(this);

        Intent intent = getIntent();

        final String TNumControl2 = intent.getExtras().getString("NumControlCoordinador");

        Cursor cursor = db.getDataCoordinador(TNumControl2);

        mButtonVerSolicitud =(Button)findViewById(R.id.mButtonVerSolicitud);
        TNumControlCoordinador = (TextView)findViewById(R.id.TNumControlCoordinador);
        TID = (TextView)findViewById(R.id.TID);
        TCarreraCoordinada = (TextView)findViewById(R.id.TCarreraCoordinada);

        while (cursor.moveToNext()) {
             numcontrol = cursor.getInt(0);
             id = cursor.getInt(1);
            idcarrera = cursor.getInt(3);
        }
        String carrera = db.getNameFromCareer(idcarrera);

        TID.setText(String.valueOf(id));

        TNumControlCoordinador.setText(String.valueOf(numcontrol));
        TCarreraCoordinada.setText(carrera);

    mButtonVerSolicitud.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
               Intent moving = new Intent(InterfazCoordinador.this,VerListaCoordinador.class);
             moving.putExtra("NumControl",TNumControl2);
               startActivity(moving);
        }
    });

        }

    }

