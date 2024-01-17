package com.example.rent.ui.usuarios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WebService {

    public String consultar()
    {
        //Preparar el texto con el resultado
        String aux = "";
        try
        {
            //Establecer URL a consultar en servidor
            URL url = new URL("http://192.168.2.10/eventpart/ws_consultar_rv.php");
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


    public String insertarcarrito(String nombre, String imagen, String precio)
    {
        //Preparar el texto con el resultado
        String aux = "";
        try
        {
            //Establecer URL a consultar en servidor
            URL url = new URL("http://192.168.2.10/eventpart/ws_insertar_carrito.php");
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

            //Verificar si el signo "?" es necesario como primer atributo
            String data = "nombre=" + URLEncoder.encode(nombre,"UTF-8");
            //monitor.append("Valor en buffer: "+data+"\n");
            //salida.append("dato enviado: " + data);
            datSal.write(data);

            data="&imagen="+ URLEncoder.encode(imagen,"UTF-8");
            datSal.write(data);

            data="&precio="+ URLEncoder.encode(precio,"UTF-8");
            datSal.write(data);


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
                if(aux.equals("2002"))
                {
                    aux = "ERROR DE CONEXION AL SERVIDOR DE DATOS";
                }
                else if(aux.equals("001"))
                {
                    aux = "Datos faltantes";
                }
                else if(aux.equals("000"))
                {
                    aux = "No se pudo registrar";
                }
                else if(aux.equals("1062"))
                {
                    aux = "ID duplicado";
                }
                else if(aux.equals("002"))
                {
                    aux = "Añadido al carrito";
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

    public String consultarcarrito()
    {
        //Preparar el texto con el resultado
        String aux = "";
        try
        {
            //Establecer URL a consultar en servidor
            URL url = new URL("http://192.168.2.10/eventpart//ws_consultar_carrito.php");
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


    public String actualizarcarrito(String id, String nombre, String imagen, String precio,
                                    String cantidad, String pago, String direccion, String telefono)
    {
        //Preparar el texto con el resultado
        String aux = "";
        try
        {
            //Establecer URL a consultar en servidor
            URL url = new URL("http://192.168.2.10/eventpart/ws_actualizar_carrito.php");
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

            //Verificar si el signo "?" es necesario como primer atributo
            String data = "id=" + URLEncoder.encode(id,"UTF-8");
            //monitor.append("Valor en buffer: "+data+"\n");
            //salida.append("dato enviado: " + data);
            datSal.write(data);

            data="&nombre="+ URLEncoder.encode(nombre,"UTF-8");
            datSal.write(data);

            data="&imagen="+ URLEncoder.encode(imagen,"UTF-8");
            datSal.write(data);

            data="&precio="+ URLEncoder.encode(precio,"UTF-8");
            datSal.write(data);

            data="&cantidad="+ URLEncoder.encode(cantidad,"UTF-8");
            datSal.write(data);

            data="&pago="+ URLEncoder.encode(pago,"UTF-8");
            datSal.write(data);

            data="&direccion="+ URLEncoder.encode(direccion,"UTF-8");
            datSal.write(data);

            data="&telefono="+ URLEncoder.encode(telefono,"UTF-8");
            datSal.write(data);


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
                if(aux.equals("2002"))
                {
                    aux = "ERROR DE CONEXION AL SERVIDOR DE DATOS";
                }
                else if(aux.equals("001"))
                {
                    aux = "Datos faltantes";
                }
                else if(aux.equals("000"))
                {
                    aux = "No existe ID";
                }
                else if(aux.equals("002"))
                {
                    aux = "Pedido realizado";
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

    public String borrarcarrito(String id)
    {
        //Preparar el texto con el resultado
        String aux = "";
        try
        {
            //Establecer URL a consultar en servidor
            URL url = new URL("http://192.168.2.10/eventpart/ws_borrar_carrito.php");
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

            //Verificar si el signo "?" es necesario como primer atributo
            String data = "id=" + URLEncoder.encode(id,"UTF-8");
            //monitor.append("Valor en buffer: "+data+"\n");
            //salida.append("dato enviado: " + data);
            datSal.write(data);
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
                if(aux.equals("2002"))
                {
                    aux = "ERROR DE CONEXION AL SERVIDOR DE DATOS";
                }
                else if(aux.equals("001"))
                {
                    aux = "Datos faltantes";
                }
                else if(aux.equals("000"))
                {
                    aux = "No se pudo borrar";
                }
                else if(aux.equals("002"))
                {
                    aux = "Dato borrado";
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
