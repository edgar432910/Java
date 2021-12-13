package Prueba.REst.Repository;

import Prueba.REst.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta,Integer> {

    // obtener una venta por su id, y estado

    Optional<Venta>findOneByIdAndEstado(Integer id, Venta.Estado estado);


}
