package com.example.aplicacion;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class listAdapterAca extends CursorAdapter {
    DatabaseHelper db;
    private CursorAdapter adapter;
    private Object VerListaAca;

    public listAdapterAca(Context context, Cursor cursor) {
        super(context, cursor, 0);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.formatolistviewaca, parent, false);
    }

    @Override
    public void bindView(View view,final Context context, Cursor cursor) {

        db = new DatabaseHelper(context);
        TextView textViewName = (TextView) view.findViewById(R.id.txtNumControlAlumno);
        TextView textViewSemestre = (TextView) view.findViewById(R.id.txtSemestre);
        TextView textViewCarreraActual = (TextView) view.findViewById(R.id.txtCarreraActual);
        TextView textViewCarreraACambiar = (TextView) view.findViewById(R.id.txtCarreraACambiarse);
        TextView textViewEstatus = (TextView) view.findViewById(R.id.txtEstatus);

        final Button buttonConvalidar = (Button) view.findViewById(R.id.Convalidar);

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


        buttonConvalidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent moving = new Intent(context,Convalidacion.class);
                moving.putExtra("NumControl",TableIDUsuario);
                context.startActivity(moving);
            }
        });
    }
}
