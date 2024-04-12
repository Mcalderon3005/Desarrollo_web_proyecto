/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.service.impl;
import com.prototipo.dao.UsuarioDao;
import com.prototipo.domain.Rol;
import com.prototipo.domain.Usuario;
import com.prototipo.service.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tayron
 */
@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements
        UsuarioDetailsService,
        UserDetailsService{

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private HttpSession session;
    
    //Busqueda del usuario y carga de los roles
    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        
        //Se busca el registro con el username...
        Usuario usuario = usuarioDao.findByUsername(username);
        
        //se valida si se encontró
        if(usuario == null){
            //No lo encontró
            throw new UsernameNotFoundException(username);
        }
        
        //Guardamos la imagen del usuario en una variable de session
        session.removeAttribute("usuarioImagen"); //Se remueve primero
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());
        
        //Si estamos acá... se encontro un usuario con el username
        //cargamos los roles para que sean Roles del sistema
        var roles = new ArrayList<GrantedAuthority>();
        
        //Se itera sobre los roles del usuario
        for(Rol r : usuario.getRoles()){ //Se cargan los roles
            roles.add(new SimpleGrantedAuthority(r.getNombre())); //getNombre serian USER, ADMIN, VENDEDOR
        }
        
        //Se retorna un usuario con la info necesaria
        
        return new User(usuario.getUsername(),
                usuario.getPassword(),
                roles); // roles de la lista
    }
    
}
