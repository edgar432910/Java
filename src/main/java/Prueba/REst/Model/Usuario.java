package Prueba.REst.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="idusuario")
    private Integer id;
    private String nombres;
    private String apellidos;
    private String nombreCompleto;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING) // trabaja con jpa
    @Valid
    private Rol rol;
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_act")
    private LocalDateTime fechaActualizacion;

    public enum Rol{
        ADMIN,
        LECTOR
    }


    @PrePersist
    private void asignarFechaCreacion(){
        fechaCreacion=LocalDateTime.now();
        nombreCompleto=this.nombres+this.apellidos;
    }
    @PreUpdate
    private void  asignarFechaUpdate(){
        fechaActualizacion=LocalDateTime.now();
    }
}
