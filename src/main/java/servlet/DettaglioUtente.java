package servlet;

import dao.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Utente;

import java.io.IOException;

@WebServlet(value = "/dettaglio-utente")
public class DettaglioUtente extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req,resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		final String errorPage = "/error.jsp";
		final String dettaglioUtente = "/dettaglioutente.jsp";
		
		final RequestDispatcher errorDispatcher = req.getRequestDispatcher(errorPage);
		final RequestDispatcher dettaglioUtenteDispatcher = req.getRequestDispatcher(dettaglioUtente);
		
		
		final int id = Integer.parseInt(req.getParameter("idUtente"));
		final Utente u = UtenteDAO.doRetrieveByID(id);
		if (u == null)
		{
			req.setAttribute("error","L'utente è nullo.");
			errorDispatcher.forward(req,resp);
		}
		else
		{
			req.setAttribute("utente",u);
			dettaglioUtenteDispatcher.forward(req,resp);
		}
		
	}
}
