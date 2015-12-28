package nyla.solutions.formInjection.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nyla.solutions.formInjection.FormDelegate;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.security.data.SecurityClient;


/**
 * 
 * <pre>
 * FormHelper provides a set of functions to assist processing of forms
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormHelper {
    private static Map statusCache = getStatusCache();
    private static Map typeCache = getTypeCache();
    
    static {
        getStatusCache();
    }
    
    public static FormStatus getFormStatus(Integer id) {
        return (FormStatus) statusCache.get(id);
    }
    
    public static FormStatus getFormStatus(String name) {
        return (FormStatus) statusCache.get(name.toLowerCase());
    }
    
    public static FormType getFormType(Integer id) {
        return (FormType) typeCache.get(id);
    }
    public static FormType getFormType(String nameOrCode) {
        return (FormType) typeCache.get(nameOrCode.toLowerCase());
    }
    
    private static Map getStatusCache() 
    {
        try {
	        Map m = new HashMap();
	        Collection statuses = new FormDelegate(new SecurityClient()).retrieveFormStatuses();
	        for (Iterator i = statuses.iterator(); i.hasNext(); ) {
	            FormStatus status = (FormStatus) i.next();
	            m.put(new Integer(status.getPrimaryKey()), status);
	            m.put(status.getName().toLowerCase(), status);
	        }
	        return m;
        }
        catch (Exception e) {
            throw new SystemException("Error getting FormStatus collection");
        }
    }
    
    private static Map getTypeCache() 
    {
        try {
	        Map m = new HashMap();
	        Collection types = new FormDelegate(new SecurityClient()).retrieveFormTypes();
	        for (Iterator i = types.iterator(); i.hasNext(); ) {
	            FormType type = (FormType) i.next();
	            m.put(type.getKey(), type);
	            m.put(type.getName().toLowerCase(), type);
	            m.put(type.getCode().toLowerCase(), type);
	        }
	        return m;
        }
        catch (Exception e) {
            throw new SystemException("Error getting FormStatus collection");
        }
    }
}
