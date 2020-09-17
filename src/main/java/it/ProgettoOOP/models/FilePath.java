package it.ProgettoOOP.models;

import java.util.regex.PatternSyntaxException;

public class FilePath extends Path{

	public FilePath() {
		super();
	}
	
	public FilePath(String path) {
		super(path);
		if (this.path.endsWith("/")) {
			this.path = this.path.substring(0, this.path.length() - 1);
		}
	}
	
	public String FileType() {
		try {
			
			String[] splitted = this.path.split("/");
			if (splitted.length > 0 && splitted[splitted.length - 1].contains(".")) {
				String[] splitted_file = (splitted[splitted.length - 1]).split("\\.");
				return splitted_file[splitted_file.length - 1];
			}
			else {
				return "Other";
			}
		} catch (PatternSyntaxException e){
			return "Other";
		}
	}
	
	public Boolean sanitize_check() {
		this.path.replaceAll("//", "/");
		if (this.path.endsWith("/")) {
			this.path = this.path.substring(0, this.path.length() - 1);
		}
		return !this.path.contains("..");
	}
	
	
	

}
