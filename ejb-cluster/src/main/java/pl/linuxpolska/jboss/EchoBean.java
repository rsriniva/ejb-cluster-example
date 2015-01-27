/**
 * 
 */
package pl.linuxpolska.jboss;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ghalajko
 *
 */
@Stateless
@Remote(IEcho.class)
public class EchoBean implements IEcho {
	/**
	 * slf4j Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(EchoBean.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see pl.linuxpolska.jboss.IEcho#echo(java.lang.String)
	 */
	@Override
	public String echo(String echo) {
		String host = System.getProperty("jboss.node.name");
		LOG.info("Execute echo host={} , msg={}", host, echo);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			LOG.error("Ups!", e);
		}
		return "Oki " + echo;
	}

}
