package model;

public class Artist {
	private long artistId;
	private String artistName;
	
	public Artist(String name) {
		this.artistName = name;
	}
	
	public Artist(long id, String name) {
		this.artistId = id;
		this.artistName = name;
	}
	
	public long getArtistId() {
		return this.artistId;
	}
	
	public String getArtistName() {
		return this.artistName;
	}
	
	public void setArtistId(long id) {
		this.artistId = id;
	}
	
}
