package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    Spinner mSpinner;
Integer Semestre=0;
    Integer NumControl=0;
    Button mButtonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        db= new DatabaseHelper(this);

        mTextNumControl=(EditText)findViewById(R.id.editTextNumControl);
        mTextPassword=(EditText)findViewById(R.id.editPassword);
        mTextPassword2=(EditText)findViewById(R.id.editPassword2);
        mTextSemestre=(EditText)findViewById(R.id.editSemestre);
        mSpinner=(Spinner)findViewById(R.id.spinner);

         Spinner sp = (Spinner)findViewById(R.id.spinner);

        ArrayList<String> listCa= db.getAllCareers();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinnerlayout,R.id.txt,listCa);
        sp.setAdapter(adapter);



        mButtonRegister=(Button)findViewById(R.id.register);


        mButtonRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                boolean digitsOnlynumcontrol = TextUtils.isDigitsOnly(mTextSemestre.getText());
                if (digitsOnlynumcontrol==false) {
                    Toast.makeText(RegisterActivity.this, "Introduce numcontrol valido", Toast.LENGTH_LONG).show();
                } else if (isEmpty(mTextNumControl) || mTextNumControl.getText().toString().length() != 8 || Integer.valueOf(mTextNumControl.getText().toString()) >= 20000000) {
                    Toast.makeText(RegisterActivity.this, "Introduce num de control de 8 digitos o que sea valido", Toast.LENGTH_LONG).show();

                } else {

                    NumControl = Integer.parseInt(mTextNumControl.getText().toString());
                }
                String NombreCarrera = mSpinner.getSelectedItem().toString();
                Integer IDCarrera = db.getIdFromCareer(NombreCarrera);

                String pwd = mTextPassword.getText().toString().trim();
                String pwd2 = mTextPassword2.getText().toString().trim();


                boolean digitsOnly = TextUtils.isDigitsOnly(mTextSemestre.getText());


                if (isEmpty(mTextSemestre)) {
                    Toast.makeText(RegisterActivity.this, "Introduce semestre valido", Toast.LENGTH_LONG).show();
                }else if(digitsOnly==false){
                    Toast.makeText(RegisterActivity.this, "Introduce semestre valido", Toast.LENGTH_LONG).show();
                }else {
                    Semestre = Integer.valueOf(mTextSemestre.getText().toString().trim());
                }

                if(mTextPassword.getText().toString().length()<6){
                    Toast.makeText(RegisterActivity.this,"Contraseña debe ser mayor a 6 digitos",Toast.LENGTH_SHORT).show();
                }

                  if(pwd.equals(pwd2)){
                    long val = db.addUser(NumControl,1,pwd,IDCarrera,Semestre);
                    if(val>0) {
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
