package co.com.crediya.models.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record UsuarioDTO(
    @NotBlank(message = "El nombre es requerido")
    String nombre,

    @NotBlank(message = "El apellido es requerido")
    String apellido,

    @NotBlank(message = "El correo electrónico es requerido")
    @Email(message = "El correo electrónico debe tener un formato válido")
    String correoElectronico,

    @NotNull(message = "La fecha de nacimiento es requerida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaNacimiento,

    @NotBlank(message = "El documento de identidad es requerido")
    String documentoIdentidad,

    String direccion,
    String telefono,

    @DecimalMin(value = "0.0", message = "El salario base no puede ser negativo")
    @DecimalMax(value = "15000000.0", message = "El salario base no puede exceder 15 millones")
    double salarioBase
) {}
