/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.service.impl;

import com.prototipo.domain.Vehiculo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.prototipo.dao.VehiculoDAO;
import com.prototipo.service.VehiculoService;
import java.util.Optional;

/**
 *
 * @author Tayron
 */
@Service
public class VehiculoServiceImpl implements VehiculoService{
    
    @Autowired
    private VehiculoDAO autoDao;

    @Override
    @Transactional(readOnly=true)
    public List<Vehiculo> getVehiculos(boolean activos) {
        var lista=autoDao.findAll();
        if (activos){ //Si se quieren solo las autos activas
            lista.removeIf(c -> !c.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Vehiculo getVehiculo(Vehiculo auto) {
        return autoDao.findById(auto.getIdVehiculo()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Vehiculo auto) {
        autoDao.save(auto);
    }

    @Override
    @Transactional
    public void delete(Vehiculo auto) {
        autoDao.delete(auto);
    }
   

    @Override
    public double convertirMoneda(double valorOriginal, String moneda) {
        double tasaCambio = 0.85; // Obtiene la tasa de cambio actual
        if ("EUR".equals(moneda)) {
            return valorOriginal * tasaCambio;
        } else {
            return valorOriginal; // No es necesario convertir si es la misma moneda
        }
    }
    
//   @Override
//public double getPrecio(Vehiculo auto){
//    Optional<Vehiculo> optionalVehiculo = autoDao.findById(auto.getIdVehiculo()); // Obtener el vehículo por ID
//    
//    // Verificar si el vehículo existe en el Optional
//    if (optionalVehiculo.isPresent()) {
//        Vehiculo vehiculo = optionalVehiculo.get(); // Obtener el vehículo del Optional
//        return vehiculo.getPrecio(); // Devolver el precio del vehículo
//    } else {
//        return 0.0; // O algún otro valor predeterminado si el vehículo no se encuentra
//    }
//}
}
