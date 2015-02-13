/**
 * 
 */
package pl.linuxpolska.jboss.client;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

import org.jboss.ejb.client.ClusterNodeSelector;

/**
 * @author ghalajko
 *
 */
public class RandomClusterNodeSelector implements ClusterNodeSelector {
	
	private static final Logger LOG = Logger.getLogger(MainClient.class.getCanonicalName());
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jboss.ejb.client.ClusterNodeSelector#selectNode(java.lang.String,
	 * java.lang.String[], java.lang.String[])
	 */
	@Override
	public String selectNode(String clusterName, String[] connectedNodes,
			String[] totalAvailableNodes) {
		final Random random = new Random();
		final StringBuilder buf = new StringBuilder();
		buf.append("clusterName=").append(clusterName);
		buf.append(",connectedNodes=").append(Arrays.toString(connectedNodes));
		buf.append(",totalAvailableNodes=").append(
				Arrays.toString(totalAvailableNodes));

		// check if there are any connected nodes. If there are then just reuse
		// them
		if (connectedNodes.length > 0) {
			if (connectedNodes.length == 1) {
				String node = connectedNodes[0];
				buf.append(",select=").append(node);
				LOG.info(buf.toString());
				return node;
			}
			final int randomConnectedNode = random
					.nextInt(connectedNodes.length);
			String node = connectedNodes[randomConnectedNode];
			buf.append(",select=").append(node);
			LOG.info(buf.toString());
			return node;
		}
		// there are no connected nodes. so use the available nodes and let the
		// cluster context
		// establish a connection for the selected node
		if (totalAvailableNodes.length == 1) {
			String node = totalAvailableNodes[0];
			buf.append(",select=").append(node);
			LOG.info(buf.toString());
			return node;
		}
		final int randomSelection = random.nextInt(totalAvailableNodes.length);
		String node = totalAvailableNodes[randomSelection];
		buf.append(",select=").append(node);
		LOG.info(buf.toString());
		
		return node;
	}

}
