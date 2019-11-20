package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class Convalidacion extends AppCompatActivity {
DatabaseHelper db;
    ListView list;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convalidacion);
         db= new DatabaseHelper(this);
       Intent intent = getIntent();
       final String numcontrol = intent.getExtras().getString("NumControl");

       list =(ListView) findViewById(R.id.listmaterias);

   Cursor cursor = db.getSolicitudUser(numcontrol);

   listAdapterMateria lista = new listAdapterMateria(this,cursor);
   list.setAdapter(lista);


        final Button btnconvalidar = (Button)findViewById(R.id.btnConvalidar);

        while(cursor.moveToNext()) {
            String convalidacion = cursor.getString(cursor.getColumnIndex("ConvalidacionFinalizada"));

            if (convalidacion.equals("1")) {
                btnconvalidar.setVisibility(View.GONE);

            }
        }


   btnconvalidar.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           String status="En proceso";
           db.convalidacionhecha(1,numcontrol);
           Cursor cursor = db.getSolicitudUserDes(status);

           String convalidacion= cursor.getString(cursor.getColumnIndex("ConvalidacionFinalizada"));
           if(convalidacion.equals("1")){
               btnconvalidar.setVisibility(View.GONE);

           }


       }
   });

    }
}
