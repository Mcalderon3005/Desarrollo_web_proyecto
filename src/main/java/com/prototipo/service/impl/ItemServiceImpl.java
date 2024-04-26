/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.service.impl;

import com.prototipo.dao.FacturaDao;
import com.prototipo.dao.UsuarioDao;
import com.prototipo.dao.VehiculoDAO;
import com.prototipo.dao.VentaDao;
import com.prototipo.domain.Factura;
import com.prototipo.domain.Item;
import com.prototipo.domain.Usuario;
import com.prototipo.domain.Vehiculo;
import com.prototipo.domain.Venta;
import com.prototipo.service.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tayron
 */
@Service
public class ItemServiceImpl implements ItemService {
    
    @Override
    public List<Item> gets() {
        return listaItems; //Definido en la interface
    }
    
    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (i.getIdVehiculo() == item.getIdVehiculo()) { //El i de la lista y el pasado por parametros
                return i;
            }
        }
        return null;    //Si no lo encuentra
    }
    
    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) {
            posicion++;
            if (i.getIdVehiculo() == item.getIdVehiculo()) { //El i de la lista y el pasado por parametros
                existe = true;
                break;  //Cancela el for
            }
        }
        if (existe) {
            listaItems.remove(posicion);
        }
    }
    
    @Override
    public void save(Item item) {
        var existe = false;
        for (Item i : listaItems) {
            if (i.getIdVehiculo() == item.getIdVehiculo()) { //El i de la lista y el pasado por parametros
                existe = true;
                if (i.getCantidad() < i.getExistencias()) { //Verifica que no sume mas de lo que hay de existencias
                    i.setCantidad(i.getCantidad() + 1); //Se suma la cantidad
                }
                break;  //Cancela el for
            }
        }
        if (!existe) {
            item.setCantidad(1);
            listaItems.add(item); //Se crea si no se encontro
        }
    }
    
    @Override
    public void update(Item item) {
        for (Item i : listaItems) {
            if (i.getIdVehiculo() == item.getIdVehiculo()) { //El i de la lista y el pasado por parametros
                i.setCantidad(item.getCantidad());                
                break;  //Cancela el for
            }
        }
    }
    
    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private VehiculoDAO vehiculoDao;
    
    @Autowired
    private FacturaDao facturaDao;
    
    @Autowired
    private VentaDao ventaDao;
    
    @Override
    public void facturar() {
        //Debemos recuperar el usuario autenticado
        
        String username;
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }
        
        if (username.isBlank()) {
            System.out.println("username en blanco");
            return;
        }
        
        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            System.out.println("usuario no encontrado " + username);
            return;
        }

        //Debemos generar la factura para tener un idFactura
        Factura factura = new Factura(usuario.getIdUsuario());
        factura = facturaDao.save(factura);

        double subtotal = 0;
        double descuento = 0;
        double total = 0;
        double subtotalLote = 0;
        for (Item i : listaItems) {
            System.out.println("Procesando vehiculo: " + i.getMarca());
            Venta venta = new Venta(factura.getIdFactura(),
                    i.getIdVehiculo(),
                    i.getPrecio(),
                    i.getCantidad());
            ventaDao.save(venta);
            Vehiculo vehiculo = vehiculoDao.getReferenceById(i.getIdVehiculo());
            vehiculo.setExistencias(vehiculo.getExistencias() - i.getCantidad());
            vehiculoDao.save(vehiculo);
            subtotalLote = i.getCantidad() * i.getPrecio();
            subtotal += subtotalLote;
            if(i.getCantidad() >= 2) {
                descuento += subtotalLote * 0.05;
            }
        }
        total = subtotal - descuento;
        //Debemos actualizar la factura con la venta total
        factura.setSubtotal(subtotal);
        factura.setDescuento(descuento);
        factura.setTotal(total);
        facturaDao.save(factura);
        listaItems.clear();
    }
    
}
