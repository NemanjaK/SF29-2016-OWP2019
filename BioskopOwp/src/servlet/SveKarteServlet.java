package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KartaDAO;
import dao.KorisnikDAO;
import model.Karta;
import model.Korisnik;
import model.Korisnik.Role;

@SuppressWarnings("serial")
public class SveKarteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ulogovanKorisnickoIme = (String) request.getSession().getAttribute("ulogovanKorisnickoIme");

		if (ulogovanKorisnickoIme == null) {
			response.sendRedirect("./Login.html");
			return;
		}
		try {
			Korisnik ulogovanKorisnik = KorisnikDAO.getOne(ulogovanKorisnickoIme);
			if (ulogovanKorisnik == null) {
				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
				return;
			}
			if (ulogovanKorisnik.getRole() == Role.KORISNIK) {
				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
				return;
			}
			List<Karta> karte = KartaDAO.getAll();

			request.setAttribute("karte", karte);
			request.getRequestDispatcher("Karte.jsp").forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
