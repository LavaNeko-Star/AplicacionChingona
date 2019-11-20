package com.example.aplicacion;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class listAdapterMateria extends CursorAdapter {
    DatabaseHelper db;
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.formatolistviewmaterias, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
       db = new DatabaseHelper(context);
        String TableIDUsuario = cursor.getString(cursor.getColumnIndex("NumControlUsuario"));
        String TableCarreraActual = cursor.getString(cursor.getColumnIndex("IDCarreraActual"));
        String TableCarreraACambiar = cursor.getString(cursor.getColumnIndex("IDCarreraACambiar"));

        ArrayList<String> listaActual = new ArrayList<>();
        ArrayList<String> listaCambiar = new ArrayList<>();
        ArrayList<Integer> listaReprobadas = new ArrayList<>();



        ArrayList<Integer>listamateriasalumno = new ArrayList<>();
        ArrayList<String>nombremateriasalumno = new ArrayList<>();
        ArrayList<String>nombremateriasreprobadasalumno = new ArrayList<>();

        db.getDataMaterias(TableCarreraActual,listaActual);
        db.getDataMaterias(TableCarreraACambiar,listaCambiar);



        TableLayout ll = (TableLayout)view.findViewById(R.id.tablamateria);

        TableLayout tabla2 = (TableLayout)view.findViewById(R.id.tablareprobadas);

        ArrayList<String> common = new ArrayList<String>(listaActual);

        common.retainAll(listaCambiar);

        db.getMateriasReprobadasUsuario(TableIDUsuario,listaReprobadas);


        db.getMateriasUsuario(TableIDUsuario,listamateriasalumno);

        for(int i=0;i<listaReprobadas.size();i++){
            Integer id = listaReprobadas.get(i);
            String nombre = db.getNameFromMateria(id);
            nombremateriasreprobadasalumno.add(nombre);
        }


        for(int i=0;i<listamateriasalumno.size();i++){
         Integer id = listamateriasalumno.get(i);
         String nombre = db.getNameFromMateria(id);
         nombremateriasalumno.add(nombre);
        }

        ArrayList<String> common3 = new ArrayList<String>(nombremateriasreprobadasalumno);
        common3.retainAll(common);

        ArrayList<String> common2 = new ArrayList<String>(nombremateriasalumno);
        common2.retainAll(common);


        for(int i=0;i<common2.size();i++){
            TableRow row= new TableRow(context);
            TextView txtGeneric = new TextView(context);
            txtGeneric.setText(common2.get(i));
            txtGeneric.setTextSize(20);
            row.addView(txtGeneric);
            ll.addView(row);
        }

        for(int i=0;i<common3.size();i++){
            TableRow row= new TableRow(context);
            TextView txtGeneric2 = new TextView(context);
            txtGeneric2.setText(common3.get(i));
            txtGeneric2.setTextSize(20);
            row.addView(txtGeneric2);
            tabla2.addView(row);
        }

        if(common3.size()>0){
            Toast.makeText(context,"Una materia requerida esta reprobada.La solicitud sera rechazada.",Toast.LENGTH_LONG).show();
            String status = "Rechazado";
            db.updateStatus(status,TableIDUsuario);

        }





    }

    public listAdapterMateria(Context context, Cursor cursor) {
        super(context, cursor, 0);

    }
}

