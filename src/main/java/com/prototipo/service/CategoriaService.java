package com.prototipo.service;

import com.prototipo.domain.Categoria;
import java.util.List;

/**
 *
 * @author Tayron
 */
public interface CategoriaService {
    public List<Categoria> getCategorias(boolean activos);
    
    //Se define la firma del metodo para recuperar un registro
    //de la tabla categoria considerando el valor de idCategoria
    public Categoria getCategoria(Categoria categoria);
    
    //Se defie la firma del método para Crear o Modificar un registro
    //Si el idCategoria que pasan esta null, se creal el registro
    //SI el idCategoria que para tiene un valor se modifica ese registro
    public void save(Categoria categoria);
    
    //Se defie la firma del método para Eliminar un registro
    //Se elimina el registro que tiene el idCategoria
    public void delete(Categoria categoria);
}
