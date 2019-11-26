package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PerfilUsuario extends AppCompatActivity {
    DatabaseHelper db;
    TextView TNumControl,TCarrera, TSemestre,TNombre,TApellidoPaterno,TApellidoMaterno;
    Integer IDCarrera,Semestre;
    ImageView imagen;
    String nombre,apellidomaterno,apellidopaterno;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);


        imagen =(ImageView)findViewById(R.id.imagen);
        TNumControl = (TextView)findViewById(R.id.NumeroControlInfo);
        TCarrera = (TextView)findViewById(R.id.txtCarreraPerfil);
        TSemestre = (TextView)findViewById(R.id.txtSemestrePerfil);
        TNombre = (TextView)findViewById(R.id.txtNombre);
        TApellidoPaterno = (TextView)findViewById(R.id.txtApellidoPaterno);
        TApellidoMaterno = (TextView)findViewById(R.id.txtApellidoMaterno);


        db = new DatabaseHelper(this);

        Intent intent = getIntent();

        final String TNumControl2 = intent.getExtras().getString("NumControl");

        TNumControl.setText(TNumControl2);
        Cursor cursor = db.getDataUser(TNumControl2);

        while (cursor.moveToNext()) {
            IDCarrera = cursor.getInt(cursor.getColumnIndex("IDCarrera"));
            Semestre = cursor.getInt(cursor.getColumnIndex("Semestre"));

        }
        String nombrecarrera = db.getNameFromCareer(IDCarrera);
        TCarrera.setText(nombrecarrera);
        TSemestre.setText(Integer.toString(Semestre));

        Cursor cursorr = db.getDataProfileU(TNumControl2);
        while(cursorr.moveToNext()) {
             nombre = cursorr.getString(7);
             apellidopaterno = cursorr.getString(8);
             apellidomaterno = cursorr.getString(9);
                                    }
        TNombre.setText(nombre);
        TApellidoPaterno.setText(apellidopaterno);
        TApellidoMaterno.setText(apellidomaterno);
    }
}
