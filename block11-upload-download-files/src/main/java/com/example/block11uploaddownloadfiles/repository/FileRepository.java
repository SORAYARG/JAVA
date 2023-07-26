package com.example.block11uploaddownloadfiles.repository;


import com.example.block11uploaddownloadfiles.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer>{
}
