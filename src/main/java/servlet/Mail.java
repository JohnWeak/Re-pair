package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Gmailer;

@WebServlet(value = "/mail")
public class Mail extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	{
		doGet(req,resp);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	{
		final var address = req.getParameter("indirizzo_mail");
		final var subject = req.getParameter("oggetto");
		final var text = req.getParameter("testo");
		
		final Gmailer gmailer = Gmailer.getInstance();
		try
		{
			if (gmailer != null)
				gmailer.sendMail(address,subject,text);
			
		} catch (Exception e) { e.printStackTrace(); }
		
		
	}
	
}
