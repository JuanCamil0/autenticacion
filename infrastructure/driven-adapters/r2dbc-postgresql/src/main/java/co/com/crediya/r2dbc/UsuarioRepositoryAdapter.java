package co.com.crediya.r2dbc;


import co.com.crediya.model.usuario.Usuario;
import co.com.crediya.model.usuario.gateways.UsuarioRepository;
import co.com.crediya.r2dbc.util.UsuarioEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UsuarioRepositoryAdapter implements UsuarioRepository {

  private final UsuarioR2DBCRepository usuarioR2DBCRepository;
  private final UsuarioEntityMapper usuarioEntityMapper;
  private final TransactionalOperator transactionalOperator;

  @Override
  public Mono<Usuario> findUserByEmailOrDocumentoIdentidad(Usuario usuario) {
    log.info("Buscando usuario en BD por email {} y correo {}", usuario.correoElectronico(), usuario.documentoIdentidad());
    return usuarioR2DBCRepository
        .findByEmailOrDocumentoIdentidad(usuario.correoElectronico(), usuario.documentoIdentidad())
        .map(usuarioEntityMapper::toDomain);
  }

  @Override
  //@Transactional this approach is for classic imperative/blocking code.
  public Mono<Usuario> createUser(Usuario usuario) {
    log.info("Creando el usuario en BD");
    return usuarioR2DBCRepository
        .save(usuarioEntityMapper.toEntity(usuario))
        .map(usuarioEntityMapper::toDomain)
        .as(transactionalOperator::transactional); //Transactional approach for reactive streams
  }

}
