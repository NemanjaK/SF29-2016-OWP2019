package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KartaDAO;
import dao.KorisnikDAO;
import dao.ProjekcijeDAO;
import dao.SedisteDAO;
import model.Karta;
import model.Korisnik;
import model.Projekcija;
import model.Sediste;

@SuppressWarnings("serial")
public class KarteServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			Projekcija projekcija = ProjekcijeDAO.getOne(id);
			request.setAttribute("projekcija", projekcija);
			request.getRequestDispatcher("KupiKartu.jsp").forward(request, response);

			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			switch (action) {
			case "add": {

				String ulogovanKorisnickoIme = (String) request.getSession().getAttribute("ulogovanKorisnickoIme");
				Korisnik ulogovanKorisnik = KorisnikDAO.getOne(ulogovanKorisnickoIme);
				
				int id = Integer.parseInt(request.getParameter("id"));
				Projekcija projekcija = ProjekcijeDAO.getOne(id);
				
				int sediste = Integer.parseInt(request.getParameter("sediste"));
				Sediste sedisteS = SedisteDAO.getOne(sediste);
				
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String formatDateTime = now.format(formatter);
				System.out.println(formatter);
				Timestamp datetime = new Timestamp(KartaDAO.DATETIME_FORMAT.parse(formatDateTime).getTime());
				
				Karta k = new Karta(projekcija, sedisteS, datetime, ulogovanKorisnik);
				KartaDAO.add(k);

				break;
			}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		response.sendRedirect("./ProjekcijeServlet");

	}
	}


