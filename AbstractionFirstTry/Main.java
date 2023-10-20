package AbstractionFirstTry;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		Artist chosenArtist = null;
		String CDname;
		
		while (chosenArtist == null) {
			System.out.println("Enter your CD (no spaces): ");
			CDname = keyboard.next();
			chosenArtist = Artist.findMyArtist(CDname);
			if (chosenArtist == null) {
				System.out.println("Artist not found! Try again!");
			}
		}
		
		chosenArtist.showArtistName();
		chosenArtist.showPlaylist();
		chosenArtist.showDuration();
	}
}
