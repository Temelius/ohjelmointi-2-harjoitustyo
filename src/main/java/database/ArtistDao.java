package database;

import java.util.List;

import model.Artist;

/**
 * ArtistDao on rajapinta, joka määrittelee, mitä operaatioita
 * DAO-luokan on toteutattava.
 */

public interface ArtistDao {
    public List<Artist> getAllItems();

    public Artist getArtist(long id);

    public boolean addArtist(Artist newArtist);

    public boolean removeArtist(Artist artist);
    
    public List<Artist> searchArtist(String keyword);
}
