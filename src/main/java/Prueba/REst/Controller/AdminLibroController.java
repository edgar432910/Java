package Prueba.REst.Controller;
import Prueba.REst.Controller.dto.LibroDTO;
import Prueba.REst.Model.Libro;
import Prueba.REst.Repository.LibroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/libros")
public class AdminLibroController {

    private final LibroRepository libroRepository;

    public AdminLibroController(LibroRepository libroRepository){
        this.libroRepository=libroRepository;
    }
// Inyectamos una dependecia, no es testeable
//    @Autowired //inyectar dependencia
//    private LibroRepository libroRepository;

    @GetMapping("/prueba")
    String holaMundo(@RequestParam(defaultValue = "valodefecto") String nombre){
        return "Hola "+nombre+"!";
    }


    @GetMapping("")
    Page<Libro> index(@PageableDefault(sort="titulo",size=2) Pageable pageable){
//        Pageable pageable= PageRequest.of();

        return libroRepository.findAll(pageable);
    }


    @GetMapping("/{id}")
    Libro GetId(@PathVariable Integer id){
        return libroRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    Libro create(@RequestBody @Validated LibroDTO libroDTO){

        //Form origen a destino bd
       Libro libro= new ModelMapper().map(libroDTO,Libro.class);
        return libroRepository.save(libro);
    }

    @PutMapping("/{id}")
    Libro update(@PathVariable Integer id, @RequestBody LibroDTO libroDTO){
        Libro libro= libroRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        new ModelMapper().map(libroDTO,libro);
        return libroRepository.save(libro);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        Libro libro= libroRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        libroRepository.delete(libro);

    }

}