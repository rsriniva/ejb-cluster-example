package pl.linuxpolska.jboss.client;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.ejb.client.EJBClientContext;

import pl.linuxpolska.jboss.IEcho;

/**
 * 
 * @author ghalajko
 *
 */
public class MainClient {
	/**
	 * LOGS
	 */
	private static final Logger LOG = Logger.getLogger(MainClient.class
			.getCanonicalName());

	/**
	 * 
	 */
	private static final int LOOPS = 1000;
	/**
	 * Context
	 */
	private static final Context context;

	/**
	 * 
	 */
	static {
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES,
				"org.jboss.ejb.client.naming");
		try {
			context = new InitialContext(jndiProperties);
		} catch (NamingException e) {
			throw new RuntimeException("Ups! No Jboss Client divers");
		}
	}

	/**
	 * Main
	 * 
	 * @param args
	 * @throws NamingException
	 */
	public static void main(String[] args) throws NamingException {
		registerListener();

		final IEcho echo = lookupEcho();

		for (int i = 0; i < LOOPS; i++) {
			LOG.info("Send to server " + i);
			try {
				echo.echo("echo" + i);
			} catch (EJBException e) {
				LOG.log(Level.FINEST, "Error on " + i, e);
			}

		}
	}

	/**
	 * 
	 * @return
	 * @throws NamingException
	 */
	private static IEcho lookupEcho() throws NamingException {
		return (IEcho) context.lookup("ejb:/ejb-cluster/EchoBean!"
				+ IEcho.class.getName());
	}

	/**
	 * 
	 */
	private static void registerListener() {
		EJBClientContext ccontext = EJBClientContext.getCurrent();
		ccontext.registerEJBClientContextListener(new MyContextListener());
	}
}
