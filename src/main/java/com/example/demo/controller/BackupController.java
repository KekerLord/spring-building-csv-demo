package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController()
public class BackupController {
  final File dbFile = new File("building.xml");

  @GetMapping("/api/backup")
  public void fileDownload(HttpServletResponse response) throws IOException {
    response.setContentType("application/xml");
    Files.copy(dbFile.toPath(), response.getOutputStream());
  }

  @PostMapping("/api/backup")
  public void fileUpload(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
    if (!file.isEmpty() && "text/xml".equals(file.getContentType())) {
      Files.copy(file.getInputStream(), dbFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    response.sendRedirect("/");
  }
}
