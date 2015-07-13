package main.java.nopaynenogame.rebirthtracker;

import java.util.HashMap;

public class Main {
	public static void main(String[] args){
		
		String saveLocation = "src/main/resources/persistentgamedata1.dat";
		
		String resourceLoc = "src/main/resources/";
		String achieveLoc = resourceLoc + "Achievements.txt";
		String itemLoc = resourceLoc + "Items.txt";
		String challengeLoc = resourceLoc + "Challenges.txt";
		
		SaveFile save = new SaveFile(saveLocation);
		String dataAchieve = save.readAchievements();
		String dataItems = save.readItems();
		String dataChallenges = save.readChallenges();
		
		ResourceFile achieveFile = new ResourceFile(achieveLoc);
		ResourceFile itemFile = new ResourceFile(itemLoc);
		ResourceFile challengeFile = new ResourceFile(challengeLoc);
		
		HashMap<String, String> achieveMap = achieveFile.toMap();
		HashMap<String, String> itemMap = itemFile.toMap();
		HashMap<String, String> challengeMap = challengeFile.toMap();
		
		int numAchieveUnlocked = 0;
		int numItemsTouched = 0;
		int numChallengesDone = 0;
		
		
		for (int i = 0; i < dataAchieve.length(); i++) {
			if(dataAchieve.charAt(i) == '1')
			{
				System.out.println(achieveMap.get(String.valueOf(i + 1)) + " Unlocked!");
				numAchieveUnlocked++;
			}
			else
				System.out.println(achieveMap.get(String.valueOf(i + 1)) + " Locked.");
		}
		
		for (int i = 0; i < dataItems.length(); i++) {
			if(dataItems.charAt(i) == '1')
			{
				if(itemMap.get(String.valueOf(i + 1)) != null)
					System.out.println((i+1) + ": " + itemMap.get(String.valueOf(i + 1)) + " Touched!");
				numItemsTouched++;
			}
			else
				if(itemMap.get(String.valueOf(i + 1)) != null)
					System.out.println((i+1) + ": " + itemMap.get(String.valueOf(i + 1)) + " Untouched.");
		}
		
		for (int i = 0; i < dataChallenges.length(); i++) {
			if(dataChallenges.charAt(i) == '1')
			{
				System.out.println(challengeMap.get(String.valueOf(i + 1)) + " Completed.");
				numChallengesDone++;
			}
			else 
				System.out.println(challengeMap.get(String.valueOf(i + 1)) + " Uncompleted.");

		}
		
		System.out.println();
		System.out.println(numAchieveUnlocked + "/" + dataAchieve.length() + " Achievements Unlocked");
		System.out.println(numItemsTouched + "/" + (dataItems.length()-5) + " Items Touched");
		System.out.println(numChallengesDone + "/" + dataChallenges.length() + " Challeneges Completed");
		
	}

}
