package com.example.rent.ui.usuarios;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rent.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CarritoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<ModelCarrito> dataList = new ArrayList<>();
    CarritoAdapter adapter;
    WebService obj = new WebService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        // Configurar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CarritoAdapter(getApplicationContext(), dataList);
        recyclerView.setAdapter(adapter);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        new ListCarritoAsyncTask().execute();
    }
    class ListCarritoAsyncTask extends android.os.AsyncTask<String, String,Void>{
        @Override
        protected Void doInBackground(String... strings) {
            String msj;
            msj = obj.consultarcarrito();
            publishProgress(msj);
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            try
            {
                //Almacenar la respuesta JSON del servidor en un arreglo e tipo JSON
                JSONArray jArray = new JSONArray(values[0]);
                //POr cada registro del arreglo JSON recuperado procesar...
                JSONObject json_data=null;
                for(int i=0;i<jArray.length();i++)
                {
                    //EL JSON parser crea un OBJETO JSON por cada registro del arreglo
                    json_data = jArray.getJSONObject(i);

                    dataList.add(new ModelCarrito(
                            json_data.getInt("id"),
                            json_data.getString("nombre"),
                            json_data.getString("imagen"),
                            json_data.getString("precio"),
                            json_data.getString("cantidad"),
                            json_data.getString("pago"),
                            json_data.getString("direccion"),
                            json_data.getString("telefono")));

                }

                adapter.notifyDataSetChanged();


            }//Si hay un problema on el JSON parser se captura e error
            catch(JSONException e)
            {   //Se asocia el error a la salida en pantalla
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}