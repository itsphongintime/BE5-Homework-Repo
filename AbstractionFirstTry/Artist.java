package AbstractionFirstTry;

public abstract class Artist {
	public abstract void showArtistName();
	public abstract void showPlaylist();
	public abstract void showDuration();
	
	public static Artist findMyArtist (String artistName) {
		switch (artistName) {
		case "joji", "Joji":
			return new Joji();
			
		case "cleanbandit", "CleanBandit":
			return new CleanBandit();
		
		case "LeagueOfLegends", "lol":
			return new LeagueOfLegends();
		
		default:
			return null;
		}
	}
}
