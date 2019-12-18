package com.feelagro.Proyecto.Final.servicios;

import com.feelagro.Proyecto.Final.entidades.Errores.ErrorServicio;
import com.feelagro.Proyecto.Final.entidades.Parametrizacion;
import com.feelagro.Proyecto.Final.repositorios.ParametRepositorio;
import com.feelagro.Proyecto.Final.repositorios.UsuarioRepositorio;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParametServicio {

    @Autowired
    ParametRepositorio parametRepositorio;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void ParametrosTemperatura(float parametromin) throws ErrorServicio {
        validar(parametromin);

        Parametrizacion param = new Parametrizacion();
        param.setParametromin(parametromin);

        parametRepositorio.save(param);
    }

    @Transactional
    public void ParametrosHumedadSuelo1(float parametromin, float parametromax) throws ErrorServicio {
        validar2(parametromin, parametromax);
      
        Parametrizacion param = new Parametrizacion();

        param.setParametromax(parametromax);
        param.setParametromin(parametromin);

        parametRepositorio.save(param);

    }
        @Transactional
    public void ParametrosHumedadSuelo2(float parametromin, float parametromax) throws ErrorServicio {
       validar2(parametromin, parametromax);
        
       Parametrizacion param = new Parametrizacion();

        param.setParametromax(parametromax);
        param.setParametromin(parametromin);

        parametRepositorio.save(param);

    }

    public void validar(float parametromin) throws ErrorServicio {
        if (parametromin == 0) {
            throw new ErrorServicio("El campo no puede ser cero");
        }
    }

    public void validar2(float parametromin, float parametromax) throws ErrorServicio {
        if (parametromin == 0) {
            throw new ErrorServicio("El campo no puede ser cero");
        }
    }
}
