package co.com.crediya.r2dbc.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

  @Id
  @Column("id_usuario")
  private int idUsuario;

  private String nombre;
  private String apellido;
  private String email;

  @Column("fecha_nacimiento")
  private LocalDate fechaNacimiento;

  @Column("documento_identidad")
  private String documentoIdentidad;

  private String direccion;
  private String telefono;

  @Column("id_rol")
  private int idRol;

  @Column("salario_base")
  private double salarioBase;

}
