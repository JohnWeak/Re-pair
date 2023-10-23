package com.example.repair;

public class Utente
{
	private int id;
    private String nome, cognome, mail;
	
	public Utente() {}
	
	/**
	 * L'utente (il dipendente) che userà il sistema software
	 * @param id ID autoassegnato nel database all'utente;
	 * @param nome Il nome dell'utente;
	 * @param cognome Il cognome dell'utente;
	 * @param mail La mail che l'utente userà per il login.
	 * */
	public Utente(int id, String nome, String cognome, String mail)
	{
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.mail = mail;
	}
	
	
	// GETTER
	public int getId()
	{
		return id;
	}
	public String getNome()
	{
		return nome;
	}
	public String getCognome()
	{
		return cognome;
	}
	public String getMail()
	{
		return mail;
	}
	
	// SETTER
	public void setId(int id)
	{
		this.id = id;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	
	
} // fine classe Utente
