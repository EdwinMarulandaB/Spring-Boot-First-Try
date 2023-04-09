package com.example.demo.controllers;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.models.Usuario;
import com.example.demo.utils.JWTutil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController{
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTutil jwTutil;
    @RequestMapping(value = "api/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario user = new Usuario();
        user.setId(id);
        user.setNombre("Edwin");
        user.setPassword("Edwin1413");
        user.setApellido("Marulanda");
        user.setEmail("Edwintrujiyo@hotmail.es");
        user.setTelefono("3226472400");

        return user;
    }


    private boolean validarToken(String token){
        String usuarioId = jwTutil.getKey(token);
        return usuarioId != null;
    };

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token){

        if(!validarToken(token)){
            return null;
        }
        return usuarioDao.getUsuarios();
    }


    @RequestMapping(value = "api/usuarios",method = RequestMethod.POST)
    public void registrarUsusario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/usuario",method = RequestMethod.PUT)
    public void editarUsuario(){

    }


    @RequestMapping(value = "api/usuarios/{id}",method = RequestMethod.DELETE)
    public void eliminarUsuario(@RequestHeader(value="Authorization") String token,@PathVariable Long id){
        if(!validarToken(token)){
            return;
        }
        usuarioDao.eliminar(id);
    }





}
