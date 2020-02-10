package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmoviDAO;
import dao.KartaDAO;
import dao.KorisnikDAO;
import dao.ProjekcijeDAO;
import model.Karta;
import model.Korisnik;
import model.Korisnik.Role;

@SuppressWarnings("serial")
public class KartaServlet extends HttpServlet {

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

			int id = Integer.parseInt(request.getParameter("id"));
			Karta karta = KartaDAO.getOne(id);

			request.setAttribute("karta", karta);
			request.getRequestDispatcher("Karta.jsp").forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
			
			String action = request.getParameter("action");
			switch (action) {
			case "delete": {
				int id = Integer.parseInt(request.getParameter("id"));
				KartaDAO.delete(id);
			}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		response.sendRedirect("./ProjekcijeServlet");

	}
}
