package com.example.rent.ui.rentador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebService {

    public String consultar()
    {
        //Preparar el texto con el resultado
        String aux = "";
        try
        {
            //Establecer URL a consultar en servidor
            URL url = new URL("http://192.168.2.10/eventpart/Consultar_pos.php");
            //Establecer conexiÃ³n con el webservice
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            //Habilitar envÃ­o de datos mediante POST
            conexion.setRequestMethod("POST");
            //Habilitar salida de datos
            conexion.setDoOutput(true);
            //Abrir Buffer de salida asociado a la conexiÃ³n
            OutputStreamWriter datSal = new OutputStreamWriter (conexion.getOutputStream());
            //Agregar valores  en formato web --> atributo = "valor";
            //Atributo


            //Enviar datos al servidor
            datSal.flush();
            datSal.close();//Cerrar buffer de escritura
            //SI LA CONEXIÃ“N SE ESTABLECE CON Ã‰XITO

            //SI LA CONEXIÓN SE ESTABLECE CON EXITO
            if (conexion.getResponseCode()== HttpURLConnection.HTTP_OK)
            {   //Apertura de buffer de entrada de datos desde el servidor
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                //Leer primer linea de la respuesta del servidor
                String linea = reader.readLine();
                //Mientras existan datos en el buffer de respuesta
                while (linea != null) {
                    aux = aux + linea;//Concatenar datos linea por linea
                    linea = reader.readLine();//leer siguiente linea
                }
                reader.close();//Cerrar buffer de lectura
                if(aux.equals("010"))
                {
                    aux = "No hay coincidencias";
                }


            }//SI NO HAY CONEXIÃƒâ€œN CON EL SERVIDOR...
            else
            {   //Se asocia el error a la salida en pantalla
                aux = "ERROR al procesar servicio: " + conexion.getResponseCode();
            }
            conexion.disconnect();//Se cierra la conexiÃƒÂ³n con el servvidor

        }
        catch(Exception ex)
        {
            aux = "ERROR de SERVIDOR: " + ex.getMessage();
        }
        return aux;
    }
}
