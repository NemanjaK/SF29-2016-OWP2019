package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KorisnikDAO;
import model.Korisnik;

@SuppressWarnings("serial")
public class KorisnikServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String korisnickoIme = request.getParameter("korisnickoIme");
		
		Korisnik korisnik = KorisnikDAO.getOne(korisnickoIme);
		
		request.setAttribute("korisnik", korisnik);
		request.getRequestDispatcher("Korisnik.jsp").forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
