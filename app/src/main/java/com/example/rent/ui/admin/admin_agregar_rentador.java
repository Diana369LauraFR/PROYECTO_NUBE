package com.example.rent.ui.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rent.R;

public class admin_agregar_rentador extends AppCompatActivity implements WebServiceAgregar.InsertarDatosListener {

    private EditText editTextName, editTextApellidoPaterno, editTextApellidoMaterno, editTextCorreo, editTextTelefono, editTextDireccion;
    private Button buttonEnviar;

    private  ImageButton regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_agregar_rentador);

        editTextName = findViewById(R.id.nombre);
        editTextApellidoPaterno = findViewById(R.id.apellido_paterno);
        editTextApellidoMaterno = findViewById(R.id.apellido_materno);
        editTextCorreo = findViewById(R.id.correo);
        editTextTelefono = findViewById(R.id.telefono);
        editTextDireccion = findViewById(R.id.direccion);

        buttonEnviar = findViewById(R.id.agregar);

         regresar = findViewById(R.id.regresar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextName.getText().toString().trim();
                String apellidoPaterno = editTextApellidoPaterno.getText().toString().trim();
                String apellidoMaterno = editTextApellidoMaterno.getText().toString().trim();
                String correo = editTextCorreo.getText().toString().trim();
                String telefono = editTextTelefono.getText().toString().trim();
                String direccion = editTextDireccion.getText().toString().trim();

                // Validar campos
                if (nombre.isEmpty() || apellidoPaterno.isEmpty() || correo.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
                    showToast("Por favor, complete todos los campos.");
                } else if (!correo.endsWith("@eventpart.mx")) {
                    showToast("El correo debe tener la extensión @eventpart.mx.");
                    limpiarCorreo();
                } else {
                    // Realizar la inserción en la base de datos
                    WebServiceAgregar insertarDatosAsyncTask = new WebServiceAgregar();
                    insertarDatosAsyncTask.setListener(admin_agregar_rentador.this);
                    insertarDatosAsyncTask.execute(nombre, apellidoPaterno, apellidoMaterno, correo, telefono, direccion);
                }
            }
        });
    }

    @Override
    public void onInsertarDatosComplete(String result) {
        if (result != null) {
            showToast(result);
            limpiarCampos();
        } else {
            showToast("Error al insertar los datos.");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void limpiarCampos() {
        editTextName.getText().clear();
        editTextApellidoPaterno.getText().clear();
        editTextApellidoMaterno.getText().clear();
        editTextCorreo.getText().clear();
        editTextTelefono.getText().clear();
        editTextDireccion.getText().clear();
    }

    private void limpiarCorreo() {

        editTextCorreo.getText().clear();

    }
}
