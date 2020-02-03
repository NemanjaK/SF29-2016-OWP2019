package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmoviDAO;
import dao.KorisnikDAO;
import model.Film;
import model.Korisnik;

@SuppressWarnings("serial")
public class FilmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ulogovanKorisnickoIme = (String) request.getSession().getAttribute("ulogovanKorisnickoIme");

		try {
			Korisnik ulogovanKorisnik = KorisnikDAO.getOne(ulogovanKorisnickoIme);
	
			
			int id = Integer.parseInt(request.getParameter("id"));
			Film film = FilmoviDAO.getOne(id);
			
			request.setAttribute("ulogovanKorisnikRole", ulogovanKorisnik);
			request.setAttribute("film", film);
			request.getRequestDispatcher("./Film.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}		
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
