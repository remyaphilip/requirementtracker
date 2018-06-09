package com.project.controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(path = "/")
@RestController
public class UploadController {

	@Value("${upload.file.directory}")
	private String uploadDirectory;

	// @PostConstruct
	// public void print(){
	// System.out.println(uploadDirectory);
	// }

	@RequestMapping(path = "upload", method = RequestMethod.POST)
	public boolean uploadFiles(@RequestParam("files") MultipartFile[] files) {
		Path absolutePath = FileSystems.getDefault().getPath(uploadDirectory).normalize().toAbsolutePath();
				
		Arrays.asList(files).stream().forEach(file->{
			String fileName = file.getOriginalFilename();
			Path newFilePath = absolutePath.resolve(fileName);
			try {
				Files.copy(file.getInputStream(), newFilePath);
				System.out.println("done!!");
			} catch (IOException e) {

				e.printStackTrace();
			}
	
		});
		return true;
	}

}
