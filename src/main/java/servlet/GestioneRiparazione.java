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
import pojo.RiparazioneConclusa;

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
		final RequestDispatcher dettaglioLavoro = req.getRequestDispatcher("dettagliolavoro.jsp");
		
		final int id, costo, status, assegnato, idUtente;
		final String marca, modello, nota, tipo, mailCliente;
		
		if ((tipo = req.getParameter("tipo")) != null)
		{
			id = Integer.parseInt(req.getParameter("id"));
			
			switch (tipo)
			{
				case "visualizza":
				{
					Riparazione r = RiparazioneDAO.doRetrieveByID(id);
					req.setAttribute("riparazione", r);
					dettaglioLavoro.forward(req, resp);
					break;
				}
				case "modifica":
					marca = req.getParameter("marca");
					modello = req.getParameter("modello");
					status = Integer.parseInt(req.getParameter("status"));
					costo = Integer.parseInt(req.getParameter("costo"));
					nota = req.getParameter("nota");
					mailCliente = req.getParameter("mailCliente");
					assegnato = Integer.parseInt(req.getParameter("assegnato"));
					RiparazioneDAO.doEdit(id, marca, modello, status, nota, costo, assegnato, mailCliente);
					req.getServletContext().setAttribute("listaRiparazioni", RiparazioneDAO.doRetrieveAll());
					req.setAttribute("assegnato", UtenteDAO.doRetrieveByID(assegnato));
					Riparazione riparazione = RiparazioneDAO.doRetrieveByID(id);
					req.setAttribute("riparazione",riparazione);
					
					// 0 : in riparazione, 1 : conclusa, 2: ritirato dal cliente
					if (status > 0) // riparazione conclusa
					{
						new RiparazioneConclusa(riparazione);
					}
					
					dettaglioLavoro.forward(req, resp);
					break;
				case "assegna":
				{
					final Riparazione r = RiparazioneDAO.doRetrieveByID(id);
					idUtente = Integer.parseInt(req.getParameter("idUtente"));
					marca = r.getMarca();
					modello = r.getModello();
					status = Integer.parseInt(req.getParameter("status"));
					costo = r.getCosto();
					nota = r.getNota();
					mailCliente = r.getMailCliente();
					RiparazioneDAO.doEdit(id, marca, modello, status, nota, costo, idUtente, mailCliente);
					req.getServletContext().setAttribute("listaRiparazioni", RiparazioneDAO.doRetrieveAll());
					
					lavori.forward(req, resp);
					break;
				}
				case "crea":
				{
					marca = req.getParameter("marca");
					modello = req.getParameter("modello");
					status = Integer.parseInt(req.getParameter("status"));
					costo = Integer.parseInt(req.getParameter("costo"));
					nota = req.getParameter("nota");
					mailCliente = req.getParameter("mailCliente");
					
					if (marca.isBlank() || modello.isBlank() || mailCliente.isBlank())
					{
						req.setAttribute("errore","I campi marca, modello e mail non possono essere vuoti");
						error.forward(req,resp);
					}
					else
					{
						RiparazioneDAO.doSave(new Riparazione(marca, modello, status, costo, nota, mailCliente));
						req.getServletContext().setAttribute("listaRiparazioni", RiparazioneDAO.doRetrieveAll());
						
						lavori.forward(req,resp);
					}
					break;
				}
			}
			
		}else
		{
			req.setAttribute("errore","errore nella servlet");
			error.forward(req,resp);
		}
		
		
		
		
		
		
		
		
		
		
	}
}
