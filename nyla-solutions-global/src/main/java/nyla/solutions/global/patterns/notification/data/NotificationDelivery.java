package nyla.solutions.global.patterns.notification.data;

import java.io.Serializable;

/**
 * <pre>
 * NotificationDelivery is a value object representation of the 
 * NotificationDelivery table and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class NotificationDelivery implements Serializable
{

   /**
    * Comment for <code>serialVersionUID</code>
    */
   private static final long serialVersionUID = -6414467730756414845L;
   /**
    * Constructor for NotificationDelivery initializes internal 
    * data settings.
    * 
    */
   public NotificationDelivery()
   {
      super();      
   }//--------------------------------------------
   public NotificationDelivery(String aMessageID, String aDestinationName)
   {
      this.setMessageID(aMessageID);
      this.setDestinationName(aDestinationName);
      
   }//--------------------------------------------
   /**
    * @return Returns the destinationName.
    */
   public String getDestinationName()
   {
      return destinationName;
   }//--------------------------------------------
   /**
    * @param destinationName The destinationName to set.
    */
   public void setDestinationName(String destinationName)
   {
      if (destinationName == null || destinationName.length() == 0)
         throw new IllegalArgumentException(
         "destinationName required in NotificationDelivery.setDestinationName");

      this.destinationName = destinationName;
   }//--------------------------------------------
   /**
    * @return Returns the messageID.
    */
   public String getMessageID()
   {
      return messageID;
   }//--------------------------------------------
   /**
    * @param messageID The messageID to set.
    */
   public void setMessageID(String messageID)
   {
      if (messageID == null || messageID.length() == 0)
         throw new IllegalArgumentException(
         "messageID required in NotificationDelivery.setMessageID");

      this.messageID = messageID;
   }//--------------------------------------------
   private String destinationName = null;
   private String messageID = null;
}
