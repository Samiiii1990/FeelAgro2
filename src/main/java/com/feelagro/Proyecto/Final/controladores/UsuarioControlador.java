
package com.feelagro.Proyecto.Final.controladores;

import com.feelagro.Proyecto.Final.entidades.Errores.ErrorServicio;
import com.feelagro.Proyecto.Final.entidades.Usuario;
import com.feelagro.Proyecto.Final.repositorios.UsuarioRepositorio;
import com.feelagro.Proyecto.Final.servicios.UsuarioServicio;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class UsuarioControlador extends HttpServlet {

    @Autowired
    private UsuarioServicio us;
    
    @Autowired
    private UsuarioRepositorio ur;

    
    @PostMapping("/crear")
    public String crear(@RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave) throws ErrorServicio {
        Usuario usu = ur.buscarPorMail(mail);
        if (usu!=null){
            return "Index2.html";
        } else {
        us.registrar(null, nombre, apellido, mail, clave);
        return "Index.html";
    }
    }
    
    @GetMapping("/entrar")
    public String entrada(){
        
        return "dispositivos.html";
        
    }

    @PostMapping("/entrar")
    public String entrar(@RequestParam String mail, @RequestParam String clave, Model model, HttpSession session) throws ErrorServicio {

        if (us.validarDatos(mail, clave)) {
            
            Usuario respuesta = ur.buscarPorMail(mail);
            
            session.setAttribute("User", respuesta);
       
            return "redirect:/dispositivos/listado";//ACÁ CAMBIÉ LA REDIRECCION
            
        } else {
           
       
            return "redirect:/index";
        }
    }
    
}