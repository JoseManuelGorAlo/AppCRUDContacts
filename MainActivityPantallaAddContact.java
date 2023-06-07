package com.example.myapplicationconstructoaltasybajas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivityPantallaAddContact extends AppCompatActivity {

    private TextView txtNombre, txtApellido, txtemail;
    private final int SIMPLE_REQUEST=1;
    private static final String BASE_URL="https://api.hubapi.com/crm/v3/objects/contacts/";
    private String enviable;
    private RequestQueue queue;

    private String nombre, apellido, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pantalla_add_contact);
        IU();
        queue = Volley.newRequestQueue(this);
        nombre= String.valueOf(txtNombre.getText());
        apellido= String.valueOf(txtApellido.getText());
        email=String.valueOf(txtemail.getText());
    }
    private void IU(){
        txtNombre=findViewById(R.id.txtNombre_addContact);
        txtApellido=findViewById(R.id.txtApellido_addContact);
        txtemail=findViewById(R.id.txtEmail_addContact);
    }
    public void usoExitAddContact(View v){
        Intent intent = new Intent(MainActivityPantallaAddContact.this, MainActivityaPantallaEleccion.class);
        startActivity(intent);
    }
    public void usoAddAddContact(View v) throws JSONException {

        if(txtNombre.equals("")||txtApellido.equals("")||txtemail.equals("")){

            Toast.makeText(this, "There is a required null field", Toast.LENGTH_LONG).show();

        }else{

           JSONObject json= crearStringenviable(txtNombre.getText().toString(),txtApellido.getText().toString(),txtemail.getText().toString());
           Log.d("crearJson",json.toString());
           methodPost(json);
        }

    }
    private  JSONObject crearStringenviable(String nombre, String apellido, String email) throws JSONException {



        String aenviar="{\r\n    \"id\": \"\",\r\n    \"properties\": {\r\n        \"createdate\": \"\",\r\n        \"email\": \""+email+"\",\r\n        \"firstname\": \""+nombre+"\",\r\n        \"hs_object_id\": \"\",\r\n        \"lastmodifieddate\": \"\",\r\n        \"lastname\": "+apellido+"\"\r\n    },\r\n    \"createdAt\": \"\",\r\n    \"updatedAt\": \"\",\r\n    \"archived\": false\r\n}\r\n";
        JSONObject aenviarjson=new JSONObject(aenviar);

        return aenviarjson;
    }

    private void methodPost(JSONObject json) throws JSONException {

            JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST,
                    BASE_URL, json ,  new Response.Listener<JSONObject>() {


                @Override
                public void onResponse(JSONObject response) {

                    Log.d("onResponse", response.toString());

                    Toast.makeText(MainActivityPantallaAddContact.this, "Contact Add"+response.toString(), Toast.LENGTH_LONG).show();

                }
            },
                    new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("onErrorResponse", error.getMessage());
                            Toast.makeText(MainActivityPantallaAddContact.this, "Contact Error", Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                // Agregar encabezados personalizados a la solicitud
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer YOUR_TOKEN_HERE");
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };



            jsonObjectRequest.setTag(SIMPLE_REQUEST);
            queue.add(jsonObjectRequest);



    }


}