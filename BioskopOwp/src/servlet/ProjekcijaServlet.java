package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KartaDAO;
import dao.KorisnikDAO;
import dao.ProjekcijeDAO;
import model.Karta;
import model.Korisnik;
import model.Projekcija;

@SuppressWarnings("serial")
public class ProjekcijaServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ulogovanKorisnickoIme = (String) request.getSession().getAttribute("ulogovanKorisnickoIme");

		int id = Integer.parseInt(request.getParameter("id"));
		Projekcija projekcija;
		try {
			
			Korisnik ulogovanKorisnik = KorisnikDAO.getOne(ulogovanKorisnickoIme);

			projekcija = ProjekcijeDAO.getOne(id);
			List<Karta> karte = KartaDAO.getKarteByProj(id); 
			
			request.setAttribute("karte", karte);
			request.setAttribute("projekcija", projekcija);
			request.setAttribute("ulogovanKorisnikRole", ulogovanKorisnik);
			request.getRequestDispatcher("Projekcija.jsp").forward(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
