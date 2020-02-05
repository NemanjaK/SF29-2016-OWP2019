package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KorisnikDAO;
import model.Korisnik;
import model.Korisnik.Role;

@SuppressWarnings("serial")
public class KorisnikServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			
		String korisnickoIme = request.getParameter("korisnickoIme");
		
		Korisnik korisnik = KorisnikDAO.getOne(korisnickoIme);
		
		request.setAttribute("ulogovanDatumRegistracije", ulogovanKorisnik.getDatumRegistracije());	
		request.setAttribute("ulogovanKorisnikRole", ulogovanKorisnik);	
		request.setAttribute("korisnik", korisnik);
		request.getRequestDispatcher("Korisnik.jsp").forward(request, response);
		} catch(Exception e) {
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
	
				String lozinka = request.getParameter("lozinka");
				if ("".equals(lozinka))
					throw new Exception("Lozinka je prazna!");

				String ponovljenaLozinka = request.getParameter("ponovljenaLozinka");
				if (!lozinka.equals(ponovljenaLozinka))
					throw new Exception("Lozinke se ne podudaraju!");
				
				korisnik.setLozinka(lozinka);

				KorisnikDAO.updateKorisnikaPass(korisnik);

				break;
			}
			}

		}  catch (Exception ex) {
			String message = ex.getMessage();
			if (message == null) {
				message = "Nepredvidjena greska!";
				ex.printStackTrace();
			}

			request.setAttribute("message", message);
			request.getRequestDispatcher("./Message.jsp").forward(request, response);
		}
		response.sendRedirect("Login.html");

	}
	

}
