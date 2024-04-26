package com.prototipo.service;

import com.prototipo.domain.Vehiculo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

/**
 *
 * @author Tayron
 */
public interface VehiculoService {
    public List<Vehiculo> getVehiculos(boolean activos);
    
    public Vehiculo getVehiculo(Vehiculo vehiculo);
    
    public void save(Vehiculo vehiculo);
    
    public void delete(Vehiculo vehiculo);
    
//    public double convertirMoneda(double valorOriginal, String moneda);
    
    //Se define una consulta JPA para colocar la moneda segun la seleccionada
//    public Model getPrecios(Model model,
//           String moneda);
    
//    public Model getPrecios(Model model, String moneda, jakarta.servlet.http.HttpServletRequest request);
    
    public Model getPrecios(Model model, String moneda);
    
    public Model getPreciosIva(Model model, String pais, Vehiculo vehiculo) ;
//    
//    public double getPrecio(Vehiculo vehiculo);
}
