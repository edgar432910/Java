package Prueba.REst.Model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idventa")
    private Integer id;
    private LocalDateTime fecha;
    private Float total;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    //mapear las relaciones nametablaVenta      idUsuario
    @ManyToOne
    @JoinColumn(name = "idusuario",referencedColumnName = "idusuario")
    private Usuario usuario;

    //no tiene un id relacionado, se necesita declarar la propiedad
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<ItemVenta> items;

    public enum Estado{
        CREADO,
        COMPLETADO
    }
}
