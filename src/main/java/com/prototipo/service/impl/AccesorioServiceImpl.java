/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.service.impl;

import com.prototipo.domain.Accesorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.prototipo.dao.AccesorioDao;
import com.prototipo.service.AccesorioService;


/**
 *
 * @author Tayron
 */
@Service
public class AccesorioServiceImpl implements AccesorioService{
    
    @Autowired
    private AccesorioDao accesorioDao;
    
//    @Autowired
//    private AccesorioService accesorioService;
//    
//    @Autowired
//    private MessageSource messageSource;

    @Override
    @Transactional(readOnly=true)
    public List<Accesorio> getAccesorios(boolean activos) {
        var lista=accesorioDao.findAll();
        if (activos){ //Si se quieren solo las autos activas
            lista.removeIf(c -> !c.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Accesorio getAccesorio(Accesorio accesorio) {
        return accesorioDao.findById(accesorio.getCod_accesorio()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Accesorio auto) {
        accesorioDao.save(auto);
    }

    @Override
    @Transactional
    public void delete(Accesorio auto) {
        accesorioDao.delete(auto);
    }
}


    

