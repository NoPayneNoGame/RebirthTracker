package main.java.nopaynenogame.rebirthtracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class ResourceFile {
	
	private String fileData; 

	public ResourceFile(String fileLoc) {
		try {
			this.fileData = readFile(fileLoc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, String> toMap() {
		HashMap<String, String> fileMap = new HashMap<String, String>();
		String[] lines = this.fileData.split("\r\n");
		for (int i = 0; i < lines.length; i++) {
			String[] words = lines[i].split("\t");
			fileMap.put(words[0], words[1]);
		}
		return fileMap;
	}
	
	@Override
	public String toString() {
		return fileData;
	}
	
	private String readFile(String fileLoc) throws IOException {
		InputStream file = null;
		String data = "";

		try{
			file = this.getClass().getResourceAsStream(fileLoc);
			int c;
			while((c = file.read()) != -1) {
				data += (char)c;
			}
		} finally {
			if (file != null) {
				file.close();
			}
		}
		return data;
	}

}
