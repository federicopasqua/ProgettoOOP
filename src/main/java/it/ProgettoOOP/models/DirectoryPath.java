package it.ProgettoOOP.models;

/**
 *Questa classe estende la classe Path e rappresenta il percorso specifico di una cartella.
 */

public class DirectoryPath extends Path{

	public DirectoryPath() {
		super();
	}
	
	/**
	 *Costruttore
	 * @param path Percorso di una cartella.
	 */
	public DirectoryPath(String path) {
		super(path);
		if (!this.path.endsWith("/")) {
			this.path += "/";
		}
	}
	
	/**
	 *Metodo che restituisce la cartella padre.
	 * @return Cartella padre.
	 */
	public String parentDirectory() {
		return this.path.substring(0, this.path.length() - 1).replaceAll("(.*/).*", "$1");
	}
	
	/**
	 *metodo che restituisce la cartella corrente.
	 * @return cartella corrente.
	 */
	public String Directory() {
		if (this.path.equals("")) return "root";
		String[] splitted = this.path.split("/");
		return splitted[splitted.length - 1];
	}

}
