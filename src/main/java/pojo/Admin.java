package pojo;

import dao.AdminDAO;

/**@author Giovanni Liguori*/
public class Admin extends Dipendente
{
	
	public void creaUtente(String nome, String cognome, String mail, String password)
	{
		AdminDAO.doCreateUtente(nome, cognome, mail, password);
	}
	
	public void rimuoviUtente(int id)
	{
		AdminDAO.doDelete(id);
	}
	
	public void modificaUtente(int id, String nome, String cognome, String mail, String password)
	{
		AdminDAO.doEdit(id, nome, cognome, mail, password);
	}
	
	public boolean isAdmin()
	{
		return true;
	}
}