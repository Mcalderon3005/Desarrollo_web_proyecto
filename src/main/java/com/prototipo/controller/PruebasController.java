/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.controller;

import com.prototipo.domain.Accesorio;
import com.prototipo.domain.Categoria;
import com.prototipo.domain.Vehiculo;
import com.prototipo.service.AccesorioService;
import com.prototipo.service.CategoriaService;
import com.prototipo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Tayron
 */
@Controller
@RequestMapping("/pruebas")
public class PruebasController {

    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = vehiculoService.getVehiculos(false);
        //Inyectado la lista controller
        model.addAttribute("vehiculos", lista);

        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "/pruebas/listado";
    }

    @GetMapping("/listado/{idCategoria}")
    public String modifica(Categoria categoria, Model model) {
        //Se obtiene un objeto categoria
        categoria = categoriaService.getCategoria(categoria);
        //Fundamental, del @OneToMany
        var lista = categoria.getVehiculo();
        model.addAttribute("vehiculos", lista);
        //Para que no se quiten las categorias
        //Se vuelven a inyectar
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);

        return "/pruebas/listado";
    }

    @GetMapping("/detalles/{idVehiculo}")
    public String detalles(Model model, Vehiculo vehiculo) {
        vehiculo = vehiculoService.getVehiculo(vehiculo);
        //Inyectado la lista controller
        model.addAttribute("vehiculo", vehiculo);

//        var categorias = categoriaService.getCategorias(false);
//        model.addAttribute("categorias", categorias);
        return "/pruebas/detalle";
    }

    @PostMapping("/detalles/calcular")
    public String calcular(@ModelAttribute("pais") String pais, Model model, Vehiculo vehiculo) {
        model = vehiculoService.getPreciosIva(model, pais, vehiculo);

        return "redirect:/detalles";
    }

    @GetMapping("/cotizar/{idVehiculo}")
    //ModelAndView porque solo se va a refrescar un pedazo
    public ModelAndView cotizar(@ModelAttribute("pais") String pais, Model model, Vehiculo vehiculo) {
//        vehiculo = vehiculoService.getVehiculo(vehiculo);
//        double precio = vehiculo.getPrecio();
//        model.addAttribute("precio", precio);
        model = vehiculoService.getPreciosIva(model, pais, vehiculo);
        return new ModelAndView("/pruebas/fragmentos :: verResultado");
    }

    @Autowired
    AccesorioService accesorioServcie;

    @GetMapping("/listado/accesorio")
    public String listadoAcc(Model model) {
        var lista = accesorioServcie.getAccesorios(false);
        model.addAttribute("accesorios", lista);

        return "/pruebas/listadoacc";
    }

    @GetMapping("/detalles/accesorio/{Cod_accesorio}")
    public String detallesAcc(Model model, Accesorio accesorio) {
        accesorio = accesorioServcie.getAccesorio(accesorio);
        model.addAttribute("accesorio", accesorio);
        
        return "/pruebas/detalleacc";
    }

//    @GetMapping("/listado/{idVehiculo}")
//    public String someMethod(Vehiculo vehiculo,Model model) {
//        Locale locale = LocaleContextHolder.getLocale();
//        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
//
//        var lista = vehiculoService.getVehiculos(false);
//        model.addAttribute("vehiculos", lista);
//
//        var categorias = categoriaService.getCategorias(false);
//        model.addAttribute("categorias", categorias);
//        
//        var precio = vehiculoService.getPrecio(vehiculo);
//        
//        String precioDolar = messageSource.getMessage("precio.dolar", new Object[]{currencyFormat.format(precio)}, locale);
//        String precioEuro = messageSource.getMessage("precio.euro", new Object[]{currencyFormat.format(precio)}, locale);
//
//        model.addAttribute("precioDolar", precioDolar);
//        model.addAttribute("precioEuro", precioEuro);
//
//        return "/pruebas/listado";
//    }
}
