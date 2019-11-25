package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PerfilUsuario extends AppCompatActivity {
    DatabaseHelper db;
    TextView TNumControl,TCarrera, TSemestre,TNombre,TApellidoPaterno,TApellidoMaterno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);



        TNumControl = (TextView)findViewById(R.id.NumeroControlInfo);
        TCarrera = (TextView)findViewById(R.id.txtCarreraPerfil);
        TSemestre = (TextView)findViewById(R.id.txtSemestrePerfil);
        TNombre = (TextView)findViewById(R.id.txtNombre);
        TApellidoPaterno = (TextView)findViewById(R.id.txtApellidoPaterno);
        TApellidoMaterno = (TextView)findViewById(R.id.txtApellidoMaterno);


        db = new DatabaseHelper(this);

        Intent intent = getIntent();

        final String TNumControl2 = intent.getExtras().getString("NumControl");



    }
}
