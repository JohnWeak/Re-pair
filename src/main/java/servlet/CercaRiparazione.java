package servlet;

import dao.RiparazioneDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Riparazione;

import java.io.IOException;
import java.util.ArrayList;

/**@author Giovanni Liguori*/
@WebServlet(value = "/cerca-riparazione")
public class CercaRiparazione extends HttpServlet
{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req,resp);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		final String errorPage = "/error.jsp";
		final String workSearchPage = "/risultati-ricerca-riparazione.jsp";
		final RequestDispatcher searchDispatcher = req.getRequestDispatcher(workSearchPage);
		final RequestDispatcher errorDispatcher = req.getRequestDispatcher(errorPage);
		
		final String mailCliente = req.getParameter("search");
		
		if (mailCliente == null || mailCliente.isBlank())
		{
			req.setAttribute("errore", "Il campo mail <br> non può essere vuoto.");
			errorDispatcher.forward(req,resp);
		}
		else
		{
			final ArrayList<Riparazione> list = RiparazioneDAO.doRetrieveCustomerRiparazioni(mailCliente);
			req.setAttribute("riparazioni", list);
			
			searchDispatcher.forward(req, resp);
		}
	}
		
}

