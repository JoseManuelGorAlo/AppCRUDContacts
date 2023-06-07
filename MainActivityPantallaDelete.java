package com.example.myapplicationconstructoaltasybajas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivityPantallaDelete extends AppCompatActivity {

    private int id;
    private final int SIMPLE_REQUEST = 1;
    private Button btnDelete, btnExit;
    private RequestQueue requestQueue;
    private EditText txtFiltro;
    private String filtro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pantalla_delete);
        IU();
        requestQueue = Volley.newRequestQueue(this);

    }

    private void IU() {

        btnDelete = findViewById(R.id.btnDelete_delete);
        btnExit = findViewById(R.id.btnExit_Update);
        txtFiltro = findViewById(R.id.txtFiltro_Delete);

    }

    public void usodeletearContacto(View view) {

        id = Integer.parseInt(String.valueOf(txtFiltro.getText()));

        String BASE_URL = "https://api.hubapi.com/crm/v3/objects/contacts/" + id;


        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, BASE_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.d("onResponse", response);
                Log.d("url", BASE_URL);
                Toast.makeText(MainActivityPantallaDelete.this, "Deleted contact", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            // Agregar encabezados personalizados a la solicitud
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "YOUR_TOKEN_HERE");
                headers.put("Content-Type", "application/json");
                return headers;
            }

        };
        stringRequest.setTag(SIMPLE_REQUEST);
        requestQueue.add(stringRequest);
    }

    public void usoBtnExitDelete(View view) {
        Intent intent = new Intent(MainActivityPantallaDelete.this, MainActivityaPantallaEleccion.class);
        startActivity(intent);
    }
}






