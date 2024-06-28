import dao.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import pojo.Utente;
import servlet.Login;

import java.io.IOException;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LoginUtenteTest
{
	private Login login;
	private HttpServletRequest requestMock;
	private HttpServletResponse responseMock;
	private HttpSession sessionMock;
	private RequestDispatcher dispatcherMock;
	private MockedStatic<UtenteDAO> mockedDAO;
	@BeforeEach
	void setup()
	{
		login = new Login();
		requestMock = mock(HttpServletRequest.class);
		responseMock = mock(HttpServletResponse.class);
		dispatcherMock = mock(RequestDispatcher.class);
		sessionMock = mock(HttpSession.class);
		mockedDAO = mockStatic(UtenteDAO.class);
	}
	@AfterEach
	void after()
	{
		mockedDAO.close();
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>mail: ""</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 * TEST CASE ID: <code>TC_1.1</code><br>
	 * TEST FRAME: <code>mail_len1<code/>
	 */
	@Test
	void emptyMail() throws ServletException, IOException
	{
		final String[] params = {"", "12345"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("mail")).thenReturn(params[0]);
		when(requestMock.getParameter("password")).thenReturn(params[1]);
		when(requestMock.getSession()).thenReturn(sessionMock);
		doNothing().when(dispatcherMock).forward(any(), any());
		login.doGet(requestMock, responseMock);
		
		verifyNoInteractions(sessionMock);
		mockedDAO.verifyNoInteractions();
		
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>mail: mariomariomario@rossirossi.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 * TEST CASE ID: <code>TC_1.2</code><br>
	 * TEST FRAME: <code>mail_len3<code/>
	 */
	@Test
	void mailTooLong() throws ServletException, IOException
	{
		final String[] params = {"mariomariomario@rossirossiross.com", "12345"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("mail")).thenReturn(params[0]);
		when(requestMock.getParameter("password")).thenReturn(params[1]);
		when(requestMock.getSession()).thenReturn(sessionMock);
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		login.doGet(requestMock, responseMock);
		
		verifyNoInteractions(sessionMock);
		mockedDAO.verifyNoInteractions();
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>mail: mario@rossi<strong>@</strong>.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 * <p>
	 * TEST CASE ID: <code>TC_1.3</code><br>
	 * TEST FRAME: <code>mail_len2, mail_form1</code><br>
	 */
	@Test
	void invalidMailFormat() throws ServletException, IOException
	{
		final String[] params = {"mario@rossi@.com", "12345"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("mail")).thenReturn(params[0]);
		when(requestMock.getParameter("password")).thenReturn(params[1]);
		mockedDAO.when(() -> UtenteDAO.doRetrieveUtenteByMailAndPassword(params[0], params[1])).thenReturn(null);
		doNothing().when(dispatcherMock).forward(requestMock,responseMock);
		
		login.doGet(requestMock, responseMock);
		
		verifyNoInteractions(sessionMock);
		
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>mail: arthur@dent.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 * <p>
	 * TEST CASE ID: <code>TC_1.4</code><br>
	 * TEST FRAME: <code>mail_len2, mail_form2, mail_es1</code><br>
	 */
	@Test
	void unkownMail() throws ServletException, IOException
	{
		final String[] params = {"arthur@dent.com", "12345"};
		mockedDAO.when(() -> UtenteDAO.doRetrieveUtenteByMailAndPassword(params[0], params[1])).thenReturn(null);
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("mail")).thenReturn(params[0]);
		when(requestMock.getParameter("password")).thenReturn(params[1]);
		when(requestMock.getSession()).thenReturn(sessionMock);
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		login.doGet(requestMock, responseMock);
		
		verifyNoInteractions(sessionMock);
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>mail: mario@rossi.com</li>
	 *     <li>password: ""</li>
	 * </ul>
	 * <p>
	 * TEST CASE ID: <code>TC_1.5</code><br>
	 * TEST FRAME: <code>mail_len2, mail_form2, mail_es2, pass_len1</code><br>
	 */
	@Test
	void emptyPassword() throws ServletException, IOException
	{
		final String[] params = {"mario@rossi.com", ""};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("mail")).thenReturn(params[0]);
		when(requestMock.getParameter("password")).thenReturn(params[1]);
		when(requestMock.getSession()).thenReturn(sessionMock);
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		login.doGet(requestMock, responseMock);
		
		verifyNoInteractions(sessionMock);
		mockedDAO.verifyNoInteractions();
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>mail: mario@rossi.com</li>
	 *     <li>password: <strong>123456</strong></li>
	 * </ul>
	 * <p>
	 * TEST CASE ID: <code>TC_1.6</code><br>
	 * TEST FRAME: <code>mail_len2, mail_form2, mail_es2, pass_len3</code><br>
	 */
	@Test
	void passwordTooLong() throws ServletException, IOException
	{
		final String[] params = {"mario@rossi.com", "123456"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("mail")).thenReturn(params[0]);
		when(requestMock.getParameter("password")).thenReturn(params[1]);
		when(requestMock.getSession()).thenReturn(sessionMock);
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		login.doGet(requestMock, responseMock);
		verifyNoInteractions(sessionMock);
		mockedDAO.verifyNoInteractions();
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>mail: mario@rossi.com</li>
	 *     <li>password: <strong>09876</strong></li>
	 * </ul>
	 * <p>
	 * TEST CASE ID: <code>TC_1.7</code><br>
	 * TEST FRAME: <code>mail_len2, mail_form2, mail_es2, pass_len2, pass_es1</code><br>
	 */
	@Test
	void loginWithUnknownPassword() throws ServletException, IOException
	{
		final String[] params = {"mario@rossi.com", "09876"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("mail")).thenReturn(params[0]);
		when(requestMock.getParameter("password")).thenReturn(params[1]);
		when(requestMock.getSession()).thenReturn(sessionMock);
		mockedDAO.when(() -> UtenteDAO.doRetrieveUtenteByMailAndPassword(params[0], params[1])).thenReturn(null);
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		login.doGet(requestMock, responseMock);
		verifyNoInteractions(sessionMock);
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>mail: mario@rossi.com</li>
	 *     <li>password: 12345</li>
	 * </ul>
	 *
	 * TEST CASE ID: <code>TC_1.8</code><br>
	 * TEST FRAME: <code>mail_len2, mail_form2, mail_es2, pass_len2, pass_es2</code><br>
	 * */
	@Test
	void correctMailAndPassword() throws ServletException, IOException  // happy flow
	{
		final String mail = "mario@rossi.com";
		final String password = "12345";
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getSession()).thenReturn(sessionMock);
		
		when(requestMock.getParameter("mail")).thenReturn(mail);
		when(requestMock.getParameter("password")).thenReturn(password);
		
		doAnswer(invocationOnMock ->
		{
			final String attribute = (String) invocationOnMock.getArguments()[0];
			final Utente user = (Utente) invocationOnMock.getArguments()[1];
			
			assert attribute.equals("utente");
			assert user.getMail().equals(mail);
			assert user.getPassword().equals(password);
			
			return null;
		}).when(sessionMock).setAttribute(anyString(), any());
		
		doNothing().when(dispatcherMock).forward(requestMock,responseMock);
		
		login.doGet(requestMock,responseMock);
	}
	
	// setup per il test del login con parametri vuoti o null
	private static Stream<Arguments> voidOrNullMailAndPassword()
	{
		return Stream.of(Arguments.of("",""),
				Arguments.of("",null),
				Arguments.of(null,""),
				Arguments.of(null,null)
		);
	}
	
	/**
	 * PARAMETERS TO TEST:
	 * <ul>
	 *     <li>mail: "" | <code>null</code></li>
	 *     <li>password: "" | <code>null</code></li>
	 * </ul>
	 *
	 * TEST CASE ID: <code>TC_1.9</code><br>
	 * TEST FRAME: <code>login_empty_or_null</code><br>
	 * */
	@ParameterizedTest
	@MethodSource("voidOrNullMailAndPassword")
	void loginWithNullOrEmptyParameters(String mail, String password) throws ServletException, IOException
	{
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getSession()).thenReturn(sessionMock);
		
		when(requestMock.getParameter("mail")).thenReturn(mail);
		when(requestMock.getParameter("password")).thenReturn(password);
		
		doAnswer((invocationOnMock ->
		{
			final String error = invocationOnMock.getArgument(0);
			final String reason = invocationOnMock.getArgument(1);
			
			assert error.equals("errore");
			assert reason.equals("I campi mail e password non possono essere vuoti!");
			
			return null;
		})).when(requestMock).setAttribute(anyString(),any());
		
		doNothing().when(dispatcherMock).forward(requestMock,responseMock);
		
		login.doGet(requestMock,responseMock);
	}
}
