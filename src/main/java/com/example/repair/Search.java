package com.example.repair;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "search", value = "/search")
public class Search extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		
		String mail = request.getParameter("mail");
		System.out.println(mail);
		Utente u = new UtenteDAO().doRetrieveByMail(mail);
		
		if (u == null)
			System.out.println("ALLARME PANICO AAAAAA UTENTE NULLO AAAAAAH");
		
		session.setAttribute("utenteCercato", u);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	
}
