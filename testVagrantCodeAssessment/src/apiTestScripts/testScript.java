package apiTestScripts;

import io.restassured.path.json.JsonPath;
import resources.TestBuildData;

public class testScript extends TestBuildData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Displaying the json payload
		System.out.println(testData());

		// TO access the json payload
		JsonPath js = new JsonPath(testData());

		// 1. Write a test to validate the team has only 4 foreign players

		// a. Fetching count of the players in the payload
		int noOfPlayers = js.getInt("player.size()");
		System.out.println("Total number of players in the playload: " + noOfPlayers);

		// b. Traverse through the players and fetching the count of foreign players
		int noOfForeignPlayers = 0;

		for (int i = 0; i < noOfPlayers; i++) {
			String playerCountry = js.getString("player[" + i + "].country");

			if (!(playerCountry.equalsIgnoreCase("India"))) {
				noOfForeignPlayers += 1;
			}
		}
		System.out.println("Total number of foreign players: " + noOfForeignPlayers);
		
		//Validatig the output
		if(noOfForeignPlayers == 4) {
			System.out.println("The team has only 4 foreign players");
		}
		else {
			System.out.println("The team don't have 4 foreign players");
		}
		
		

	}

}
