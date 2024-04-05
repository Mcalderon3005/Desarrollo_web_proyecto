package com.prototipo.service;

import com.prototipo.domain.Vehiculo;
import java.util.List;

/**
 *
 * @author Tayron
 */
public interface VehiculoService {
    public List<Vehiculo> getVehiculos(boolean activos);
    
    public Vehiculo getVehiculo(Vehiculo vehiculo);
    
    public void save(Vehiculo vehiculo);
    
    public void delete(Vehiculo vehiculo);
    
    public double convertirMoneda(double valorOriginal, String moneda);
//    
//    public double getPrecio(Vehiculo vehiculo);
}
