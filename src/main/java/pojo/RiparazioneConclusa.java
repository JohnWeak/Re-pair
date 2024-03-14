package pojo;

import java.util.Date;

public class RiparazioneConclusa extends Riparazione
{
	private Date data;
	private boolean ritiroDisponibile;
	
	public RiparazioneConclusa()
	{
		Gmailer.getInstance();
	}
	
	public void notificaCliente(String mail)
	{
		// final Gmailer gmailer = Gmailer.getInstance(mail);
	}
	
}
