package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Album;

public class JDBCAlbumDao implements AlbumDao {
	@Override
	public List<Album> getAllItemsByArtist(long id) {
		// SQL query string
		String query = "SELECT AlbumId, Title, ArtistId FROM Album WHERE ArtistId = ?";

		// Connection
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet results = null;

		List<Album> items = new ArrayList<Album>();

		try {

			conn = Database.connect();
			stmt = conn.prepareStatement(query);
			stmt.setLong(1, id);
			results = stmt.executeQuery();

			while (results.next()) {
				long albumId = results.getLong("AlbumId");
				String albumTitle = results.getString("Title");
				long artistId = results.getLong("ArtistId");
				Album newAlbum = new Album(albumId, albumTitle, artistId);
				items.add(newAlbum);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, results);
		}

		return items;
	}

	@Override
	public boolean addAlbum(Album newAlbum) {

		String query = "INSERT INTO Album (Title, ArtistId) VALUES (?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			// Katsotaan onko Albumi jo olemassa tietokannassa.
			List<Album> items = getAllItemsByArtist(newAlbum.getArtistId());
			for (Album listItem : items) {
				if (listItem.getAlbumTitle().equalsIgnoreCase(newAlbum.getAlbumTitle())
						&& listItem.getArtistId() == newAlbum.getArtistId()) {
					return false;
				}
			}

			conn = Database.connect();

			stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, newAlbum.getAlbumTitle());
			stmt.setLong(2, newAlbum.getArtistId());
			int count = stmt.executeUpdate();

			result = stmt.getGeneratedKeys();
			result.next();

			// Haetaan generoitu ID ja asetetaan se uudelle oliolle.
			int generatedId = result.getInt(1);
			newAlbum.setAlbumId(generatedId);

			// Tarkistaa onko uusi rivi lisätty ja palauttaa true jos onnistui.
			// Ei ollut mitään hajua miten tämä javassa toimii niin otin täältä referenssiä
			// ->
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
	public boolean removeAlbum(long id) {

		String query = "DELETE FROM Album WHERE AlbumId = ?";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet results = null;

		try {

			conn = Database.connect();
			stmt = conn.prepareStatement(query);

			stmt.setLong(1, id);
			if (stmt.executeUpdate() > 0) return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, results);
		}
		return false;
	}
	
	public boolean removeAllArtistAlbums(long artistId) {
		
		String query = "DELETE FROM Album WHERE ArtistId = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet results = null;

		try {

			conn = Database.connect();
			stmt = conn.prepareStatement(query);

			stmt.setLong(1, artistId);
			if (stmt.executeUpdate() > 0) return true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, results);
		}
		
		return false;
	}
	
	// tätä metodia en ehtinyt implementtaamaan
	@Override
	public List<Album> searchAlbum(String keyword) {

		String query = "SELECT AlbumId, Title, ArtistId FROM Album WHERE Title LIKE ? ORDER BY Title ASC";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet results = null;

		List<Album> items = new ArrayList<Album>();

		try {

			conn = Database.connect();
			stmt = conn.prepareStatement(query);

			stmt.setString(1, "%" + keyword + "%");
			results = stmt.executeQuery();

			while (results.next()) {
				long albumId = results.getLong("AlbumId");
				String albumTitle = results.getString("Title");
				long artistId = results.getLong("ArtistId");
				Album newAlbum = new Album(albumId, albumTitle, artistId);
				items.add(newAlbum);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, results);
		}

		return items;
	}
}
