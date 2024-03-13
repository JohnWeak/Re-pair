package pojo;

/**@author Giovanni Liguori*/
public class Riparazione
{
	private int id, costo, assegnato;
	private String marca, modello, status, nota;
	
	public Riparazione(){}
	
	public Riparazione(String marca, String modello, String status, int costo, String nota)
	{
		this.marca = marca;
		this.modello = modello;
		this.status = status;
		this.costo = costo;
		this.nota = nota;
	}
	
	// GETTER
	public String getMarca()
	{
		return marca;
	}
	public String getModello()
	{
		return modello;
	}
	public String getStatus()
	{
		return status;
	}
	public int getId()
	{
		return id;
	}
	public int getCosto()
	{
		return costo;
	}
	public String getNota()
	{
		return nota;
	}
	public int getAssegnato()
	{
		return assegnato;
	}
	public String getAll()
	{
		return "Il tuo " + marca + " " + modello + " è in fase di<br/>" + status;
	}
	
	
	// SETTER
	public void setMarca(String marca)
	{
		this.marca = marca;
	}
	public void setModello(String modello)
	{
		this.modello = modello;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public void setCosto(int costo)
	{
		this.costo = costo;
	}
	public void setNota(String nota)
	{
		this.nota = nota;
	}
	public void setAssegnato(int assegnato)
	{
		this.assegnato = assegnato;
	}
	
}
