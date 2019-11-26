package com.example.aplicacion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Questionario extends AppCompatActivity {
    DatabaseHelper db;
    Button answer1, answer2, answer3, answer4, answer5;
    TextView question;

    private Preguntas mPreguntas = new Preguntas();

    private String mRespuesta;

    private int mPreguntasLenght = mPreguntas.mPreguntas.length;

    private int Economia = 0, Diseño = 0, Salud = 0, Ciencia = 0, Arte = 0;
    Intent a = new Intent(this,VerLista.class);
    Integer r;
    String NumControl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);

        db = new DatabaseHelper(this);

        r = 0;
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        answer5 = (Button) findViewById(R.id.answer5);

        Intent intent = getIntent();

        NumControl = intent.getExtras().getString("NumControl");

        question = (TextView) findViewById(R.id.pregunta);
        updateQuestion(r);

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r == 9) {
                    end();
                }else {
                    Economia++;
                    r++;
                    updateQuestion(r);
                }
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r == 9) {
                    end();
                }else {
                    Diseño++;
                    r++;
                    updateQuestion(r);
                }
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r == 9) {
                    end();
                } else {

                    Salud++;
                    r++;
                    updateQuestion(r);
                }
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r == 9) {
                    end();
                }else {
                    Ciencia++;
                    r++;
                    updateQuestion(r);
                }
            }
        });
        answer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (r == 9) {
                    end();
                } else {
                    Arte++;
                    r++;
                    updateQuestion(r);
                }
            }
        });

    }

    private void updateQuestion(int numero) {
        question.setText(mPreguntas.getQuestion(numero));
        answer1.setText(mPreguntas.getRespuestas1(numero));
        answer2.setText(mPreguntas.getRespuestas2(numero));
        answer3.setText(mPreguntas.getRespuestas3(numero));
        answer4.setText(mPreguntas.getRespuestas4(numero));
        answer5.setText(mPreguntas.getRespuestas5(numero));
    }





    private void end(){

        String resultado="";
        ArrayList<Integer> array = new ArrayList();
        array.add(Economia);
        array.add(Diseño);
        array.add(Salud);
        array.add(Ciencia);
        array.add(Arte);
        Collections.sort(array);
        Collections.reverse(array);

        if(array.get(0)==Economia){
            resultado="Economia";
        }
        if(array.get(0)==Diseño){
            resultado="Diseño";
        }
        if(array.get(0)==Salud){
            resultado="Salud";
        }
        if(array.get(0)==Ciencia){
            resultado="Ciencias";
        }
        if(array.get(0)==Arte){
            resultado="Artes";
        }


        db.updateResultado(resultado,NumControl);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Questionario.this);

        alertDialogBuilder
                .setMessage("Fin del examen ")
                .setPositiveButton("Salir",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                startActivity(a);
                                finish();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}
