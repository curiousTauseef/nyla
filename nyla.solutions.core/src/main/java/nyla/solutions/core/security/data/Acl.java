package nyla.solutions.core.security.data;

import java.io.Serializable;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Set;

public interface Acl extends Serializable
{
	String getName();
	

	boolean addEntry(Principal caller, Principal principal, Permission permission);
	
	boolean addEntry(Principal caller, Principal principal, String permission);
	
	boolean addEntry(Principal caller, Principal principal, boolean negative, String permission);

	boolean addEntry(Principal caller, AccessControl accessControl);

	void mergePermissions(AccessControl from, AccessControl to);
	
	boolean checkPermission(Principal principal, Permission permission);
	
	boolean checkPermission(Set<Group> groups, Permission permission);

	boolean revokeAccess(Principal caller, AccessControl accessControl);
	
	boolean checkPermission(Principal aPrincipal, String aPermission);
	
	boolean isEmpty();
	
	void clear();

}