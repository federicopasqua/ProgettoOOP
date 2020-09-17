package it.ProgettoOOP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;

import it.ProgettoOOP.exceptions.DeleteFailException;
import it.ProgettoOOP.exceptions.InvalidPathException;
import it.ProgettoOOP.exceptions.ZipSlipException;
import it.ProgettoOOP.models.DirectoryPath;
import it.ProgettoOOP.models.directory;
import it.ProgettoOOP.services.Services;

@RestController
public class Controller {
	
	@Autowired
    Services service;
	
	
	@PostMapping("/getdirectories")
	public ResponseEntity<Object> get_directories(@RequestBody DirectoryPath path){

		try {
			return new ResponseEntity<>(service.get_dropbox_list(path), HttpStatus.OK);
		} catch (InvalidPathException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error with dropbox API");
		}
		
	}
	
	@PostMapping("/downloaddirectory")
	public ResponseEntity<Object> download_directory(@RequestBody DirectoryPath path){
		try {
			service.download_directory(path);
		}catch (InvalidPathException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}catch (ZipSlipException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error with dropbox API");
		}
		
		return new ResponseEntity<>("Downloaded.", HttpStatus.OK);
	}
	
	@PostMapping("/getlocaldirectories")
	public ResponseEntity<Object> get_local_directories(@RequestBody DirectoryPath path){
		try {
			return new ResponseEntity<>(service.get_local_list(path), HttpStatus.OK);
		} catch (InvalidPathException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping("/get_metadata")
	public String get_metadata() {
		ObjectMapper mapper = new ObjectMapper();
	    //There are other configuration options you can set.  This is the one I needed.
	    mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
	    try {
	    	JsonSchema schema = mapper.generateJsonSchema(directory.class);

	    
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	
	@PostMapping("/delete_folder")
	public ResponseEntity<Object> delete_folder(@RequestBody DirectoryPath path){
		try {
			service.delete_directory(path);
		}catch (InvalidPathException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}catch (DeleteFailException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown Error");
		}
		
		return new ResponseEntity<>("Removed.", HttpStatus.OK);
	}
	
	@PostMapping("/getstatistics")
	public ResponseEntity<Object> get_statistics(@RequestBody DirectoryPath path){
		try {
			return new ResponseEntity<>(service.get_directory_statistics(path), HttpStatus.OK);
		} catch (InvalidPathException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	
}
