package co.com.crediya.model.usuario.error;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionTest {

    @Test
    void shouldCreateBusinessExceptionWithCodeAndMessage() {
        String message = "Test error message";
        HttpStatus code = HttpStatus.BAD_REQUEST;
        
        BusinessException exception = new BusinessException(code, message);
        
        assertEquals(message, exception.getMessage());
        assertEquals(code, exception.getCode());
    }

    @Test
    void shouldCreateBusinessExceptionWithForbiddenStatus() {
        String message = "Forbidden operation";
        HttpStatus code = HttpStatus.FORBIDDEN;
        
        BusinessException exception = new BusinessException(code, message);
        
        assertEquals(message, exception.getMessage());
        assertEquals(HttpStatus.FORBIDDEN, exception.getCode());
    }

    @Test
    void shouldCreateBusinessExceptionWithInternalServerErrorStatus() {
        String message = "Internal server error";
        HttpStatus code = HttpStatus.INTERNAL_SERVER_ERROR;
        
        BusinessException exception = new BusinessException(code, message);
        
        assertEquals(message, exception.getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getCode());
    }
}