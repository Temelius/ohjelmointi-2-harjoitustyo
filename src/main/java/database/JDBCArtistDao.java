package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Artist;

public class JDBCArtistDao implements ArtistDao {

	@Override
	public List<Artist> getAllItems() {
		// SQL query string
		String query = "SELECT ArtistId, Name FROM Artist ORDER BY Name ASC";
		
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

	@Override
	public Artist getArtist(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addArtist(Artist newArtist) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeArtist(Artist artist) {
		// TODO Auto-generated method stub
		return false;
	}

}
