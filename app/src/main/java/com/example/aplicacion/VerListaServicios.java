package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class VerListaServicios extends AppCompatActivity {
      DatabaseHelper db;
      ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lista_servicios);

        DatabaseHelper db = new DatabaseHelper(this);

        lv = (ListView) findViewById(R.id.seruser_list);

        String status="Confirmado";

        Cursor cursor =db.getSolicitudUserDes(status);

        listAdapterSer listAdapter = new listAdapterSer(this,cursor);
        lv.setAdapter(listAdapter);

    }
    }

