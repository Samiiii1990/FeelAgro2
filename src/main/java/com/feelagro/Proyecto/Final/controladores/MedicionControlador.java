package com.feelagro.Proyecto.Final.controladores;

import com.feelagro.Proyecto.Final.entidades.Errores.ErrorServicio;
import com.feelagro.Proyecto.Final.entidades.Medicion;
import com.feelagro.Proyecto.Final.repositorios.MedicionRepositorio;
import com.feelagro.Proyecto.Final.servicios.MedicionServicio;
import com.feelagro.Proyecto.Final.servicios.UsuarioServicio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
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
public class MedicionControlador {
    
    @Autowired
    private MedicionRepositorio medicionRepositorio;

    @Autowired
    private MedicionServicio ms;

    
    @GetMapping("/listado2")
    public String listado2(Integer dispositivos_id, ModelMap modelo) {
          
              List<Medicion> mediciones;
   
              mediciones = ms.buscarMedicionesPorId(dispositivos_id);
              modelo.put("medicion", mediciones);
        
        return "dispositivos.html";
    }
     @GetMapping("/displayGraf")   
    public String getDataFromDB(Model model) {
        ArrayList<Integer> hum1 = new ArrayList<>();
        ArrayList<Integer> hum2 = new ArrayList<>();
        ArrayList<Integer> hr = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        
        List<Medicion> dataList = medicionRepositorio.findAll();
        
        for (Medicion dataList1 : dataList) {
            hum1.add(dataList1.getHumedadsuelo1()); 
            hum2.add(dataList1.getHumedadsuelo2()); 
            hr.add(dataList1.getHumedadrelativa()); 
            temp.add(dataList1.getTemperatura()); 
        }
        model.addAttribute("hum1", hum1);
        model.addAttribute("hum2", hum2);
        model.addAttribute("hr", hr);
        model.addAttribute("temp", temp);        
        return "graficos";
    }  
}