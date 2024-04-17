package org.kshrd.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    String saveFile(MultipartFile file) throws IOException;
    Resource getFileByFileName(String fileName) throws IOException;
    ResponseEntity<?> saveMultipleFiles(List<MultipartFile> files);
}
