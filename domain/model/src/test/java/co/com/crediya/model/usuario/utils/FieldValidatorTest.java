package co.com.crediya.model.usuario.utils;

import co.com.crediya.model.usuario.error.BusinessException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FieldValidatorTest {

    @Test
    void shouldValidateValidEmail() {
        assertDoesNotThrow(() -> 
            FieldValidator.validarCorreo("email", "test@example.com")
        );
    }

    @Test
    void shouldThrowExceptionForInvalidEmail() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            FieldValidator.validarCorreo("email", "invalid-email")
        );
        assertTrue(exception.getMessage().contains("formato de correo"));
    }

    @Test
    void shouldThrowExceptionForNullEmail() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            FieldValidator.validarCorreo("email", null)
        );
        assertTrue(exception.getMessage().contains("obligatorio"));
    }

    @Test
    void shouldValidateValidSalary() {
        assertDoesNotThrow(() -> 
            FieldValidator.validarSalario("salario", 2500000.0)
        );
    }

    @Test
    void shouldValidateMinimumSalary() {
        assertDoesNotThrow(() -> 
            FieldValidator.validarSalario("salario", 0.0)
        );
    }

    @Test
    void shouldValidateMaximumSalary() {
        assertDoesNotThrow(() -> 
            FieldValidator.validarSalario("salario", 15000000.0)
        );
    }

    @Test
    void shouldThrowExceptionForNegativeSalary() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            FieldValidator.validarSalario("salario", -1000.0)
        );
        assertTrue(exception.getMessage().contains("debe estar entre"));
    }

    @Test
    void shouldThrowExceptionForExcessiveSalary() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            FieldValidator.validarSalario("salario", 16000000.0)
        );
        assertTrue(exception.getMessage().contains("debe estar entre"));
    }

    @Test
    void shouldThrowExceptionForNullSalary() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            FieldValidator.validarSalario("salario", null)
        );
        assertTrue(exception.getMessage().contains("debe estar entre"));
    }

    @Test
    void shouldValidateValidDate() {
        assertDoesNotThrow(() -> 
            FieldValidator.validarCampoFecha("fecha", LocalDate.of(1990, 5, 15))
        );
    }

    @Test
    void shouldThrowExceptionForNullDate() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            FieldValidator.validarCampoFecha("fecha", null)
        );
        assertTrue(exception.getMessage().contains("formato yyyy-MM-dd"));
    }

    @Test
    void shouldValidateRequiredField() {
        assertDoesNotThrow(() -> 
            FieldValidator.validarCampoRequerido("campo", "valor")
        );
    }

    @Test
    void shouldThrowExceptionForNullRequiredField() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            FieldValidator.validarCampoRequerido("campo", null)
        );
        assertTrue(exception.getMessage().contains("obligatorio"));
    }

    @Test
    void shouldThrowExceptionForBlankRequiredField() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            FieldValidator.validarCampoRequerido("campo", "   ")
        );
        assertTrue(exception.getMessage().contains("obligatorio"));
    }

    @Test
    void shouldThrowExceptionForEmptyRequiredField() {
        BusinessException exception = assertThrows(BusinessException.class, () ->
            FieldValidator.validarCampoRequerido("campo", "")
        );
        assertTrue(exception.getMessage().contains("obligatorio"));
    }
}