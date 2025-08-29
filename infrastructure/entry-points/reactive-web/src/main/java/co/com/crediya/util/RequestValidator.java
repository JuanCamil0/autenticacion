package co.com.crediya.util;

import co.com.crediya.models.usuario.UsuarioDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RequestValidator {

  private final Validator validator;

  public Mono<UsuarioDTO> validarUsuarioDto(UsuarioDTO usuarioDto) {
    Set<ConstraintViolation<UsuarioDTO>> violaciones = validator.validate(usuarioDto);
    if (!violaciones.isEmpty()) {
      return Mono.error(new ConstraintViolationException(violaciones));
    }
    return Mono.just(usuarioDto);
  }
}
