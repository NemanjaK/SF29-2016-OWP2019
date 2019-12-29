package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmoviDAO;
import dao.KorisnikDAO;
import model.Film;
import model.Korisnik;

@SuppressWarnings("serial")
public class KorisniciServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Korisnik> korisnici = KorisnikDAO.getAll();
		
		request.setAttribute("korisnici", korisnici);
		request.getRequestDispatcher("Korisnici.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			String action = request.getParameter("action");
			switch (action) {
			case "update": {

				/*String korisnickoIme = request.getParameter("korisnickoIme");
				Korisnik korisnik = KorisnikDAO.getOne(korisnickoIme);

				String lozinka = request.getParameter("lozinka");	*/
				break;
			}
			case "delete": {
				String korisnickoIme = request.getParameter("korisnickoIme");
				KorisnikDAO.delete(korisnickoIme);
			}
			}

		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		response.sendRedirect("./KorisniciServlet");
	}

}
