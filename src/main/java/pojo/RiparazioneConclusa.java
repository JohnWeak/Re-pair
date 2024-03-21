package pojo;

import dao.ClienteDAO;

public class RiparazioneConclusa extends Riparazione
{
	private final boolean ritiroDisponibile = true;
	
	public RiparazioneConclusa(Riparazione r)
	{
		final String mailCliente, nomeCliente, testoMail;
		
		mailCliente = r.getMailCliente();
		nomeCliente = ClienteDAO.doCercaClienteByMail(mailCliente).getNome();
		testoMail = String.format("Gentile %s,\nil tuo %s %s è stato riparato!\nPassa in negozio per ritirarlo!\n",nomeCliente,r.getMarca(),r.getModello());
		notificaCliente(mailCliente, testoMail);
	}
	
	private void notificaCliente(String mailCliente, String testoMail)
	{
		final Gmailer gmailer;
		final String oggetto = "Riparazione conclusa!";
		try
		{
			gmailer = Gmailer.getInstance();
			gmailer.sendMail(mailCliente, oggetto, testoMail);
		}
		catch (Exception e) { e.printStackTrace(); }
	}
	
	public boolean isRitiroDisponibile()
	{
		return ritiroDisponibile;
	}
	
}
