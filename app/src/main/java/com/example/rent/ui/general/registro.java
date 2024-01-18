package com.example.rent.ui.general;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rent.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class registro extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String UPLOAD_URL = "http://192.168.1.197:5555/eventpart/registro.php";

    private EditText nombre, direccion, telefono, apellido_paterno, apellido_materno, correo;

    private RadioButton usuario, rentador;
    private RadioGroup tipo;
    private ImageButton regresar;

    private ImageView iv;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button btnBuscar = findViewById(R.id.btnBuscar);
        Button btnSubir = findViewById(R.id.btnSub);
        nombre = findViewById(R.id.nombre);
        iv = findViewById(R.id.imageView);
        usuario = findViewById(R.id.usuario);
        rentador = findViewById(R.id.rentador);
        tipo = findViewById(R.id.tipo);
        direccion = findViewById(R.id.direccion);
        correo = findViewById(R.id.correo);
        telefono = findViewById(R.id.telefono);
        apellido_paterno = findViewById(R.id.apellido_paterno);
        regresar = findViewById(R.id.regresar);
        apellido_materno = findViewById(R.id.apellido_materno);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }

    private void showFileChooser() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Seleccionar imagen"), PICK_IMAGE_REQUEST);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PICK_IMAGE_REQUEST && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showFileChooser();
        } else {
            Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                iv.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getStringImagen(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void uploadImage() {
        if (bitmap != null) {

            if (nombre.getText().toString().trim().isEmpty() || direccion.getText().toString().trim().isEmpty()
                    || telefono.getText().toString().trim().isEmpty() || apellido_paterno.getText().toString().trim().isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            final ProgressDialog loading = ProgressDialog.show(this, "Subiendo...", "Espere por favor", false, false);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();
                            Toast.makeText(registro.this, response, Toast.LENGTH_LONG).show();
                            Log.d("fallaSQL", Objects.requireNonNull(response));


                            // Limpiar los campos
                            nombre.setText("");
                            direccion.setText("");
                            telefono.setText("");
                            apellido_paterno.setText("");
                            apellido_materno.setText("");
                            correo.setText("");
                            tipo.clearCheck();
                            iv.setImageResource(R.drawable.addn); // Establecer la imagen predeterminada

                            // Restaurar bitmap a null para evitar que se vuelva a cargar
                            bitmap = null;
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    loading.dismiss();
                    Toast.makeText(registro.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("fallaSQL", Objects.requireNonNull(error.getMessage()));
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("foto", getStringImagen(bitmap));
                    params.put("nombre", nombre.getText().toString().trim());
                    int selectedId = tipo.getCheckedRadioButtonId();
                    if (selectedId != -1) {
                        RadioButton radioButton = findViewById(selectedId);
                        String tipoSeleccionado = radioButton.getText().toString();
                        params.put("tipo", tipoSeleccionado);
                    } else {
                        params.put("tipo", "Usuario");
                    }

                    params.put("apellido_paterno", apellido_paterno.getText().toString().trim());
                    params.put("apellido_materno", apellido_materno.getText().toString().trim());
                    params.put("correo", correo.getText().toString().trim());
                    params.put("direccion", direccion.getText().toString().trim());
                    params.put("telefono", telefono.getText().toString().trim());
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(this, "Selecciona una imagen", Toast.LENGTH_SHORT).show();
        }
    }
}
