package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AlbumDao;
import database.JDBCAlbumDao;
import model.Album;

@WebServlet("/albums")
public class AlbumServlet extends HttpServlet{
	private AlbumDao dao = new JDBCAlbumDao();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long artistId = Long.parseLong(req.getParameter("ArtistId"));
		
		List<Album> allAlbums = dao.getAllItemsByArtist(artistId);
		
		req.setAttribute("items", allAlbums);
		
		req.getRequestDispatcher("/WEB-INF/album.jsp").forward(req, resp);
	}
}
