package com.example.rent.ui.rentador;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
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

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private List<Model> dataList = new ArrayList<>();
    MyAdapter adapter;
    WebService obj = new WebService();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       ImageButton regresar = findViewById(R.id.regresar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        recyclerView = findViewById(R.id.rvDatos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        new AsyncTask().execute();
    }

    class AsyncTask extends android.os.AsyncTask<String, String,Void>{
        @Override
        protected Void doInBackground(String... strings) {
            String msj;
            msj = obj.consultar();
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

                    dataList.add(new Model(json_data.getInt("id"), json_data.getString("foto"),
                            json_data.getString("nombre_articulo"),

                            "Precio: $"+json_data.getString("precio"),
                          "Disponibles hoy: "+  json_data.getString("stock"), json_data.getString("descripcion") ,
                            json_data.getString("id")
                            //ver Model para agregar mas
                    ));



                }
                adapter = new MyAdapter(getApplicationContext(), dataList);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();


            }//Si hay un problema on el JSON parser se captura e error
            catch(JSONException e)
            {   //Se asocia el error a la salida en pantalla
                Toast.makeText(getApplicationContext(), values[0], Toast.LENGTH_LONG).show();
            }
        }
    }






}