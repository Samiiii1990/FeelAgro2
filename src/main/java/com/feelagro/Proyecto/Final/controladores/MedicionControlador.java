
package com.feelagro.Proyecto.Final.controladores;

import com.feelagro.Proyecto.Final.entidades.Errores.ErrorServicio;
import com.feelagro.Proyecto.Final.entidades.Medicion;
import com.feelagro.Proyecto.Final.servicios.MedicionServicio;
import com.feelagro.Proyecto.Final.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dispositivos")
public class MedicionControlador {
    

    @Autowired
    private MedicionServicio ms;

    
    @GetMapping("/listado2")
    public String listado2(Integer dispositivos_id, ModelMap modelo) {
          
              List<Medicion> mediciones;
   
              mediciones = ms.buscarMedicionesPorId(dispositivos_id);
              modelo.put("medicion", mediciones);
        
        return "dispositivos.html";
    }
    
}
