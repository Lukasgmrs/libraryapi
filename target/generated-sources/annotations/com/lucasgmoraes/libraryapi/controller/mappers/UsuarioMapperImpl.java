package com.lucasgmoraes.libraryapi.controller.mappers;

import com.lucasgmoraes.libraryapi.controller.dto.UsuarioDTO;
import com.lucasgmoraes.libraryapi.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-10T22:12:31-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.11 (Azul Systems, Inc.)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(UsuarioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setLogin( dto.login() );
        usuario.setSenha( dto.senha() );
        usuario.setEmail( dto.email() );
        List<String> list = dto.roles();
        if ( list != null ) {
            usuario.setRoles( new ArrayList<String>( list ) );
        }

        return usuario;
    }
}
