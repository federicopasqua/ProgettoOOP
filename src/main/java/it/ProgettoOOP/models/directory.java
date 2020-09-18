package it.ProgettoOOP.models;

import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Questa classe rappresenta una cartella.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class directory {
	private String name;
	private DirectoryPath path;
	private LinkedList<FileModel> files = new LinkedList<FileModel>();
	private LinkedList<directory> directories = new LinkedList<directory>();
	
	public directory() {
		this.name = "root";
		this.path = new DirectoryPath("");
	}
	
	
	/**
	 *Costruttore
	 * @param name Nome della cartella
	 * @param path Percorso della cartella
	 */
	public directory(String name, DirectoryPath path) {
		this.name = name;
		this.path = path;
	}
	
	public String getName() {
		return name;
	}

	public String getPath() {
		return path.getPath();
	}

	public LinkedList<FileModel> getFiles() {
		return files;
	}

	public LinkedList<directory> getDirectories() {
		return directories;
	}

	/**
	 *Aggiunge una cartella all'interno della cartella corrente.
	 * @param dir L'oggetto directory da inserire.
	 * @return true se l'operazione è andata a buon fine.
	 */
	public Boolean insert_directory(directory dir) {
		return this.directories.add(dir);
	}
	
	/**
	 *Aggiunge un file all'interno della cartella corrente.
	 * @param file L'oggetto FileModel da inserire.
	 * @return true se l'operazione è andata a buon fine.
	 */
	public Boolean insert_file(FileModel file) {
		return this.files.add(file);
	}
	
	
	
}
