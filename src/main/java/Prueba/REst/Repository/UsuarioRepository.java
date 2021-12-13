package Prueba.REst.Repository;

import Prueba.REst.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario,Integer> {

    Optional<Usuario>findOneByEmail(String email);

    // existe el correo electronico
    boolean existsByEmail(String email);



}
