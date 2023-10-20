package AbstractionFirstTry;

public class Joji extends Artist {
	@Override
	public void showArtistName() {
		System.out.println("Artist found! You have chosen Joji's playlist.");
	}

	@Override
	public void showPlaylist() {
		System.out.println("Currently playing:");
		System.out.println("1. Slow Dancing In the Dark");
		System.out.println("2. Glimpse Of Us");
		System.out.println("3. Die For You");
		System.out.println("4. Run");
		System.out.println("5. Can't get over you");
	}

	@Override
	public void showDuration() {
		System.out.println("Duration: 15:59");
	}

}
