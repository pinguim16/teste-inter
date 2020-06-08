package com.provainter.mapper;

import com.provainter.model.Usuario;
import com.provainter.model.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Cesar
 * @see com.provainter.mapper
 * @since 06/06/2020
 */
@Mapper(uses = DigitoMapper.class)
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

    Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);

    List<Usuario> usuarioDTOSToUsuarios (List<UsuarioDTO> usuarioDTOS);

    List<UsuarioDTO> usuariosToUsuarioDTOS(List<Usuario> usuarios);
}
