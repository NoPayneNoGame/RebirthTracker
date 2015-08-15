package main.java.nopaynenogame.rebirthtracker;

import java.io.FileInputStream;
import java.io.IOException;

public class SaveFile {

	private String fileData = "";
	private String fileAchieves = "";
	private String fileItems = "";
	private String fileChallenges = "";
	private Boolean valid = false;
	
	public SaveFile(String filePath) {
		try {
			getData(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readAll() {
		return this.fileData;
	}
	
	public String readAchievements() { 
		return this.fileAchieves;
	}
	
	public String readItems() {
		return this.fileItems;
	}
	
	public String readChallenges() {
		return this.fileChallenges;
	}
	
	public Boolean isValid() {
		return this.valid;
	}
	
	private void getData(String filePath) throws IOException {
		FileInputStream save = null;
		try{
			save = new FileInputStream(filePath);
			int c;
			int i = 0;
			while((c = save.read()) != -1) {
				this.fileData += c;
				i++;
				if (i >= 34 && i <= 211) //Achievements are recorded from 34-211
					this.fileAchieves += c;	
				if( i >= 677 && i <= 1022) //Items are recorded from 677-1022
					this.fileItems += c;
				if(i >= 1124 && i <= 1143) //Challenges are recorded from 1124-1143
					this.fileChallenges += c;
			}
		} finally {
			if (save != null) {
				save.close();
				this.valid = true;
			}
		}
	}

}
