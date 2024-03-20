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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req,resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		final String errorPage = "/error.jsp";
		final String workSearchPage = "/risultati-ricerca-riparazione.jsp";
		final RequestDispatcher searchDispatcher = req.getRequestDispatcher(workSearchPage);
		final RequestDispatcher errorDispatcher = req.getRequestDispatcher(errorPage);
		
		final String workID = req.getParameter("search");
		//resp.setContentType("text/html");
		
		if (workID == null || workID.isBlank())
		{
			req.setAttribute("errore", "l'ID del lavoro non può essere vuoto.");
			req.setAttribute("errore", "L'ID cercato non corrisponde <br> ad alcuna riparazione.");
			errorDispatcher.forward(req,resp);
		}
		else {
			req.setAttribute("riparazioni", RiparazioneDAO.doRetrieveCustomerRiparazioni(workID));

			/*
			final ArrayList<Riparazione> listaRiparazioni = (ArrayList<Riparazione>) getServletContext().getAttribute("listaRiparazioni");
			
			final int id = Integer.parseInt(workID);
			for (Riparazione r : listaRiparazioni)
			{
				if (r.getId() == id)
				{
					req.setAttribute("riparazione", r);

					break;
				}
			}*/
			searchDispatcher.forward(req, resp);
		}
		}
		
	}

