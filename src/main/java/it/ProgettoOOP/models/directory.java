package it.ProgettoOOP.models;

import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonInclude;

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

	public Boolean insert_directory(directory dir) {
		return this.directories.add(dir);
	}
	
	public Boolean insert_file(FileModel file) {
		return this.files.add(file);
	}
	
	
	
}
