package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AlbumDao;
import database.ArtistDao;
import database.JDBCAlbumDao;
import database.JDBCArtistDao;
import model.Artist;

@WebServlet("/artists/search")
public class SearchArtistServlet extends HttpServlet {
	private ArtistDao artistDao = new JDBCArtistDao();
	private AlbumDao albumDao = new JDBCAlbumDao();
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String keyword = req.getParameter("q");
    	
    	List<Artist> items = artistDao.searchArtist(keyword);
    	
        req.setAttribute("items", items);

        // forward the request to the index.jsp page
        req.getRequestDispatcher("/WEB-INF/searchResults.jsp").forward(req, resp);
    	
    }
}
