package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Spinner;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextNumControl;
    EditText mTextPassword;
    EditText mTextPassword2;
    EditText mTextSemestre;
    EditText mNombre;
    EditText mApellido1;
    EditText mApellido2;

    Spinner mSpinner;
Integer Semestre=0;
    Integer NumControl=0;
    Button mButtonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        db= new DatabaseHelper(this);

        mNombre=(EditText)findViewById(R.id.editNombre);
        mApellido1=(EditText)findViewById(R.id.editApellido1);
        mApellido2=(EditText)findViewById(R.id.editApellido2);

        mTextNumControl=(EditText)findViewById(R.id.editTextNumControl);
        mTextPassword=(EditText)findViewById(R.id.editPassword);
        mTextPassword2=(EditText)findViewById(R.id.editPassword2);
        mTextSemestre=(EditText)findViewById(R.id.editSemestre);
        mSpinner=(Spinner)findViewById(R.id.spinner);
        mButtonRegister=(Button)findViewById(R.id.register);
         Spinner sp = (Spinner)findViewById(R.id.spinner);

        ArrayList<String> listCa= db.getAllCareers();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinnerlayout,R.id.txt,listCa);
        sp.setAdapter(adapter);


        mButtonRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                boolean digitsOnlynumcontrol = TextUtils.isDigitsOnly(mTextSemestre.getText());
                boolean digitsOnly = TextUtils.isDigitsOnly(mTextSemestre.getText());
                if (digitsOnlynumcontrol==false) {
                    Toast.makeText(RegisterActivity.this, "Introduce numcontrol valido", Toast.LENGTH_LONG).show();
                } else if (isEmpty(mTextNumControl) || mTextNumControl.getText().toString().length() != 8 || Integer.valueOf(mTextNumControl.getText().toString()) >= 20000000) {
                    Toast.makeText(RegisterActivity.this, "Introduce num de control de 8 digitos o que sea valido", Toast.LENGTH_LONG).show();

                } else  if (isEmpty(mTextSemestre)) {
                    Toast.makeText(RegisterActivity.this, "Introduce semestre valido", Toast.LENGTH_LONG).show();
                }else if(digitsOnly==false) {
                    Toast.makeText(RegisterActivity.this, "Introduce semestre valido", Toast.LENGTH_LONG).show();
                }else if(Integer.valueOf(mTextSemestre.getText().toString().trim())<=0){
                    Toast.makeText(RegisterActivity.this, "Introduce semestre valido", Toast.LENGTH_LONG).show();
                } else if(Integer.valueOf(mTextSemestre.getText().toString().trim())>=10){
                    Toast.makeText(RegisterActivity.this, "Introduce semestre valido", Toast.LENGTH_LONG).show();
                }else{
                    Semestre = Integer.valueOf(mTextSemestre.getText().toString().trim());
                    NumControl = Integer.parseInt(mTextNumControl.getText().toString());
                }
                String NombreCarrera = mSpinner.getSelectedItem().toString();
                Integer IDCarrera = db.getIdFromCareer(NombreCarrera);

                String pwd = mTextPassword.getText().toString().trim();
                String pwd2 = mTextPassword2.getText().toString().trim();
                String Nombre = mNombre.getText().toString().trim();
                String Apellido1 = mApellido1.getText().toString().trim();
                String Apellido2 = mApellido2.getText().toString().trim();



                int a=0;



                if(mTextPassword.getText().toString().length()<6){
                    Toast.makeText(RegisterActivity.this,"Contraseña debe ser mayor a 6 digitos",Toast.LENGTH_SHORT).show();
                }else if(pwd.equals(pwd2)){
                    long val2 = db.addUsuario(Nombre,Apellido1,Apellido2);

                    Cursor cursorr = db.getLastUser();
                    while(cursorr.moveToNext()) {
                        a = cursorr.getInt(cursorr.getColumnIndex("seq"));
                    }
                    long val = db.addEstudiante(a,NumControl,1,pwd,IDCarrera,Semestre);
                    if(val>0&&val2>0) {
                        Toast.makeText(RegisterActivity.this,"Registro exitoso",Toast.LENGTH_SHORT).show();

                        Intent moving = new Intent(RegisterActivity.this, MainActivity.class);

                        startActivity(moving);
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this,"Fallo al registrarse",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(RegisterActivity.this, "Ambas contrasenas no son iguales", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    boolean isEmpty(EditText Text){
        CharSequence str = Text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}
