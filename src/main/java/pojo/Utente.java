package pojo;

import dao.UtenteDAO;

/**@author Giovanni Liguori*/
public class Utente extends Dipendente
{
	private boolean admin = false;
	
	// Costruttore vuoto per permettere al DAO di istanziare un nuovo utente tramite i setter
	public Utente() {}
	
	/**
	 * L'utente (il dipendente) che userà il sistema software
	 * @param id ID univoco che rappresenta l'utente
	 * @param nome Il nome dell'utente
	 * @param cognome Il cognome dell'utente
	 * @param mail La mail che l'utente userà per il login
	 * */
	public Utente(int id, String nome, String cognome, String mail, boolean admin)
	{
		super.setId(id);
		super.setNome(nome);
		super.setCognome(cognome);
		super.setMail(mail);
		this.admin = admin;
	}
	
	public void autoAssegna(int idRiparazione)
	{
		UtenteDAO.doAssegnaRiparazione(idRiparazione, getId());
	}
	
	public void archivia(int idRiparazione, String status)
	{
		UtenteDAO.doModificaStatusRiparazione(idRiparazione,status);
	}
	
	public void annota(int idRiparazione, String nota)
	{
		UtenteDAO.doModificaNotaRiparazione(idRiparazione,nota);
	}
	
	
	// GETTER & SETTER
	public boolean isAdmin()
	{
		return admin;
	}
	public void setAdmin(boolean admin)
	{
		this.admin = admin;
	}
	
} // fine classe Utente
