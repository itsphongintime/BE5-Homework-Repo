package AbstractionFirstTry;

public class LeagueOfLegends extends Artist{
	@Override
	public void showArtistName() {
		System.out.println("Artist found! You have chosen League's playlist.");
		System.out.println("Playing Worlds playlist.");
	}

	@Override
	public void showPlaylist() {
		System.out.println("Currently playing:");
		System.out.println("1. Gods");
		System.out.println("2. Worlds 2023 Orchestral Theme");
		System.out.println("3. RISE");
		System.out.println("4. Warriors");
		System.out.println("5. Legends Never Die");
		
	}

	@Override
	public void showDuration() {
		System.out.println("Duration: 17:54");
	}
}
