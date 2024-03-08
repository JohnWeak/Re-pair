package pojo;

/**@author Giovanni Liguori*/
public abstract class Dipendente
{
	private int id;
	private String mail, nome, cognome;
	
	// GETTER
	public String getMail()
	{
		return mail;
	}
	public String getNome()
	{
		return nome;
	}
	public String getCognome()
	{
		return cognome;
	}
	public int getId()
	{
		return id;
	}
	
	// SETTER
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	
}
