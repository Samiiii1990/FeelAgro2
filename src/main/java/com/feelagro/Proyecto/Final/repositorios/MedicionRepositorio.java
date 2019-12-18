
package com.feelagro.Proyecto.Final.repositorios;

import com.feelagro.Proyecto.Final.entidades.Dispositivos;
import com.feelagro.Proyecto.Final.entidades.Medicion;
import com.feelagro.Proyecto.Final.entidades.Parametrizacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicionRepositorio extends JpaRepository<Medicion,Integer>   {
    
         //@Query("SELECT * FROM medicion m INNER JOIN u usuario ON m.dispositivos_id=u.dispositivos_id")
         //public Medicion medir(@Param("param")Integer id);
    
          @Query("SELECT m FROM Medicion m WHERE dispositivos.id=:param")
          public List<Medicion> medir(@Param("param")Integer dispositivos_id);
          

   
}
