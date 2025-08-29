package co.com.crediya.util;

import co.com.crediya.model.usuario.error.BusinessException;
import co.com.crediya.models.transaction.Transaction;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class ErrorHandler {
    
    public static Mono<ServerResponse> handleCreationError(Throwable ex) {
        log.error("Ocurrió un error durante el flujo de {}", Transaction.CREAR_USUARIO, ex);
        // Validar excepciones de Jakarta
        if (ex instanceof ConstraintViolationException cve) {
            List<String> errores = cve.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .toList();
            return ServerResponse.status(HttpStatus.BAD_REQUEST)
                .bodyValue(ResponseBuilder.error(Transaction.CREAR_USUARIO, errores));


        // Validar excepciones de negocio
        } else if (ex instanceof BusinessException) {
            return ServerResponse.status(((BusinessException) ex).getCode())
                .bodyValue(ResponseBuilder.error(Transaction.CREAR_USUARIO, List.of(ex.getMessage())));


        // Validar otras excepciones
        } else {
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .bodyValue(ResponseBuilder.error(Transaction.CREAR_USUARIO, List.of("Error interno del servidor")));
        }
    }

}