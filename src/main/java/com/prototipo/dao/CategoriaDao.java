package com.prototipo.dao;

import com.prototipo.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Tayron
 */
public interface CategoriaDao extends JpaRepository<Categoria,Long>{
    
}
