package it.ProgettoOOP.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import it.ProgettoOOP.exceptions.InvalidPathException;
import it.ProgettoOOP.exceptions.ZipSlipException;
import it.ProgettoOOP.models.DirectoryPath;
import it.ProgettoOOP.models.FileModel;
import it.ProgettoOOP.models.FilePath;
import it.ProgettoOOP.models.directory;
import it.ProgettoOOP.models.statistics;

/**
*Insieme di metodi statici utilizzati in Services
*/
public class utils {
	
	/**
	 *Metodo statico per mappare le cartelle in locale in un oggetto Directory in modo ricorsivo.
	 *@param path Percorso della cartella.
	 *@return La cartella locale mappata su un oggetto Direcotory.
	 *@throws InvalidPathException Viene lanciata un'eccezione se il percorso non è valido.
	 */
	public static directory map_directory(DirectoryPath path) throws InvalidPathException{
		String directoryName = new File("").getAbsolutePath() + "/Downloads";
		
		
		File filedir = new File(directoryName + path.getPath());
		if(!filedir.exists()) throw new InvalidPathException("Directory does not exist");
		directory dir = new directory(path.Directory(), path);
        File[] files = filedir.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isDirectory()) {
                	directory new_directory = map_directory(new DirectoryPath(path.getPath() + file.getName()));
                    dir.insert_directory(new_directory);
                } else {
                    dir.insert_file(new FileModel(file.getName(), new FilePath(path.getPath() + file.getName()), file.length()));
                }
            }
        }
        return dir;
	}
	
	/**
	 *Metodo per estrarre un file zip.
	 *@param zipFile Percorso del file zip.
	 *@param destDir Percorso cartella di destinazione.
	 *@throws ZipSlipException Viene lanciata un'eccezione se viene rilevato uno zip slip.
	 */
	public static void unzip(String zipFile, String destDir) throws ZipSlipException, IOException {
		
	    int BUFFER = 2048;
	    File file = new File(zipFile);

	    ZipFile zip = new ZipFile(file);

	    File folder = new File(destDir);
	    folder.mkdir();
	    String canonicalFile = folder.getCanonicalPath();
	    Enumeration zipFileEntries = zip.entries();
	    
	    while (zipFileEntries.hasMoreElements())
	    {
	        
	        ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
	        String currentEntry = entry.getName();
	        File destFile = new File(destDir, currentEntry);
	        String canonicalDestinationFile = destFile.getCanonicalPath();
	          if (!canonicalDestinationFile.startsWith(canonicalFile + File.separator)) {
	            throw new ZipSlipException("Entry is outside of the target dir ");
	        }
	        File destinationParent = destFile.getParentFile();
	        
	        destinationParent.mkdirs();

	        if (!entry.isDirectory())
	        {
	            BufferedInputStream is = new BufferedInputStream(zip
	            .getInputStream(entry));
	            int currentByte;
	            
	            byte data[] = new byte[BUFFER];

	            
	            FileOutputStream fos = new FileOutputStream(destFile);
	            BufferedOutputStream dest = new BufferedOutputStream(fos,
	            BUFFER);

	            
	            while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
	                dest.write(data, 0, currentByte);
	            }
	            dest.flush();
	            dest.close();
	            is.close();
	        }

	        
	    }
	    zip.close();
        
    }
	
	/**
	 *Calcola le statistiche dei file contenuti in una cartella in modo ricorsivo.
	 *@param directory Cartella di cui calcolare le statistiche.
	 *@param stats Un oggetto statistics a cui aggiornare le statistiche (usato per la ricorsione).
	 */
	public static void calculate_statistics(directory directory, statistics stats) {
		for (FileModel file: directory.getFiles()) stats.add_file_statistics(file.path().FileType(), file);
		for (directory dir: directory.getDirectories()) calculate_statistics(dir, stats);
	}
	
	
}
