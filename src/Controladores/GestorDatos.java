
package Controladores;

import Modelo.Agencia;
import java.util.List;
import java.io.*;
import Modelo.Mision;
import java.util.ArrayList;


public class GestorDatos 
{
    private static final String ARCHIVO_AGENCIAS = "agencias.csv";
    private static final String ARCHIVO_MISIONES = "misiones.bin";
    private static final String ARCHIVO_ALTO_PRESUPUESTO = "alto_presupuesto.text";
    
    
    public List<Agencia> cargarAgencias ()
    {
        List<Agencia> agencias = new ArrayList<Agencia>();
        File archivo = new File(ARCHIVO_AGENCIAS);
        
        if(!archivo.exists())
        {
            System.out.println("No existe" + ARCHIVO_AGENCIAS);
        
            return agencias;
    
        }
    
    try(BufferedReader br = new BufferedReader(new FileReader(archivo)))
    {
        String linea;
        boolean primeraLinea = true;
        while((linea = br.readLine()) != null)
        {
            if(primeraLinea)
            {
                primeraLinea = false;
                continue;
            }
            String[] partes = linea.split(",");
            if(partes.length < 5) continue;
    
            int id = Integer.parseInt(partes[0].trim());
    
            String nombre = partes[1].trim();
            
            String pais = partes[2].trim();
    
            int anioFundacion = Integer.parseInt(partes[3].trim());
    
            
    
            String email = partes[4].trim();
    
            Agencia a = new Agencia(id, nombre, pais, anioFundacion, email);
            
            agencias.add(a);
    
        }
    }
    
    catch(Exception e)
    {
        System.out.println("Error al leer agencias " + e.getMessage());
    }

        return agencias;
    }
    
    
    public List<Mision> cargarMisiones(){File archivo = new File(ARCHIVO_MISIONES);
    
        if(!archivo.exists())
        {
            return new ArrayList<Mision>();
        }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo)))
        {
            return(List<Mision>) ois.readObject();
            
        }
        catch(Exception e)
        {
            System.out.println("Error al leer misiones " + e.getMessage());
        }
        return new ArrayList<Mision>();
    }
    
    public void guardarMisiones(List<Mision> misiones)
    {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_MISIONES)))
        {
            oos.writeObject(misiones);
            
        }
        catch(IOException e)
        {
            System.out.println("Error al guardar misiones " + e.getMessage());
        }
    }
    
    
    public void exportarAltoPresupuesto(List<Mision> misiones)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_ALTO_PRESUPUESTO)))
        {
            for(Mision m : misiones)
            {
                if(m.getPresupuesto() > 100000000)
                {
                    bw.write(m.toString() + " - Presupuesto: " + m.getPresupuesto());
                    bw.newLine();
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }
    
    public String leerAltoPresupuesto()
    {
        StringBuilder sb = new StringBuilder();
        
        File archivo = new File(ARCHIVO_ALTO_PRESUPUESTO);
        
        if(!archivo.exists()) return "No hay misiones de alto presupuesto exportadas";
        
        try(BufferedReader br = new BufferedReader(new FileReader(archivo)))
        {
            String linea;
            while((linea = br.readLine())!= null)
            {
                sb.append(linea).append("\n");
            }
        }
        catch(Exception e)
        {
            return "Error al leer: " + e.getMessage();
        }
        return sb.toString();
    }
}
