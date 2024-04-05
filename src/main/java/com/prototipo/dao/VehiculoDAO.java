package com.prototipo.dao;

import com.prototipo.domain.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tayron
 */
public interface VehiculoDAO extends JpaRepository<Vehiculo,Long>{
    
}
