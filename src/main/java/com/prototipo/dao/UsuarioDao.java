/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prototipo.dao;
import com.prototipo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tayron
 */
public interface UsuarioDao 
        extends JpaRepository<Usuario,Long>{

    //Consulta, busca el el username que tiene el username del rol
    public Usuario findByUsername(String username);
    
    public Usuario findByUsernameAndPassword(String username, String Password);

    public Usuario findByUsernameOrCorreo(String username, String correo);

    //Registrar usuarios
    public boolean existsByUsernameOrCorreo(String username, String correo);
}