package com.example.myapplicationconstructoaltasybajas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivityPantallaShowContacts extends AppCompatActivity {

    private TextView txtRespuesta;
    RequestQueue requestQueue;
    String response;
    private static final String BASE_URL="https://api.hubapi.com/crm/v3/objects/contacts/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pantalla_show_contacts);
        requestQueue= Volley.newRequestQueue(this);
        IU();
        jsonObjectRequest();
    }
    private void IU(){
        txtRespuesta=findViewById(R.id.txtRespuesta);
    }
    private void jsonObjectRequest(){

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(
                Request.Method.GET,
                BASE_URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray= response.getJSONArray("results");
                            int size= jsonArray.length();
                            for (int i =0;i<size;i++){
                                JSONObject jsonObject= new JSONObject(jsonArray.get(i).toString());
                                Log.d("Variable", response.toString());
                                String idRespuesta= jsonObject.getString("id");
                                JSONObject respuesta= jsonObject.getJSONObject("properties");
                                String respuesta1= respuesta.getString("firstname");
                                String respuesta2= respuesta.getString("lastname");
                                String respuesta3=respuesta.getString("email");
                                txtRespuesta.append("-------------Contacto "+idRespuesta+"--------------"+
                                        "\n"+respuesta1+
                                        "\n"+respuesta2+"\n"
                                        +respuesta3+"\n"
                                        +"----------------------------------------------------"+"\n");
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "YOUR_TOKEN_HERE");
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);

    }
    public void usoBackShow(View view){
        Intent intent = new Intent(MainActivityPantallaShowContacts.this, MainActivityaPantallaEleccion.class);
        startActivity(intent);

    }



}