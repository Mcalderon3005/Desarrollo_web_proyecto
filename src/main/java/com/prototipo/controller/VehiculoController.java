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
        @RequestParam("imagenFile") MultipartFile imagenFile,
        @RequestParam("pdfFile") MultipartFile pdfFile) {
    if (!imagenFile.isEmpty()) { // Debo subir la imagen
        vehiculoService.save(vehiculo);
        String ruta = firebaseStorageService.cargaImagen(imagenFile, "vehiculo", vehiculo.getIdVehiculo());
        vehiculo.setRutaImagen(ruta);
    }
    if (!pdfFile.isEmpty()) { // Verificar si se seleccionó un archivo PDF
        vehiculoService.save(vehiculo);
        String rutapdf = firebaseStorageService.cargaPDF(pdfFile, "vehiculo", vehiculo.getIdVehiculo());
        vehiculo.setRutaInforme(rutapdf);
    }
    vehiculoService.save(vehiculo);
    return "redirect:/vehiculo/listado";
}

/*
    Método para editar un vehículo que ya existe en la base de datos.
    Se crea este método separado de guardar debido a un problema que causaba
    que se quitaba el null de las ruta informe cuando no se modificaban.
    Esto provocaba que las rutas de informe se mostraran incorrectamente
    incluso si el vehículo no tenía una ruta asociada.
*/
@PostMapping("/editar")
public String editar(Vehiculo vehiculo,
                   @RequestParam("imagenFile") MultipartFile imagenFile,
                   @RequestParam("pdfFile") MultipartFile pdfFile) {
    Vehiculo vehiculoExistente = vehiculoService.getVehiculo(vehiculo);
 
    String rutaInformeActual = vehiculoExistente.getRutaInforme();
    
    if (!imagenFile.isEmpty()) { 
        String ruta = firebaseStorageService.cargaImagen(imagenFile, "vehiculo", vehiculo.getIdVehiculo());
        vehiculo.setRutaImagen(ruta);
    }

    if (!pdfFile.isEmpty()) {
        String rutaInformeNueva = firebaseStorageService.cargaPDF(pdfFile, "vehiculo", vehiculo.getIdVehiculo());
        vehiculo.setRutaInforme(rutaInformeNueva);
    } else {
        // Conservar la ruta de informe actual si no se selecciona un nuevo archivo
        vehiculo.setRutaInforme(rutaInformeActual);
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
//    public String home(@RequestParam(name = "moneda", required = false, defaultValue = "euro") String moneda, Model model) {
//        model = vehiculoService.getPrecios(model, moneda);
//        return "/vehiculo/listado";
//    }

//    @GetMapping("/listado")
//    public String cambiarMoneda(double moneda) {
//         vehiculoService.get(moneda, "EUR");
//         return "/vehiculo/listado";
//    }
}
