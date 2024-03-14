package servlet;

import dao.AdminDAO;
import dao.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Utente;

import java.io.IOException;

@WebServlet(value = "/gestione-utente")
public class GestioneUtente extends HttpServlet
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
		final RequestDispatcher ok = req.getRequestDispatcher("dettaglioutente.jsp");
		
		final int id;
		final String nome, cognome, mail, password;
		final boolean admin;
		
		id = Integer.parseInt(req.getParameter("id"));
		nome = req.getParameter("nuovoNome");
		cognome = req.getParameter("nuovoCognome");
		mail = req.getParameter("nuovaMail");
		password = req.getParameter("password");
		admin = Boolean.parseBoolean(req.getParameter("nuovoAdmin"));
		
		if (nome.isBlank() || cognome.isBlank() || mail.isBlank())
		{
			req.setAttribute("errore", "Tutti i campi devono essere compilati prima di poter salvare le modifche!");
			error.forward(req, resp);
		}
		else
		{
			AdminDAO.doModificaUtente(id, nome, cognome, mail, password, admin);
			req.setAttribute("utente",UtenteDAO.doRetrieveByID(id));
			ok.forward(req, resp);
		}
		
	}
}
