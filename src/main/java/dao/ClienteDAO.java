package dao;

import pojo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public abstract class ClienteDAO
{

	public static ArrayList<Cliente> doRetrieveAll()
	{
		ArrayList<Cliente> list = new ArrayList<>();
		Cliente c = null;
		try
		{
			Connection connection = ConPool.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM clienti");
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				c = new Cliente();
				c.setMail(rs.getString(1));
				c.setNome(rs.getString(2));
				
				list.add(c);
			}
		}catch (Exception e) { e.printStackTrace(); }
		
		return list;
	}
	
	public static Cliente doCercaClienteByMail(String mail)
	{
		Cliente toReturn = null;
		final var list = doRetrieveAll();
		for (Cliente c : list)
		{
			if (c.getMail().equals(mail))
			{
				toReturn = c;
				break;
			}
		}
		return toReturn;
	}
	
}
