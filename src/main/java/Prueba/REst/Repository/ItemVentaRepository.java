package Prueba.REst.Repository;

import Prueba.REst.Model.ItemVenta;
import Prueba.REst.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemVentaRepository extends JpaRepository<ItemVenta,Integer> {


}
