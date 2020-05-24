package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.AlbumDao;
import database.JDBCAlbumDao;
import model.Album;

@WebServlet("/albums")
public class AlbumServlet extends HttpServlet{
	private AlbumDao dao = new JDBCAlbumDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long artistId = Long.parseLong(req.getParameter("ArtistId"));
		
		List<Album> allAlbums = dao.getAllItemsByArtist(artistId);
		
		req.setAttribute("items", allAlbums);
		req.setAttribute("artistId", artistId);
		
		req.getRequestDispatcher("/WEB-INF/album.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long artistId = Long.parseLong(req.getParameter("artistId"));
		
		String name = req.getParameter("name");
		
		Album newAlbum = new Album(name, artistId);
		
		dao.addAlbum(newAlbum);
		
		resp.sendRedirect("/albums?ArtistId="+artistId);
		
	}
}
