
package com.feelagro.Proyecto.Final.repositorios;

import com.feelagro.Proyecto.Final.entidades.Medicion;
import com.feelagro.Proyecto.Final.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer>{
    
       @Query("Select c FROM Usuario c where c.mail=:mail")
    public Usuario buscarPorMail(@Param("mail")String mail);
        
    
}
