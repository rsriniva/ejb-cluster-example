package pl.linuxpolska.jboss.client;

import java.util.Hashtable;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import pl.linuxpolska.jboss.IEcho;

/**
 * 
 * @author ghalajko
 *
 */
public class MainClient {

	private static final int LOOPS = 100;

	/**
	 * Main
	 * 
	 * @param args
	 * @throws NamingException
	 */
	public static void main(String[] args) throws NamingException {
		final IEcho echo = lookupEcho();

		for (int i = 0; i < LOOPS; i++) {
			System.out.println("Send to server " + i);
			try {
				echo.echo("echo" + i);
			} catch (EJBException e) {
				System.err.println("Error on "+i);
			}

		}
	}

	/**
	 * 
	 * @return
	 * @throws NamingException
	 */
	private static IEcho lookupEcho() throws NamingException {
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);

		return (IEcho) context.lookup("ejb:/ejb-cluster/EchoBean!"
				+ IEcho.class.getName());
	}
}
