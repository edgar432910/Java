package Prueba.REst.Controller.dto;

import Prueba.REst.Model.Usuario;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UsuarioDTO {
//    propiedades actualizables

    //No permite nullos ni caracteres en blanco
    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    @Size(min=3, max = 100)
    private String password;

    @NotNull
    private Usuario.Rol rol;
}
