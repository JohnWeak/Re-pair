import dao.RiparazioneDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import pojo.Riparazione;
import servlet.GestioneRiparazione;

import java.io.IOException;
import java.util.HashMap;

import static org.mockito.Mockito.*;

public class CreazioneRiparazioneTest
{
	private final String tipo = "crea";
	private final String campiVuoti = "I campi marca, modello, mail e costo non possono essere vuoti";
	private final String errore = "errore";
	private final String lunghezzaErrata = "Marca/modello non possono essere più di 10 caratteri<br>La mail non può eccedere 33 caratteri.";
	private final String formatoErrato = "Il formato di marca, modello o mail cliente non è corretto.";
	private final String costoErrato = "Il costo deve essere compreso fra 0 e 999.";
	private final String costoNumerico = "Il costo deve essere un valore numerico";
	
	private HttpServletRequest requestMock;
	private HttpServletResponse responseMock;
	private RequestDispatcher dispatcherMock;
	private ServletContext servletContext;
	private GestioneRiparazione servlet;
	
	@BeforeEach
	void setup()
	{
		requestMock = mock(HttpServletRequest.class);
		responseMock = mock(HttpServletResponse.class);
		dispatcherMock = mock(RequestDispatcher.class);
		servletContext = mock(ServletContext.class);
		servlet = new GestioneRiparazione();
	}
	
	
	/**TEST CASE ID: <code>TC_4.1</code><br>
	 * TEST FRAME: <code>marca_len1</code>
	 * */
	@Test
	void marcaVuota() throws ServletException, IOException
	{
		final String[] params = {"", "Galaxy", "100", "carla@azzurrini.com"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("tipo")).thenReturn(tipo);
		when(requestMock.getParameter("id")).thenReturn("0");
		
		when(requestMock.getParameter("marca")).thenReturn(params[0]);
		when(requestMock.getParameter("modello")).thenReturn(params[1]);
		when(requestMock.getParameter("costo")).thenReturn(params[2]);
		when(requestMock.getParameter("mailCliente")).thenReturn(params[3]);
		when(requestMock.getParameter("status")).thenReturn("0");
		when(requestMock.getParameter("nota")).thenReturn("");
		
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		servlet.doGet(requestMock, responseMock);
		
		verify(requestMock).setAttribute(errore, campiVuoti);
	}
	
	/**
	 * TEST CASE ID: <code>TC_4.2</code><br>
	 * TEST FRAME: <code>marca_len3</code>
	 * */
	@Test
	void marcaTooLong() throws ServletException, IOException
	{
		final String[] params = {"SamsungSams", "Galaxy", "100", "carla@azzurrini.com"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getServletContext()).thenReturn(servletContext);
		
		when(requestMock.getParameter("tipo")).thenReturn(tipo);
		when(requestMock.getParameter("id")).thenReturn("0");
		
		when(requestMock.getParameter("marca")).thenReturn(params[0]);
		when(requestMock.getParameter("modello")).thenReturn(params[1]);
		when(requestMock.getParameter("costo")).thenReturn(params[2]);
		when(requestMock.getParameter("mailCliente")).thenReturn(params[3]);
		when(requestMock.getParameter("status")).thenReturn("0");
		when(requestMock.getParameter("nota")).thenReturn("");
		
		doNothing().when(dispatcherMock).forward(requestMock,responseMock);
		
		servlet.doGet(requestMock,responseMock);
		
		verify(requestMock).setAttribute(errore,lunghezzaErrata);
	}
	
	/**
	 * TEST CASE ID: <code>TC_4.3</code><br>
	 * TEST FRAME: <code>marca_len2, marca_form1</code>
	 * */
	@Test
	void invalidMarca() throws ServletException, IOException
	{
		final String[] params = {"54m5ung", "Galaxy", "100", "carla@azzurrini.com"};
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("tipo")).thenReturn(tipo);
		when(requestMock.getParameter("id")).thenReturn("0");
		
		when(requestMock.getParameter("marca")).thenReturn(params[0]);
		when(requestMock.getParameter("modello")).thenReturn(params[1]);
		when(requestMock.getParameter("costo")).thenReturn(params[2]);
		when(requestMock.getParameter("mailCliente")).thenReturn(params[3]);
		when(requestMock.getParameter("status")).thenReturn("0");
		when(requestMock.getParameter("nota")).thenReturn("");
		
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		servlet.doGet(requestMock, responseMock);
		
