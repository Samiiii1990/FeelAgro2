
package com.feelagro.Proyecto.Final.repositorios;

import com.feelagro.Proyecto.Final.entidades.Medicion;
import com.feelagro.Proyecto.Final.entidades.Parametrizacion;
import com.feelagro.Proyecto.Final.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametRepositorio extends JpaRepository<Parametrizacion,Integer> {
    
     @Query("SELECT c FROM Parametrizacion c WHERE c.parametromin = :param")
         public Parametrizacion IndicarMin(@Param("param")Float parametromin);
         
     @Query("SELECT c FROM Parametrizacion c WHERE c.parametromax = :param")
         public Parametrizacion IndicarMax(@Param("param")Float parametromax);         
    
}
