package com.prototipo.controller;

import com.prototipo.domain.Vehiculo;
import com.prototipo.service.CategoriaService;
import com.prototipo.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.prototipo.service.VehiculoService;

/**
 *
 * @author Tayron
 */

@Controller
@RequestMapping("/vehiculo")
public class VehiculoController {
    
    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        //False para mostrar todas, si fuera true solo mostraria las activas
        var lista = vehiculoService.getVehiculos(false);
        model.addAttribute("vehiculos", lista);
        model.addAttribute("totalAutos", lista.size());
        
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "/vehiculo/listado";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String save(Vehiculo vehiculo,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) { //debo subir la imagen
            vehiculoService.save(vehiculo);
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "vehiculo", vehiculo.getIdVehiculo());
            vehiculo.setRutaImagen(ruta);
        }
        vehiculoService.save(vehiculo);
        return "redirect:/vehiculo/listado";
    }

    @GetMapping("/modificar/{idVehiculo}")
    public String modifica(Vehiculo vehiculo, Model model) {
        vehiculo = vehiculoService.getVehiculo(vehiculo);
        model.addAttribute("vehiculo", vehiculo);
        
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "/vehiculo/modifica";
    }

    @GetMapping("/eliminar/{idVehiculo}")
    public String elimina(Vehiculo vehiculo) {
        vehiculoService.delete(vehiculo);
        return "redirect:/vehiculo/listado";
    }
    
//    @GetMapping("/listado")
//    public String cambiarMoneda(double moneda) {
//         vehiculoService.convertirMoneda(moneda, "EUR");
//         return "/vehiculo/listado";
//    }
}
    
    

