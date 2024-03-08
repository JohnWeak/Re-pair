package servlet;

import java.io.*;

import dao.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pojo.Utente;

/**@author Giovanni Liguori*/
@WebServlet(value = "/login")
public class Login extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		doGet(request,response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		final String errorPage = "/error.jsp";
		final String lavoriPage = "/lavori.jsp";
		
		final RequestDispatcher errorDispatcher = request.getRequestDispatcher(errorPage);
		final RequestDispatcher lavoriDispatcher = request.getRequestDispatcher(lavoriPage);
		
		final HttpSession session = request.getSession();
		response.setContentType("text/html");
		
		final String mail = request.getParameter("mail");
		final String password = request.getParameter("password");
		
		if (mail == null || password == null || mail.isBlank() || password.isBlank())
		{
			request.setAttribute("errore","I campi mail e password non possono essere vuoti!");
			errorDispatcher.forward(request,response);
		}
		else
		{
			final Utente utente = UtenteDAO.doRetrieveUtenteByMailAndPassword(mail,password);
			
			if (utente == null)
			{
				request.setAttribute("errore","L'utente cercato non è presente nel database.");
				errorDispatcher.forward(request,response);
			}
			else
			{
				session.setAttribute("utente", utente);
				lavoriDispatcher.forward(request, response);
			}
		}
		
	} // fine doGet
	
} // fine HelloServlet