package com.example.repair;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class Login extends HttpServlet
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        doGet(request,response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        response.setContentType("text/html");

        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        UtenteDAO utenteDAO = new UtenteDAO();

        Utente utente = utenteDAO.doRetrieveByMailPassword(mail, password);
        
        session.setAttribute("utente", utente);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        dispatcher.forward(request, response);

    } // fine doGet

} // fine HelloServlet