package Prueba.REst.Controller;

import Prueba.REst.Model.Libro;
import Prueba.REst.Repository.LibroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final LibroRepository libroRepository;

    public HomeController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping("/ultimos-libros")
    List<Libro> ultimosLibros(){
        return libroRepository.findTop6ByOrderByFechaCreacionDesc();
    }

    @GetMapping("/libros")
    Page<Libro> getLibros(@PageableDefault(sort = "titulo", size = 10)Pageable pageable){
        return libroRepository.findAll(pageable);
    }
    @GetMapping("/libros/{slug}")
    Libro getLibro(@PathVariable String slug){
        return libroRepository
                .findBySlug(slug)
                .orElseThrow(EntityNotFoundException::new);
    }

}
