package nyla.solutions.global.patterns.notification.data;

import nyla.solutions.core.data.Property;

/**
 * <pre>
 * EndPoint notification service endpoint reference
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public interface EndPointReference
{

	/**
	 * @return Returns the address
	 */
	public abstract String getAddress();

	/**
	 * @param address The address to set.
	 */
	public abstract void setAddress(String address);

	/**
	 * @return Returns the referenceProperties.
	 */

	public abstract Property[] getReferenceProperties();

	/**
	 * 
	 * @param referenceProperties The referenceProperties to set.
	 */

	public abstract void setReferenceProperties(Property[] referenceProperties);

	/**
	 * 
	 * @return Returns the serviceName.
	 */

	public abstract String getServiceName();

	/**
	 * 
	 * @param serviceName The serviceName to set.
	 */

	public abstract void setServiceName(String serviceName);

}