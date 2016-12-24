package nyla.solutions.global.patterns.notification.data;

import java.io.Serializable;

import nyla.solutions.core.data.Property;



/**

 * <pre>

 * A Web service endpoint is a (referenceable) entity, processor, or resource 

 * where Web service messages can be targeted. Endpoint references convey the 

 * information needed to identify/reference a Web service endpoint, and may be used 

 * in several different ways: 

 * 

 * endpoint references are suitable for conveying the information

 * needed to access a Web service endpoint, but are also used to provide 

 * addresses for individual messages sent to and from Web services. 

 * 

 * To deal with this last usage case this specification defines a family 

 * of message information headers that allows

 * uniform addressing of messages independent of underlying transport. 

 * These message information headers convey end-to-end message characteristics 

 * including addressing for source and destination endpoints as well as message identity.

 * 

 * Both of these constructs are designed to be extensible and re-usable so that other

 * specifications can build on and leverage endpoint references and messaging.

 * </pre> 

 * @author Gregory Green

 * @version 1.0

 */

public class EndPointAbstract implements Serializable, EndPointReference

{

   /**
    * Comment for <code>serialVersionUID</code>
    */
   private static final long serialVersionUID = -727073057710118278L;

   /**

    * 

    */

   public EndPointAbstract()

   {

      super();



   }//----------------------------------------------------   

   /**

    * @return Returns the address.

    */

   public String getAddress()

   {

      return address;

   }//----------------------------------------------------

   /**

    * @param address The address to set.

    */

   public void setAddress(String address)

   {

      if (address == null)

         address = "";



      this.address = address;

   }//----------------------------------------------------

   /**
    * @return Returns the referenceProperties.
    */

   public Property[] getReferenceProperties()
   {
	   if(referenceProperties == null)
		   return null;

      return referenceProperties.clone();

   }//----------------------------------------------------

   /**
    * @param referenceProperties The referenceProperties to set
    */

   public void setReferenceProperties(Property[] referenceProperties)
   {
	   if(referenceProperties == null)
		   this.referenceProperties = null;
	   else
		   this.referenceProperties = referenceProperties.clone();

   }//----------------------------------------------------

   /**

    * @return Returns the serviceName.

    */

   public String getServiceName()

   {

      return serviceName;

   }//----------------------------------------------------

   /**

    * @param serviceName The serviceName to set.

    */

   public void setServiceName(String serviceName)

   {

      if (serviceName == null)

         serviceName = "";



      this.serviceName = serviceName;

   }//----------------------------------------------------



   private String serviceName = "";

   private Property [] referenceProperties = null;

   private String address = null;

}

