package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmoviDAO;
import dao.KorisnikDAO;
import model.Film;
import model.Korisnik;

@SuppressWarnings("serial")
public class FilmoviServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ulogovanKorisnickoIme = (String) request.getSession().getAttribute("ulogovanKorisnickoIme");

		try {

			Korisnik ulogovanKorisnik = KorisnikDAO.getOne(ulogovanKorisnickoIme);

			String naziv = request.getParameter("nazivFilter");
			naziv = (naziv != null ? naziv : "");

			int minTrajanje = 0;
			try {
				String minTrajanjeFilter = request.getParameter("minTrajanjeFilter");
				minTrajanje = Integer.parseInt(minTrajanjeFilter);
				minTrajanje = (minTrajanje >= 0 ? minTrajanje : 0);
			} catch (Exception ex) {
			}
			int maxTrajanje = Integer.MAX_VALUE;
			try {
				String maxTrajanjeFilter = request.getParameter("maxTrajanjeFilter");
				maxTrajanje = Integer.parseInt(maxTrajanjeFilter);
				maxTrajanje = (maxTrajanje >= 0 ? maxTrajanje : Integer.MAX_VALUE);
			} catch (Exception ex) {
			}

			int minGodinaProizvodnje = 0;
			try {
				String minGodinaProizvodnjeFilter = request.getParameter("minGodinaProizvodnjeFilter");
				minGodinaProizvodnje = Integer.parseInt(minGodinaProizvodnjeFilter);
				minGodinaProizvodnje = (minGodinaProizvodnje >= 0 ? minGodinaProizvodnje : 0);
			} catch (Exception ex) {
			}
			int maxGodinProizvodnje = Integer.MAX_VALUE;
			try {
				String maxGodinProizvodnjeFilter = request.getParameter("maxGodinProizvodnjeFilter");
				maxGodinProizvodnje = Integer.parseInt(maxGodinProizvodnjeFilter);
				maxGodinProizvodnje = (maxGodinProizvodnje >= 0 ? maxGodinProizvodnje : Integer.MAX_VALUE);
			} catch (Exception ex) {
			}

			String zanrovi = request.getParameter("zanroviFilter");
			zanrovi = (zanrovi != null ? zanrovi : "");

			String distributer = request.getParameter("distributerFilter");
			distributer = (distributer != null ? distributer : "");

			String zemljaPorekla = request.getParameter("zemljaPoreklaFilter");
			zemljaPorekla = (zemljaPorekla != null ? zemljaPorekla : "");

			
			String sort = request.getParameter("sort");
			sort = (sort != null ? sort : "");

			
			List<Film> filmovi = FilmoviDAO.getAll(naziv, zanrovi, distributer, zemljaPorekla, minTrajanje, maxTrajanje,
					minGodinaProizvodnje, maxGodinProizvodnje, sort);

		
			request.setAttribute("ulogovanKorisnikRole", ulogovanKorisnik);
			request.setAttribute("filmovi", filmovi);
			request.getRequestDispatcher("Filmovi.jsp").forward(request, response);
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

				String naziv = request.getParameter("naziv");

				String reziser = request.getParameter("reziser");

				String glumci = request.getParameter("glumci");

				String zanrovi = request.getParameter("zanrovi");

				String distributer = request.getParameter("distributer");

				Integer godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));

				String opis = request.getParameter("opis");

				int trajanje = Integer.parseInt(request.getParameter("trajanje"));

				String zemljaPorekla = request.getParameter("zemljaPorekla");

				int obrisan = 0;
				
				int id = FilmoviDAO.getFilmId();

				Film film = new Film(id, naziv, reziser, glumci, zanrovi, distributer, godinaProizvodnje, opis,
						trajanje, zemljaPorekla,obrisan);
				FilmoviDAO.add(film);

				break;
			}
			case "update": {

				int id = Integer.parseInt(request.getParameter("id"));
				Film film = FilmoviDAO.getOne(id);

				String naziv = request.getParameter("naziv");
				naziv = (!"".equals(naziv) ? naziv : film.getNaziv());

				String reziser = request.getParameter("reziser");
				reziser = (!"".equals(reziser) ? reziser : film.getReziser());

				String glumci = request.getParameter("glumci");
				glumci = (!"".equals(glumci) ? glumci : film.getGlumci());

				String zanrovi = request.getParameter("zanrovi");
				zanrovi = (!"".equals(zanrovi) ? zanrovi : film.getZanrovi());

				String distributer = request.getParameter("distributer");
				distributer = (!"".equals(distributer) ? distributer : film.getDistributer());

				int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
				godinaProizvodnje = (godinaProizvodnje > 0 ? godinaProizvodnje : film.getGodinaProizvodnje());

				String opis = request.getParameter("opis");
				opis = (!"".equals(opis) ? opis : film.getOpis());

				int trajanje = Integer.parseInt(request.getParameter("trajanje"));
				trajanje = (trajanje > 0 ? trajanje : film.getTrajanje());

				String zemljaPorekla = request.getParameter("zemljaPorekla");
				zemljaPorekla = (!"".equals(zemljaPorekla) ? zemljaPorekla : film.getZemljaPorekla());

				film.setNaziv(naziv);
				film.setReziser(reziser);
				film.setGlumci(glumci);
				film.setZanrovi(zanrovi);
				film.setDistributer(distributer);
				film.setGodinaProizvodnje(godinaProizvodnje);
				film.setOpis(opis);
				film.setTrajanje(trajanje);
				film.setZemljaPorekla(zemljaPorekla);
				FilmoviDAO.update(film);

				break;
			}
			case "delete": {
				int id = Integer.parseInt(request.getParameter("id"));
				FilmoviDAO.delete(id);
			}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		response.sendRedirect("./FilmoviServlet");

	}

}
