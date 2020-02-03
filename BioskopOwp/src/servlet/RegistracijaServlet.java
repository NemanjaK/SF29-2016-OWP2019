package servlet;

import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DateConverter;
import dao.KorisnikDAO;
import model.Korisnik;
import model.Korisnik.Role;

@SuppressWarnings("serial")
public class RegistracijaServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String korisnickoIme = request.getParameter("korisnickoIme");

			if (KorisnikDAO.getOne(korisnickoIme) != null)
				throw new Exception("Korisnicko ime vec postoji!");
			if ("".equals(korisnickoIme))
				throw new Exception("Korisnicko ime je prazno!");

			String lozinka = request.getParameter("lozinka");
			if ("".equals(lozinka))
				throw new Exception("Lozinka je prazna!");

			String ponovljenaLozinka = request.getParameter("ponovljenaLozinka");
			if (!lozinka.equals(ponovljenaLozinka))
				throw new Exception("Lozinke se ne podudaraju!");

			/*
			 * Date now = Date(); now.toString(); SimpleDateFormat formatter = new
			 * SimpleDateFormat("yyyy-MM-dd"); System.out.println(formatter); Date datetime
			 * = new Date(formatter.(now).getTime());
			 */
			//Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, datetime, Role.KORISNIK);
			//KorisnikDAO.add(korisnik);

			response.sendRedirect("LoginServlet");
		} catch (Exception ex) {
			String message = ex.getMessage();
			if (message == null) {
				message = "Nepredvidjena greska!";
				ex.printStackTrace();
			}

			request.setAttribute("message", message);
			request.getRequestDispatcher("./Message.jsp").forward(request, response);
		}

	}

	private Date Date() {
		// TODO Auto-generated method stub
		return null;
	}
}
