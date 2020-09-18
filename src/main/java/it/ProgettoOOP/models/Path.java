package it.ProgettoOOP.models;

/**
 *Classe che identifica un percorso.
 */

public class Path {
	protected String path;
	
	public Path() {
		this.path = "";
	}
	
	/**
	 *Costruttore
	 * @param path Percorso.
	 */
	public Path(String path) {
		path.replaceAll("//", "/");
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		if (path.equals("/")) path = "";
		this.path = path;
	}
	
	public String getPath() {
		if (this.path.equals("")) return "/";
		return path;
	}
	
	/**
	 *Getter specifico per DropBox dove root è rappresentato da "" invece di "/"
	 */
	public String dropboxCompatiblePath() {
		return path;
	}
	
	/**
	 *Metodo che corregge eventuali errori e controlla non ci siano tentativi di accedere file non autorizzati
	 */
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
