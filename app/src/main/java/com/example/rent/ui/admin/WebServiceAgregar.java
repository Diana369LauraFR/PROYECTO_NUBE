package com.example.rent.ui.admin;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;




public class WebServiceAgregar extends AsyncTask<String, Void, String> {

    private InsertarDatosListener listener;

    public interface InsertarDatosListener {
        void onInsertarDatosComplete(String result);
    }

    public void setListener(InsertarDatosListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String nombre = params[0];
        String apellidoPaterno = params[1];
        String apellidoMaterno = params[2];
        String correo = params[3];
        String telefono = params[4];
        String direccion = params[5];

        try {
            // Establecer la conexión con el servidor PHP
            URL url = new URL("http://192.168.1.197:5555/eventpart/insertar_datos.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Pasar los parámetros al script PHP
            String postData = URLEncoder.encode("nombre", "UTF-8") + "=" + URLEncoder.encode(nombre, "UTF-8");
            postData += "&" + URLEncoder.encode("apellido_paterno", "UTF-8") + "=" + URLEncoder.encode(apellidoPaterno, "UTF-8");
            postData += "&" + URLEncoder.encode("apellido_materno", "UTF-8") + "=" + URLEncoder.encode(apellidoMaterno, "UTF-8");
            postData += "&" + URLEncoder.encode("correo", "UTF-8") + "=" + URLEncoder.encode(correo, "UTF-8");
            postData += "&" + URLEncoder.encode("telefono", "UTF-8") + "=" + URLEncoder.encode(telefono, "UTF-8");
            postData += "&" + URLEncoder.encode("direccion", "UTF-8") + "=" + URLEncoder.encode(direccion, "UTF-8");

            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(postData.getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();

            // Leer la respuesta del servidor
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();

            return response.toString();
        } catch (Exception e) {
            Log.e("WebServiceAgregar", "Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (listener != null) {
            listener.onInsertarDatosComplete(result);
        }
    }
}
