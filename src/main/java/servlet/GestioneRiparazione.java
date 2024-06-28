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
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req,resp);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		final RequestDispatcher error = req.getRequestDispatcher("error.jsp");
		final RequestDispatcher lavori = req.getRequestDispatcher("lavori.jsp");
		final RequestDispatcher dettaglioLavoro = req.getRequestDispatcher("dettagliolavoro.jsp");
		
		final int id, costo, status, assegnato, idUtente;
		final String marca, modello, nota, tipo, mailCliente;
		final String campiVuoti = "I campi marca, modello, mail e costo non possono essere vuoti";
		
		
		if (req.getParameter("tipo") != null)
		{
			tipo = req.getParameter("tipo").toLowerCase();
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
					final String regex = "[a-z]{1,10}";
					final String mailRegex = "[a-z0-9]{1,20}@[a-z]{1,10}\\.[a-z]{2,3}";
					
					final String lunghezzaErrata = "Marca/modello non possono essere più di 10 caratteri<br>La mail non può eccedere 33 caratteri.";
					final String formatoErrato = "Il formato di marca, modello o mail cliente non è corretto.";
					final String costoErrato = "Il costo deve essere compreso fra 0 e 999.";
					final String costoNumerico = "Il costo deve essere un valore numerico";
					
					marca = req.getParameter("marca");
					modello = req.getParameter("modello");
					status = Integer.parseInt(req.getParameter("status"));
					mailCliente = req.getParameter("mailCliente");
					nota = req.getParameter("nota");
					
					if (req.getParameter("costo") == null || marca.isBlank() || modello.isBlank() || mailCliente.isBlank())
					{
						req.setAttribute("errore",campiVuoti);
						error.forward(req,resp);
					}
					else if (marca.length() > 10 || modello.length() > 10 || mailCliente.length() > 33)
					{
						req.setAttribute("errore",lunghezzaErrata);
						error.forward(req,resp);
					}
					else if (!marca.toLowerCase().matches(regex) || !modello.toLowerCase().matches(regex) || !mailCliente.toLowerCase().matches(mailRegex))
					{
						req.setAttribute("errore",formatoErrato);
						error.forward(req,resp);
					}
					else
					{
						try
						{
							costo = Integer.parseInt(req.getParameter("costo"));
							if (costo < 0 || costo > 999)
							{
								req.setAttribute("errore",costoErrato);
								error.forward(req,resp);
							}
							
							RiparazioneDAO.doSave(new Riparazione(marca, modello, status, costo, nota, mailCliente));
							req.getServletContext().setAttribute("listaRiparazioni", RiparazioneDAO.doRetrieveAll());
							
							lavori.forward(req,resp);
						}
						catch (NumberFormatException e)
						{
							req.setAttribute("errore", costoNumerico);
							error.forward(req,resp);
						}
						
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
