package it.ProgettoOOP.services;

import it.ProgettoOOP.exceptions.DeleteFailException;
import it.ProgettoOOP.exceptions.InvalidPathException;
import it.ProgettoOOP.exceptions.ZipSlipException;
import it.ProgettoOOP.models.*;
import it.ProgettoOOP.utils.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

@Service
public class Services {
	
	public directory get_dropbox_list(DirectoryPath path) throws InvalidPathException, MalformedURLException, IOException, ParseException{
		if (!path.sanitize_check()) {
			throw new InvalidPathException("Nice Try");
		}
		String url = "https://api.dropboxapi.com/2/files/list_folder";
	

		HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
		openConnection.setRequestMethod("POST");
		openConnection.setRequestProperty("Authorization",
					"Bearer kif7AowhemcAAAAAAAAAAdCeflZWrY4vqGksu16E8VAGSyIzy34QZAQyyMbg_FFv");
		openConnection.setRequestProperty("Content-Type", "application/json");
		openConnection.setRequestProperty("Accept", "application/json");
		openConnection.setDoOutput(true);
		String jsonBody = "{\r\n" + "    \"path\": \"" + path.dropboxCompatiblePath() + "\",\r\n" + "    \"recursive\": false,\r\n"
				+ "    \"include_media_info\": false,\r\n" + "    \"include_deleted\": false,\r\n"
				+ "    \"include_has_explicit_shared_members\": false,\r\n"
				+ "    \"include_mounted_folders\": true,\r\n" + "    \"include_non_downloadable_files\": true\r\n"
				+ "}";

		try (OutputStream os = openConnection.getOutputStream()) {
			byte[] input = jsonBody.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		InputStream in = openConnection.getInputStream();

		String data = "";
		String line = "";
		try {
			InputStreamReader inR = new InputStreamReader(in);
			BufferedReader buf = new BufferedReader(inR);

			while ((line = buf.readLine()) != null) {
				data += line;
			}
		} finally {
			in.close();
		}
		JSONObject obj = (JSONObject) JSONValue.parseWithException(data);
		JSONArray array = (JSONArray) obj.get("entries");
		directory dir = new directory(path.Directory(), path);
		for (int i = 0; i < array.size(); i++) {
			JSONObject item = (JSONObject) array.get(i);
			if ("folder".equals((String) item.get(".tag"))){
				dir.insert_directory(new directory((String) item.get("name"), new DirectoryPath((String) item.get("path_lower"))));
			}
			else if ("file".equals((String) item.get(".tag"))){
				dir.insert_file(new FileModel((String) item.get("name"), new FilePath((String) item.get("path_lower")), (Long) item.get("size")));
			}
		}
		return dir;
	}
	
	
	public void download_directory(DirectoryPath path) throws InvalidPathException, IOException, ZipSlipException{
		if (!path.sanitize_check()) {
			throw new InvalidPathException("Nice Try");
		}
		if (path.getPath().equals("/")) throw new InvalidPathException("Can't download root folder");
		String Surl = "https://content.dropboxapi.com/2/files/download_zip";

		URL url = new URL(Surl);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Authorization",
					"Bearer kif7AowhemcAAAAAAAAAAdCeflZWrY4vqGksu16E8VAGSyIzy34QZAQyyMbg_FFv");
		connection.setRequestProperty("Content-Type", "application/octet-stream");
		connection.setRequestProperty("Dropbox-API-Arg", "{\"path\": \"" + path.dropboxCompatiblePath() + "\"}");
		InputStream input = connection.getInputStream();
		byte[] buffer = new byte[4096];
		int n;
		
		String directoryName = "C:/Users/feder/Documents/GitHub/ProgettoOOP/Downloads";
	    String fileName = "temp.zip";
	    File file = new File(directoryName + "/" + fileName);

		OutputStream output = new FileOutputStream( file );
		while ((n = input.read(buffer)) != -1) 
		{
		    output.write(buffer, 0, n);
		}
		output.close();
		
		utils.unzip(directoryName + "/" + fileName, directoryName + path.parentDirectory());
		file.delete();
	}

	
	public directory get_local_list(DirectoryPath path) throws  InvalidPathException{
		if (!path.sanitize_check()) {
			throw new InvalidPathException("Nice Try");
		}
		return utils.map_directory(path);
	}
	
	public void delete_directory(DirectoryPath path) throws InvalidPathException, DeleteFailException {
		if (!path.sanitize_check()) {
			throw new InvalidPathException("Nice Try");
		}
		
		String directoryName = "C:/Users/feder/Documents/GitHub/ProgettoOOP/Downloads";
		
		
		if (path.getPath().equals("/")) {
			File dir = new File(directoryName);
			File[] files = dir.listFiles();
		    if(files!=null) {
		        for(File f: files) {
		        	if(!FileSystemUtils.deleteRecursively(f)) {
						throw new DeleteFailException("Cannot delete this folder!");
					}
		        }
		    }
		}else {
			File dir = new File(directoryName + path.getPath());
			if(!FileSystemUtils.deleteRecursively(dir)) {
				throw new DeleteFailException("Cannot delete this folder!");
			}
			
		}
		
	}
	
	
	public statistics get_directory_statistics(DirectoryPath path) throws InvalidPathException {
		if (!path.sanitize_check()) {
			throw new InvalidPathException("Nice Try");
		}
		directory dir = utils.map_directory(path);
		statistics stats = new statistics();
		utils.calculate_statistics(dir, stats);
		return stats;
	}

}
