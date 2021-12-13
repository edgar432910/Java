package Prueba.REst.service;

import Prueba.REst.exception.FileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileSystemStorageService {
    private final static String STORAGE_LOCATION="mediafile";

    @PostConstruct
    private void init(){
        try {

            Files.createDirectories(Paths.get(STORAGE_LOCATION));

        }catch (IOException ex){
            throw new RuntimeException("No se pudo crear el almacen de datos:"+STORAGE_LOCATION) ;
        }

    }

    public String store(MultipartFile file){
        String originalFilename=file.getOriginalFilename();
        String filename= UUID.randomUUID()+"."+ StringUtils.getFilenameExtension(originalFilename);
       if(file.isEmpty()){
           throw new RuntimeException("No se puede almacenar un archivo vacion"+originalFilename);

       }
       try {
           InputStream inputStream= file.getInputStream();
           //mueve hacia un almacen de archivos
           Files.copy(inputStream, Paths.get(STORAGE_LOCATION).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
       }catch (IOException e) {
          throw new RuntimeException("Fallo al almacenar el archivo"+originalFilename);
       }
       return filename;

    }

    public Resource loadAsResource (String filename){
        try {
            Path path=Paths.get(STORAGE_LOCATION).resolve(filename);
            Resource resource= new UrlResource(path.toUri());
            if(resource.exists()|| resource.isReadable()){
                return resource;
            }else{
                throw new FileNotFoundException("El archivo no ha sido encontrado: "+filename);

            }

        }catch (MalformedURLException ex){
            throw new FileNotFoundException("El archivo no ha sido encontrado: "+filename);
        }
    }
    public void delete(String filename){
        Path path=Paths.get(STORAGE_LOCATION).resolve(filename);
        try {
            FileSystemUtils.deleteRecursively(path);

        } catch (IOException e) {

        }
    }

}
