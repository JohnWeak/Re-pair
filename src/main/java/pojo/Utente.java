package pojo;

/**@author Giovanni Liguori*/
public class Utente extends Dipendente
{
	private boolean isAdmin = false;
	
	// Costruttore vuoto per permettere al DAO di istanziare un nuovo utente tramite i setter
	public Utente() {}
	
	/**
	 * L'utente (il dipendente) che userà il sistema software
	 * @param id ID univoco che rappresenta l'utente
	 * @param nome Il nome dell'utente
	 * @param cognome Il cognome dell'utente
	 * @param mail La mail che l'utente userà per il login
	 * */
	public Utente(int id, String nome, String cognome, String mail, boolean isAdmin)
	{
		super.setId(id);
		super.setNome(nome);
		super.setCognome(cognome);
		super.setMail(mail);
		this.isAdmin = isAdmin;
	}
	
	// GETTER
	public boolean isAdmin()
	{
		return isAdmin;
	}
	
	// SETTER
	public void setAdmin(boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}
	
} // fine classe Utente
