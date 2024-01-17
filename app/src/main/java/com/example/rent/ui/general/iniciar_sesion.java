package com.example.rent.ui.general;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rent.MenuPrincilaActivity;
import com.example.rent.MenuPrincilaActivityRentador;
import com.example.rent.MenuPrincilaActivityUser;
import com.example.rent.R;

import org.json.JSONException;
import org.json.JSONObject;

public class iniciar_sesion extends AppCompatActivity {

    private EditText editTextCorreo;
    private EditText editTextPass;
    private Button buttonIngresar;
    private TextView registrarme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);


        registrarme = findViewById(R.id.registrarme);


        registrarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });



        editTextCorreo = findViewById(R.id.correo);
        editTextPass = findViewById(R.id.pass);
        buttonIngresar = findViewById(R.id.ingresar);

        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = editTextCorreo.getText().toString().trim();
                String pass = editTextPass.getText().toString().trim();

                if (!correo.isEmpty() && !pass.isEmpty()) {
                    // Ejecutar la tarea asíncrona para iniciar sesión
                    new LoginTask().execute(correo, pass);
                } else {
                    Toast.makeText(iniciar_sesion.this, "Ingrese correo y contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buttonIngresar = findViewById(R.id.ingresar);

        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = editTextCorreo.getText().toString().trim();
                String pass = editTextPass.getText().toString().trim();

                if (!correo.isEmpty() && !pass.isEmpty()) {
                    // Ejecutar la tarea asíncrona para iniciar sesión
                    new LoginTask().execute(correo, pass);
                } else {
                    Toast.makeText(iniciar_sesion.this, "Ingrese correo y contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });












    }




    private void registrar(){
        Intent intent1 = new Intent(this, registro.class);
        startActivity(intent1);
    }



    private class LoginTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            // Obtener los parámetros
            String correo = params[0];
            String pass = params[1];

            // Realizar la solicitud HTTP para consultar la base de datos
            // Asegúrate de reemplazar "URL_DEL_PHP" con la URL correcta de tu script PHP
            String url = "http://192.168.2.10/eventpart/validar_usuario.php?correo="+correo+"&pass="+pass;

            //http://192.168.1.66/eventpart/validar_usuario.php?correo=diana@gmail.com&pass=1234
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Procesar la respuesta del servidor
                            try {
                                if (response.getBoolean("success")) {
                                    // Inicio de sesión exitoso
                                    String cargo = response.getString("cargo");
                                    if (cargo.equals("Administrador")) {
                                        // Redirigir al usuario a la actividad del Administrador
                                        Intent intent = new Intent(iniciar_sesion.this, MenuPrincilaActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (cargo.equals("Rentador")) {
                                        // Redirigir al usuario a la actividad del Rentador
                                        Intent intent = new Intent(iniciar_sesion.this, MenuPrincilaActivityRentador.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (cargo.equals("Usuario")) {
                                        // Redirigir al usuario a la actividad del Usuario
                                        Intent intent = new Intent(iniciar_sesion.this, MenuPrincilaActivityUser.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    // Credenciales inválidas
                                    Toast.makeText(iniciar_sesion.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Error en la solicitud HTTP
                            Toast.makeText(iniciar_sesion.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
                        }
                    });

            // Agregar la solicitud a la cola de solicitudes de Volley
            Volley.newRequestQueue(iniciar_sesion.this).add(request);

            return null;
        }
    }
}
