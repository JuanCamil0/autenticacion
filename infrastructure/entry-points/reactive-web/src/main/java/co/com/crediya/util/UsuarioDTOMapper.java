package co.com.crediya.util;

import co.com.crediya.models.usuario.UsuarioDTO;
import co.com.crediya.model.usuario.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioDTOMapper {

  Usuario toDomain(UsuarioDTO usuarioDto);

  UsuarioDTO toDTO(Usuario usuario);
}
