package com.example.rent.ui.usuarios;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.rent.R;

public class detallesCarrito extends AppCompatActivity {

    TextView txtid, txtnombre, txtprecio;
    EditText editcantidad,editpago, editdirec, editphone;
    ImageView imagen1;
    WebService obj = new WebService();

    String urlImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_carrito);

        imagen1 = findViewById(R.id.imgdetcart);

        txtid = findViewById(R.id.txtidDetCart);
        txtnombre = findViewById(R.id.txtNomDetCart);
        txtprecio = findViewById(R.id.txtPrecioDetCart);
        editcantidad = findViewById(R.id.editcantidad);
        editpago = findViewById(R.id.editpago);
        editdirec = findViewById(R.id.editdirec);
        editphone = findViewById(R.id.editphone);

        int Id = getIntent().getIntExtra("item", 0);
        String Nombre = getIntent().getStringExtra("nombre");
        String Precio = getIntent().getStringExtra("precio");
        String Cantidad = getIntent().getStringExtra("cantidad");
        String Pago = getIntent().getStringExtra("pago");
        String Direccion = getIntent().getStringExtra("direccion");
        String Phone = getIntent().getStringExtra("telefono");

        Uri imagenUri = Uri.parse(getIntent().getStringExtra("imagen"));
        Glide.with(this)
                .load(imagenUri)
                .into(imagen1);

        txtid.setText(String.valueOf(Id));
        txtnombre.setText(Nombre);
        txtprecio.setText(Precio);
        editcantidad.setText(Cantidad);
        editpago.setText(Pago);
        editdirec.setText(Direccion);
        editphone.setText(Phone);


        urlImagen = imagenUri.toString();

    }

    public void actualizarcarrito(View view)
    {
        if(txtid.getText().toString().isEmpty() || txtnombre.getText().toString().isEmpty()||
                urlImagen.isEmpty()|| txtprecio.getText().toString().isEmpty()
                || editcantidad.getText().toString().isEmpty() || editpago.getText().toString().isEmpty()
                || editdirec.getText().toString().isEmpty()|| editphone.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Datos faltantes", Toast.LENGTH_LONG).show();
        }
        else
        {
            new UpdateAsyncTask().execute(txtid.getText().toString(), txtnombre.getText().toString(),
                    urlImagen, txtprecio.getText().toString(),editcantidad.getText().toString(),
                    editpago.getText().toString(), editdirec.getText().toString(), editphone.getText().toString());
        }
    }

    class UpdateAsyncTask extends AsyncTask<String, String, Void> {


        @Override
        protected Void doInBackground(String... strings) {
            String msj;
            msj = obj.actualizarcarrito(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5]
                    , strings[6], strings[7]);
            publishProgress(msj);
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Toast.makeText(getApplicationContext(), values[0], Toast.LENGTH_LONG).show();
        }
    }

    public void borrarcarrito(View view)
    {
        if(txtid.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Datos faltantes", Toast.LENGTH_LONG).show();
        }
        else
        {
            new DeleteAsyncTask().execute(txtid.getText().toString());
        }
    }
    class DeleteAsyncTask extends AsyncTask<String, String, Void> {


        @Override
        protected Void doInBackground(String... strings) {
            String msj;
            msj = obj.borrarcarrito(strings[0]);
            publishProgress(msj);
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Toast.makeText(getApplicationContext(), values[0], Toast.LENGTH_LONG).show();
        }
    }


}