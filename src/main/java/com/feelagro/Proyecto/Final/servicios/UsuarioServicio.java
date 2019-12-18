
package com.feelagro.Proyecto.Final.servicios;

import com.feelagro.Proyecto.Final.entidades.Errores.ErrorServicio;
import com.feelagro.Proyecto.Final.entidades.Usuario;
import com.feelagro.Proyecto.Final.repositorios.UsuarioRepositorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio implements UserDetailsService {
    
@Autowired
private UsuarioRepositorio usuarioRepositorio;

@Autowired
private MailServicio mailServicio;

  
    @Transactional
    public void registrar(MultipartFile archivo, String nombre, String apellido, String mail, String clave) throws ErrorServicio {



        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);

        String encriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setClave(encriptada);
        usuario.setAlta(new Date());

        System.out.println("llegue hasta usuario servicio =?");

        usuarioRepositorio.save(usuario);

        // notificacionesservicio.enviar("Bienvenidos al tinder de Mascota","Tinder de Mascota",usuario.getMail());
    }

    /**
     *
     * @param archivo
     * @param id
     * @param nombre
     * @param apellido
     * @param mail
     * @param clave
     * @throws ErrorServicio
     */
    @Transactional
    public void modificar(MultipartFile archivo, Integer id, String nombre, String apellido, String mail, String clave) throws ErrorServicio {

        validar(nombre, apellido, mail, clave);
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setMail(mail);
            String encriptada = new BCryptPasswordEncoder().encode(clave);
            usuario.setClave(encriptada);

            

            usuarioRepositorio.save(usuario);
        } else {

            throw new ErrorServicio("No se encontró el usuario solicitado");
        }
    }

    /**
     *
     * @param id
     * @throws ErrorServicio
     */
    @Transactional
    public void deshabilitar(Integer id) throws ErrorServicio {

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setBaja(new Date());
            usuarioRepositorio.save(usuario);
        } else {

            throw new ErrorServicio("No se encontró el usuario solicitado");
        }
    }

    /**
     *
     * @param id
     * @throws ErrorServicio
     */
  @Transactional
    public void habilitar(Integer id) throws ErrorServicio {

        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.setBaja(null);
            usuarioRepositorio.save(usuario);
        } else {

            throw new ErrorServicio("No se encontró el usuario solicitado");
        }
    }

    /**
     *
     * @param nombre
     * @param apellido
     * @param mail
     * @param clave
     * @throws ErrorServicio
     */
    public void validar(String nombre, String apellido, String mail, String clave) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {

            throw new ErrorServicio("El nombre del usuario no puede ser nulo");
        }
        if (apellido == null || apellido.isEmpty()) {

            throw new ErrorServicio("El apellido del usuario no puede ser nulo");
        }
        if (mail == null || mail.isEmpty()) {

            throw new ErrorServicio("El mail del usuario no puede ser nulo");
        }

        if (clave == null || clave.isEmpty() || clave.length() <= 6) {

            throw new ErrorServicio("El clave del usuario no puede ser nula y tiene que tener más de 6 digitos");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String mail) {
        Usuario usuario = usuarioRepositorio.buscarPorMail(mail);
        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("MODULO_FOTOS");
            permisos.add(p1);
            GrantedAuthority p2 = new SimpleGrantedAuthority("MODULO_MASCOTAS");
            permisos.add(p2);
            GrantedAuthority p3 = new SimpleGrantedAuthority("MODULO_VOTOS");
            permisos.add(p3);

            User user = new User(mail, mail, permisos);

            return user;
        } else {
            return null;
        }
    }

    @Transactional
    public boolean validarDatos(String mail, String clave) {
        Usuario respuesta = usuarioRepositorio.buscarPorMail(mail);

        boolean encriptada = new BCryptPasswordEncoder().matches(clave, respuesta.getClave());
            
   
            
             return encriptada;

    
    
}
         @Transactional
        public void EliminarUsuario(Integer id) throws ErrorServicio {


        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            usuario.getId();

            usuarioRepositorio.delete(usuario);
        } else {
            throw new ErrorServicio("No se encontró el id");
        }
    }
}
