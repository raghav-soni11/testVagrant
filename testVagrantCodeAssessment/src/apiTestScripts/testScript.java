package apiTestScripts;



import io.restassured.path.json.JsonPath;
import resources.TestBuildData;

public class testScript extends TestBuildData {

	public static void main(String[] args) {

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

		// Validatig the output
		if (noOfForeignPlayers == 4) {
			System.out.println("The team has only 4 foreign players");
		} else {
			System.out.println("The team don't have 4 foreign players");
		}

		// 2. Write a test that validates that there is atleast one wicket keeper

		int noOfWicketKeepers = 0;

		for (int j = 0; j < noOfPlayers; j++) {
			String playerCountry = js.getString("player[" + j + "].role");

			if (playerCountry.equalsIgnoreCase("Wicket-keeper")) {
				noOfWicketKeepers += 1;
			}
		}
		System.out.println("Total number of wicket keeper players: " + noOfWicketKeepers);

		// Validatig the output
		if (noOfWicketKeepers >= 1) {
			System.out.println("The team has atleast one wicket-keeper");
		} else {
			System.out.println("The team don't a wicket-keeper");
		}

	}

}
