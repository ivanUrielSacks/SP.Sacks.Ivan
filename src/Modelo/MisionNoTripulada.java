
package Modelo;


public class MisionNoTripulada extends Mision
{
    private TipoDispositivo tipoDispositivo;

    public MisionNoTripulada()
    {
    }

    public MisionNoTripulada(int idMision, String nombre, String destino, double presupuesto, 
            int duracionDias, Agencia agencia, TipoDispositivo tipoDispositivo) 
    {
        super(idMision, nombre, destino, presupuesto, duracionDias, agencia);
        this.tipoDispositivo = tipoDispositivo;
    }

    public TipoDispositivo getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(TipoDispositivo tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }
    
    @Override
    public String getTipo()
    {
        return "No tripulada";
    }
    
    
}
