package database;

import java.util.List;

import model.Album;

public interface AlbumDao {
    public List<Album> getAllItemsByArtist(long id);

    public boolean addAlbum(Album newAlbum);

    public boolean removeAlbum(long id);
    
    public List<Album> searchAlbum(String keyword);
}
