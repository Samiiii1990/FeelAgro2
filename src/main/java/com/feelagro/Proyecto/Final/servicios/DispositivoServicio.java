
package com.feelagro.Proyecto.Final.servicios;

import com.feelagro.Proyecto.Final.entidades.Dispositivos;
import com.feelagro.Proyecto.Final.entidades.Errores.ErrorServicio;
import com.feelagro.Proyecto.Final.repositorios.DispositivoRepositorio;
import com.feelagro.Proyecto.Final.repositorios.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispositivoServicio {
    
    @Autowired
    DispositivoRepositorio dispositivoRepositorio;
     
    @PersistenceContext
    private EntityManager em;
    
   @Transactional
       public void AgregarDispositivo(Integer id, String ubicacion,Integer id_usuario) throws ErrorServicio {
        validar(id,ubicacion);

        Dispositivos dispositivos = new Dispositivos();

         dispositivos.setId(id);
         dispositivos.setUbicacion(ubicacion);
         dispositivos.setId_usuario(id_usuario);
         
         dispositivoRepositorio.save(dispositivos);
    
}
       public void validar(Integer id, String ubicacion) throws ErrorServicio {
        if (ubicacion== null) {
            throw new ErrorServicio("El campo nombre no puede estar vacío");
        }

  }
       
       public Dispositivos buscarporubicacion (String ubicacion){
           return dispositivoRepositorio.buscarporubicacion (ubicacion);
       }

     @Transactional
    public void actualizarDispositivo(Integer id, String ubicacion) throws Exception{
        Dispositivos dispositivo = null;
        if(id != null){
           dispositivo= buscarDispositivo(id);
        }
        
        if(dispositivo == null){
            dispositivo = new Dispositivos();
        }
        
        if(ubicacion== null || ubicacion.trim().isEmpty()){
            throw new Exception("La ubicacion no puede ser nula.");
        }
        
        dispositivo.setUbicacion(ubicacion);

        
        em.merge(dispositivo);
    }
        @Transactional
    public void eliminarDispositivo(Integer id) throws Exception{
            Dispositivos dispositivo = buscarDispositivo(id);
            em.remove(dispositivo);
    }
    
    public Dispositivos buscarDispositivo(Integer id){
        return em.find(Dispositivos.class, id);
    }
    
    public List<Dispositivos> buscarDispositivos(String q){
        return em.createQuery("SELECT c FROM Dispositivos c WHERE d.ubicacion LIKE :q OR c.ubicacion LIKE :q").setParameter("q", "%" + q + "%") .getResultList();
    }
    
    public List<Dispositivos> buscarDispositivos(){
        return em.createQuery("SELECT c FROM Dispositivos c").getResultList();
    }
    public List<Dispositivos> buscarDispositivos(Integer id_login){
        
        
        return em.createQuery("SELECT c FROM Dispositivos c where c.id_usuario= :id_login").setParameter("id_login", id_login).getResultList();
        
        //ACÁ AGREGUÉ---!!!!!
    }
    
    
}

