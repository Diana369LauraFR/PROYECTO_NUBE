package com.example.rent.ui.rentador;

import com.android.volley.toolbox.StringRequest;

public class Model {
    Integer id;

    //agregar e ir abajo
    String textoPrincipal, imagen, precio, stock, descripcion, textoIdentificador;

    public Model(Integer id, String imagen, String textoPrincipal, String precio, String stock, String descripcion, String textoIdentificador) {
        this.id = id;
        this.imagen = imagen;
        this.textoPrincipal = textoPrincipal;
        this.textoIdentificador = textoIdentificador;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
    }


//aqui:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTextoPrincipal() {

        return textoPrincipal;
    }

    public void setTextoPrincipal(String textoPrincipal) {this.textoPrincipal = textoPrincipal;
    }


    public String getTextoIdentificador() {
        return textoIdentificador;
    }

    public void settextoIdentificador(String textoIdentificador) {this.textoIdentificador = textoIdentificador;
    }


    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {this.precio = precio;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {this.stock= stock;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String stock) {this.descripcion= descripcion;
    }

//ver myadapter



}
