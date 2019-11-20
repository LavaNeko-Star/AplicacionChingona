package com.example.aplicacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class VerListaCoordinador extends AppCompatActivity {
    DatabaseHelper db;
    public ListView lv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lista_coordinador);

        DatabaseHelper db = new DatabaseHelper(this);

        Intent intent = getIntent();
        final String TNumControl2 = intent.getExtras().getString("NumControl");

        lv = (ListView) findViewById(R.id.coruser_list);

        Cursor cursor = db.getSolicitudUserCoor(TNumControl2);

        listAdapterCor listAdapter = new listAdapterCor(this, cursor);
        lv.setAdapter(listAdapter);

        }



        }


