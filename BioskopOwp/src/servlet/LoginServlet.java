package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KorisnikDAO;
import model.Korisnik;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String korisnickoIme = request.getParameter("korisnickoIme");
		String lozinka = request.getParameter("lozinka");
		
		try{
			Korisnik korisnik = KorisnikDAO.get(korisnickoIme, lozinka);
			if(korisnik == null) {
				response.sendRedirect("./Login.html");
				return;
			}
			
			request.getSession().setAttribute("ulogovanKorisnickoIme", korisnik.getKorisnickoIme());
			//request.getSession().setAttribute("ulogovanDatumRegistracije", korisnik.getDatumRegistracije());
			request.getSession().setAttribute("ulogovanRole", korisnik.getRole());

			response.sendRedirect("./ProjekcijeServlet");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
