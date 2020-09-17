package it.ProgettoOOP.models;

import java.util.HashMap;
import java.util.Map;

public class statistics {
	private Map<String, FilesStatistics> stats = new HashMap<>();

	public Map<String, FilesStatistics> getStats() {
		return stats;
	}
	
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