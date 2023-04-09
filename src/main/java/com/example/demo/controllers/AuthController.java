package com.example.demo.controllers;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.models.Usuario;
import com.example.demo.utils.JWTutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTutil jwTutil;
    @RequestMapping(value = "api/login",method = RequestMethod.POST)
    public String iniciarSesion(@RequestBody Usuario usuario){

        Usuario user = usuarioDao.login(usuario);
        if(user != null){
            String token = jwTutil.create(String.valueOf(user.getId()),user.getEmail());
            return token;
        };
        return "FAIL";
    }
}
