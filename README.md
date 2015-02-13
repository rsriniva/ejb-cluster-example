# ejb-cluster-example
JBoss EJB Cluster Example

# Instalation

Run clean JBoss EAP 6.3 in domain mode 

__./jboss-eap-6.3/bin/domain.sh__


Run install script

__./jboss-eap-6.3/bin/jboss-cli.sh  -c --file=setup.cli__

Deploy file __ejb-cluster.jar__ on server and assign to __group-a__ and __group-b__


In the console logs, you should see:

__JBAS010238: Number of cluster members: 2__

Run __java -jar ejb-cluster-client-jar-with-dependencies.jar__ to test your configuration. 
