package com.example.aplicacion;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.loader.content.Loader;


public class listAdapterCor extends CursorAdapter {
    DatabaseHelper db;
    private CursorAdapter adapter;
   VerListaCoordinador ver;
   String newStatus;
    public listAdapterCor(Context context, Cursor cursor) {
        super(context, cursor, 0);

    }


    public void bindView(final View view, final Context context, final Cursor cursor) {
        db = new DatabaseHelper(context);
        TextView textViewName = (TextView) view.findViewById(R.id.txtNumControlAlumno);

        TextView textViewSemestre = (TextView) view.findViewById(R.id.txtSemestre);
        TextView textViewCarreraActual = (TextView) view.findViewById(R.id.txtCarreraActual);
        TextView textViewCarreraACambiar = (TextView) view.findViewById(R.id.txtCarreraACambiarse);
        TextView textViewEstatus = (TextView) view.findViewById(R.id.txtEstatus);

        final Button buttonConvalidar = (Button) view.findViewById(R.id.Enviaracoonvalidacion);
        final Button buttonRechazar = (Button) view.findViewById(R.id.Rechazar);
        Button verperfil =(Button)view.findViewById(R.id.Verperfil);
        final String TableIDUsuario = cursor.getString(cursor.getColumnIndex("NumControlUsuario"));
        final String TableIDCoordinador = cursor.getString(cursor.getColumnIndex("NumControlCoordinador"));
        String TableSemestre = cursor.getString(cursor.getColumnIndex("SemestreUsuario"));
        String TableCarreraActual = cursor.getString(cursor.getColumnIndex("IDCarreraActual"));
        String TableCarreraACambiar = cursor.getString(cursor.getColumnIndex("IDCarreraACambiar"));
        final String Estatus = cursor.getString(cursor.getColumnIndex("Status"));

        String carreraactual = db.getNameFromCareer(Integer.valueOf(TableCarreraActual));
        String carreraacambiar = db.getNameFromCareer(Integer.valueOf(TableCarreraACambiar));

        textViewName.setText(TableIDUsuario);

        textViewSemestre.setText(TableSemestre);
        textViewEstatus.setText(Estatus);
        textViewCarreraActual.setText(carreraactual);
        textViewCarreraACambiar.setText(carreraacambiar);

        if(Estatus.equals("Cancelada")){
            buttonConvalidar.setVisibility(view.GONE);
            buttonRechazar.setVisibility(view.GONE);
        }
        if(Estatus.equals("Rechazado")){
            buttonConvalidar.setVisibility(view.GONE);
            buttonRechazar.setVisibility(view.GONE);
        }
        if(Estatus.equals("En proceso")){
            buttonConvalidar.setVisibility(view.GONE);
        }
        if(Estatus.equals("Confirmado")){
            buttonConvalidar.setVisibility(view.GONE);
            buttonRechazar.setVisibility(view.GONE);
        }

        buttonConvalidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = "En proceso";
                db.updateStatus(status, TableIDUsuario);
                Cursor cursor = db.getSolicitudUserCoor(TableIDCoordinador);
                changeCursor(cursor);

                    String newStatus = cursor.getString(cursor.getColumnIndex("Status"));
                    notifyDataSetChanged();

                if(newStatus.equals("En proceso")){
                    buttonConvalidar.setVisibility(view.GONE);

                }

                }

                                            });



        buttonRechazar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String status = "Rechazado";
                db.updateStatus(status, TableIDUsuario);
                Cursor cursor = db.getSolicitudUserCoor(TableIDCoordinador);
                changeCursor(cursor);

                    String newStatus = cursor.getString(cursor.getColumnIndex("Status"));
                    notifyDataSetChanged();

                if(newStatus.equals("Rechazado")){
                    buttonConvalidar.setVisibility(view.GONE);
                    buttonRechazar.setVisibility(view.GONE);
                }


                }




        });
        verperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moving = new Intent(context,PerfilUsuario.class);
                moving.putExtra("NumControl",TableIDUsuario);
                context.startActivity(moving);
            }
        });
    }


    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.formatolistviewcor, parent, false);

    }
    public void changeCursor(Cursor cursor){
       super.swapCursor(cursor);

    }

}


