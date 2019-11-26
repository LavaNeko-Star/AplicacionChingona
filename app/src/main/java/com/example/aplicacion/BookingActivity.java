package com.example.aplicacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookingActivity extends AppCompatActivity {
    DatabaseHelper db;
    Spinner mSpinner;
    Button mButtonRealizarSolicitud;
    Integer NumControlUsuario;
    Integer NumControlCoordinador;
    Integer IDCarreraActual;
    Integer IDCarreraACambiar;
    String FechaRealizacion;
    Integer SemestreUsuario;
    Integer ID, IDCarrera, Semestre;
    Integer NumControlAcademia;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        db= new DatabaseHelper(this);
        mSpinner=(Spinner)findViewById(R.id.spinner2);

        ContentValues values = new ContentValues();

        Intent intent = getIntent();
        final String TNumControl2 = intent.getExtras().getString("NumControl");

        ArrayList<String> listCa= db.getAllCareers();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinnerlayout,R.id.txt,listCa);

        mButtonRealizarSolicitud = (Button)findViewById(R.id.mButtonRealizarSolicitud);
        mSpinner.setAdapter(adapter);

        Cursor cursor = db.getDataUser(TNumControl2);

        while (cursor.moveToNext()) {
            IDCarrera = cursor.getInt(cursor.getColumnIndex("IDCarrera"));
            Semestre = cursor.getInt(cursor.getColumnIndex("Semestre"));
        }



        mButtonRealizarSolicitud=(Button)findViewById(R.id.mButtonRealizarSolicitud);



        mButtonRealizarSolicitud.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BookingActivity.this);

                alertDialogBuilder
                        .setMessage("Confirme creacion de solicitud")
                        .setPositiveButton("Confirmar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {


                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        String strDate = sdf.format(new Date());
                                        NumControlUsuario = Integer.valueOf(TNumControl2);
                                        IDCarreraActual = IDCarrera;
                                        SemestreUsuario = Semestre;
                                        FechaRealizacion = strDate;
                                        String NombreCarrera = mSpinner.getSelectedItem().toString();
                                        IDCarreraACambiar = db.getIdFromCareer(NombreCarrera);
                                        NumControlCoordinador = db.getIDFromCoordinador(IDCarreraActual);
                                        NumControlAcademia = db.getIDFromAcademia(IDCarreraACambiar);

                                        boolean res = db.checkSolicitud(String.valueOf(NumControlUsuario));

                                        if(IDCarreraActual==IDCarreraACambiar){
                                            Toast.makeText(BookingActivity.this,"Tu carrera actual es la misma",Toast.LENGTH_SHORT).show();

                                        } else if(res==true){
                                            Toast.makeText(BookingActivity.this,"Ya has hecho una solicitud",Toast.LENGTH_SHORT).show();
                                        }else {

                                            long val = db.addSolicitud(NumControlUsuario, NumControlCoordinador, NumControlAcademia, IDCarreraActual, IDCarreraACambiar, FechaRealizacion, SemestreUsuario);


                                            if (val > 0) {
                                                Toast.makeText(BookingActivity.this, "Solicitud exitosa", Toast.LENGTH_SHORT).show();
                                                finish();

                                            } else {
                                                Toast.makeText(BookingActivity.this, "Fallo solicitud", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }
                                });
                alertDialogBuilder.setNegativeButton("Cancelar",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int i){
                         dialog.cancel();
                        }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
});
    }
}
