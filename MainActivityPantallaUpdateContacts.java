package com.example.myapplicationconstructoaltasybajas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivityPantallaUpdateContacts extends AppCompatActivity {

    private final int SIMPLE_REQUEST=1;
    private RequestQueue queue;
    private int id;
    private String name,lastname, email;


    private Button btnComint, btnExit;
    private EditText txtName, txtLastname, txtEmail , txtFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pantalla_update_contacts);
        IU();
    }
    private void IU(){
        btnComint=findViewById(R.id.btnCommit_Update);
        btnExit=findViewById(R.id.btnExit_Update);
        txtName=findViewById(R.id.txtNombre_update);
        txtLastname=findViewById(R.id.txtApellido_UpdateContacts);
        txtEmail=findViewById(R.id.txtEmail_updateContacts);
        txtFiltro=findViewById(R.id.txtfiltro);

    }
    public void usoExitUpdate(View view){
        Intent intent= new Intent(MainActivityPantallaUpdateContacts.this, MainActivityaPantallaEleccion.class);

        startActivity(intent);
    }
    public void usoBtnCommit(View view){

        email= String.valueOf(txtEmail.getText());
        name= String.valueOf(txtName.getText());
        lastname= String.valueOf(txtLastname.getText());
        id=Integer.parseInt(String.valueOf(txtFiltro.getText()));

       class MyTask extends AsyncTask<Void,Void,String>{

              protected String doInBackground(Void... params) {

               String aenviar = "{\r\n    \"id\": \"\",\r\n    \"properties\": {\r\n        \"createdate\": \"\",\r\n        \"email\": \"" + email + "\",\r\n        \"firstname\": \"" + name + "\",\r\n        \"hs_object_id\": \"\",\r\n        \"lastmodifieddate\": \"\",\r\n        \"lastname\":\"" + lastname + "\"\r\n    },\r\n    \"createdAt\": \"\",\r\n    \"updatedAt\": \"\",\r\n    \"archived\": false\r\n}\r\n";


               String result = null;

               try {

                   URL url = new URL("https://api.hubapi.com/crm/v3/objects/contacts/" + id);
                   HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                   connection.setRequestMethod("PATCH");
                   connection.setDoOutput(true);
                   connection.setRequestProperty("Content-Type", "application/json");
                   connection.setRequestProperty("authorization", "YOUR_TOKEN_HERE");
                   OutputStream outputStream = connection.getOutputStream();
                   outputStream.write(aenviar.getBytes());
                   outputStream.flush();
                   outputStream.close();

                   int responseCode = connection.getResponseCode();
                   if (responseCode == HttpURLConnection.HTTP_OK) {
                       Toast.makeText(MainActivityPantallaUpdateContacts.this, "all right", Toast.LENGTH_SHORT).show();
                   } else {
                      Toast.makeText(MainActivityPantallaUpdateContacts.this,"Error on Update", Toast.LENGTH_LONG).show();
                   }
                   connection.disconnect();


               } catch (IOException e) {
                   e.printStackTrace();
               }
               return null;
           }
           protected void onPostExecute(String result) {
               // Actualiza la interfaz de usuario con el resultado
           }

       }

       }


}
