package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InterfazAcademia extends AppCompatActivity {
    DatabaseHelper db;
    Button mButtonVerSolicitud;
    TextView TID,TNumControlAcademia,TCarreraAcademia;
    Integer numcontrol,id,idcarrera;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_academia);

        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        final String NumControl = intent.getExtras().getString("NumControlAcademia");


        Cursor cursor = db.getDataAcademia(NumControl);

        TNumControlAcademia = (TextView)findViewById(R.id.TNumControlAcademia);
        TID = (TextView)findViewById(R.id.TID);
        TCarreraAcademia = (TextView)findViewById(R.id.TCarreraAcademia);
        mButtonVerSolicitud = (Button)findViewById(R.id.mButtonVerSolicitudAcade);

        while (cursor.moveToNext()) {
            numcontrol = cursor.getInt(0);
            id = cursor.getInt(1);
            idcarrera = cursor.getInt(3);
        }
        String carrera = db.getNameFromCareer(idcarrera);

        TNumControlAcademia.setText(String.valueOf(numcontrol));
        TID.setText(String.valueOf(id));
        TCarreraAcademia.setText(carrera);

        mButtonVerSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent moving = new Intent(InterfazAcademia.this,VerListaAca.class);
              moving.putExtra("NumControlAcademia",NumControl);
              startActivity(moving);
            }
        });

    }
}
