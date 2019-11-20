package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class VerListaAca extends AppCompatActivity {
    DatabaseHelper db;
    public ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lista_aca);

        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        final String TNumControl2 = intent.getExtras().getString("NumControlAcademia");;


        lv = (ListView) findViewById(R.id.acauser_list);

        String status="En proceso";

        Cursor cursor = db.getSolicitudUserAca(TNumControl2,status);

        listAdapterAca listAdapter = new listAdapterAca(this, cursor);
        lv.setAdapter(listAdapter);



    }



}