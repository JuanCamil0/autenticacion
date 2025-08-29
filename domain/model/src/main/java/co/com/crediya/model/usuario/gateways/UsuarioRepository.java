package co.com.crediya.model.usuario.gateways;

import co.com.crediya.model.usuario.Usuario;
import reactor.core.publisher.Mono;

public interface UsuarioRepository {

  Mono<Usuario> findUserByEmailOrDocumentoIdentidad(Usuario usuario);

  Mono<Usuario> createUser(Usuario usuario);
}
