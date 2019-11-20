package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.database.Cursor;
public class InterfazUsuario extends AppCompatActivity {
    DatabaseHelper db;
    TextView TNumControl, TID, TCarrera, TSemestre;
    Integer ID, IDCarrera, Semestre;

    Button mButtonSolicitud;
    Button mButtonVerSolicitud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_interfaz_usuario);

        db = new DatabaseHelper(this);

        Intent intent = getIntent();

        final String TNumControl2 = intent.getExtras().getString("NumControl");

        mButtonSolicitud = (Button) findViewById(R.id.mButtonSolicitud);
        TNumControl = (TextView) findViewById(R.id.TNumControl);
        TCarrera = (TextView) findViewById(R.id.TCarrera);
        TSemestre = (TextView) findViewById(R.id.TSemestre);
        mButtonVerSolicitud = (Button) findViewById(R.id.mButtonVerSolicitud);
        TNumControl.setText(TNumControl2);

        Cursor cursor = db.getDataUser(TNumControl2);

        while (cursor.moveToNext()) {
            ID = cursor.getInt(1);
            IDCarrera = cursor.getInt(3);
            Semestre = cursor.getInt(4);

        }
        String nombrecarrera = db.getNameFromCareer(IDCarrera);
        TCarrera.setText(String.valueOf(nombrecarrera));
        TSemestre.setText(String.valueOf(Semestre));



        mButtonSolicitud.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               Intent moving = new Intent(InterfazUsuario.this,BookingActivity.class);
               moving.putExtra("NumControl",TNumControl2);
               startActivity(moving);
            }



    });
        mButtonVerSolicitud.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                Intent moving = new Intent(InterfazUsuario.this,VerLista.class);
                moving.putExtra("NumControl",TNumControl2);
                startActivity(moving);
            }
        });
}
}
