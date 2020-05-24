package model;

public class Artist {
	private long artistId;
	private String artistName;
	private int albumCount;
	
	public Artist(String name) {
		this.artistName = name;
	}
	
	public Artist(long id, String name) {
		this.artistId = id;
		this.artistName = name;
	}
	
	public Artist(long id, String name, int albumCount) {
		this.artistId = id;
		this.artistName = name;
		this.albumCount = albumCount;
	}
	
	public long getArtistId() {
		return this.artistId;
	}
	
	public String getArtistName() {
		return this.artistName;
	}
	
	public int getAlbumCount() {
		return this.albumCount;
	}
	
	public void setArtistId(long id) {
		this.artistId = id;
	}
	
}
