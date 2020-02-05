package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmoviDAO;
import dao.KorisnikDAO;
import model.Film;
import model.Korisnik;
import model.Korisnik.Role;

@SuppressWarnings("serial")
public class KorisniciServlet extends HttpServlet {

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
			
			List<Korisnik> korisnici = KorisnikDAO.getAll();

			request.setAttribute("korisnici", korisnici);
			request.setAttribute("ulogovanKorisnikRole", ulogovanKorisnik);	
			request.getRequestDispatcher("Korisnici.jsp").forward(request, response);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String action = request.getParameter("action");
			switch (action) {
			case "update": {

				String korisnickoIme = request.getParameter("korisnickoIme");
				Korisnik korisnik = KorisnikDAO.getOne(korisnickoIme);

				String updatedKosrisnickoIme = request.getParameter("newKorisnickoIme");

				String role = request.getParameter("role");
				Role newRole = Role.valueOf(role);
				
				String lozinka = request.getParameter("lozinka");
				if ("".equals(lozinka))
					throw new Exception("Lozinka je prazna!");

				String ponovljenaLozinka = request.getParameter("ponovljenaLozinka");
				if (!lozinka.equals(ponovljenaLozinka))
					throw new Exception("Lozinke se ne podudaraju!");			
				
				korisnik.setLozinka(lozinka);
				korisnik.setRole(newRole);

				KorisnikDAO.updateKorisnika(korisnik, updatedKosrisnickoIme);

				break;
			}
			case "delete": {
				String korisnickoIme = request.getParameter("korisnickoIme");
				KorisnikDAO.delete(korisnickoIme);
			}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		response.sendRedirect("./KorisniciServlet");
	}

}
