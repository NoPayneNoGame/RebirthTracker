package main.java.nopaynenogame.rebirthtracker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		
		//String saveLocation = "src/main/resources/persistentgamedata1.dat";
		//String saveLocation = "C:\\Program Files (x86)\\Steam\\userdata\\73781797\\250900\\remote\\persistentgamedata2.dat";
		
		File jarPath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String propPath = jarPath.getParent();
        propPath = propPath.replaceAll("%20", " ");

        Scanner in = null;
        try {
			 in = new Scanner(new FileReader(propPath + "\\SavePath.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		String saveLocation = in.nextLine();
		
		
		String resourceLoc = "/main/resources/";
		String achieveLoc = resourceLoc + "Achievements.txt";
		String itemLoc = resourceLoc + "Items.txt";
		String challengeLoc = resourceLoc + "Challenges.txt";
		
		ResourceFile achieveFile = new ResourceFile(achieveLoc);
		ResourceFile itemFile = new ResourceFile(itemLoc);
		ResourceFile challengeFile = new ResourceFile(challengeLoc);
		
		HashMap<String, String> achieveMap = achieveFile.toMap();
		HashMap<String, String> itemMap = itemFile.toMap();
		HashMap<String, String> challengeMap = challengeFile.toMap();
		
		Boolean running = true;
		
		while(running){
		
			SaveFile save = new SaveFile(saveLocation);
			String dataAchieve = save.readAchievements();
			String dataItems = save.readItems();
			String dataChallenges = save.readChallenges();
			
	
			int numAchieveUnlocked = 0;
			int numItemsTouched = 0;
			int numChallengesDone = 0;
			
			
			for (int i = 0; i < dataAchieve.length(); i++) {
				if(dataAchieve.charAt(i) == '1')
				{
					//System.out.println(achieveMap.get(String.valueOf(i + 1)) + " Unlocked!");
					numAchieveUnlocked++;
				}
				//else
					//System.out.println(achieveMap.get(String.valueOf(i + 1)) + " Locked.");
			}
			
			for (int i = 0; i < dataItems.length(); i++) {
				if(dataItems.charAt(i) == '1')
				{
					//if(itemMap.get(String.valueOf(i + 1)) != null)
						//System.out.println((i+1) + ": " + itemMap.get(String.valueOf(i + 1)) + " Touched!");
					numItemsTouched++;
				}
				//else
					//if(itemMap.get(String.valueOf(i + 1)) != null)
						//System.out.println((i+1) + ": " + itemMap.get(String.valueOf(i + 1)) + " Untouched.");
			}
			
			for (int i = 0; i < dataChallenges.length(); i++) {
				if(dataChallenges.charAt(i) == '1')
				{
					//System.out.println(challengeMap.get(String.valueOf(i + 1)) + " Completed.");
					numChallengesDone++;
				}
				//else 
					//System.out.println(challengeMap.get(String.valueOf(i + 1)) + " Uncompleted.");
	
			}
			
			//System.out.println(save.readAll());
			System.out.println();
			System.out.println(numAchieveUnlocked + "/" + dataAchieve.length() + " Achievements Unlocked");
			System.out.println(numItemsTouched + "/" + (dataItems.length()-5) + " Items Touched");
			System.out.println(numChallengesDone + "/" + dataChallenges.length() + " Challenges Completed");
			
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("achievements.txt"), "utf-8"))) {
				writer.write(numAchieveUnlocked + "/" + dataAchieve.length());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("items.txt"), "utf-8"))) {
				writer.write(numItemsTouched + "/" + (dataItems.length()-5));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("challenges.txt"), "utf-8"))) {
				writer.write(numChallengesDone + "/" + dataChallenges.length());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
