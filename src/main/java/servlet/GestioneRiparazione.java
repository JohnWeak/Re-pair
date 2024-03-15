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

@WebServlet(value = "/gestione-riparazione")
public class GestioneRiparazione extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req,resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		final RequestDispatcher error = req.getRequestDispatcher("error.jsp");
		final RequestDispatcher lavori = req.getRequestDispatcher("lavori.jsp");
		
		final int id, costo;
		final String marca, modello, status, nota;
		
		id = Integer.parseInt(req.getParameter("id"));
		marca = req.getParameter("marca");
		modello = req.getParameter("modello");
		status = req.getParameter("status");
		nota = req.getParameter("nota");
		costo = Integer.parseInt(req.getParameter("costo"));
		
		if(id > 0) // modifica riparazione
		{
			RiparazioneDAO.doEdit(id,marca,modello,status,nota,costo);
			req.getServletContext().setAttribute("listaRiparazioni",RiparazioneDAO.doRetrieveAll());
			req.setAttribute("riparazione", RiparazioneDAO.doRetrieveByID(id));
			
			req.getRequestDispatcher("dettagliolavoro.jsp").forward(req,resp);
		}
		else // crea nuova riparazione
		{
			final String mailCliente = req.getParameter("mailCliente");
			if(marca.isBlank() || modello.isBlank() || mailCliente.isBlank())
			{
				req.setAttribute("errore", "Marca, Modello e Mail Cliente devono essere compilati!");
				error.forward(req,resp);
			}
			else
			{
				RiparazioneDAO.doSave(new Riparazione(marca,modello,status,costo,nota,mailCliente));
				
				req.getServletContext().setAttribute("listaRiparazioni",RiparazioneDAO.doRetrieveAll());
				lavori.forward(req,resp);
			}
			
			
		}
		
		
		
		
	}
}
