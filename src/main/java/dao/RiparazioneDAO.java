package dao;

import pojo.Riparazione;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**@author Giovanni Liguori*/
public abstract class RiparazioneDAO
{
	public static ArrayList<Riparazione> doRetrieveAll()
	{
		Riparazione riparazione;
		ArrayList<Riparazione> list = new ArrayList<>();
		try
		{
			final Connection c = ConPool.getConnection();
			final PreparedStatement ps = c.prepareStatement("SELECT * FROM riparazioni");
			final ResultSet result = ps.executeQuery();
			
			while (result.next())
			{
				riparazione = new Riparazione();
				riparazione.setId(result.getInt(1));
				riparazione.setMarca(result.getString(2));
				riparazione.setModello(result.getString(3));
				riparazione.setStatus(result.getString(4));
				riparazione.setNota(result.getString(5));
				riparazione.setCosto(result.getInt(6));
				riparazione.setAssegnato(result.getInt(7));
				list.add(riparazione);
			}
			
		}catch (Exception e) {}
		
		return list;
	}
	
	public static void doSave(Riparazione r)
	{
		final String s = "INSERT INTO riparazioni (`marca`, `modello`, `status`, `nota`, `costo`,`assegnato`) VALUES(?,?,?,?,?,?);";
		try
		{
			Connection con = ConPool.getConnection();
			PreparedStatement ps = con.prepareStatement(s);
			ps.setString(1,r.getMarca());
			ps.setString(2,r.getModello());
			ps.setString(3,r.getStatus());
			ps.setString(4,r.getNota());
			ps.setInt(5,r.getCosto());
			ps.setInt(6,r.getAssegnato());
			
			ps.executeQuery();
			
		}catch (Exception e) {}
	}
	
	public static Riparazione doRetrieveByID(int id)
	{
		Riparazione toReturn = null;
		final ArrayList<Riparazione> list = doRetrieveAll();
		for (Riparazione r : list)
		{
			if (r.getId() == id)
			{
				toReturn = r;
				break;
			}
		}
		return toReturn;
		/*
		Riparazione r = null;
		try
		{
			final Connection c = ConPool.getConnection();
			final PreparedStatement ps = c.prepareStatement("SELECT * FROM riparazioni WHERE id = ?");
			ps.setInt(1,id);
			final ResultSet result = ps.executeQuery();
			if (result.next())
			{
				r = new Riparazione();
				r.setMarca(result.getString(2));
				r.setModello(result.getString(3));
				r.setStatus(result.getString(4));
				r.setNota(result.getString(5));
				r.setCosto(result.getInt(6));
				r.setAssegnato(result.getInt(7));
			}
			
		}catch (Exception e) {}
		return r;*/
	}
	
	public static boolean doEdit(int id, String marca, String modello, String status, String nota, int costo)
	{
		boolean success = true;
		if (doRetrieveByID(id) != null)
		{
			try
			{
				final Connection c = ConPool.getConnection();
				final PreparedStatement ps = c.prepareStatement("UPDATE riparazioni SET marca = ?, modello = ?, status=?, nota = ?, costo = ? WHERE id=?");
				ps.setString(1, marca);
				ps.setString(2, modello);
				ps.setString(3,status);
				ps.setString(4, nota);
				ps.setInt(4, costo);
				ps.setInt(5,id);
				ps.executeUpdate();
				
			} catch (Exception e) {}
		}
		else
		{
			success = false;
		}
		
		return success;
	}
	
}
