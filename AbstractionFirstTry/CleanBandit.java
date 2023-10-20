package AbstractionFirstTry;

public class CleanBandit extends Artist {

	@Override
	public void showArtistName() {
		System.out.println("Artist found! You have chosen Clean Bandit's playlist.");
	}

	@Override
	public void showPlaylist() {
		System.out.println("Currently playing:");
		System.out.println("1. Baby");
		System.out.println("2. Rockabye");
		System.out.println("3. Solo");
		System.out.println("4. Rather Be");
		System.out.println("5. Tears");
		
	}

	@Override
	public void showDuration() {
		System.out.println("Duration: 19:38");
	}

}
