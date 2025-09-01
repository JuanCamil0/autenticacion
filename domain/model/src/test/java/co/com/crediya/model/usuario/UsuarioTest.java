package co.com.crediya.model.usuario;

import co.com.crediya.model.usuario.error.BusinessException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void shouldCreateValidUsuario() {
        Usuario usuario = new Usuario(
            "Juan",
            "Pérez",
            "juan.perez@example.com",
            LocalDate.of(1990, 5, 15),
            "12345678",
            "Calle 123",
            "3001234567",
            2500000.0
        );

        assertEquals("Juan", usuario.nombre());
        assertEquals("Pérez", usuario.apellido());
        assertEquals("juan.perez@example.com", usuario.correoElectronico());
        assertEquals(LocalDate.of(1990, 5, 15), usuario.fechaNacimiento());
        assertEquals("12345678", usuario.documentoIdentidad());
        assertEquals("Calle 123", usuario.direccion());
        assertEquals("3001234567", usuario.telefono());
        assertEquals(2500000.0, usuario.salarioBase());
    }

    @Test
    void shouldThrowExceptionWhenNombreIsNull() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            new Usuario(null, "Pérez", "juan@example.com", LocalDate.now(), "12345678", null, null, 2500000.0)
        );
        assertTrue(exception.getMessage().contains("nombre"));
    }

    @Test
    void shouldThrowExceptionWhenNombreIsBlank() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            new Usuario("", "Pérez", "juan@example.com", LocalDate.now(), "12345678", null, null, 2500000.0)
        );
        assertTrue(exception.getMessage().contains("nombre"));
    }

    @Test
    void shouldThrowExceptionWhenApellidoIsNull() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            new Usuario("Juan", null, "juan@example.com", LocalDate.now(), "12345678", null, null, 2500000.0)
        );
        assertTrue(exception.getMessage().contains("apellido"));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            new Usuario("Juan", "Pérez", "invalid-email", LocalDate.now(), "12345678", null, null, 2500000.0)
        );
        assertTrue(exception.getMessage().contains("correo"));
    }

    @Test
    void shouldThrowExceptionWhenFechaNacimientoIsNull() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            new Usuario("Juan", "Pérez", "juan@example.com", null, "12345678", null, null, 2500000.0)
        );
        assertTrue(exception.getMessage().contains("fecha"));
    }

    @Test
    void shouldThrowExceptionWhenDocumentoIdentidadIsNull() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            new Usuario("Juan", "Pérez", "juan@example.com", LocalDate.now(), null, null, null, 2500000.0)
        );
        assertTrue(exception.getMessage().contains("documento"));
    }

    @Test
    void shouldThrowExceptionWhenSalarioIsNegative() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            new Usuario("Juan", "Pérez", "juan@example.com", LocalDate.now(), "12345678", null, null, -1000.0)
        );
        assertTrue(exception.getMessage().contains("salario"));
    }

    @Test
    void shouldThrowExceptionWhenSalarioExceedsMaximum() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            new Usuario("Juan", "Pérez", "juan@example.com", LocalDate.now(), "12345678", null, null, 16000000.0)
        );
        assertTrue(exception.getMessage().contains("salario"));
    }
}