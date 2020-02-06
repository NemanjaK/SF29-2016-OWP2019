package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmoviDAO;
import dao.KorisnikDAO;
import dao.ProjekcijeDAO;
import dao.SalaDAO;
import dao.tipProjekcijeDAO;
import model.Film;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.TipProjekcije;
import model.Korisnik.Role;

@SuppressWarnings("serial")
public class AddProjekcijaServlet extends HttpServlet {

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
			}if (ulogovanKorisnik.getRole() == Role.KORISNIK) {
				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
				return;
			}
			
			List<Sala> sale = SalaDAO.getAll();
			List<TipProjekcije> tipovi = tipProjekcijeDAO.getAll();
			List<Film> filmovi = FilmoviDAO.getNaziv();
			
			request.setAttribute("ulogovanKorisnikRole", ulogovanKorisnik);
			request.setAttribute("filmovi", filmovi);
			request.setAttribute("sale", sale);
			request.setAttribute("tipovi", tipovi);
		
		request.getRequestDispatcher("DodajProjekciju.jsp").forward(request, response);
		}catch(Exception ex) {
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
			}	if (ulogovanKorisnik.getRole() == Role.KORISNIK) {
				request.getRequestDispatcher("./LogoutServlet").forward(request, response);
				return;
			}
		
			String action = request.getParameter("action");
			switch (action) {
			case "add": {


				int sala = Integer.parseInt(request.getParameter("sala"));
				Sala sl = new Sala(sala);

				String dateString = request.getParameter("date");
				String timeString = request.getParameter("time");
				String datetimeString = dateString + " " + timeString;
				System.out.println(datetimeString);
				Timestamp datetime = new Timestamp(ProjekcijeDAO.DATETIME_FORMAT.parse(datetimeString).getTime());

				String cenaKarte = request.getParameter("cenaKarte");
				double cena = Double.parseDouble(cenaKarte);

				int film = Integer.parseInt(request.getParameter("film"));
				Film f = new Film(film);

				int sad = Integer.parseInt(request.getParameter("tipProjekcije"));
				TipProjekcije tp = new TipProjekcije(sad);

				Projekcija p = new Projekcija(tp, sl, datetime, cena, ulogovanKorisnik, f);
				ProjekcijeDAO.add(p);

				break;
			}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		response.sendRedirect("./ProjekcijeServlet");

	}
}
