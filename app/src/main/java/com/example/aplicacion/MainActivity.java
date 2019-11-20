package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.DatagramPacket;

public class MainActivity extends AppCompatActivity {
    EditText mTextNumControl;
    EditText mTextPassword;
    Button mLoginButton;
    Button mRegisterButton;
    DatabaseHelper db;
  Integer ch=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mTextNumControl = (EditText) findViewById(R.id.editTextNumControl);

        mTextPassword = (EditText) findViewById(R.id.editPassword);

        mLoginButton = (Button) findViewById(R.id.login);
        mRegisterButton = (Button) findViewById(R.id.register);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){
                String NumControl = String.valueOf(mTextNumControl.getText().toString().trim());
                String pwd = mTextPassword.getText().toString().trim();
                if(isEmpty(mTextNumControl)){
                    Toast.makeText(MainActivity.this,"Introduce num de control",Toast.LENGTH_LONG).show();

                }else {
                     ch = db.getIdForString(NumControl);
                    }

                boolean res = db.checkUser(NumControl,pwd);

                if(isEmpty(mTextPassword)) {
                    Toast.makeText(MainActivity.this, "Introduce contraseña", Toast.LENGTH_LONG).show();
                }


                if(res==true&&ch==1){

                    Intent moving = new Intent(MainActivity.this, InterfazUsuario.class);

                    moving.putExtra("NumControl",NumControl);
                    startActivity(moving);
                    finish();
                }
                if(res==true&&ch==2){

                    Intent moving = new Intent(MainActivity.this, InterfazCoordinador.class);
                    moving.putExtra("NumControlCoordinador",NumControl);
                    startActivity(moving);
                    finish();
                }
                if(res==true&&ch==3){

                    Intent moving = new Intent(MainActivity.this, InterfazServicios.class);

                    moving.putExtra("NumControlServicios",NumControl);

                    startActivity(moving);
                    finish();
                }
                if(res==true&&ch==4){

                    Intent moving = new Intent(MainActivity.this, InterfazAcademia.class);
                    moving.putExtra("NumControlAcademia",NumControl);
                    startActivity(moving);
                    finish();
                }
                if(res==true&&ch==5){

                    Intent moving = new Intent(MainActivity.this, InterfazDesarrollo.class);
                    moving.putExtra("NumControlDesarrollo",NumControl);
                    startActivity(moving);
                    finish();
                }
                if(res==false){
                    Toast.makeText(MainActivity.this,"Num de control o contraseña incorrectos",Toast.LENGTH_LONG).show();
                }


            }

        });
    }
    boolean isEmpty(EditText Text){
        CharSequence str = Text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}
