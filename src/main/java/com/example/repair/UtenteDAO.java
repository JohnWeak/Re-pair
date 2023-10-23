package com.example.repair;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO
{
    /**Metodo per recuperare un utente dal database cercandolo tramite mail e password.
     * @param mail la mail dell'utente da cercare;
     * @param password la password dell'utente da cercare.
     * @return l'utente cercato, se le credenziali sono corrette; <code>null</code> in caso contrario.*/
    public Utente doRetrieveByMailPassword(String mail, String password)
    {
        try (Connection con = ConPool.getConnection())
        {
            int id;
            String nome, cognome, email;
            
            PreparedStatement ps =
              con.prepareStatement("SELECT id, nome, cognome, mail, password FROM dipendenti WHERE mail=? AND password=?");
            ps.setString(1,mail);
            ps.setString(2,password);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                id = rs.getInt(1);
                nome = rs.getString(2);
                cognome = rs.getString(3);
                email = rs.getString(4);
                
                return new Utente(id, nome, cognome, email);
            }
            return null;
        } catch (SQLException e) { throw new RuntimeException(e); }
        
    } // fine doRetrieveByMailPassword()

} // fine classe UtenteDAO
