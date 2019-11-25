package com.example.aplicacion;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class listAdapterDes extends CursorAdapter {
    DatabaseHelper db;
    private CursorAdapter adapter;
    VerListaCoordinador ver;
    String newStatus;
    public listAdapterDes(Context context, Cursor cursor) {
        super(context, cursor, 0);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.formatolistviewdes, parent, false);


    }

    @Override
    public void bindView(final View view, Context context, Cursor cursor) {
        db = new DatabaseHelper(context);
        TextView textViewName = (TextView) view.findViewById(R.id.txtNumControlAlumno);

        TextView textViewSemestre = (TextView) view.findViewById(R.id.txtSemestre);
        TextView textViewCarreraActual = (TextView) view.findViewById(R.id.txtCarreraActual);
        TextView textViewCarreraACambiar = (TextView) view.findViewById(R.id.txtCarreraACambiarse);
         TextView textViewEstatus = (TextView) view.findViewById(R.id.txtEstatus);

         final Button buttonExamen = (Button) view.findViewById(R.id.aplicarexamen);


        final String TableIDUsuario = cursor.getString(cursor.getColumnIndex("NumControlUsuario"));
        final String TableIDCoordinador = cursor.getString(cursor.getColumnIndex("NumControlCoordinador"));
        String TableSemestre = cursor.getString(cursor.getColumnIndex("SemestreUsuario"));
        String TableCarreraActual = cursor.getString(cursor.getColumnIndex("IDCarreraActual"));
        String TableCarreraACambiar = cursor.getString(cursor.getColumnIndex("IDCarreraACambiar"));
        String Estatus = cursor.getString(cursor.getColumnIndex("Status"));
        final String AutorizacionExamen = cursor.getString(cursor.getColumnIndex("AutorizacionExamen"));

        String carreraactual = db.getNameFromCareer(Integer.valueOf(TableCarreraActual));
        String carreraacambiar = db.getNameFromCareer(Integer.valueOf(TableCarreraACambiar));

        textViewName.setText(TableIDUsuario);
        textViewSemestre.setText(TableSemestre);
        textViewEstatus.setText(Estatus);
        textViewCarreraActual.setText(carreraactual);
        textViewCarreraACambiar.setText(carreraacambiar);

        if(AutorizacionExamen.equals("1")){
            buttonExamen.setVisibility(view.GONE);

        }

        buttonExamen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String status="En proceso";
                db.autorizarExamen(1,TableIDUsuario);
                Cursor cursor = db.getSolicitudUserDes(status);
                changeCursor(cursor);

                String aprobacionExamen= cursor.getString(cursor.getColumnIndex("AutorizacionExamen"));
                if(aprobacionExamen.equals("1")){
                    buttonExamen.setVisibility(view.GONE);

                }

            }
               });
    }
}
