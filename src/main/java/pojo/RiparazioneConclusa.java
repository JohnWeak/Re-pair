package pojo;

import dao.ClienteDAO;

public class RiparazioneConclusa extends Riparazione
{
	private final boolean ritiroDisponibile = true;
	
	public RiparazioneConclusa(Riparazione r)
	{
		String mailCliente, nomeCliente, testoMail;
		Gmailer g = Gmailer.getInstance();
		
		mailCliente = r.getMailCliente();
		nomeCliente = ClienteDAO.doCercaClienteByMail(mailCliente).getNome();
		testoMail = String.format("Gentile %s,\nil tuo %s %s è stato riparato!\nPassa in negozio per ritirarlo!\n",nomeCliente,r.getMarca(),r.getModello());
		
		try
		{
			g.sendMail(mailCliente, "Riparazione conclusa!", testoMail);
		}
		catch (Exception e) { e.printStackTrace(); }
		
		
	}
	
	public void notificaCliente(String mail)
	{
		// final Gmailer gmailer = Gmailer.getInstance(mail);
		
	}
	
}
