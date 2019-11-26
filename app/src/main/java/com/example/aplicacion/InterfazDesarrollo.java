package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class InterfazDesarrollo extends AppCompatActivity {
    DatabaseHelper db;
    Button mButtonVerSolicitud;
    TextView TID,TNumControlDesarrollo;
Integer id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_desarrollo);

        db = new DatabaseHelper(this);



        Intent intent = getIntent();

        final String NumControl2 = intent.getExtras().getString("NumControlDesarrollo");


TNumControlDesarrollo =(TextView)findViewById(R.id.TNumControlDesarrollo);

        TNumControlDesarrollo.setText(NumControl2);

         Cursor cursor = db.getDataDesarrollo(NumControl2);
        mButtonVerSolicitud = (Button)findViewById(R.id.mButtonVerSolicitudDes);

        TID = (TextView)findViewById(R.id.TID);

        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndex("ID"));

        }
        TID.setText(String.valueOf(id));

        mButtonVerSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moving = new Intent(InterfazDesarrollo.this,VerListaDes.class);
                startActivity(moving);
            }
        });
    }
}
