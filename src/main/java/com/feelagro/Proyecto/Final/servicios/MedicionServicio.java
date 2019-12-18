package com.feelagro.Proyecto.Final.servicios;

import com.feelagro.Proyecto.Final.entidades.Errores.ErrorServicio;
import com.feelagro.Proyecto.Final.entidades.Medicion;
import com.feelagro.Proyecto.Final.entidades.Parametrizacion;
import com.feelagro.Proyecto.Final.entidades.Usuario;
import com.feelagro.Proyecto.Final.repositorios.MedicionRepositorio;

import com.feelagro.Proyecto.Final.repositorios.UsuarioRepositorio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MedicionServicio {

    @Autowired
    MedicionRepositorio medicionRepositorio;

    @Autowired
    NotificacionServicio notificacionServicio;

    private Parametrizacion param;

    @Autowired
    private MailServicio mailServicio;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public boolean ComparacionTemperatura(float parametromin, float temperatura) throws ErrorServicio {
        validar(parametromin);

        return param.getParametromin() <= temperatura;

    }

    @Transactional
    public void AlertaTemperatura(String cuerpo, String titulo, String mail, float parametromin, float temperatura, Usuario usuario) throws ErrorServicio {

        if (ComparacionTemperatura(parametromin, temperatura)) {

            notificacionServicio.enviar("La Temperatura ha descendido por debajo del limite establecido", "ALERTA!! PELIGRO DE HELADAS", usuario.getMail());
        }
    }

    @Transactional
    public boolean ComparacionHumedadSuelo1Menor(float parametromin, float parametromax, float humedadsuelo1) throws ErrorServicio {
        validar(parametromin);

        return param.getParametromin() <= humedadsuelo1;

    }

    @Transactional
    public boolean ComparacionHumedadSuelo1Mayor(float parametromin, float parametromax, float humedadsuelo1) throws ErrorServicio {
        validar(parametromin);

        return param.getParametromax() >= humedadsuelo1;
    }

    @Transactional
    public void AlertaHumedadSuelo1Menor(String cuerpo, String titulo, String mail, float parametromin, float parametromax, float humedadsuelo1, Usuario usuario) throws ErrorServicio {

        if (ComparacionHumedadSuelo1Menor(parametromin, parametromax, humedadsuelo1)) {

            notificacionServicio.enviar("La Humedad del Suelo a 30 cm es menor a los parametros establecidos", "ALERTA!!REVISAR FRECUENCIA DE RIEGO", usuario.getMail());
        }
    }

    @Transactional
    public void AlertaHumedadSuelo1Mayor(String cuerpo, String titulo, String mail, float parametromin, float parametromax, float humedadsuelo1, Usuario usuario) throws ErrorServicio {

        if (ComparacionHumedadSuelo1Mayor(parametromin, parametromax, humedadsuelo1)) {

            notificacionServicio.enviar("La Humedad del Suelo a 30 cm es mayor a los parametros establecidos", "ALERTA!!REVISAR FRECUENCIA DE RIEGO", usuario.getMail());
        }
    }

    @Transactional
    public boolean ComparacionHumedadSuelo2Menor(float parametromin, float parametromax, float humedadsuelo2) throws ErrorServicio {
        validar(parametromin);

        return param.getParametromin() <= humedadsuelo2;

    }

    @Transactional
    public boolean ComparacionHumedadSuelo2Mayor(float parametromin, float parametromax, float humedadsuelo2) throws ErrorServicio {
        validar(parametromin);

        return param.getParametromax() >= humedadsuelo2;

    }

    @Transactional
    public void AlertaHumedadSuelo2Menor(String cuerpo, String titulo, String mail, float parametromin, float parametromax, float humedadsuelo2, Usuario usuario) throws ErrorServicio {

        if (ComparacionHumedadSuelo2Menor(parametromin, parametromax, humedadsuelo2)) {

            notificacionServicio.enviar("La Humedad del Suelo a 60 cm es menor a los parametros establecidos", "ALERTA!!REVISAR FRECUENCIA DE RIEGO", usuario.getMail());
        }
    }

    @Transactional
    public void AlertaHumedadSuelo2Mayor(String cuerpo, String titulo, String mail, float parametromin, float parametromax, float humedadsuelo2, Usuario usuario) throws ErrorServicio {

        if (ComparacionHumedadSuelo2Mayor(parametromin, parametromax, humedadsuelo2)) {

            notificacionServicio.enviar("La Humedad del Suelo a 60 cm es mayor a los parametros establecidos", "ALERTA!!REVISAR FRECUENCIA DE RIEGO", usuario.getMail());
        }
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
        public List<Medicion> buscarMedicionesPorId(Integer dispositivos_id) {

        List<Medicion> mediciones = medicionRepositorio.medir(dispositivos_id);
        return mediciones;
    }
}
