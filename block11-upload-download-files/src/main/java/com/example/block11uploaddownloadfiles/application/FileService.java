package com.example.block11uploaddownloadfiles.application;

import com.example.block11uploaddownloadfiles.domain.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface FileService {

    File store(MultipartFile file);
    Path load(String fileName);
    Resource loadAsResourceById(Integer id);
    Resource loadAsResourceByFilename(String fileName);
    void setPath(String path);
    void deleteAll();
    void init();

}
