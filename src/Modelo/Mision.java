
package Modelo;

import java.io.Serializable;

public abstract class Mision implements Serializable
{
    private int idMision;
    private String nombre;
    private String destino;
    private double presupuesto;
    private int duracionDias;
    private Agencia agencia;
    
    public Mision(){}

    public Mision(int idMision, String nombre, String destino, double presupuesto, int duracionDias, Agencia agencia) 
    {
        this.idMision = idMision;
        this.nombre = nombre;
        this.destino = destino;
        this.presupuesto = presupuesto;
        this.duracionDias = duracionDias;
        this.agencia = agencia;
    }

    public int getIdMision() {
        return idMision;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDestino() {
        return destino;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setIdMision(int idMision) {
        this.idMision = idMision;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }
    
    public abstract String getTipo();
    
    @Override
    public String toString()
    {
        return idMision + " - " + nombre + " (" + getTipo() + ")";
    }
    
    
}
