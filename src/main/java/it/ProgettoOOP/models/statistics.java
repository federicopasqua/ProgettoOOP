package it.ProgettoOOP.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe che rappresenta le statistiche di tutti i file in una cartella.
 */
public class statistics {
	private Map<String, FilesStatistics> stats = new HashMap<>();

	public Map<String, FilesStatistics> getStats() {
		return stats;
	}
	
	/**
	 *Metodo che aggiunge un file alle statistiche dividendolo per tipo.
	 */
	public void add_file_statistics(String type, FileModel file) {
		if (this.stats.containsKey(type)) {
			this.stats.get(type).add_file(file);
		}
		else {
			FilesStatistics stats = new FilesStatistics();
			stats.add_file(file);
			this.stats.put(type, stats);
		}
		
	}
}
;