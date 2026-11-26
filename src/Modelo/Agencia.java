
package Modelo;

import java.io.Serializable;


public class Agencia implements Serializable
{
    private int id;
    private String nombre;
    private String pais;

    private int anioFundacion;
    private String email;
    
    public Agencia(){}

    public Agencia(int id, String nombre, String pais, int anioFundacion, String email) 
    {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.anioFundacion = anioFundacion;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }


    public int getanioFundacion() {
        return anioFundacion;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public void setanioFundacion(int anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() 
    {
        return  nombre + "( " + email + ")";
    }
    
    
    
}
