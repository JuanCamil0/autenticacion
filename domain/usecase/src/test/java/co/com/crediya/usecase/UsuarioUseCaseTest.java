package co.com.crediya.usecase;

import co.com.crediya.model.usuario.Usuario;
import co.com.crediya.model.usuario.error.BusinessException;
import co.com.crediya.model.usuario.gateways.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    private UsuarioUseCase usuarioUseCase;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuarioUseCase = new UsuarioUseCase(usuarioRepository);
        usuario = new Usuario(
            "Juan",
            "Pérez",
            "juan.perez@example.com",
            LocalDate.of(1990, 5, 15),
            "12345678",
            "Calle 123",
            "3001234567",
            2500000.0
        );
    }

    @Test
    void shouldCreateUsuarioWhenNotExists() {
        when(usuarioRepository.findUserByEmailOrDocumentoIdentidad(any(Usuario.class)))
            .thenReturn(Mono.empty());
        when(usuarioRepository.createUser(any(Usuario.class)))
            .thenReturn(Mono.just(usuario));

        StepVerifier.create(usuarioUseCase.crearUsuario(usuario))
            .expectNext(usuario)
            .verifyComplete();
    }

    @Test
    void shouldThrowExceptionWhenUsuarioExists() {
        when(usuarioRepository.findUserByEmailOrDocumentoIdentidad(any(Usuario.class)))
            .thenReturn(Mono.just(usuario));

        StepVerifier.create(usuarioUseCase.crearUsuario(usuario))
            .expectError(BusinessException.class)
            .verify();
    }

    @Test
    void shouldThrowExceptionWithCorrectMessageWhenUsuarioExists() {
        when(usuarioRepository.findUserByEmailOrDocumentoIdentidad(any(Usuario.class)))
            .thenReturn(Mono.just(usuario));

        StepVerifier.create(usuarioUseCase.crearUsuario(usuario))
            .expectErrorMatches(throwable -> 
                throwable instanceof BusinessException &&
                throwable.getMessage().equals("El usuario ya existe")
            )
            .verify();
    }

    @Test
    void shouldPropagateRepositoryError() {
        RuntimeException repositoryError = new RuntimeException("Database error");
        when(usuarioRepository.findUserByEmailOrDocumentoIdentidad(any(Usuario.class)))
            .thenReturn(Mono.error(repositoryError));

        StepVerifier.create(usuarioUseCase.crearUsuario(usuario))
            .expectError(RuntimeException.class)
            .verify();
    }

    @Test
    void shouldPropagateCreateUserError() {
        RuntimeException createError = new RuntimeException("Create user error");
        when(usuarioRepository.findUserByEmailOrDocumentoIdentidad(any(Usuario.class)))
            .thenReturn(Mono.empty());
        when(usuarioRepository.createUser(any(Usuario.class)))
            .thenReturn(Mono.error(createError));

        StepVerifier.create(usuarioUseCase.crearUsuario(usuario))
            .expectError(RuntimeException.class)
            .verify();
    }
}