package co.com.crediya.r2dbc.util;

import co.com.crediya.model.usuario.Usuario;
import co.com.crediya.r2dbc.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioEntityMapper {

  @Mapping(target = "correoElectronico", source = "email")
  Usuario toDomain(UsuarioEntity usuarioEntity);

  @Mapping(target = "idUsuario", ignore = true) //Ignorar PK, no interesa mapearlo al dominio
  @Mapping(target = "idRol", ignore = true)     //Ignorar idRol, no pertenece al alcance de la HU-1
  @Mapping(target = "email", source = "correoElectronico")
  UsuarioEntity toEntity(Usuario usuario);
}
