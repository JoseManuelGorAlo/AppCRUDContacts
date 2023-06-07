package com.example.myapplicationconstructoaltasybajas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txtCabecera, txtUsuario, txtpwd;
    private Button btngo, btnExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btngo=findViewById(R.id.buttonGo);
        btnExit=findViewById(R.id.buttonExit);
        identificardorElementos();

    }
    private void identificardorElementos (){

        txtCabecera=findViewById(R.id.txtCabecera);
        txtUsuario=findViewById(R.id.txtUsuario);
        txtpwd=findViewById(R.id.editTextTextPassword);


    }

    public void usoBtnGo(View view) {

        String condicionUsuario= String.valueOf(txtUsuario.getText());
        String condicionPwd= String.valueOf(txtpwd.getText());
        if(condicionUsuario.equals(condicionPwd)){
            Toast.makeText(this, "All good, all correcto", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, MainActivityaPantallaEleccion.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Error user or pwd", Toast.LENGTH_LONG).show();
        }
    }
    public  void usoBtnExit(View view){

        Toast.makeText(this, "Bye!!", Toast.LENGTH_LONG).show();

        finish();
    }
}