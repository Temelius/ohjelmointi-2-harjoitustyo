package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ArtistDao;
import database.JDBCArtistDao;
import database.AlbumDao;
import database.JDBCAlbumDao;
import model.Artist;

@WebServlet("/artists")
public class ArtistServlet extends HttpServlet{
	
	private ArtistDao artistDao = new JDBCArtistDao();
	private AlbumDao albumDao = new JDBCAlbumDao();
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Artist> artists = artistDao.getAllItems();

        // pass the time string to the JSP page as an attribute
        req.setAttribute("items", artists);

        // forward the request to the index.jsp page
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	long id = Long.parseLong(req.getParameter("id"));
    	
    	Artist artist = artistDao.getArtist(id);
    	// Jos artisti on olemassa, poistetaan Artisti ja kaikki sen Artistin albumit.
    	if (artist != null) {
    		artistDao.removeArtist(artist);
    		albumDao.removeAllArtistAlbums(artist.getArtistId());
    	}
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String name = req.getParameter("name");
    	
    	Artist newArtist = new Artist(name);
    	
    	artistDao.addArtist(newArtist);
    	
    	resp.sendRedirect("/artists");
    }
    
}
