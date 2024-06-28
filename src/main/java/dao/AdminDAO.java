package dao;

import pojo.Admin;
import pojo.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;

/**@author Giovanni Liguori*/
public abstract class AdminDAO
{
	public static ArrayList<Admin> doRetrieveAll()
	{
		ArrayList<Admin> list = new ArrayList<>();
		Admin admin;
		try
		{
			final Connection con = ConPool.getConnection();
			final PreparedStatement ps = con.prepareStatement("SELECT * FROM utenti WHERE isAdmin=1");
			final ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				admin = new Admin();
				admin.setNome(rs.getString(2));
				admin.setCognome(rs.getString(3));
				admin.setMail(rs.getString(4));
				list.add(admin);
			}
			
		}catch (Exception e) { e.printStackTrace(); }
		
		return list;
	}
	
	
	public static void doCreaUtente(String nome, String cognome, String mail, String password, boolean admin)
	{
		final String namesRegex = "[a-z]{1,10}";
		final String passwordRegex = "[0-9]{1,5}";
		final String mailRegex = "[a-z0-9]{1,20}@[a-z]{1,10}\\.[a-z]{2,3}";
		
		if (nome == null || cognome == null || mail == null || password == null ||
			!nome.toLowerCase().matches(namesRegex) || !cognome.toLowerCase().matches(namesRegex) ||
			!password.toLowerCase().matches(passwordRegex) || !mail.toLowerCase().matches(mailRegex)
		)
		{
			return;
		}
		
		try
		{
			final Connection con = ConPool.getConnection();
			
			final Utente utente = UtenteDAO.doRetrieveUtenteByMail(mail);
			if (utente != null)
			{
				return;
			}
			
			final PreparedStatement ps = con.prepareStatement("INSERT INTO utenti (`nome`, `cognome`, `mail`, `password`, `isAdmin`) VALUES (?,?,?,?,?)");
			ps.setString(1, nome);
			ps.setString(2, cognome);
			ps.setString(3, mail);
			ps.setString(4, password);
			ps.setBoolean(5, admin);
			
			ps.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
	}
	
	public static void doModificaUtente(int id, String nome, String cognome, String mail, String password, boolean admin)
	{
		try
		{
			final Connection con = ConPool.getConnection();
			final PreparedStatement ps = con.prepareStatement("UPDATE utenti SET nome=?,cognome=?,mail=?,password=?,isAdmin=? WHERE id = ?");
			ps.setString(1, nome);
			ps.setString(2, cognome);
			ps.setString(3, mail);
			ps.setString(4, password);
			ps.setBoolean(5,admin);
			ps.setInt(6, id);
			
			ps.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
	}
	
	public static void doCancella(int id, boolean utente)
	{
		final PreparedStatement ps;
		try
		{
			final Connection con = ConPool.getConnection();
			if (utente)
			{
				ps = con.prepareStatement("DELETE FROM utenti WHERE id=?");
			}
			else
			{
				ps = con.prepareStatement("DELETE FROM riparazioni WHERE id=?");
			}
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
		}catch (Exception e) { e.printStackTrace(); }
	}
	
	public static void doCreaRiparazione(String marca, String modello, int costo)
	{
		try
		{
			final Connection con = ConPool.getConnection();
			final PreparedStatement ps = con.prepareStatement("INSERT INTO riparazioni (`marca`, `modello`, `costo`) VALUES (?,?,?)");
			ps.setString(1, marca);
			ps.setString(2, modello);
			ps.setInt(3, costo);
			
			ps.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
	}
	
	public static void doModificaRiparazione(int id, String marca, String modello, String status, String nota, int costo, int assegnato)
	{
		try
		{
			final Connection con = ConPool.getConnection();
			final PreparedStatement ps = con.prepareStatement("UPDATE riparazioni SET marca=?,modello=?,status=?,nota=?,costo=?,assegnato=? WHERE id = ?");
			ps.setString(1, marca);
			ps.setString(2, modello);
			ps.setString(3, status);
			ps.setString(4, nota);
			ps.setInt(5,costo);
			ps.setInt(6,assegnato);
			ps.setInt(7, id);
			
			ps.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		
	}
	
}
