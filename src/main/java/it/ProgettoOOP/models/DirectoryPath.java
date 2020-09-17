package it.ProgettoOOP.models;

public class DirectoryPath extends Path{

	public DirectoryPath() {
		super();
	}
	
	public DirectoryPath(String path) {
		super(path);
		if (!this.path.endsWith("/")) {
			this.path += "/";
		}
	}
	
	public String parentDirectory() {
		return this.path.substring(0, this.path.length() - 1).replaceAll("(.*/).*", "$1");
	}
	
	public String Directory() {
		if (this.path.equals("")) return "root";
		String[] splitted = this.path.split("/");
		return splitted[splitted.length - 1];
	}

}
