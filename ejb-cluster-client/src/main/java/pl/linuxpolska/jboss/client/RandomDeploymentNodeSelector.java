/**
 * 
 */
package pl.linuxpolska.jboss.client;

import java.util.Arrays;
import java.util.Random;

import org.jboss.ejb.client.DeploymentNodeSelector;

/**
 * @author ghalajko
 *
 */
public class RandomDeploymentNodeSelector implements DeploymentNodeSelector {
	@Override
	public String selectNode(String[] eligibleNodes, String appName,
			String moduleName, String distinctName) {
		// Just a single node available, so just return it
		if (eligibleNodes.length == 1) {
			return eligibleNodes[0];
		}
		final Random random = new Random();
		final int randomSelection = random.nextInt(eligibleNodes.length);
		String node = eligibleNodes[randomSelection];
		
		System.out.println("Servers:"+Arrays.toString(eligibleNodes)+" selected:"+node);
		
		return node;
	}
}
