package com.example.rent.ui.usuarios;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.rent.R;

public class detallesProducto extends AppCompatActivity {
    TextView txtTitulo, txtprecio,txtstook,txtdescripcion;
    ImageView imagen1;
    WebService obj = new WebService();

    String urlImagen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto);

        imagen1 = findViewById(R.id.imgProductDetalles);

        txtTitulo = findViewById(R.id.txtNomProductDetalles);
        txtprecio = findViewById(R.id.txtPrecioDetalles);
        txtstook = findViewById(R.id.txtStookDetalles);
        txtdescripcion = findViewById(R.id.txtDescripDetalles);


        String Titulo = getIntent().getStringExtra("nombre");
        String Precio = getIntent().getStringExtra("precio");
        String Stook = getIntent().getStringExtra("stook");
        String Descrip = getIntent().getStringExtra("descripcion");


        Uri imagenUri = Uri.parse(getIntent().getStringExtra("imagen"));
        Glide.with(this)
                .load(imagenUri)
                .into(imagen1);

        txtTitulo.setText(Titulo);
        txtprecio.setText(Precio);
        txtstook.setText(Stook);
        txtdescripcion.setText(Descrip);

        urlImagen = imagenUri.toString();


    }
    public void insertarcarrito(View view)
    {
        if(txtTitulo.getText().toString().isEmpty() || urlImagen.isEmpty()||
                txtprecio.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Datos faltantes", Toast.LENGTH_LONG).show();
        }
        else
        {
            new InsertAsyncTask().execute(txtTitulo.getText().toString(), urlImagen,
                    txtprecio.getText().toString());
        }
    }
    class InsertAsyncTask extends AsyncTask<String, String, Void> {


        @Override
        protected Void doInBackground(String... strings) {
            String msj;
            msj = obj.insertarcarrito(strings[0], strings[1], strings[2]);
            publishProgress(msj);
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Toast.makeText(getApplicationContext(), values[0], Toast.LENGTH_LONG).show();
        }
    }


}