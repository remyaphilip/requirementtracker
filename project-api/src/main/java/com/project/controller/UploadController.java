package com.project.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(path = "/")
@RestController
public class UploadController {

	//@Value("${upload.file.directory}")
	private String uploadDirectory = "";
	
	//@PostConstruct
	//public void print(){
	//	System.out.println(uploadDirectory);
	//}

	Path path = Paths.get(uploadDirectory);
	String fileName;

	@RequestMapping(path = "upload", method = RequestMethod.POST)
	public boolean uploadFiles(@RequestBody MultipartFile[] files) {

		for (MultipartFile file : files) {
			fileName = file.getOriginalFilename();
			try {
				Files.copy(file.getInputStream(), path);
				System.out.println("done!!");
			} catch (IOException e) {
	
				e.printStackTrace();
			}
		}

		return true;

	}

}
