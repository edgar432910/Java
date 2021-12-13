package Prueba.REst.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ItemVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iditem_venta")
    private Integer id;

    private Float precio;
    private Integer numeroDescargarDisponibles;

    //mapear las relaciones nametablaitemventa     idLibro
    @ManyToOne
    @JoinColumn(name = "idlibro",referencedColumnName = "idlibro")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "idventa",referencedColumnName = "idventa")
    private Venta venta;
}
