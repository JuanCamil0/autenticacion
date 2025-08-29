package co.com.crediya.model.usuario.utils;

import co.com.crediya.model.usuario.error.BusinessException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;

public class FieldValidator {

  private static final DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final double MAX_SALARIO = 15000000;
  private static final double MIN_SALARIO = 0;
  private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

  private FieldValidator() {}

  public static void validarCorreo(String nombreCampo, String valor) {
    validarCampoRequerido(nombreCampo, valor);
    if (!Pattern.matches(EMAIL_REGEX, valor)) {
      throw new BusinessException(HttpStatus.FORBIDDEN, "El campo " + nombreCampo + "debe tener un formato de correo valido");
    }
  }

  public static void validarSalario(String nombreCampo, Double valor) {
    if(valor == null || (valor < MIN_SALARIO || valor > MAX_SALARIO)) {
      throw new BusinessException(HttpStatus.FORBIDDEN, "El campo " + nombreCampo + " debe estar entre " + MIN_SALARIO + " y " + MAX_SALARIO);
    }
  }

  public static void validarCampoFecha(String nombreCampo, LocalDate date) {
    try {
      Objects.requireNonNull(date);
      date.format(formater);
    } catch (NullPointerException | DateTimeException ex) {
      throw new BusinessException(HttpStatus.FORBIDDEN, "El campo " + nombreCampo + " debe tener formato yyyy-MM-dd");
    }
  }

  public static void validarCampoRequerido(String nombreCampo, String valor) {
    if (valor == null || valor.isBlank()) {
      throw new BusinessException(HttpStatus.FORBIDDEN, "El campo " + nombreCampo + " es obligatorio");
    }
  }

}
