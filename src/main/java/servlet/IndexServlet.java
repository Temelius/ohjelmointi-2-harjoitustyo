package servlet;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.JDBCArtistDao;
import database.ArtistDao;
import model.Artist;

@WebServlet("")
public class IndexServlet extends HttpServlet{
	
	private ArtistDao dao = new JDBCArtistDao();
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Artist> artists = dao.getAllItems();

        // pass the time string to the JSP page as an attribute
        req.setAttribute("items", artists);

        // forward the request to the index.jsp page
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
