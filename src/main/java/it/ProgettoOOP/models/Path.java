package it.ProgettoOOP.models;

public class Path {
	protected String path;
	
	public Path() {
		this.path = "";
	}
	
	
	public Path(String path) {
		path.replaceAll("//", "/");
		if (!this.path.startsWith("/")) {
			this.path = "/" + this.path;
		}
		if (path.equals("/")) path = "";
		this.path = path;
	}
	
	public String getPath() {
		if (this.path.equals("")) return "/";
		return path;
	}
	
	public String dropboxCompatiblePath() {
		return path;
	}
	
	
	public Boolean sanitize_check() {
		this.path.replaceAll("//", "/");
		if (!this.path.startsWith("/")) {
			this.path = "/" + this.path;
		}
		if (!this.path.endsWith("/")) {
			this.path += "/";
		}
		if (this.path.equals("/")) this.path = "";
		return !this.path.contains("..");
	}
	
	
	
}
