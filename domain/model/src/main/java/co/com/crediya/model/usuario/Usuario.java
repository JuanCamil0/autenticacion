package co.com.crediya.model.usuario;

import co.com.crediya.model.usuario.utils.FieldValidator;
import java.time.LocalDate;

public record Usuario(
    String nombre,                  // required
    String apellido,                // required
    String correoElectronico,       // required
    LocalDate fechaNacimiento,      // required
    String documentoIdentidad,      // required
    String direccion,
    String telefono,
    double salarioBase              // required

) {
    public Usuario {
      FieldValidator.validarCampoRequerido("nombre", nombre);
      FieldValidator.validarCampoRequerido("apellido", apellido);
      FieldValidator.validarCorreo("correo electrónico", correoElectronico);
      FieldValidator.validarCampoFecha("fecha de nacimiento", fechaNacimiento);
      FieldValidator.validarCampoRequerido("documento de identidad", documentoIdentidad);
      FieldValidator.validarSalario("salario base", salarioBase);
    }

}
