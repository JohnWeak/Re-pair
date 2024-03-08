

package dao;

import pojo.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	
	public static void doCreateUtente(String nome, String cognome, String mail, String password)
	{
		try
		{
			final Connection con = ConPool.getConnection();
			final PreparedStatement ps = con.prepareStatement("INSERT INTO utenti (`nome`, `cognome`, `mail`, `password`) VALUES (?,?,?,?)");
			ps.setString(1, nome);
			ps.setString(2, cognome);
			ps.setString(3, mail);
			ps.setString(4, password);
			
			ps.executeQuery();
		}catch (Exception e) { e.printStackTrace(); }
	}
	
	public static void doEdit(int id, String nome, String cognome, String mail, String password)
	{
		try
		{
			final Connection con = ConPool.getConnection();
			final PreparedStatement ps = con.prepareStatement("UPDATE utenti SET nome=?,cognome=?,mail=?,password=? WHERE id = ?");
			ps.setString(1, nome);
			ps.setString(2, cognome);
			ps.setString(3, mail);
			ps.setString(4, password);
			ps.setInt(5, id);
			
			ps.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
	}
	
	public static void doDelete(int id)
	{
		try
		{
			final Connection con = ConPool.getConnection();
			final PreparedStatement ps = con.prepareStatement("DELETE FROM utenti WHERE id=?");
			ps.setInt(1, id);
			
			ps.executeQuery();
		}catch (Exception e) { e.printStackTrace(); }
	}
	
}
