package co.com.crediya.router;

import co.com.crediya.config.UsuarioPathProperties;
import co.com.crediya.models.response.ResponseDTO;
import co.com.crediya.models.usuario.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public interface RouterDefinition {

  @Bean
  @RouterOperation(
      path = "/usuarios",
      method = RequestMethod.POST,
      operation = @Operation(
          operationId = "crearUsuario",
          summary = "Crear nuevo usuario",
          description = "Crea un nuevo usuario en el sistema",
          requestBody = @RequestBody(
              required = true,
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = UsuarioDTO.class),
                  examples = @ExampleObject(
                      name = "Ejemplo Request de creación de Usuario",
                      value = """
                          {
                            "nombre": "Juan",
                            "apellido": "Pérez",
                            "correoElectronico": "juan.perez@example.com",
                            "fechaNacimiento": "1990-05-15",
                            "documentoIdentidad": "12345678",
                            "direccion": "Calle 123 #45-67",
                            "telefono": "3001234567",
                            "salarioBase": 2500000.0
                          }
                          """
                  )
              )
          ),
          responses = {
              @ApiResponse(
                  responseCode = "201",
                  description = "Usuario creado exitosamente",
                  content = @Content(
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = ResponseDTO.class),
                      examples = @ExampleObject(
                          name = "Ejemplo de respuesta exitosa",
                          value = """
                              {
                                "transaction": "CREAR_USUARIO",
                                "estado": "SUCCESS"
                              }
                              """
                      )
                  )
              ),
              @ApiResponse(
                  responseCode = "400",
                  description = "Datos de entrada inválidos",
                  content = @Content(
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = ResponseDTO.class),
                      examples = @ExampleObject(
                          name = "Ejemplo de respuesta por error en los campos requeridos",
                          value = """
                              {
                                "estado": "ERROR",
                                "errores": [
                                  "El nombre es requerido",
                                  "El correo electrónico debe tener un formato válido"
                                ]
                              }
                              """
                      )
                  )
              ),
              @ApiResponse(
                  responseCode = "500",
                  description = "Error interno del servidor",
                  content = @Content(
                      mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = ResponseDTO.class),
                      examples = @ExampleObject(
                          name = "Ejemplo de respuesta por error interno del microservicio",
                          value = """
                              {
                                "estado": "ERROR",
                                "errores": ["Error interno del servidor"]
                              }
                              """
                      )
                  )
              )
          }
      )
  )
  RouterFunction<ServerResponse> routerFunction(
      UsuarioPathProperties usuarioPathProperties, RouterHandler routerHandler);
}
