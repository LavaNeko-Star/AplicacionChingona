package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class VerListaDes extends AppCompatActivity {
    DatabaseHelper db;
    ListView lv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lista_des);

        DatabaseHelper db = new DatabaseHelper(this);

        lv = (ListView) findViewById(R.id.desuser_list);

        String status="En proceso";

        Cursor cursor =db.getSolicitudUserDes(status);

        listAdapterDes listAdapter = new listAdapterDes(this,cursor);
        lv.setAdapter(listAdapter);

    }
}
