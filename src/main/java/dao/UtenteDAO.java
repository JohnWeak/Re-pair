package dao;

import pojo.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**@author Giovanni Liguori*/
public abstract class UtenteDAO
{
	
	public static ArrayList<Utente> doRetrieveAll()
	{
		ArrayList<Utente> list = new ArrayList<>();
		Utente utente;
		try
		{
			final Connection con = ConPool.getConnection();
			final PreparedStatement ps = con.prepareStatement("SELECT * FROM utenti");
			final ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				utente = newObjectUtente(rs);
				list.add(utente);
			}
			
		}catch (Exception e) { }
		
		return list;
	}
	public static Utente doRetrieveUtenteByMailAndPassword(String mail, String password)
	{
		Utente utente = null;
		
		try
		{
			final Connection con = ConPool.getConnection();
			final PreparedStatement ps = con.prepareStatement("SELECT * FROM utenti WHERE mail=? && password=?");
			ps.setString(1,mail);
			ps.setString(2, password);
			final ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				utente = newObjectUtente(rs);
			}
		}catch (Exception e ){}
		
		return utente;
	}
	public static Utente doRetrieveUtenteByMail(String mail)
	{
		Utente utente = null;
		try
		{
			final Connection con = ConPool.getConnection();
			final PreparedStatement ps = con.prepareStatement("SELECT * FROM utenti WHERE mail=?");
			ps.setString(1,mail);
			final ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			{
				utente = newObjectUtente(rs);
			}
		}catch (Exception e ){}
		
		return utente;
	}
	
	public static Utente doRetrieveByID(int id)
	{
		Utente utente = null;
		try
		{
			final Connection con = ConPool.getConnection();
			final PreparedStatement ps = con.prepareStatement("SELECT * FROM utenti WHERE id = ?");
			ps.setInt(1,id);
			final ResultSet rs = ps.executeQuery();
			if (rs.next())
			{
				utente = newObjectUtente(rs);
			}
		}catch (Exception e) {}
		
		return utente;
	}
	
	public static Utente newObjectUtente(ResultSet rs)
	{
		Utente utente = null;
		try
		{
			final int id = rs.getInt(1);
			final String nome = rs.getString(2);
			final String cognome = rs.getString(3);
			final String mail = rs.getString(4);
			// final String password = rs.getString(5);
			final boolean isAdmin = rs.getBoolean(6);
			
			utente = new Utente(id, nome, cognome, mail, isAdmin);
		}catch (Exception e) { e.printStackTrace(); }
		
		return utente;
	}
	
} // fine classe UtenteDAO
