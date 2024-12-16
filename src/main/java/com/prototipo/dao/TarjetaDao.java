/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prototipo.dao;

import com.prototipo.domain.Tarjeta;
import com.prototipo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Tayron
 */
public interface TarjetaDao extends JpaRepository<Tarjeta, String> {
//    public Tarjeta findByUsuario(Usuario usuario);

    public Tarjeta findByNumeroTarjeta(String numeroTarjeta);

//    public Tarjeta findByUsuario(Long idUsuario);
    @Query(nativeQuery = true, value = "SELECT * FROM tarjeta WHERE id_usuario = :id_usuario")
    public Tarjeta findByIdUsuario(@Param("id_usuario") long idUsuario);
}
