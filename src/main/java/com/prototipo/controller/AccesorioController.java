package com.prototipo.controller;

import com.prototipo.domain.Accesorio;
import com.prototipo.service.AccesorioService;
import com.prototipo.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.prototipo.service.AccesorioService;

/**
 *
 * @author Tayron
 */
@Controller
@RequestMapping("/accesorio")
public class AccesorioController {

    @Autowired
    private AccesorioService accesorioService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = accesorioService.getAccesorios(false);
        model.addAttribute("accesorios", lista);
        model.addAttribute("totalAccesorios", lista.size());
        return "/accesorios/listado";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String save(Accesorio accesorio,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) { //debo subir la imagen
            accesorioService.save(accesorio);
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "accesorio", accesorio.getCod_accesorio());
            accesorio.setRutaImagen(ruta);
        }
        accesorioService.save(accesorio);
        return "redirect:/accesorio/listado";
    }

    @GetMapping("/modificar/{cod_accesorio}")
    public String modifica(Accesorio accesorio, Model model) {
        accesorio = accesorioService.getAccesorio(accesorio);
        model.addAttribute("accesorio", accesorio);
        return "/accesorios/modifica";
    }

    @GetMapping("/eliminar/{cod_accesorio}")
    public String elimina(Accesorio accesorio) {
        accesorioService.delete(accesorio);
        return "redirect:/accesorio/listado";
    }
}
