/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prototipo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author Tayron
 */
@Data
@Entity
@Table(name = "tarjeta")
public class Tarjeta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "numero_tarjeta")
    private String numeroTarjeta;
    private String titular;
    @Column(name = "f_vencimiento")
    private String fechaVencimiento;
    private int cvv;
    
    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false, unique = true) // Clave foránea a Usuario
    private Usuario usuario;
//    
//    id_usuario INT NOT NULL,
//CONSTRAINT FK_tarjeta_usuario FOREIGN KEY (id_usuario)
//REFERENCES concesionaria.usuario(id_usuario)
    
}
