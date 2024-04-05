package com.prototipo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Tayron
 */
@Data
@Entity
@Table(name="vehiculo")
public class Vehiculo implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_vehiculo")
    private Long idVehiculo;
    private String marca;
    private String modelo;
    private int anio;
    private String color;
    private double precio;
    private int existencias;
    private String rutaImagen;
    private boolean activo;
    
    @ManyToOne 
    @JoinColumn(name="id_categoria")
    private Categoria categoria;
    
}
