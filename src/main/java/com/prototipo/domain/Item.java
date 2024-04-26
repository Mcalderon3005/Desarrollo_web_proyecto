/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Tayron
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends Vehiculo{
    private int cantidad;//Guardar la cantidad de elementos que quiero en particular de ese producto
    
    public Item(Vehiculo v){
        super.setIdVehiculo(v.getIdVehiculo());
        super.setMarca(v.getMarca());
        super.setModelo(v.getModelo());
        super.setCategoria(v.getCategoria());
        super.setAnio(v.getAnio());
        super.setColor(v.getColor());
        super.setPrecio(v.getPrecio());
        super.setExistencias(v.getExistencias());
        super.setRutaImagen(v.getRutaImagen());
        super.setActivo(v.isActivo());
        super.setRutaInforme(v.getRutaInforme());
        this.cantidad = 0;
    }
}