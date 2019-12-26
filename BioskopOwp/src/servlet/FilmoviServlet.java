package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmoviDAO;
import model.Film;

@SuppressWarnings("serial")
public class FilmoviServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String naziv = request.getParameter("nazivFilter");
		naziv = (naziv != null? naziv: "");
		
		List<Film> filmovi = FilmoviDAO.getAll(naziv);
		
		request.setAttribute("filmovi", filmovi);		
		request.getRequestDispatcher("Filmovi.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String action = request.getParameter("action");
			switch (action) {
			case "add": {
				/*
				 * String naziv = request.getParameter("naziv");
				 * 
				 * String reziser = request.getParameter("reziser");
				 * 
				 * String glumci = request.getParameter("glumci");
				 * 
				 * String zanrovi = request.getParameter("zanrovi");
				 * 
				 * String distributer = request.getParameter("distributer");
				 * 
				 * String godinaProizvodnje = request.getParameter("godinaProizvodnjeF"); int
				 * godinaProizvodnjeF = Integer.parseInt(godinaProizvodnje);
				 * 
				 * String opis = request.getParameter("opis");
				 * 
				 * int trajanje = Integer.parseInt(request.getParameter("trajanje"));
				 * 
				 * String zemljaPorekla = request.getParameter("zemljaPorekla");
				 * 
				 * int id = FilmoviDAO.getFilmId();
				 * 
				 * Film film = new Film(id, naziv, reziser, glumci, zanrovi, distributer,
				 * godinaProizvodnjeF, opis, trajanje, zemljaPorekla); FilmoviDAO.add(film);
				 */
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
				distributer = (!"".equals(distributer) ? naziv : film.getDistributer());

				int godinaProizvodnje = Integer.parseInt(request.getParameter("godinaProizvodnje"));
				godinaProizvodnje = (godinaProizvodnje > 0 ? godinaProizvodnje : film.getGodinaProizvodnje());

				String opis = request.getParameter("opis");
				opis = (!"".equals(opis) ? opis : film.getOpis());

				int trajanje = Integer.parseInt(request.getParameter("trajanje"));
				trajanje = (trajanje > 0 ? trajanje : film.getTrajanje());

				String zemljaPorekla = request.getParameter("zemljaPorekla");
				zemljaPorekla = (!"".equals(zemljaPorekla) ? zemljaPorekla : film.getZemljaPorekla());

				film.setDistributer(distributer);
				film.setGlumci(glumci);
				film.setGodinaProizvodnje(godinaProizvodnje);
				film.setNaziv(naziv);
				film.setOpis(opis);
				film.setReziser(reziser);
				film.setTrajanje(trajanje);
				film.setZanrovi(zanrovi);
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
