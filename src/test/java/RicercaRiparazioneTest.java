import dao.ConPool;
import dao.RiparazioneDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import pojo.Riparazione;
import servlet.CercaRiparazione;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class RicercaRiparazioneTest
{
	@Test
	void emptySearch()
	{
		final String mailCliente = "";
		final ArrayList<Riparazione> list = RiparazioneDAO.doRetrieveCustomerRiparazioni(mailCliente);
		
		assert list.isEmpty();
	}
	
	
	@Test
	void mailTooLong()
	{
		final String mailCliente = "carlacarlacarlacarla@arancioni.com";
		final ArrayList<Riparazione> list = RiparazioneDAO.doRetrieveCustomerRiparazioni(mailCliente);
		
		assert list.isEmpty();
	}
	
	@Test
	void invalidMail()
	{
		final String mailCliente = "carla@azzurrini@.com";
		final ArrayList<Riparazione> list = RiparazioneDAO.doRetrieveCustomerRiparazioni(mailCliente);
		
		assert list.isEmpty();
	}
	
	@Test
	void mailNotFound() throws SQLException
	{
		final String mailCliente = "giuseppe@verdi.com";
		
		final Connection c = ConPool.getConnection();
		final PreparedStatement ps = c.prepareStatement("SELECT * FROM riparazioni WHERE mailCliente=?");
		ps.setString(1,mailCliente);
		final ResultSet result = ps.executeQuery();
		
		assert !result.next();
		
		final ArrayList<Riparazione> list = RiparazioneDAO.doRetrieveCustomerRiparazioni(mailCliente);
		assert list.isEmpty();
	}
	
	@Test
	void validMail() throws SQLException
	{
		final String mailCliente = "carla@azzurrini.com";
		
		final Connection c = ConPool.getConnection();
		final PreparedStatement ps = c.prepareStatement("SELECT * FROM riparazioni WHERE mailCliente=?");
		ps.setString(1, mailCliente);
		final ResultSet result = ps.executeQuery();
		
		if (!result.next())
		{
			final String s = "INSERT INTO riparazioni (`marca`, `modello`, `status`, `nota`, `costo`, `assegnato`, `mailCliente`) VALUES (?,?,?,?,?,?,?);";
			
				Connection con = ConPool.getConnection();
				PreparedStatement prs = con.prepareStatement(s);
				prs.setString(1,"Samsung");
				prs.setString(2,"Galaxy");
				prs.setInt(3,0);
				prs.setString(4,"");
				prs.setInt(5,2);
				prs.setInt(6,0);
				prs.setString(7,mailCliente);
				
				prs.executeUpdate();
		}
		
		final ArrayList<Riparazione> list = RiparazioneDAO.doRetrieveCustomerRiparazioni(mailCliente);
		
		assert !list.isEmpty() && list.get(0).getMailCliente().equals(mailCliente);
	}
	
	@Test
	void mailNull()
	{
		final String mailCliente = null;
		final ArrayList<Riparazione> list = RiparazioneDAO.doRetrieveCustomerRiparazioni(mailCliente);
		
		assert list.isEmpty();
	}
	
	
}
