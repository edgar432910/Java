package Prueba.REst.Repository;

import Prueba.REst.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

    //consulta Los ultimos libros 6 libros publicados en base a su fecha de creacion
    List<Libro>findTop6ByOrderByFechaCreacionDesc();


    //Query
//    @Query("select l form Libro l where l.slug=?1")
//    Optional<Libro> findBySlugPersonal(String slug);

    // Busqueda de libro por slug
    Optional<Libro> findBySlug(String slug);


}
