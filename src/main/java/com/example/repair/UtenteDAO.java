package com.example.repair;

import model.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDAO
{
    private final int MAIL_AND_PASSWORD = 0;
    private final int MAIL_ONLY = 1;
    private final String[] preparedStatement =
    {
        "SELECT id, nome, cognome, mail, password FROM dipendenti WHERE mail=? AND password=?",
        "SELECT id, nome, cognome, mail FROM dipendenti WHERE mail=?"
    };
    
    
    
    /**Metodo per recuperare un utente dal database cercandolo tramite mail e password.
     * @param mail la mail dell'utente da cercare;
     * @param password la password dell'utente da cercare.
     * @return un oggetto di tipo <code>Utente</code> se le credenziali sono corrette; <code>null</code> in caso contrario.*/
    public Utente doRetrieveByMailPassword(String mail, String password)
    {
        return searchInDB(MAIL_AND_PASSWORD,new String[]{mail, password});
    } // fine doRetrieveByMailPassword()
    
    
    /**Metodo per recuperare un utente dal database cercandolo tramite mail.
     * @param mail la mail dell'utente da cercare;
     * @return un oggetto di tipo <code>Utente</code> se le credenziali sono corrette; <code>null</code> in caso contrario.*/
    public Utente doRetrieveByMail(String mail)
    {
        return searchInDB(MAIL_ONLY,new String[]{mail});
    } // fine doRetrieveByMail()
    
    
    
    private Utente searchInDB(int with, String[] args)
    {
        int index = 1;
        
        try (Connection con = ConPool.getConnection())
        {
            int id;
            String nome, cognome, email;
            
            PreparedStatement ps = con.prepareStatement(preparedStatement[with]);
           
            for (String arg : args)
                ps.setString(index++, arg);
            
            // ps.setString(1,args[0]);
            // ps.setString(2,args[1]);
            
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
    
    } // fine searchInDB()
    
} // fine classe UtenteDAO
