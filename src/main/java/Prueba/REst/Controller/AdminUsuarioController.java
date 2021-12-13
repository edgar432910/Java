package Prueba.REst.Controller;

import Prueba.REst.Controller.dto.UsuarioDTO;
import Prueba.REst.Model.Libro;
import Prueba.REst.Model.Usuario;
import Prueba.REst.Repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/admin/usuario")
public class AdminUsuarioController {

    private final UsuarioRepository usuarioRepository;

    public AdminUsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository=usuarioRepository;
    }
    @GetMapping("/prueba")
    String holaMundo(@RequestParam(defaultValue = "valodefecto") String nombre){
        return "Hola "+nombre+"!";
    }

    @GetMapping("")
    Page<Usuario> GetALL( @PageableDefault(sort="nombres",size=2) Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }
    @GetMapping("/{id}")
    Usuario GetOne(Integer id){
        return usuarioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    Usuario Create(@RequestBody @Validated UsuarioDTO usuarioDTO){
        Usuario usuario= new ModelMapper().map(usuarioDTO, Usuario.class);
        return  usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    Usuario Update(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        new  ModelMapper().map(usuarioDTO,usuario);
        return  usuarioRepository.save(usuario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void Delete(@PathVariable Integer id){
        Usuario usuario= usuarioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        usuarioRepository.delete(usuario);
    }


}
