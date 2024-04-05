package com.prototipo.service;

/**
 *
 * @author Tayron
 */
import org.springframework.web.multipart.MultipartFile;

public interface FirebaseStorageService {

    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);

    //El BuketName es el <id_del_proyecto> + ".appspot.com"
    final String BucketName = "concesionaria-6e588.appspot.com";

    //Esta es la ruta básica de este proyecto concesionaria
    final String rutaSuperiorStorage = "concesionaria";

    //Ubicación donde se encuentra el archivo de configuración Json
    final String rutaJsonFile = "firebase";
    
    //El nombre del archivo Json
    final String archivoJsonFile = "concesionaria-6e588-firebase-adminsdk-lp4yt-9970569f64.json";
}
