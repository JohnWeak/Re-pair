package pojo;

import dao.AdminDAO;

/**@author Giovanni Liguori*/
public class Admin extends Dipendente
{
	
	public void creaUtente(String nome, String cognome, String mail, String password)
	{
		AdminDAO.doCreaUtente(nome, cognome, mail, password);
	}
	
	public void modificaUtente(int id, String nome, String cognome, String mail, String password)
	{
		AdminDAO.doModificaUtente(id, nome, cognome, mail, password);
	}
	
	public void rimuovi(int id, boolean utente)
	{
		AdminDAO.doCancella(id, utente);
	}
	
	public void creaRiparazione(String marca, String modello, int costo)
	{
		AdminDAO.doCreaRiparazione(marca,modello,costo);
	}
	
	public void modificaRiparazione(int id, String marca, String modello, String status, String nota, int costo, int assegnato)
	{
		AdminDAO.doModificaRiparazione(id, marca, modello, status, nota, costo, assegnato);
	}
}