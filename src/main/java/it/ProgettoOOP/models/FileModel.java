package it.ProgettoOOP.models;

/**
 *Classe che rappresenta un file.
 */


public class FileModel {
	private String name;
	private FilePath path;
	private Long dimension;
	
	/**
	 *COstruttore
	 * @param name Nome del file
	 * @param path Percorso del file
	 * @param dim Dimensione del file
	 */
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
