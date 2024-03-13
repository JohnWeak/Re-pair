package servlet;

import dao.AdminDAO;
import dao.RiparazioneDAO;
import dao.UtenteDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import pojo.Admin;
import pojo.Riparazione;
import pojo.Utente;

import java.util.ArrayList;

/**@author Giovanni Liguori*/
@WebListener
public class InitServlet implements ServletContextListener
{
	public ServletContext context = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		final ArrayList<Riparazione> listaRiparazioni = RiparazioneDAO.doRetrieveAll();
		final ArrayList<Utente> listaUtenti = UtenteDAO.doRetrieveAll();
		final ArrayList<Admin> listaAdmin = AdminDAO.doRetrieveAll();
		
		context = sce.getServletContext();
		context.setAttribute("listaRiparazioni", listaRiparazioni);
		context.setAttribute("listaUtenti", listaUtenti);
		context.setAttribute("listaAdmin",listaAdmin);
	}
}
