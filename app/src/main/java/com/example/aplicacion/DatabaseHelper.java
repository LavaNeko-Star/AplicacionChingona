package com.example.aplicacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTableLockedException;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Database_name = "registros.db";
    public static final String Table_name = "Usuario";
    public static final String Table_name2 = "Coordinador";
    public static final String Table_name3 = "Carrera";
    public static final String Table_name4 = "Materia";
    public static final String Table_name5 = "Calificacion";
    public static final String Table_name6 = "Academia";
    public static final String Table_name7 = "DepartamentoDeDesarrolloAcademico";
    public static final String Table_name8 = "DepartamentoDeServiciosEscolares";
    public static final String Table_name9 = "Solicitud";
    public static final String Table_name10 = "Examen";

    public static final String col_1 = "NumControl";
    public static final String col_2 = "ID";
    public static final String col_3 = "Password";

    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_name, null, 23);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Carrera (IDCarrera INTEGER PRIMARY KEY, NombreCarrera Text)");
        db.execSQL("CREATE TABLE Usuario (IDUsuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,Nombre TEXT,ApellidoPaterno TEXT,ApellidoMaterno TEXT)");
        db.execSQL("CREATE TABLE Examen (IDExamen INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,FechaRealizacion TEXT,FechaRealizado TEXT,ResultadoExamen TEXT )");
        db.execSQL("CREATE TABLE Materia (IDMateria INTEGER PRIMARY KEY, NombreMateria Text,IDCarrera INTEGER, FOREIGN KEY (IDCarrera) REFERENCES Carrera(IDCarrera))");
        db.execSQL("CREATE TABLE Calificacion(IDCalificacion INTEGER PRIMARY KEY, Calificacion INTEGER, IDEstudiante INTEGER,IDMateria INTEGER, FOREIGN KEY (IDEstudiante) REFERENCES Estudiante(NumControl), FOREIGN KEY (IDMateria) REFERENCES Materia(IDMateria))");
        db.execSQL("CREATE TABLE Estudiante (IDUsuario INTEGER NOT NULL,NumControl INTEGER PRIMARY KEY, ID INTEGER,Password TEXT, IDCarrera INTEGER,Semestre INTEGER, FOREIGN KEY (IDCarrera) REFERENCES Carrera(IDCarrera),FOREIGN KEY (IDUsuario) REFERENCES Usuario(IDUsuario))");
        db.execSQL("CREATE TABLE Coordinador (NumControlCoordinador INTEGER PRIMARY KEY, ID INTEGER,Password TEXT,IDCarrera TEXT,FOREIGN KEY (IDCarrera) REFERENCES Carrera(IDCarrera))");
        db.execSQL("CREATE TABLE Academia (NumControlAcademia INTEGER PRIMARY KEY, ID INTEGER, Password TEXT,IDCarrera INTEGER,FOREIGN KEY (IDCarrera) REFERENCES Carrera(IDCarrera))");
        db.execSQL("CREATE TABLE DepartamentoDeDesarrolloAcademico (NumControlDesarrollo INTEGER PRIMARY KEY, ID INTEGER, Password TEXT)");
        db.execSQL("CREATE TABLE DepartamentoDeServiciosEscolares (NumControlServicios INTEGER PRIMARY KEY, ID INTEGER, Password TEXT)");
        db.execSQL("CREATE TABLE Solicitud(IDSolicitud INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,NumControlUsuario INTEGER, NumControlCoordinador INTEGER,NumControlAcademia INTEGER, IDCarreraActual INTEGER, IDCarreraACambiar INTEGER,FechaRealizacion TEXT,SemestreUsuario INTEGER,Status TEXT,AutorizacionExamen INTEGER,ResultadoExamen Text,ConvalidacionFinalizada INTEGER,FOREIGN KEY (NumControlUsuario) REFERENCES Estudiante(NumControl), FOREIGN KEY (NumControlCoordinador) REFERENCES Coordinador(NumControlCoordinador),FOREIGN KEY (IDCarreraActual) REFERENCES Usuario(IDCarrera), FOREIGN KEY (IDCarreraACambiar) REFERENCES Carrera(IDCarrera),FOREIGN KEY (NumControlAcademia) REFERENCES Academia(NumControlAcademia))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_name);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name2);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name3);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name4);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name5);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name6);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name7);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name8);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name9);
        db.execSQL("DROP TABLE IF EXISTS " + Table_name10);
        onCreate(db);

    }




    public long addEstudiante(Integer IDUsuario,Integer NumControl, Integer ID, String Password, Integer IDCarrera, Integer Semestre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDUsuario",IDUsuario);
        contentValues.put("NumControl", NumControl);
        contentValues.put("ID", 1);
        contentValues.put("Password", Password);
        contentValues.put("IDCarrera", IDCarrera);
        contentValues.put("Semestre", Semestre);
        long res = db.insert("Estudiante", null, contentValues);
        db.close();
        return res;

    }
    public long addExamen(String FechaRealizacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FechaRealizacion", FechaRealizacion);
        long res = db.insert("Examen", null, contentValues);
        db.close();
        return res;
    }
    public long addUsuario(String Nombre,String ApellidoPaterno,String ApellidoMaterno) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nombre", Nombre);
        contentValues.put("ApellidoPaterno",ApellidoPaterno);
        contentValues.put("ApellidoPaterno",ApellidoMaterno);
        long res = db.insert("Usuario", null, contentValues);
        db.close();
        return res;
    }


    public long addSolicitud(Integer NumControlUsuario, Integer NumControlCoordinador, Integer NumControlAcademia, Integer IDCarreraActual, Integer IDCarreraACambiar, String FechaRealizacion, Integer SemestreUsuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NumControlUsuario", NumControlUsuario);
        contentValues.put("NumControlCoordinador", NumControlCoordinador);
        contentValues.put("NumControlAcademia", NumControlAcademia);
        contentValues.put("IDCarreraActual", IDCarreraActual);
        contentValues.put("IDCarreraACambiar", IDCarreraACambiar);
        contentValues.put("FechaRealizacion", FechaRealizacion);
        contentValues.put("SemestreUsuario", SemestreUsuario);
        contentValues.put("Status", "Enviado");
        contentValues.put("ResultadoExamen", "");
        contentValues.put("AutorizacionExamen", 0);
        contentValues.put("ConvalidacionFinalizada",0);
        long res = db.insert("Solicitud", null, contentValues);
        db.close();
        return res;

    }

    public boolean checkUser(String NumControl, String password) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Estudiante where NumControl = ? and Password = ?", new String[]{NumControl, password});
        Cursor cursor2 = db.rawQuery("select * from Coordinador where NumControlCoordinador = ? and Password = ?", new String[]{NumControl, password});
        Cursor cursor3 = db.rawQuery("select * from DepartamentoDeServiciosEscolares where NumControlServicios = ? and Password = ?", new String[]{NumControl, password});
        Cursor cursor4 = db.rawQuery("select * from Academia where NumControlAcademia = ? and Password = ?", new String[]{NumControl, password});
        Cursor cursor5 = db.rawQuery("select * from DepartamentoDeDesarrolloAcademico where NumControlDesarrollo = ? and Password = ?", new String[]{NumControl, password});

        int count = cursor.getCount();
        int count2 = cursor2.getCount();
        int count3 = cursor3.getCount();
        int count4 = cursor4.getCount();
        int count5 = cursor5.getCount();


        if (count > 0) {
            return true;
        } else if (count2 > 0) {
            return true;
        } else if (count2 > 0) {
            return true;
        } else if (count3 > 0) {
            return true;
        } else if (count4 > 0) {
            return true;
        } else if (count5 > 0) {
            return true;
        } else {

            return false;
        }
    }

    public boolean checkSolicitud(String NumControl) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Solicitud where NumControlUsuario = ?", new String[]{NumControl});
        int count = cursor.getCount();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getIdForString(String NumControl) {
        int res = 0;
        char a = NumControl.charAt(0);
        char b = NumControl.charAt(1);
        String ab = a + "" + b;
        int abc = Integer.valueOf(ab);
        if (abc < 20) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query("Estudiante", new String[]{"ID",}, "NumControl" + "=?", new String[]{NumControl}, null, null, null, null);
            if ((cursor != null) && (cursor.getCount() > 0)) {
                cursor.moveToFirst();
                res = cursor.getInt(cursor.getColumnIndex("ID"));
            } else {
                res = -1;
            }
            if (cursor != null) {
                cursor.close();
            }
        } else if (abc == 21) {

            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query("Coordinador", new String[]{"ID",}, "NumControlCoordinador" + "=?", new String[]{NumControl}, null, null, null, null);
            if ((cursor != null) && (cursor.getCount() > 0)) {
                cursor.moveToFirst();
                res = cursor.getInt(cursor.getColumnIndex("ID"));
            } else {
                res = -1;
            }
            if (cursor != null) {
                cursor.close();
            }
        } else if (abc == 22) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query("DepartamentoDeServiciosEscolares", new String[]{"ID",}, "NumControlServicios" + "=?", new String[]{NumControl}, null, null, null, null);
            if ((cursor != null) && (cursor.getCount() > 0)) {
                cursor.moveToFirst();
                res = cursor.getInt(cursor.getColumnIndex("ID"));
            } else {
                res = -1;
            }
            if (cursor != null) {
                cursor.close();
            }


        } else if (abc == 23) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query("Academia", new String[]{"ID",}, "NumControlAcademia" + "=?", new String[]{NumControl}, null, null, null, null);
            if ((cursor != null) && (cursor.getCount() > 0)) {
                cursor.moveToFirst();
                res = cursor.getInt(cursor.getColumnIndex("ID"));
            } else {
                res = -1;
            }
            if (cursor != null) {
                cursor.close();
            }


        } else if (abc == 24) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query("DepartamentoDeDesarrolloAcademico", new String[]{"ID",}, "NumControlDesarrollo" + "=?", new String[]{NumControl}, null, null, null, null);
            if ((cursor != null) && (cursor.getCount() > 0)) {
                cursor.moveToFirst();
                res = cursor.getInt(cursor.getColumnIndex("ID"));
            } else {
                res = -1;
            }
            if (cursor != null) {
                cursor.close();
            }


        }


        return res;
    }

    public Cursor getDataUser(String NumControl) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursoru = db.rawQuery("select * from Estudiante where NumControl =?", new String[]{NumControl});
        return cursoru;
    }
    public Cursor getLastUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "select seq from sqlite_sequence where name='Usuario';";
        Cursor cursoru = db.rawQuery(selectQuery,null);
        return cursoru;
    }


    public ArrayList<String> getAllCareers() {
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            String sql = "Select NombreCarrera From Carrera";
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String NombreCarrera = cursor.getString(cursor.getColumnIndex("NombreCarrera"));
                    list.add(NombreCarrera);

                }
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();

        }
        return list;
    }

    public String getNameFromCareer(Integer idcarrera) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT NombreCarrera from Carrera where IDCarrera ='" + idcarrera + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "0";
        }
        cursor.moveToFirst();
        String value = cursor.getString(cursor.getColumnIndex("NombreCarrera"));
        return value;
    }

    public String getNameFromMateria(Integer idmateria) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT NombreMateria from Materia where IDMateria ='" + idmateria + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return "0";
        }
        cursor.moveToFirst();
        String value = cursor.getString(cursor.getColumnIndex("NombreMateria"));
        return value;
    }

    public Integer getIDFromCoordinador(Integer idcarrera) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT NumControlCoordinador from Coordinador where IDCarrera ='" + idcarrera + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int value = cursor.getInt(cursor.getColumnIndex("NumControlCoordinador"));
        return value;
    }

    public Integer getIDFromAcademia(Integer idcarrera) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT NumControlAcademia from Academia where IDCarrera ='" + idcarrera + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int value = cursor.getInt(cursor.getColumnIndex("NumControlAcademia"));
        return value;
    }


    public Integer getIdFromCareer(String carrera) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT IDCarrera from Carrera where NombreCarrera ='" + carrera + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() < 1) {
            cursor.close();
            return 0;
        }

        cursor.moveToFirst();
        int value = cursor.getInt(cursor.getColumnIndex("IDCarrera"));
        return value;
    }

    public Cursor getSolicitudUser(String NumControl) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursoru = db.rawQuery("select rowid _id,* from Solicitud where NumControlUsuario =?", new String[]{NumControl});
        return cursoru;
    }

    public Cursor getSolicitudUserCoor(String NumControl) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursoru = db.rawQuery("select rowid _id,* from Solicitud where NumControlCoordinador =?", new String[]{NumControl});
        return cursoru;
    }

    public Cursor getSolicitudUserAca(String NumControl, String status) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursoru = null;
        if (NumControl != null) {
            cursoru = db.rawQuery("select rowid _id,* from Solicitud where NumControlAcademia =? AND Status =?", new String[]{NumControl, status});


        }
        return cursoru;
    }





    public Cursor getSolicitudUserDes(String status) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursoru = db.rawQuery("select rowid _id,* from Solicitud where Status =?", new String[]{status});
        return cursoru;
    }

    public Cursor getSolicitud(){
        SQLiteDatabase handler = this.getReadableDatabase();

        Cursor todoCursor = handler.rawQuery("SELECT  rowid _id,* FROM Solicitud ", null);
 return todoCursor;
    }




    public boolean verSiYaExisteSolicitud(String NumControlUsuario) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Solicitud where NumControlUsuario = ?", new String[]{NumControlUsuario});

        int count = cursor.getCount();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
    public void updateStatus(String status,String numcontrol){
        SQLiteDatabase db=this.getWritableDatabase();
        String strSQL = "UPDATE Solicitud SET Status ='"+ status +"'WHERE NumControlUsuario = "+ numcontrol;

        db.execSQL(strSQL);

    }

    public Cursor getDataServicios(String NumControlServicios) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursoru = db.rawQuery("select * from DepartamentoDeServiciosEscolares where NumControlServicios =?", new String[]{NumControlServicios});
        return cursoru;
    }


    public Cursor getDataCoordinador(String NumControlCoordinador) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursoru = db.rawQuery("select * from Coordinador where NumControlCoordinador =?", new String[]{NumControlCoordinador});
        return cursoru;
    }
    public Cursor getDataDesarrollo(String NumControlDesarrollo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursoru = db.rawQuery("select * from DepartamentoDeDesarrolloAcademico where NumControlDesarrollo =?", new String[]{NumControlDesarrollo});
        return cursoru;
    }
    public void updateResultado(String resultado, String numcontrol){
        SQLiteDatabase db=this.getWritableDatabase();
        String strSQL = "UPDATE Solicitud SET ResultadoExamen='"+resultado+"'WHERE NumControlUsuario ="+numcontrol;
        db.execSQL(strSQL);
    }
    public void autorizarExamen(Integer autorizacion, String numcontrol){
        SQLiteDatabase db=this.getWritableDatabase();
        String strSQL = "UPDATE Solicitud SET AutorizacionExamen='"+autorizacion+"'WHERE NumControlUsuario ="+numcontrol;
        db.execSQL(strSQL);
    }
    public void convalidacionhecha(Integer autorizacion, String numcontrol){
        SQLiteDatabase db=this.getWritableDatabase();
        String strSQL = "UPDATE Solicitud SET ConvalidacionFinalizada='"+autorizacion+"'WHERE NumControlUsuario ="+numcontrol;
        db.execSQL(strSQL);
    }
    public Cursor getDataSer(String status) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursoru = db.rawQuery("select * from Solicitud where Status =?", new String[]{status});
        return cursoru;
    }
    public Cursor getDataAcademia(String NumControlAcademia){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursoru = db.rawQuery("select * from Academia where NumControlAcademia =?", new String[]{NumControlAcademia});
        return cursoru;
    }
    public void getDataMaterias(String IDCarrera,ArrayList<String> array){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursoru = db.rawQuery("select NombreMateria from Materia where IDCarrera =?", new String[]{IDCarrera});
        while(cursoru.moveToNext()){
            array.add(cursoru.getString(cursoru.getColumnIndex("NombreMateria")));
        }
    }
    public void getMateriasUsuario(String NumControlAcademia,ArrayList<Integer> array){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursoru = db.rawQuery("select IDMateria from Calificacion where IDUsuario = ? and Calificacion >= 70", new String[]{NumControlAcademia});
        while(cursoru.moveToNext()){
            array.add(cursoru.getInt(cursoru.getColumnIndex("IDMateria")));
        }
    }
    public void getMateriasReprobadasUsuario(String NumControlAcademia,ArrayList<Integer> array){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursoru = db.rawQuery("select IDMateria from Calificacion where IDUsuario = ? and Calificacion < 70", new String[]{NumControlAcademia});
        while(cursoru.moveToNext()){
            array.add(cursoru.getInt(cursoru.getColumnIndex("IDMateria")));
        }
    }
}



