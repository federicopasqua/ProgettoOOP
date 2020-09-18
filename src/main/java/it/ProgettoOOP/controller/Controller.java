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


/**
 * Definizione di tutti gli endpoint disponibili.
 */
@RestController
public class Controller {
	
	@Autowired
    Services service;
	
	/**
    * Visualizza il contenuto delle cartelle nel DropBox.
    * @param path Percorso della cartella da visualizzare.
    */
	@PostMapping("/getdirectories")
	public ResponseEntity<Object> get_directories(@RequestBody DirectoryPath path){

		try {
			return new ResponseEntity<>(service.get_dropbox_list(path), HttpStatus.OK);
		} catch (InvalidPathException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error with dropbox API");
		}
		
	}
	
	/**
    * Scarica una cartella in locale.
    * @param path Percorso della cartella da scaricare.
    */
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
	
	/**
    * Visualizza le cartelle scaricate in locale.
    * @param path Path della cartella da visualizzare.
    */
	@PostMapping("/getlocaldirectories")
	public ResponseEntity<Object> get_local_directories(@RequestBody DirectoryPath path){
		try {
			return new ResponseEntity<>(service.get_local_list(path), HttpStatus.OK);
		} catch (InvalidPathException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	
	/**
    * Visualizza i metadata.
    */
	@GetMapping("/get_metadata")
	public String get_metadata() {
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
	    try {
	    	JsonSchema schema = mapper.generateJsonSchema(directory.class);
	    
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	/**
    * Cancella una cartella in locale.
    * @param path Percorso della cartella da eliminare.
    */
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
	
	/**
    * Visualizza le statistiche sulla dimensione dei file divisi per estensione.
    * @param path Percorso della cartella da analizzare.
    */
	@PostMapping("/getstatistics")
	public ResponseEntity<Object> get_statistics(@RequestBody DirectoryPath path){
		try {
			return new ResponseEntity<>(service.get_directory_statistics(path), HttpStatus.OK);
		} catch (InvalidPathException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	
}
