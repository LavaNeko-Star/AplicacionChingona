package com.example.aplicacion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class listAdapter extends CursorAdapter {
    DatabaseHelper db;

    public listAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);

    }

    public void bindView(View view, final Context context, final Cursor cursor) {
        db = new DatabaseHelper(context);
        final VerLista verlista = new VerLista();

        TextView textViewName = (TextView) view.findViewById(R.id.txtNumControlAlumno);
        TextView textViewCoordinador = (TextView) view.findViewById(R.id.txtNumControlCoordinador);
        TextView textViewSemestre = (TextView) view.findViewById(R.id.txtSemestre);
        TextView textViewCarreraActual = (TextView) view.findViewById(R.id.txtCarreraActual);
        TextView textViewCarreraACambiar = (TextView) view.findViewById(R.id.txtCarreraACambiarse);
        TextView textViewResultados = (TextView) view.findViewById(R.id.txtResultado);

        final TextView textViewEstatus = (TextView) view.findViewById(R.id.txtEstatus);

        final Button buttonConfirmar = (Button) view.findViewById(R.id.Confirmar);
        final Button buttonCancelar = (Button) view.findViewById(R.id.Cancelar);





        final String TableIDUsuario = cursor.getString(cursor.getColumnIndex("NumControlUsuario"));
        String TableIDCoordinador = cursor.getString(cursor.getColumnIndex("NumControlCoordinador"));
        String TableSemestre = cursor.getString(cursor.getColumnIndex("SemestreUsuario"));
        String TableCarreraActual = cursor.getString(cursor.getColumnIndex("IDCarreraActual"));
        String TableCarreraACambiar = cursor.getString(cursor.getColumnIndex("IDCarreraACambiar"));
        String TableResultado = cursor.getString(cursor.getColumnIndex("ResultadoExamen"));
        final String Estatus = cursor.getString(cursor.getColumnIndex("Status"));


        String carreraactual = db.getNameFromCareer(Integer.valueOf(TableCarreraActual));
        String carreraacambiar = db.getNameFromCareer(Integer.valueOf(TableCarreraACambiar));

        textViewName.setText(TableIDUsuario);
        textViewCoordinador.setText(TableIDCoordinador);
        textViewSemestre.setText(TableSemestre);
        textViewEstatus.setText(Estatus);
        textViewCarreraActual.setText(carreraactual);
        textViewCarreraACambiar.setText(carreraacambiar);
        textViewResultados.setText(TableResultado);

         if(Estatus.equals("Rechazado")||Estatus.equals("Confirmado")||Estatus.equals("Cancelada")){
          buttonCancelar.setVisibility(view.GONE);
          buttonConfirmar.setVisibility(view.GONE);
          }



        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String status = "Confirmado";
                db.updateStatus(status, TableIDUsuario);
                Cursor cursor3 = db.getSolicitudUser(TableIDUsuario);
                listAdapter.this.notifyDataSetChanged();

                swapCursor(cursor3);
                String newStatus = cursor3.getString(cursor3.getColumnIndex("Status"));
                if( newStatus.equals("Confirmado")){
                    buttonCancelar.setVisibility(view.GONE);
                    buttonConfirmar.setVisibility(view.GONE);
                }
            }


        });
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder
                        .setMessage("¿Esta seguro de cancelar la solicitud?")
                        .setPositiveButton("Cancelar solicitud",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        String status = "Cancelada";
                                        db.updateStatus(status, TableIDUsuario);

                                        Cursor cursor3 = db.getSolicitudUser(TableIDUsuario);

                                        swapCursor(cursor3);

                                        String newStatus = cursor3.getString(cursor3.getColumnIndex("Status"));
                                        if( newStatus.equals("Cancelada")){
                                            buttonCancelar.setVisibility(view.GONE);
                                            buttonConfirmar.setVisibility(view.GONE);
                                        }
                                    }
                                });
                alertDialogBuilder.setNegativeButton("Salir",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int i){
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }





        });

    }



    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.formatolistview, parent, false);

    }




}


