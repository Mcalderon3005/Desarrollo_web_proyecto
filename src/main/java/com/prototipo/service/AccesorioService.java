package com.prototipo.service;

import com.prototipo.domain.Accesorio;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

/**
 *
 * @author Tayron
 */
public interface AccesorioService {
    public List<Accesorio> getAccesorios(boolean activos);
    
    public Accesorio getAccesorio(Accesorio accesorio);
    
    public void save(Accesorio accesorio);
    
    public void delete(Accesorio accesorio);
    
}
