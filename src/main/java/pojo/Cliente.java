package pojo;

public class Cliente
{
	private String nome, mail;
	
	public Cliente(){}
	
	public Cliente(String nome, String mail)
	{
		this.nome = nome;
		this.mail = mail;
	}

	public String getNome()
	{
		return nome;
	}
	public String getMail()
	{
		return mail;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public void setMail(String mail)
	{
		this.mail = mail;
	}
}
