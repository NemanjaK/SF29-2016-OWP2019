package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			
			//String sort = request.getParameter("sort");

			List<Projekcija> projekcije = ProjekcijeDAO.getAll(minCenaKarte, maxCenaKarte, nazivFilm, tipProjekcije, sala);

			request.setAttribute("ulogovanKorisnikRole", ulogovanKorisnik);

			request.setAttribute("projekcije", projekcije);

			request.getRequestDispatcher("Projekcije.jsp").forward(request, response);

		} catch (Exception ex) {
			System.out.print("greska");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String action = request.getParameter("action");
			switch (action) {
			case "add": {

				String tProjekcije = request.getParameter("tipProjekcije");
				int i = Integer.parseInt(tProjekcije);
				TipProjekcije tp = tipProjekcijeDAO.getOne(i);

				String sala = request.getParameter("sala");
				int s = Integer.parseInt(sala);
				Sala sp = SalaDAO.getOne(s);

				String datumVreme = request.getParameter("datumVreme");
				Date vreme = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(datumVreme);

				String cenaKarte = request.getParameter("cenaKarte");
				double cena = Double.parseDouble(cenaKarte);

				String admin = request.getParameter("admin");
				Korisnik k = KorisnikDAO.getOneProj(admin);

				String film = request.getParameter("film");
				int f = Integer.parseInt(film);
				Film filmP = FilmoviDAO.getOneProj(f);

				int id = ProjekcijeDAO.getProjekcijaId();

				Projekcija p = new Projekcija(id, tp, sp, vreme, cena, k, filmP);
				System.out.println("XXXX");
				ProjekcijeDAO.add(p);
				System.out.println("YYYYY");

				break;
			}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		response.sendRedirect("./ProjekcijeServlet");


	}
}
