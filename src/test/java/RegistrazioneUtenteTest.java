import dao.AdminDAO;
import dao.ConPool;
import dao.UtenteDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.stubbing.Answer;
import pojo.Utente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Stream;
import static org.mockito.Mockito.*;


public class RegistrazioneUtenteTest
{
	private static final boolean isAdmin = false;
	private static ArrayList<Utente> listaUtenti = new ArrayList<>();
	private static Utente utente = new Utente();
	
	@BeforeEach
	void setUser()
	{
		listaUtenti = new ArrayList<>();
		utente = new Utente();
	}
	
	@BeforeAll
	static void setMock() throws SQLException
	{
		final var mockedPreparedStatement = mock(PreparedStatement.class);
		final var mockedConnection = mock(Connection.class);
		final var mockedConPool = mockStatic(ConPool.class);
		final var mockedResultSet = mock(ResultSet.class);
		final var mockedUtenteDAO = mockStatic(UtenteDAO.class);
		
		when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
		mockedConPool.when(ConPool::getConnection).thenReturn(mockedConnection);
		
		mockedUtenteDAO.when(() -> UtenteDAO.doRetrieveUtenteByMail(anyString())).thenAnswer((invocationOnMock) ->
		{
			final var mail = invocationOnMock.getArgument(0, String.class);
			
			for (Utente user : listaUtenti)
			{
				if (user.getMail().equals(mail))
				{
					return user;
				}
			}
			return null;
		});
		
		doAnswer((Answer<Integer>) (invocationOnMock) ->
		{
			listaUtenti.add(utente);
			return 0;
		}).when(mockedPreparedStatement).executeUpdate();
		
		when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
		when(mockedResultSet.next()).thenReturn(true).thenReturn(false);
		
		doAnswer((Answer<Void>) invocationOnMock ->
		{
			final var index = invocationOnMock.getArgument(0, Integer.class);
			final var value = invocationOnMock.getArgument(1, String.class);
					
			switch (index)
			{
				case 1: utente.setNome(value); break;
				case 2: utente.setCognome(value); break;
				case 3: utente.setMail(value); break;
				case 4: utente.setPassword(value); break;
				case 5: utente.setAdmin(isAdmin); break;

				default: throw new SQLException();
			}
			
			return null;
		}).when(mockedPreparedStatement).setString(anyInt(), anyString());
		
		doAnswer((Answer<Object>) invocationOnMock ->
		{
			final var index = invocationOnMock.getArgument(0, Integer.class);
			switch (index)
			{
				case 1: return -1;
				case 2: return "Gianluca";
				case 3: return "Verdi";
				case 4: return "gianluca@verdi.com";
				case 5: return "12345";
				case 6: return false;
				
				default: return null;
			}
			
		}).when(mockedResultSet).getString(anyInt());
		
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>nome: ""</li>
	 *     <li>cognome: Verdi</li>
	 *     <li>mail: gianluca@verdi.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 * TEST CASE ID: <code>TC_2.1</code><br>
	 * TEST FRAME: <code>nome_len1</code>
	 * */
	@Test
	void emptyName()
	{
		final String[] params = {"", "Verdi", "gianluca@verdi.com", "12345"};
		AdminDAO.doCreaUtente(params[0], params[1], params[2],params[3], isAdmin);
		
		assert utente.getNome() == null;
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>nome: GianlucaGia</li>
	 *     <li>cognome: Verdi</li>
	 *     <li>mail: gianluca@verdi.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 * TEST CASE ID: <code>TC_2.2</code><br>
	 * TEST FRAME: <code>nome_len3</code>
	 * */
	@Test
	void nameTooLong()
	{
		final String[] params = {"GianlucaGia", "Verdi", "gianluca@verdi.com", "12345"};
		AdminDAO.doCreaUtente(params[0], params[1], params[2],params[3], isAdmin);
		
		assert utente.getNome() == null;
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>name: Gianluc4</li>
	 *     <li>surname: Verdi</li>
	 *     <li>mail: gianluca@verdi.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 *
	 * TEST CASE ID: <code>TC_2.3</code><br>
	 * TEST FRAME: <code>nome_len2, nome_form1</code><br>
	 * */
	@Test
	void invalidName()
	{
		final String[] params = {"Gianluc4", "Verdi", "gianluca@verdi.com", "12345"};
		AdminDAO.doCreaUtente(params[0], params[1], params[2], params[3], isAdmin);
		
		assert utente.getNome() == null;
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>name: Gianluca</li>
	 *     <li>surname: ""</li>
	 *     <li>mail: gianluca@verdi.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 *
	 * TEST CASE ID: <code>TC_2.4</code><br>
	 * TEST FRAME: <code>nome_len2, nome_form2, cogn_len1</code><br>
	 * */
	@Test
	void empySurname()
	{
		final String[] params = {"Gianluca", "", "gianluca@verdi.com", "12345"};
		AdminDAO.doCreaUtente(params[0], params[1], params[2], params[3], isAdmin);
		
		assert utente.getNome() == null;
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>name: Gianluca</li>
	 *     <li>surname: VerdiVerdiV</li>
	 *     <li>mail: gianluca@verdi.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 *
	 * TEST CASE ID: <code>TC_2.5</code><br>
	 * TEST FRAME: <code>nome_len2, nome_form2, cogn_len3</code><br>
	 * */
	@Test
	void surnameTooLong()
	{
		final String[] params = {"Gianluca", "VerdiVerdiV", "gianluca@verdi.com", "12345"};
		AdminDAO.doCreaUtente(params[0], params[1], params[2], params[3], isAdmin);
		
		assert utente.getNome() == null;
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>name: Gianluca</li>
	 *     <li>surname: V3rd1</li>
	 *     <li>mail: gianluca@verdi.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 *
	 * TEST CASE ID: <code>TC_2.6</code><br>
	 * TEST FRAME: <code>nome_len2, nome_form2, cogn_len2, cogn_form1</code><br>
	 * */
	@Test
	void invalidSurname()
	{
		final String[] params = {"Gianluca", "V3rdi", "gianluca@verdi.com", "12345"};
		AdminDAO.doCreaUtente(params[0], params[1], params[2], params[3], isAdmin);
		
		assert utente.getNome() == null;
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>name: Gianluca</li>
	 *     <li>surname: Verdi</li>
	 *     <li>mail: ""</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 *
	 * TEST CASE ID: <code>TC_2.7</code><br>
	 * TEST FRAME: <code>nome_len2, nome_form2, cogn_len2, cogn_form2, mail_len1</code><br>
	 * */
	@Test
	void emptyMail()
	{
		final String[] params = {"Gianluca", "Verdi", "", "12345"};
		AdminDAO.doCreaUtente(params[0], params[1], params[2], params[3], isAdmin);
		
		assert utente.getNome() == null;
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>name: Gianluca</li>
	 *     <li>surname: Verdi</li>
	 *     <li>mail: gianlucagianlucagian@verdi.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 *
	 * TEST CASE ID: <code>TC_2.8</code><br>
	 * TEST FRAME: <code>nome_len2, nome_form2, cogn_len2, cogn_form2, mail_len3</code><br>
	 * */
	@Test
	void mailTooLong()
	{
		final String[] params = {"Gianluca", "Verdi", "gianlucagianlucagianluca@verdi.com", "12345"};
		AdminDAO.doCreaUtente(params[0], params[1], params[2], params[3], isAdmin);
		
		assert utente.getNome() == null;
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>name: Gianluca</li>
	 *     <li>surname: Verdi</li>
	 *     <li>mail: gianluca@verdi@.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 *
	 * TEST CASE ID: <code>TC_2.9</code><br>
	 * TEST FRAME: <code>nome_len2, nome_form2, cogn_len2, cogn_form2, mail_len2, mail_form1</code><br>
	 * */
	@Test
	void createUserWithInvalidMail()
	{
		final String[] credentials = {"Gianluca", "Verdi", "gianluca@verdi@.com", "12345"};
		AdminDAO.doCreaUtente(credentials[0], credentials[1], credentials[2], credentials[3], isAdmin);
		
		assert utente.getNome() == null;
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>name: Gianluca</li>
	 *     <li>surname: Verdi</li>
	 *     <li>mail: gianluca@verdi.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 * <p>
	 * TEST CASE ID: <code>TC_2.10</code><br>
	 * TEST FRAME: <code>nome_len2, nome_form2, cogn_len2, cogn_form2, mail_len2, mail_form2, mail_es1</code><br>
	 */
	@Test
	void duplicateMail()
	{
		final String[] params = {"Gianluca", "Verdi", "gianluca@verdi.com", "12345"};
		assert listaUtenti.isEmpty();
		
		AdminDAO.doCreaUtente(params[0], params[1], params[2], params[3], isAdmin);
		assert listaUtenti.size() == 1;
		
		AdminDAO.doCreaUtente(params[0], params[1], params[2], params[3], isAdmin);
		assert listaUtenti.size() == 1;
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>name: Gianluca</li>
	 *     <li>surname: Verdi</li>
	 *     <li>mail: gianluca@verdi.com</li>
	 *     <li>password: ""</li>
	 * </ul>
	 * <p>
	 * TEST CASE ID: <code>TC_2.11</code><br>
	 * TEST FRAME: <code>nome_len2, nome_form2, cogn_len2, cogn_form2, mail_len2, mail_form2, mail_es2, pass_len1</code><br>
	 */
	@Test
	void emptyPassword()
	{
		final String[] params = {"Gianluca", "Verdi", "gianluca@verdi.com", ""};
		AdminDAO.doCreaUtente(params[0], params[1], params[2], params[3], isAdmin);
		
		assert utente.getNome() == null;
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>name: Gianluca</li>
	 *     <li>surname: Verdi</li>
	 *     <li>mail: gianluca@verdi.com</li>
	 *     <li>password: 123456</li>
	 * </ul>
	 * <p>
	 * TEST CASE ID: <code>TC_2.12</code><br>
	 * TEST FRAME: <code>nome_len2, nome_form2, cogn_len2, cogn_form2, mail_len2, mail_form2, mail_es2, pass_len3</code><br>
	 */
	@Test
	void passwordTooLong()
	{
		final String[] params = {"Gianluca", "Verdi", "gianluca@verdi.com", "123456"};
		AdminDAO.doCreaUtente(params[0], params[1], params[2], params[3], isAdmin);
		
		assert utente.getNome() == null;
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>name: Gianluca</li>
	 *     <li>surname: Verdi</li>
	 *     <li>mail: gianluca@verdi.com</li>
	 *     <li>password: 1abc5</li>
	 * </ul>
	 * <p>
	 * TEST CASE ID: <code>TC_2.13</code><br>
	 * TEST FRAME: <code>nome_len2, nome_form2, cogn_len2, cogn_form2, mail_len2, mail_form2, mail_es2, pass_len2, pass_form1</code><br>
	 */
	@Test
	void createUserWithInvalidPassword()
	{
		final String[] credentials = {"Gianluca", "Verdi", "gianluca@verdi.com", "1abc5"};
		AdminDAO.doCreaUtente(credentials[0], credentials[1], credentials[2], credentials[3], isAdmin);
		
		assert utente.getNome() == null;
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>name: Gianluca</li>
	 *     <li>surname: Verdi</li>
	 *     <li>mail: gianluca@verdi.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 * <p>
	 * TEST CASE ID: <code>TC_2.14</code><br>
	 * TEST FRAME: <code>nome_len2, nome_form2, cogn_len2, cogn_form2, mail_len2, mail_form2, mail_es2, pass_len2, pass_form2</code><br>
	 */
	@Test
	void validUser()
	{
		final String[] params = {"Gianluca", "Verdi", "gianluca@verdi.com", "12345"};
		AdminDAO.doCreaUtente(params[0], params[1], params[2], params[3], isAdmin);
		
		assert utente.getNome().equals(params[0]);
		assert utente.getCognome().equals(params[1]);
		assert utente.getMail().equals(params[2]);
		assert utente.getPassword().equals(params[3]);
	}
	
	
	// metodo che genera in automatico i parametri da passare al test successivo
	private static Stream<Arguments> combinations()
	{
		final String[] values = {"",null};
		return Stream.of(values)
			.flatMap(v1 -> Stream.of(values)
			.flatMap(v2 -> Stream.of(values)
			.flatMap(v3 -> Stream.of(values)
			.map(v4 -> Arguments.of(v1, v2, v3, v4))))
			);
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>name: "" | <code>null</code></li>
	 *     <li>surname: "" | <code>null</code></li>
	 *     <li>mail: "" | <code>null</code></li>
	 *     <li>password: "" | <code>null</code></li>
	 * </ul>
	 * <p>
	 * TEST CASE ID: <code>TC_2.15</code><br>
	 * TEST FRAME: <code>reg_empty_or_null</code><br>
	 */
	@ParameterizedTest
	@MethodSource("combinations")
	void createUserWithNullOrEmptyParameters(String nome, String cognome, String mail, String password)
	{
		AdminDAO.doCreaUtente(nome,cognome,mail,password,isAdmin);
		assert utente.getNome() == null;
	}
	
}
