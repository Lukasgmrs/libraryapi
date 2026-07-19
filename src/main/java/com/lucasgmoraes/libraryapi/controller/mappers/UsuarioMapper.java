package com.lucasgmoraes.libraryapi.controller.mappers;

import com.lucasgmoraes.libraryapi.controller.dto.UsuarioDTO;
import com.lucasgmoraes.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

}
