/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prototipo.service;

import com.prototipo.domain.Tarjeta;
import com.prototipo.domain.Usuario;


/**
 *
 * @author Tayron
 */
public interface TarjetaService {
    
    public Tarjeta getTarjeta(Tarjeta tarjeta);
    
    public Tarjeta getTarjetaPorUsuarioAutenticado();
    
    public void delete(Tarjeta tarjeta);
    
    public void save(Tarjeta tarjeta);
    
    public void update(Tarjeta tarjeta);
}
