package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.assist.AssistContent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.Intent;
public class VerLista extends AppCompatActivity {

    DatabaseHelper db;
    ArrayList<String> listItem;
    ArrayAdapter Adapter;
    public static listAdapter listadapter;
    public ListView lv;
    Button examen;
Integer AutorizacionExamen=0;
String ResultadoExamen="";
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_lista);
        DatabaseHelper db = new DatabaseHelper(this);

        final String TNumControl2 = intent.getExtras().getString("NumControl");
        examen = (Button) findViewById(R.id.btnExamen);
        lv = (ListView) findViewById(R.id.user_list);


        Cursor cursor = db.getSolicitudUser(TNumControl2);

        if (cursor.moveToFirst()) {
            AutorizacionExamen = cursor.getInt(cursor.getColumnIndex("AutorizacionExamen"));
            ResultadoExamen = cursor.getString(cursor.getColumnIndex("ResultadoExamen"));
        }

        if(AutorizacionExamen.equals(0)){
            examen.setVisibility(View.GONE);
        }
        if(!ResultadoExamen.equals("")){
            examen.setVisibility(View.GONE);
        }



        listAdapter listadapter = new listAdapter(this, cursor);


        lv.setAdapter(listadapter);
        listadapter.notifyDataSetChanged();

        if (cursor.moveToNext()) {
            String newStatus = cursor.getString(cursor.getColumnIndex("Status"));

            if (!newStatus.equals("En proceso")) {
                examen.setVisibility(View.GONE);

            }

        }
        examen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moving = new Intent(VerLista.this,Questionario.class);
                moving.putExtra("NumControl",TNumControl2);
                startActivity(moving);
                finish();
            }
        });
    }
}






