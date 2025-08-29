package co.com.crediya.usecase;

import co.com.crediya.model.usuario.Usuario;
import co.com.crediya.model.usuario.error.BusinessException;
import co.com.crediya.model.usuario.gateways.UsuarioRepository;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

public class UsuarioUseCase {

  private final UsuarioRepository usuarioRepository;

  public UsuarioUseCase(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public Mono<Usuario> crearUsuario(Usuario usuario) {
    return esUsuarioExistente(usuario)
        .flatMap(esExistente -> esExistente
            ? Mono.error(new BusinessException(HttpStatus.FORBIDDEN, "El usuario ya existe"))
            : usuarioRepository.createUser(usuario));
  }


  private Mono<Boolean> esUsuarioExistente(Usuario usuario) {
    return usuarioRepository
        .findUserByEmailOrDocumentoIdentidad(usuario)
        .hasElement();
  }
}
