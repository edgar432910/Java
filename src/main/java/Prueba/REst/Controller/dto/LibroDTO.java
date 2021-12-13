package Prueba.REst.Controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
public class LibroDTO {
    // propiedades actualizables
//    No debe ser nullo, ni caracteres vacios, ni espacio en blanco ctlr+q

    @NotBlank
    @Size(min=3, max = 100)
    private String titulo;

    @NotBlank
    private String slug;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String rutaPortada;
    @NotBlank
    private String rutaArchivo;

    @NotNull
    @PositiveOrZero
    private Float precio;

}
