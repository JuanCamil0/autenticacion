package co.com.crediya.router;

import co.com.crediya.models.transaction.Transaction;
import co.com.crediya.models.usuario.UsuarioDTO;
import co.com.crediya.usecase.UsuarioUseCase;
import co.com.crediya.util.UsuarioDTOMapper;
import co.com.crediya.util.RequestValidator;
import co.com.crediya.util.ErrorHandler;
import co.com.crediya.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class RouterHandler {


  //Not an actual error. See note at UseCasesConfig from app-service module.
  private final UsuarioUseCase usuarioUseCase;
  private final RequestValidator requestValidator;
  private final UsuarioDTOMapper usuarioDTOMapper;


  public Mono<ServerResponse> crearUsuarioListener(ServerRequest serverRequest) {
    log.info("Inicio de Transaccion {}", Transaction.CREAR_USUARIO);
    return serverRequest
        .bodyToMono(UsuarioDTO.class)
        .doOnNext(usuarioDTO -> log.info("Request recibido: {}", usuarioDTO))
        .flatMap(requestValidator::validarUsuarioDto)
        .map(usuarioDTOMapper::toDomain)
        .flatMap(usuarioUseCase::crearUsuario)
        .then(Mono.just(ResponseBuilder.success(Transaction.CREAR_USUARIO)))
        .flatMap(response -> ServerResponse.status(HttpStatus.CREATED).bodyValue(response))
        .onErrorResume(ErrorHandler::handleCreationError)
        .doFinally(signalType -> log.info("Fin de Transaccion {}", Transaction.CREAR_USUARIO));
  }



}
