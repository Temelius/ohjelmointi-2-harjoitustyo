package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Artist;

/**
 * JDBCArtistDao toteuttaa rajapinnan, ja sisältää konkreettisen
 * SQL-logiikan.
 */

public class JDBCArtistDao implements ArtistDao {

	@Override
	public List<Artist> getAllItems() {
		// SQL query string
		String query = "SELECT Artist.ArtistId, Name, COUNT(AlbumId) AS AlbumCount" 
					 + " FROM Artist"
					 + " LEFT JOIN Album ON Album.ArtistId = Artist.ArtistId"
					 + " GROUP BY Artist.ArtistId"
					 + " ORDER BY Name ASC";
		
		// Connection
		Connection conn = null;
		PreparedStatement stmt= null;
		ResultSet results = null;
		
		List<Artist> items = new ArrayList<Artist>();
		
		try {
			
			conn = Database.connect();
			stmt = conn.prepareStatement(query);
			results = stmt.executeQuery();
			
			while(results.next()) {
				long artistId = results.getLong("ArtistId");
				String artistName = results.getString("Name");
				int albumCount = results.getInt("AlbumCount");
				Artist newArtist = new Artist(artistId, artistName, albumCount);
				items.add(newArtist);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, results);
		}
		
		return items;
	}

	@Override
	public Artist getArtist(long id) {
    	List<Artist> items = getAllItems();
    	
    	for (Artist item : items) {
			if (item.getArtistId() == id) {
				return new Artist(item.getArtistId(), item.getArtistName(), item.getAlbumCount());
			}
		}
    	
        return null;
	}

	@Override
	public boolean addArtist(Artist newArtist) {
		
		String query = "INSERT INTO Artist (Name) VALUES (?)";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
    		// Katsotaan onko Artisti jo olemassa tietokannassa.
    		List<Artist> items = getAllItems();
    		for (Artist listItem : items) {
				if (listItem.getArtistName().equalsIgnoreCase(newArtist.getArtistName())) {
					return false;
				}
			}
    		
    		conn = Database.connect();
    		
    		stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    		stmt.setString(1, newArtist.getArtistName());
    		int count = stmt.executeUpdate();
    		
    		result = stmt.getGeneratedKeys();
    		result.next();
    		
    		// Haetaan generoitu ID ja asetetaan se uudelle oliolle.
    		int generatedId = result.getInt(1);
    		newArtist.setArtistId(generatedId);

    		// Tarkistaa onko uusi rivi lisätty ja palauttaa true jos onnistui.
    		// Ei ollut mitään hajua miten tämä javassa toimii niin otin täältä referenssiä ->
    		// https://stackoverflow.com/questions/24378270/how-do-you-determine-if-an-insert-or-update-was-successful-using-java-and-mysql
    		if (count > 0) {
    			return true;
    		}
    		
    		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, result);
		}
		
		return false;
	}

	@Override
	public boolean removeArtist(Artist artist) {
		
		String query = "DELETE FROM Artist WHERE ArtistId = ?";
		
    	Connection conn = null;
    	PreparedStatement stmt= null;
    	ResultSet results = null;
    	
    	List<Artist> items = getAllItems();
    	
    	try {
    		
			conn = Database.connect();
			stmt = conn.prepareStatement(query);
    		
        	for (Artist listItem : items) {
    			if (artist.getArtistName().equalsIgnoreCase(listItem.getArtistName())) {
    				
    				stmt.setLong(1, listItem.getArtistId());
    				
    				if (stmt.executeUpdate() > 0) return true;
    				
    			}
    		}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, results);
		}
		return false;
	}
	
	@Override
	public List<Artist> searchArtist(String keyword) {
		
		String query = "SELECT ArtistId, Name FROM Artist WHERE Name LIKE ? ORDER BY Name ASC";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet results = null;
		
		List<Artist> items = new ArrayList<Artist>();
		
		try {
			
			conn = Database.connect();
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, "%" + keyword + "%");
			results = stmt.executeQuery();
			
			while(results.next()) {
				long artistId = results.getLong("ArtistId");
				String artistName = results.getString("Name");
				Artist newArtist = new Artist(artistId, artistName);
				items.add(newArtist);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, results);
		}
		
		return items;
	}

}
