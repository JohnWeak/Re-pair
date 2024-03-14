package pojo;

/**@author Giovanni Liguori*/
public abstract class Dipendente
{
	private int id;
	private String mail, nome, cognome, password;
	
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
	public String getPassword()
	{
		return password;
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
	public void setPassword(String password)
	{
		this.password = password;
	}
	
}
