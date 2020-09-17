package it.ProgettoOOP.models;

public class FileModel {
	private String name;
	private FilePath path;
	private Long dimension;
	
	public FileModel(String name, FilePath path, Long dim) {
		this.name = name;
		this.path = path;
		this.dimension = dim;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path.getPath();
	}
	
	public FilePath path() {
		return path;
	}

	public Long getDimension() {
		return dimension;
	}
	
}
