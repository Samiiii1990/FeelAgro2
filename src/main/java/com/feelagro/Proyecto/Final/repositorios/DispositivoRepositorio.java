
package com.feelagro.Proyecto.Final.repositorios;

import com.feelagro.Proyecto.Final.entidades.Dispositivos;
import com.feelagro.Proyecto.Final.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepositorio extends JpaRepository<Dispositivos,Integer> {
    
        // @Query("SELECT c FROM Dispositvos c WHERE c.id = :id")
         //public<List>Dispositivos buscarDispostivosPorId(@Param("id")Integer id);
    
    @Query("SELECT c FROM Dispositivos c WHERE c.ubicacion = :ubicacion")
    public Dispositivos buscarporubicacion(@Param("ubicacion")String ubicacion);

}

