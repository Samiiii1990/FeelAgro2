sasasasasasas
package com.feelagro.Proyecto.Final.controladores;

import com.feelagro.Proyecto.Final.entidades.Dispositivos;
import com.feelagro.Proyecto.Final.entidades.Errores.ErrorServicio;
import com.feelagro.Proyecto.Final.entidades.Usuario;
import com.feelagro.Proyecto.Final.repositorios.DispositivoRepositorio;
import com.feelagro.Proyecto.Final.servicios.DispositivoServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dispositivos")
public class DispositivoControlador {
    
    @Autowired
    DispositivoServicio ds;
    
    @Autowired
    DispositivoRepositorio dr;

    
     @GetMapping("/SumarDispositivos")
    public String Sumar(Model model) {

        
        
    return "SumarDispositivos.html";
    }
        


    @PostMapping("/agregar")
    
    public String AgregarDispositivo (Integer id,@RequestParam(required = false) String ubicacion,HttpSession session){
            
        try {
            
            Usuario user=(Usuario) session.getAttribute("User");//ACA AGREGUÉ..!!!
           
            ds.AgregarDispositivo(id, ubicacion,user.getId());
            
       
              
        } catch (ErrorServicio ex) {
            Logger.getLogger(DispositivoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
                return "redirect:/dispositivos/listado";
        
    }
    
    
    
    @GetMapping("/listado")//ACA AGREGUE...!!! //
    public String listado(ModelMap modelo,HttpSession session) {
        
         Usuario user=(Usuario) session.getAttribute("User");
          
        List<Dispositivos> dispositivos;
   
        dispositivos = ds.buscarDispositivos(user.getId());
       
        modelo.put("dispositivos", dispositivos);
        
        return "dispositivos.html";
        
    }
    
    
    @GetMapping("/actualizacion")
    public String actualizacion(@RequestParam(required = false) Integer id, ModelMap modelo) {
        if (id != null) {
            Dispositivos dispositivos = ds.buscarDispositivo(id);
            modelo.put("dispositivos", dispositivos);
        } else {
            modelo.put("dispositivos", new Dispositivos());
        }
        
                return "dispositivos.html";
    }
     @PostMapping("/actualizar")
    public String actualizar(@RequestParam(required = false)  Integer  id, @RequestParam String ubicacion, ModelMap modelo) {

        try {
            ds.actualizarDispositivo(id, ubicacion);
        } catch (Exception ex) {
            return "redirect:/dispositivos/actualizacion?id=" + id + "&error=" + ex.getMessage();
        }

        return "redirect:/dispositivos/listado";
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam Integer id) {
        try {
            ds.eliminarDispositivo(id);
            return "redirect:/dispositivos/listado";
        } catch (Exception ex) {
            return "redirect:/dispositivos/listado?error=No se pudo eliminar el dispositivo.";
        }
    }
    
  
    
}
