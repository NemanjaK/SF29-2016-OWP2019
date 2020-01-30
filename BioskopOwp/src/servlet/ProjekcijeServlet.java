package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmoviDAO;
import dao.KorisnikDAO;
import dao.ProjekcijeDAO;
import model.Film;
import model.Korisnik;
import model.Projekcija;

@SuppressWarnings("serial")
public class ProjekcijeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String ulogovanKorisnickoIme = (String)
		request.getSession().getAttribute("ulogovanKorisnickoIme");
		try {

			Korisnik ulogovanKorisnik = KorisnikDAO.getOne(ulogovanKorisnickoIme); 

			
			double minCenaKarte = 0.0;
			try {
				String minCenaKarteFilter = request.getParameter("minCenaKarteFilter");
				minCenaKarte = Double.parseDouble(minCenaKarteFilter);
				minCenaKarte = (minCenaKarte >= 0.0? minCenaKarte: 0.0);
			} catch (Exception ex) {
			}
			
			double maxCenaKarte = Double.MAX_VALUE;
			try {
				String maxCenaKarteFilter = request.getParameter("maxCenaKarteFilter");
				maxCenaKarte = Double.parseDouble(maxCenaKarteFilter);
				maxCenaKarte = (maxCenaKarte >= 0.0? maxCenaKarte: Double.MAX_VALUE);
			} catch (Exception ex) {}
			
			
			System.out.print("...preGetAll");
			//OVO PRODJE
			List<Projekcija> projekcije = ProjekcijeDAO.getAll();
			System.out.print("...posleGetAll");
			//OVO NE PRODJE

			request.setAttribute("ulogovanKorisnikRole", ulogovanKorisnik);

			request.setAttribute("projekcije", projekcije);

			request.getRequestDispatcher("Projekcije.jsp").forward(request, response);

		} catch (Exception ex) {
			System.out.print("greska");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
