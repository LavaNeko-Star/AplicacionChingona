package com.example.aplicacion;

import android.os.Build;

public class Preguntas {

    public String mPreguntas[]={
            "Si pudieras tener una actividad extra durante las tardes, elegiría un curso de:",
            "Mi asignatura escolar favorita es:",
            "Le tengo admiración a:",
            "Si fuera auxiliar de alguna persona, sería:",
            "Si estoy viendo la televisión, me gustan los programas de:",
            "Laborando en un futuro, me visualizo:",
            "Alguien que podría tener mi admiración y respeto sería alguien que:",
            "Si llego a tener un evento, mis tareas a realizar son:",
            "Si se iniciara un nuevo negocio, mi aportación sería:",
            "Si leo un libro o una revista, trata de:"
            };

    private String mRespuestas[][]={
            {"Finanzas personales","Tecnologia de robots"," Auxilios medicos"," Reciclaje","Piano o guitarra"},
            {"Español, geografía, ciencias sociales","Física y matemáticas","Filosofía o historia "," Biología","Actividades artísticas"},
            {"Grandes empresarios","Fundadores de redes para comunicaciones","Enfermeras y doctores","Los creadores de nuevos medicamentos","Artistas"},
            {"Político","Ingeniero","Maestro","Cientifico","Dibujante"},
            {"Noticias actuales","Avances tecnológicos","Problemáticas de personas","Documentales","Caricaturas"},
            {"Laborando con fórmulas","Laborando con maquinaria","Laborando con personas","Laborando con pequeños organismos","Laborando con pinturas"},
            {"Lider de un equipo","Creador de un producto","Persona que ayuda a otros","Es capaz de estudiar seres vivos","Ha creado obras musicales"},
            {"Dirigir al personal","Revisar el equipo de sonido","Atendiendo invitados","Revisar las instalaciones","Preparar el diseño ambiental"},
            {"Hacer la contabilidad","Supervisar la tecnologia","Saludar a los clientes","Revisar calidad de alimentos","Hacer el diseño ambiental"},
            {"Politica","Tecnologia","Psicologia","Cuerpo humano","Arte"}
    };
    public String getQuestion(int a){
        String question = mPreguntas[a];
        return question;

    }
    public String getRespuestas1(int a){
        String choice = mRespuestas[a][0];
        return choice;
    }
    public String getRespuestas2(int a){
        String choice = mRespuestas[a][1];
        return choice;
    }
    public String getRespuestas3(int a){
        String choice = mRespuestas[a][2];
        return choice;
    }
    public String getRespuestas4(int a){
        String choice = mRespuestas[a][3];
        return choice;
    }
    public String getRespuestas5(int a){
        String choice = mRespuestas[a][4];
        return choice;
    }
}
