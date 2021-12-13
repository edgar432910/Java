package Prueba.REst.Controller;

import Prueba.REst.service.FileSystemStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/assets")
public class AssetController {
    private final FileSystemStorageService storageService;

    public AssetController(FileSystemStorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/upload")
    Map<String,String> upload(@RequestParam MultipartFile file){
        String filename=storageService.store(file);
        Map<String,String> result=new HashMap<>();
        result.put("filename",filename);
        return result;

    }

    @GetMapping("/{filename:.+}")
    ResponseEntity<Resource> load(@PathVariable String filename) throws IOException {
        Resource resource=storageService.loadAsResource(filename);
        String contentType= Files.probeContentType(resource.getFile().toPath());
        return ResponseEntity
                .ok()
                .header("Content-type",contentType)
                .body(resource);
    }

    @GetMapping("/prueba")
    String prubeba(){
        return "funciono";
    }
}
