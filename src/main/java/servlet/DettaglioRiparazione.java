package servlet;

import dao.RiparazioneDAO;
import dao.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Riparazione;
import pojo.Utente;

import java.io.IOException;

@WebServlet(value = "/dettaglio-riparazione")
public class DettaglioRiparazione extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req,resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		final String dettaglioLavoroPage = "dettagliolavoro.jsp";
		final String errorPage = "/error.jsp";
		final RequestDispatcher dettaglioDispatcher = req.getRequestDispatcher(dettaglioLavoroPage);
		final RequestDispatcher errorDispatcher = req.getRequestDispatcher(errorPage);
		
		final int id = Integer.parseInt(req.getParameter("id"));
		final Riparazione r = RiparazioneDAO.doRetrieveByID(id);
		Utente assegnato;
		if (r == null)
		{
			req.setAttribute("errore", "Errore nel recupero della riparazione dal database.");
			errorDispatcher.forward(req,resp);
		}
		else
		{
			req.setAttribute("riparazione",r);
			
			if ((assegnato = UtenteDAO.doRetrieveByID(r.getAssegnato())) != null)
			{
				req.setAttribute("assegnato",assegnato);
			}
			
			dettaglioDispatcher.forward(req,resp);
		}
	}
}

