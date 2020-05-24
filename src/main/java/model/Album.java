package model;

public class Album {
	private long albumId;
	private String  albumTitle;
	private long artistId;
	
	public Album(String title) {
		this.albumTitle = title;
	}
	
	public Album(long id, String title, long artist) {
		this.albumId = id;
		this.albumTitle = title;
		this.artistId = artist;
	}
	
	public long getAlbumId() {
		return this.albumId;
	}
	
	public String getAlbumTitle() {
		return this.albumTitle;
	}
	
	public long getArtistId() {
		return this.artistId;
	}
	
	public void setAlbumId(long id) {
		this.albumId = id;
	}
	
}
