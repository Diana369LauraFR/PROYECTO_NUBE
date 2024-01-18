package com.example.rent.ui.rentador;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.rent.MenuPrincilaActivityRentador;
import com.example.rent.R;
import com.example.rent.ui.general.iniciar_sesion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageButton regresar = findViewById(R.id.regresar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        final ImageView imagen1 = findViewById(R.id.imageView2);
        final TextView txtTitulo = findViewById(R.id.titulo);
        final TextView txtPrecio = findViewById(R.id.precio);
        final TextView txtStock = findViewById(R.id.stock);
        final TextView txtDescripcion = findViewById(R.id.descripcion);
        final TextView txtIdentificador = findViewById(R.id.identificador);



        String Identificador = getIntent().getStringExtra("identificador");
        String Titulo = getIntent().getStringExtra("titulo");
        String Precio = getIntent().getStringExtra("precio");
        String Stock = getIntent().getStringExtra("stock");
        String Descripcion = getIntent().getStringExtra("descripcion");
        Uri imagenUri = Uri.parse(getIntent().getStringExtra("logo"));


        Glide.with(this)
                .load(imagenUri)
                .into(imagen1);
        txtIdentificador.setText(Identificador);
        txtTitulo.setText(Titulo);
        txtPrecio.setText(Precio);
        txtStock.setText(Stock);
        txtDescripcion.setText(Descripcion);


    }




}