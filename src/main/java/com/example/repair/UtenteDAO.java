package com.example.repair;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO
{
    public Utente doRetrieveByMailPassword(String mail, String password)
    {
        try (Connection con = ConPool.getConnection())
        {
            PreparedStatement ps =
              con.prepareStatement("SELECT id, nome, cognome, mail, password FROM dipendenti WHERE mail=? AND password=?");
            ps.setString(1,mail);
            ps.setString(2,password);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                Utente utente = new Utente();
                utente.setId(rs.getInt(1));
                utente.setNome(rs.getString(2));
                utente.setCognome(rs.getString(3));
                utente.setMail(rs.getString(4));
                
                return utente;
            }
            return null;
        } catch (SQLException e) { throw new RuntimeException(e); }
        
    } // fine doRetrieveByMailPassword()

} // fine classe UtenteDAO
