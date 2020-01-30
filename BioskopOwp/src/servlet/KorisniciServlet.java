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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Korisnik> korisnici;
		try {
			korisnici = KorisnikDAO.getAll();
			
			request.setAttribute("korisnici", korisnici);

			request.getRequestDispatcher("Korisnici.jsp").forward(request, response);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {

			String action = request.getParameter("action");
			switch (action) {
			case "update": {
				
				String korisnickoIme = request.getParameter("korisnickoIme");
				Korisnik korisnik = KorisnikDAO.getOne(korisnickoIme);
				
				String updatedKosrisnickoIme = request.getParameter("newKorisnickoIme");
								
				String role = request.getParameter("role");			
				Role newRole = Role.valueOf(role);
				
				korisnik.setRole(newRole);
				
				KorisnikDAO.updateKorisnika(korisnik, updatedKosrisnickoIme);
			
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