		verify(requestMock).setAttribute(errore, formatoErrato);
	}
	
	/**
	 * TEST CASE ID: <code>TC_4.4</code><br>
	 * TEST FRAME: <code>marca_len2, marca_form2, mod_len1</code>
	 * */
	@Test
	void emptyModello() throws ServletException, IOException
	{
		final String[] params = {"Samsung", "", "100", "carla@azzurrini.com"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("tipo")).thenReturn(tipo);
		when(requestMock.getParameter("id")).thenReturn("0");
		
		when(requestMock.getParameter("marca")).thenReturn(params[0]);
		when(requestMock.getParameter("modello")).thenReturn(params[1]);
		when(requestMock.getParameter("costo")).thenReturn(params[2]);
		when(requestMock.getParameter("mailCliente")).thenReturn(params[3]);
		when(requestMock.getParameter("status")).thenReturn("0");
		when(requestMock.getParameter("nota")).thenReturn("");
		
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		servlet.doGet(requestMock, responseMock);
		
		verify(requestMock).setAttribute("errore", campiVuoti);
	}
	
	/**
	 * TEST CASE ID: <code>TC_4.5</code><br>
	 * TEST FRAME: <code>marca_len2, marca_form2,mod_len3</code>
	 * */
	@Test
	void modelloTooLong() throws ServletException, IOException
	{
		final String[] params = {"Samsung", "GalaxyGalax", "100", "carla@azzurrini.com"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("tipo")).thenReturn(tipo);
		when(requestMock.getParameter("id")).thenReturn("0");
		
		when(requestMock.getParameter("marca")).thenReturn(params[0]);
		when(requestMock.getParameter("modello")).thenReturn(params[1]);
		when(requestMock.getParameter("costo")).thenReturn(params[2]);
		when(requestMock.getParameter("mailCliente")).thenReturn(params[3]);
		when(requestMock.getParameter("status")).thenReturn("0");
		when(requestMock.getParameter("nota")).thenReturn("");
		
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		servlet.doGet(requestMock, responseMock);
		
		verify(requestMock).setAttribute(errore, lunghezzaErrata);
	}
	
	/**
	 * TEST CASE ID: <code>TC_4.6</code><br>
	 * TEST FRAME: <code>marca_len2, marca_form2,mod_len2, mod_form1</code>
	 * */
	@Test
	void invalidModello() throws ServletException, IOException
	{
		final String[] params = {"Samsung", "G4l4xy", "100", "carla@azzurrini.com"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("tipo")).thenReturn(tipo);
		when(requestMock.getParameter("id")).thenReturn("0");
		
		when(requestMock.getParameter("marca")).thenReturn(params[0]);
		when(requestMock.getParameter("modello")).thenReturn(params[1]);
		when(requestMock.getParameter("costo")).thenReturn(params[2]);
		when(requestMock.getParameter("mailCliente")).thenReturn(params[3]);
		when(requestMock.getParameter("status")).thenReturn("0");
		when(requestMock.getParameter("nota")).thenReturn("");
		
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		servlet.doGet(requestMock, responseMock);
		
		verify(requestMock).setAttribute(errore, formatoErrato);
	}
	
	/**
	 * TEST CASE ID: <code>TC_4.7</code><br>
	 * TEST FRAME: <code>marca_len2, marca_form2, mod_len2, mod_form2, cos_form1</code>
	 * */
	@Test
	void invalidCosto() throws ServletException, IOException
	{
		final String[] params = {"Samsung", "Galaxy", "abc", "carla@azzurrini.com"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("tipo")).thenReturn(tipo);
		when(requestMock.getParameter("id")).thenReturn("0");
		
		when(requestMock.getParameter("marca")).thenReturn(params[0]);
		when(requestMock.getParameter("modello")).thenReturn(params[1]);
		when(requestMock.getParameter("costo")).thenReturn(params[2]);
		when(requestMock.getParameter("mailCliente")).thenReturn(params[3]);
		when(requestMock.getParameter("status")).thenReturn("0");
		when(requestMock.getParameter("nota")).thenReturn("");
		
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		servlet.doGet(requestMock, responseMock);
		
		verify(requestMock).setAttribute(errore, costoNumerico);
	}
	/**
	 * TEST CASE ID: <code>TC_4.8</code><br>
	 * TEST FRAME: <code>marca_len2, marca_form2, mod_len2, mod_form2, cos_form2, cos_value1</code>
	 * */
	@Test
	void valoreCostoNegativo() throws ServletException, IOException
	{
		final String[] params = {"Samsung", "Galaxy", "-1", "carla@azzurrini.com"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("tipo")).thenReturn(tipo);
		when(requestMock.getParameter("id")).thenReturn("0");
		
		when(requestMock.getServletContext()).thenReturn(servletContext);
		when(requestMock.getParameter("marca")).thenReturn(params[0]);
		when(requestMock.getParameter("modello")).thenReturn(params[1]);
		when(requestMock.getParameter("costo")).thenReturn(params[2]);
		when(requestMock.getParameter("mailCliente")).thenReturn(params[3]);
		when(requestMock.getParameter("status")).thenReturn("0");
		when(requestMock.getParameter("nota")).thenReturn("");
		
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		servlet.doGet(requestMock, responseMock);
		
		verify(requestMock).setAttribute(errore, costoErrato);
	}
	
	/**
	 * TEST CASE ID: <code>TC_4.9</code><br>
	 * TEST FRAME: <code>marca_len2, marca_form2, mod_len2, mod_form2, cos_form2, cos_value3</code>
	 * */
	@Test
	void valoreCostoEccessivo() throws ServletException, IOException
	{
		final String[] params = {"Samsung", "Galaxy", "1000", "carla@azzurrini.com"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("tipo")).thenReturn(tipo);
		when(requestMock.getParameter("id")).thenReturn("0");
		when(requestMock.getServletContext()).thenReturn(servletContext);
		
		when(requestMock.getParameter("marca")).thenReturn(params[0]);
		when(requestMock.getParameter("modello")).thenReturn(params[1]);
		when(requestMock.getParameter("costo")).thenReturn(params[2]);
		when(requestMock.getParameter("mailCliente")).thenReturn(params[3]);
		when(requestMock.getParameter("status")).thenReturn("0");
		when(requestMock.getParameter("nota")).thenReturn("");
		
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		servlet.doGet(requestMock, responseMock);
		
		verify(requestMock).setAttribute(errore, costoErrato);
	}
	
	/**
	 * TEST CASE ID: <code>TC_4.10</code><br>
	 * TEST FRAME: <code>marca_len2, marca_form2, mod_len2, mod_form2, cos_form2, cos_value2, mail_len1</code>
	 * */
	@Test
	void emptyMail() throws ServletException, IOException
	{
		final String[] params = {"Samsung", "Galaxy", "100", ""};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("tipo")).thenReturn(tipo);
		when(requestMock.getParameter("id")).thenReturn("0");
		
		when(requestMock.getParameter("marca")).thenReturn(params[0]);
		when(requestMock.getParameter("modello")).thenReturn(params[1]);
		when(requestMock.getParameter("costo")).thenReturn(params[2]);
		when(requestMock.getParameter("mailCliente")).thenReturn(params[3]);
		when(requestMock.getParameter("status")).thenReturn("0");
		when(requestMock.getParameter("nota")).thenReturn("");
		
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		servlet.doGet(requestMock, responseMock);
		
		verify(requestMock).setAttribute(errore, campiVuoti);
	}
	/**
	 * TEST CASE ID: <code>TC_4.11</code><br>
	 * TEST FRAME: <code>marca_len2, marca_form2, mod_len2, mod_form2, cos_form2, cos_value2, mail_len2, mail_form1</code>
	 * */
	@Test
	void invalidMail() throws ServletException, IOException
	{
		final String[] params = {"Samsung", "Galaxy", "100", "carla@azzurrini@.com"};
		
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("tipo")).thenReturn(tipo);
		when(requestMock.getParameter("id")).thenReturn("0");
		
		when(requestMock.getParameter("marca")).thenReturn(params[0]);
		when(requestMock.getParameter("modello")).thenReturn(params[1]);
		when(requestMock.getParameter("costo")).thenReturn(params[2]);
		when(requestMock.getParameter("mailCliente")).thenReturn(params[3]);
		when(requestMock.getParameter("status")).thenReturn("0");
		when(requestMock.getParameter("nota")).thenReturn("");
		
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		servlet.doGet(requestMock, responseMock);
		
		verify(requestMock).setAttribute(errore, formatoErrato);
	}
	
	/**
	 * TEST CASE ID: <code>TC_4.12</code><br>
	 * TEST FRAME: <code>marca_len2, marca_form2, mod_len2, mod_form2, cos_form2, cos_value2, mail_len2, mail_form2</code>
	 * */
	@Test
	void everythingIsCorrect() throws ServletException, IOException  // happy flow
	{
		final String[] params = {"Samsung", "Galaxy", "100", "carla@azzurrini.com"};
		final MockedStatic<RiparazioneDAO> mockedRiparazioneDAO = mockStatic(RiparazioneDAO.class);
		final HashMap<Integer,Riparazione> map = new HashMap<>();
		
		when(requestMock.getServletContext()).thenReturn(servletContext);
		when(requestMock.getRequestDispatcher(anyString())).thenReturn(dispatcherMock);
		when(requestMock.getParameter("tipo")).thenReturn(tipo);
		when(requestMock.getParameter("id")).thenReturn("0");
		
		when(requestMock.getParameter("marca")).thenReturn(params[0]);
		when(requestMock.getParameter("modello")).thenReturn(params[1]);
		when(requestMock.getParameter("costo")).thenReturn(params[2]);
		when(requestMock.getParameter("mailCliente")).thenReturn(params[3]);
		when(requestMock.getParameter("status")).thenReturn("0");
		when(requestMock.getParameter("nota")).thenReturn("");
		
		mockedRiparazioneDAO.when(() -> RiparazioneDAO.doSave(any())).thenAnswer(invocationOnMock ->
		{
			final Riparazione r = invocationOnMock.getArgument(0);
			map.put(r.getId(), r);
			
			return null;
		});
		
		doNothing().when(dispatcherMock).forward(requestMock, responseMock);
		
		servlet.doGet(requestMock, responseMock);
		verify(servletContext).setAttribute(anyString(), any());
		
		assert !map.isEmpty();
		assert map.get(0).getMarca().equals(params[0]);
		assert map.get(0).getModello().equals(params[1]);
		assert map.get(0).getCosto() == Integer.parseInt(params[2]);
		assert map.get(0).getMailCliente().equals(params[3]);
		assert map.get(0).getStatus() == 0;
		assert map.get(0).getNota().isEmpty();
		
	}
}
