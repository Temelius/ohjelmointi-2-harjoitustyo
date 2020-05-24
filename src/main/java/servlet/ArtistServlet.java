package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCArtistDao;
import database.ArtistDao;
import model.Artist;

@WebServlet("/artists")
public class ArtistServlet extends HttpServlet{
	
	private ArtistDao dao = new JDBCArtistDao();
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Artist> artists = dao.getAllItems();

        // pass the time string to the JSP page as an attribute
        req.setAttribute("items", artists);

        // forward the request to the index.jsp page
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	long id = Long.parseLong(req.getParameter("id"));
    	
    	Artist artist = dao.getArtist(id);
    	if (artist != null) dao.removeArtist(artist);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String name = req.getParameter("name");
    	
    	Artist newArtist = new Artist(name);
    	
    	dao.addArtist(newArtist);
    	
    	resp.sendRedirect("/artists");
    }
    
}
