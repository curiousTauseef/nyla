package nyla.solutions.core.security.data;

import java.io.Serializable;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Set;

public interface Acl extends Serializable
{
	String getName();
	

	
	boolean checkPermission(Principal principal, Permission permission);
	
	boolean checkPermission(Set<Group> groups, Permission permission);


	
	boolean checkPermission(Principal aPrincipal, String aPermission);
	
	boolean isEmpty();


}