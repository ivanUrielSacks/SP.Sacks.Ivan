
package Modelo;

public class MisionTripulada extends Mision
{
    private int cantidadAstronautas;
    
    public MisionTripulada(){};
    
    public MisionTripulada(int idMision, String nombre, String destino, double presupuesto,
            int duracionDias, Agencia agencia, int cantidadAstronautas)
    {
        super(idMision, nombre, destino, presupuesto, duracionDias, agencia);
        this.cantidadAstronautas = cantidadAstronautas;
    }

    public int getCantidadAstronautas() 
    {
        return cantidadAstronautas;
    }

    public void setCantidadAstronautas(int cantidadAstronautas) {
        this.cantidadAstronautas = cantidadAstronautas;
    }
    @Override
    public String getTipo()
    {
        return "Tripulada";
    }
    
}
