package com.example.myapplicationconstructoaltasybajas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivityaPantallaEleccion extends AppCompatActivity {

    private Button btnAddContact, btnDeleteContact, btnUpdateContact, btnShowContact;
    private ImageButton btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitya_pantalla_eleccion);
        IU();

    }
    public void usoBack(View view){
        Intent intent = new Intent(MainActivityaPantallaEleccion.this, MainActivity.class);
        startActivity(intent);
    }

    @SuppressLint("WrongViewCast")
    private void IU(){
        btnAddContact=findViewById(R.id.buttonanadir);
        btnDeleteContact=findViewById(R.id.buttonborrar);
        btnShowContact=findViewById(R.id.buttonMostrar);
        btnUpdateContact=findViewById(R.id.buttonModificar);
        btnBack=findViewById(R.id.imageButtonBack);
    }
    public void usoBtnAddContact(View view){
        Intent intent = new Intent(MainActivityaPantallaEleccion.this, MainActivityPantallaAddContact.class);
        startActivity(intent);

    }
    public void usoBtnDeleteContact(View view){
        Intent intent = new Intent(MainActivityaPantallaEleccion.this, MainActivityPantallaDelete.class);
        startActivity(intent);

    }
    public void usoBtnUpdateContact(View view){
        Intent intent = new Intent(MainActivityaPantallaEleccion.this, MainActivityPantallaUpdateContacts.class);
        startActivity(intent);

    }
   public void usoBtnShowCnt(View view){
       Intent intent = new Intent(MainActivityaPantallaEleccion.this, MainActivityPantallaShowContacts.class);
       startActivity(intent);
   }
}