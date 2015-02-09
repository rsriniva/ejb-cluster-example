/**
 * 
 */
package pl.linuxpolska.jboss.client;

import java.util.logging.Logger;

import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.EJBClientContextListener;
import org.jboss.ejb.client.EJBReceiverContext;

/**
 * @author ghalajko
 *
 */
public class MyContextListener implements EJBClientContextListener {
	
	private static final Logger LOG = Logger.getLogger(MainClient.class.getCanonicalName());
	

	/* (non-Javadoc)
	 * @see org.jboss.ejb.client.EJBClientContextListener#contextClosed(org.jboss.ejb.client.EJBClientContext)
	 */
	@Override
	public void contextClosed(EJBClientContext ejbClientContext) {
		LOG.info("Close context");

	}

	/* (non-Javadoc)
	 * @see org.jboss.ejb.client.EJBClientContextListener#receiverRegistered(org.jboss.ejb.client.EJBReceiverContext)
	 */
	@Override
	public void receiverRegistered(EJBReceiverContext receiverContext) {
		LOG.info("Create new connection "+receiverContext.getReceiver());
	}

	/* (non-Javadoc)
	 * @see org.jboss.ejb.client.EJBClientContextListener#receiverUnRegistered(org.jboss.ejb.client.EJBReceiverContext)
	 */
	@Override
	public void receiverUnRegistered(EJBReceiverContext receiverContext) {
		LOG.info("Close connection "+receiverContext);
	}

}
