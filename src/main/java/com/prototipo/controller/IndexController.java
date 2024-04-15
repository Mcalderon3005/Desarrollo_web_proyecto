/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.controller;

import com.prototipo.domain.Categoria;
import com.prototipo.domain.Usuario;
import com.prototipo.domain.Vehiculo;
import com.prototipo.service.CategoriaService;
import com.prototipo.service.CorreoMarketingService;
import com.prototipo.service.FirebaseStorageService;
import com.prototipo.service.UsuarioService;
import com.prototipo.service.VehiculoService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Tayron
 */
@Controller
//@RequestMapping("/")
public class IndexController {

    @Autowired
    private VehiculoService vehiculoService;
//    @Autowired
//    private CategoriaService categoriaService;
//    
    
//    @GetMapping("/")
//    public String listado(Model model, HttpSession session) {
//        var lista = vehiculoService.getVehiculos(false);
//        model.addAttribute("vehiculos", lista);
//        String moneda = (String) session.getAttribute("moneda");
//        model.addAttribute("moneda", moneda); // Agrega la moneda al modelo
//        return "index";
//    }
    
    @GetMapping(value = {"/", "/index"})
    public String home(@RequestParam(name = "moneda", required = false, defaultValue = "dolar") String moneda, Model model) {
        model = vehiculoService.getPrecios(model, moneda);
       
            //session.setAttribute("moneda", moneda);
        
        return "index";
    }
//    
    
@GetMapping("/encuentranos/listado")
    public String encuentranos() {
        return "encuentranos/listado";
    }

    @GetMapping("/promociones/listado")
    public String promociones() {
        return "promociones/listado";
    }

    @GetMapping("/envios/listado")
    public String envios() {
        return "envios/listado";
    }
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CorreoMarketingService correoMarketingService;

    @GetMapping("/informe/listado")
    public String nuevo(Model model) {
        var usuariolista = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuariolista);
        
        return "/informe/listado";
    }

//    @PostMapping("/informe/listado/enviarMensaje")
//    public String enviarMensaje(Usuario usuario, @RequestParam("mensaje") String mensaje) throws MessagingException {
//        usuario = usuarioService.getUsuario(usuario);
//        correoMarketingService.mostrarMensajeLanzamientoVehiculo(usuario, mensaje);
//        return "redirect:/informe/listado";
//    }
    
//    @Autowired
//    private FirebaseStorageService firebaseStorageService;
//    
//    @PostMapping("/informe/listado/enviarMensaje")
//    public String save(Vehiculo vehiculo,
//            @RequestParam("imagenFile") MultipartFile imagenFile) {
//        if (!imagenFile.isEmpty()) { //debo subir la imagen
//            vehiculoService.save(vehiculo);
//            String ruta = firebaseStorageService.cargaPDF(imagenFile, "vehiculo", vehiculo.getIdVehiculo());
//            vehiculo.setRutaImagen(ruta);
//        }
//        vehiculoService.save(vehiculo);
//        return "redirect:/informe/listado";
//    }
//    @GetMapping(value = {"/", "/index"})
//    public String listado(Model model) {
//        var vehiculos = vehiculoService.getVehiculos(false);
//        model.addAttribute("vehiculos", vehiculos);
//        return "index";
//    }
//    
//    @GetMapping(value = {"/{idCategoria}", "/index/{idCategoria}"})
//    public String modifica(Categoria categoria, Model model) {
//        categoria = categoriaService.getCategoria(categoria);
//        model.addAttribute("categoria", categoria);
//        return "index";
//    }

//    @GetMapping("/index/{idCategoria}")
//    public String modifica(Categoria categoria, Model model) {
//        //Se obtiene un objeto categoria
//        categoria = categoriaService.getCategoria(categoria);
//
//        //Fundamental, del @OneToMany
//        var lista = categoria.getVehiculo();
//        model.addAttribute("vehiculos", lista);
//
//        //Para que no se quiten las categorias
//        //Se vuelven a inyectar
//        var categorias = categoriaService.getCategorias(false);
//        model.addAttribute("categorias", categorias);
//
//        return "index";
//    }

//    @GetMapping("/index/activos")
//    public String listadoActivos(Model model) {
//        var vehiculosActivos = vehiculoService.getVehiculos(true); // Obtener vehículos activos
//        model.addAttribute("vehiculos", vehiculosActivos); // Pasar vehículos activos al modelo
//        return "index"; // Devolver la misma vista que la de vehículos inactivos
//    }
//
//    @GetMapping("/index/inactivos")
//    public String listadoInactivos(Model model) {
//        var vehiculosInactivos = vehiculoService.getVehiculos(false); // Obtener vehículos inactivos
//        model.addAttribute("vehiculos", vehiculosInactivos); // Pasar vehículos inactivos al modelo
//        return "index"; // Devolver la misma vista que la de vehículos activos
//    }
    

//@GetMapping("/listado/{idCategoria}")
//    public String modifica(Vehiculo vehiculo, Model model) {
//        //Se obtiene un objeto categoria
//        vehiculo = vehiculoService.getVehiculo(vehiculo);
//        
//        //Fundamental, del @OneToMany
//        var lista = vehiculo.getVehiculo();
//        model.addAttribute("productos", lista);
//        
//        //Para que no se quiten las categorias
//        //Se vuelven a inyectar
//        var categorias = categoriaService.getCategorias(false);
//        model.addAttribute("categorias", categorias);
//        
//        return "/pruebas/listado";
//    }
}
