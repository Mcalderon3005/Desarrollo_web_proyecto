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
@Table(name="accesorio")
public class Accesorio implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod_accesorio")
    private Long Cod_accesorio;
    private String nombre_accesorio;
    private double precio;
    private int existencias;
    private String rutaImagen;
    private boolean activo;
    
}
