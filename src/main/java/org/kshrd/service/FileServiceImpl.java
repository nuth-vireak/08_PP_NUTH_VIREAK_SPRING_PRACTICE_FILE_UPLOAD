package org.kshrd.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    //store image in images folder
    private final Path path = Paths.get("src/main/resources/images");

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        // get filename with extension (cute-cat.png)
        String fileName = file.getOriginalFilename();
        // cute-cat.png => [cute-cat, png]
        assert fileName != null;
//        if (file.getOriginalFilename().contains(".png") || file.getOriginalFilename().contains(".pdf")) {
            // convert file name to uuid format form
            fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);
            // if the folder not exist create one
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            // copy byte that from input stream to file
            Files.copy(file.getInputStream(), path.resolve(fileName));
            return fileName;
//        } else {
//            return "Upload Failed";
//        }
    }

    @Override
    public Resource getFileByFileName(String fileName) throws IOException {
        //get file path
        Path path = Paths.get("src/main/resources/images/" + fileName);
        //read file as byte
        return new ByteArrayResource(Files.readAllBytes(path));
    }

    @Override
    public ResponseEntity<?> saveMultipleFiles(List<MultipartFile> files) {
        return null;
    }
}
