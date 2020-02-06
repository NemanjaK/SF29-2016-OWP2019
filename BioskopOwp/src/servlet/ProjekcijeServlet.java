package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmoviDAO;
import dao.KartaDAO;
import dao.KorisnikDAO;
import dao.ProjekcijeDAO;
import dao.SalaDAO;
import dao.tipProjekcijeDAO;
import model.Film;
import model.Karta;
import model.Korisnik;
import model.Projekcija;
import model.Sala;
import model.TipProjekcije;

@SuppressWarnings("serial")
public class ProjekcijeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ulogovanKorisnickoIme = (String) request.getSession().getAttribute("ulogovanKorisnickoIme");
		try {

			Korisnik ulogovanKorisnik = KorisnikDAO.getOne(ulogovanKorisnickoIme);

			double minCenaKarte = 0.0;
			try {
				String minCenaKarteFilter = request.getParameter("minCenaKarteFilter");
				minCenaKarte = Double.parseDouble(minCenaKarteFilter);
				minCenaKarte = (minCenaKarte >= 0.0 ? minCenaKarte : 0.0);
			} catch (Exception ex) {
			}

			double maxCenaKarte = Double.MAX_VALUE;
			try {
				String maxCenaKarteFilter = request.getParameter("maxCenaKarteFilter");
				maxCenaKarte = Double.parseDouble(maxCenaKarteFilter);
				maxCenaKarte = (maxCenaKarte >= 0.0 ? maxCenaKarte : Double.MAX_VALUE);
			} catch (Exception ex) {
			}
			
			String nazivFilm = request.getParameter("nazivFilmaFilter");
			nazivFilm = (nazivFilm != null ? nazivFilm : "");

			
			String tipProjekcije = request.getParameter("tipProjkecijeFilter");
			tipProjekcije = (tipProjekcije != null ? tipProjekcije : "");


			String sala = request.getParameter("salaFilter");
			sala = (sala != null ? sala : "");
			
			String sort = request.getParameter("sort");
			sort = (sort != null ? sort : "");
			System.out.println("dodje li");
			
//			
//			 String vremeOdparam = request.getParameter("vremeOd"); DateTimeFormatter
//			 formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
//			 vremeOdparam  = (vremeOdparam != null ? vremeOdparam: ""); 
//			 Date vremeOd = (Date) formatter.parse(vremeOdparam); 
			List<Karta> karte = KartaDAO.getIzvesajKarti();
			List<Projekcija> projekcije = ProjekcijeDAO.getAll(minCenaKarte, maxCenaKarte, nazivFilm, tipProjekcije,
					sala, sort);
			
			request.setAttribute("ulogovanKorisnikRole", ulogovanKorisnik);
			
			request.setAttribute("karte", karte);
			request.setAttribute("projekcije", projekcije);
			request.getRequestDispatcher("Projekcije.jsp").forward(request, response);

		} catch (Exception ex) {
			System.out.print("greska");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			/*
			 * String action = request.getParameter("action"); switch (action) { case "add":
			 * {
			 * 
			 * String ulogovanKorisnickoIme = (String)
			 * request.getSession().getAttribute("ulogovanKorisnickoIme"); Korisnik
			 * ulogovanKorisnik = KorisnikDAO.getOne(ulogovanKorisnickoIme);
			 * 
			 * int sala = Integer.parseInt(request.getParameter("sala")); Sala sl = new
			 * Sala(sala);
			 * 
			 * String dateString = request.getParameter("date"); String timeString =
			 * request.getParameter("time"); String datetimeString = dateString + " " +
			 * timeString; System.out.println(datetimeString); Timestamp datetime = new
			 * Timestamp(ProjekcijeDAO.DATETIME_FORMAT.parse(datetimeString).getTime());
			 * 
			 * String cenaKarte = request.getParameter("cenaKarte"); double cena =
			 * Double.parseDouble(cenaKarte);
			 * 
			 * 
			 * // String korisnickoIme = request.getParameter("korisnickoIme"); Korisnik //
			 * korisnik = new Korisnik(korisnickoIme); // int film =
			 * Integer.parseInt(request.getParameter("film")); Film f = new Film(film);
			 * 
			 * int sad = Integer.parseInt(request.getParameter("tipProjekcije"));
			 * TipProjekcije tp = new TipProjekcije(sad);
			 * 
			 * Projekcija p = new Projekcija(tp, sl, datetime, cena, ulogovanKorisnik,f);
			 * ProjekcijeDAO.add(p);
			 * 
			 * break; } }
			 * 
			 */} catch (Exception ex) {
			ex.printStackTrace();
		}
		response.sendRedirect("./ProjekcijeServlet");


	}
}
