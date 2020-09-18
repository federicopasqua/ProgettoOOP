package it.ProgettoOOP.models;

import java.util.regex.PatternSyntaxException;

/**
 *Questa classe estende la classe Path e rappresenta il percorso specifico di un file
 */

public class FilePath extends Path{

	public FilePath() {
		super();
	}
	
	/**
	 *Costruttore
	 * @param path Percorso di un file.
	 */
	public FilePath(String path) {
		super(path);
		if (this.path.endsWith("/")) {
			this.path = this.path.substring(0, this.path.length() - 1);
		}
	}
	
	/**
	 *Restituisce l'estensione del file se presente, altrimenti restituisce 'other'
	 */
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
	
	/**
	 *Metodo che corregge eventuali errori e controlla non ci siano tentativi di accedere file senza autorizzazione
	 */
	public Boolean sanitize_check() {
		this.path.replaceAll("//", "/");
		if (this.path.endsWith("/")) {
			this.path = this.path.substring(0, this.path.length() - 1);
		}
		return !this.path.contains("..");
	}
	
	
	

}
