package com.prototipo.dao;

import com.prototipo.domain.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tayron
 */
public interface VehiculoDAO extends JpaRepository<Vehiculo,Long>{
    
    //Se define una consulta JPA para colocar la moneda segun la seleccionada
//    public double findByPrecio(
//            Vehiculo vehiculo, String moneda);
//    
}
