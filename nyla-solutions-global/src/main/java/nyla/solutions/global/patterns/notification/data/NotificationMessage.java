package nyla.solutions.global.patterns.notification.data;
import java.io.Serializable;

/**
 * 
 * <pre>
 * NotificationMessage contains the topic and message details of the notification.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */

public class NotificationMessage
implements Serializable
{
   /**
    * 
    * Constructor for NotificationMessage initializes internal 
    * data settings.
    *
    */
    public NotificationMessage()
    {

        topic = null;

        producerReference = null;

        message = null;

        embeddedObject = null;

    }//--------------------------------------------
    /**
     * 
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }//--------------------------------------------
    /**
     * Set message
     * @param message new message
     */
    public void setMessage(String message)
    {

        if(message == null)
            message = "";

        this.message = message;
    }//--------------------------------------------
    /**
     * 
     * @return producerReference
     */
    public EndPointReference getProducerReference()

    {

        return producerReference;

    }//--------------------------------------------
    /**
     * Set producerReference
     * @param producerReference the producerReference
     */
    public void setProducerReference(EndPointReference producerReference)
    {

        this.producerReference = producerReference;
    }//--------------------------------------------
    /**
     * 
     * @return topic
     */
    public String getTopic()
    {
        return topic;
    }//--------------------------------------------
    /**
     * Set topic
     * @param topic the topic
     */
    public void setTopic(String topic)
    {

        if(topic == null)
            topic = "";

        this.topic = topic;
    }//--------------------------------------------
    /**
     * Set embeddedObject
     * @return embeddedObject
     */
    public Serializable acquireEmbeddedObject()
    {
        return embeddedObject;
    }//--------------------------------------------
    /**
     * Set embeddedObject
     * @param embeddedObject the embeddedObject
     */
    public void assignEmbeddedObject(Serializable embeddedObject)
    {

        if(embeddedObject == null)
            embeddedObject = "";

        this.embeddedObject = embeddedObject;
    }//--------------------------------------------
   /**
    * @return Returns the messageID.
    */
   public String acquireMessageID()
   {
      return messageID;
   }//--------------------------------------------
   /**
    * @param messageID The messageID to set.
    */
   public void assignMessageID(String messageID)
   {
      if (messageID == null)
         messageID = "";

      this.messageID = messageID;
   }//--------------------------------------------
   /**
    * 
    * @return messageID != null && messageID.length() != 0
    */
   public boolean hasID()
   {
      return messageID != null && messageID.length() != 0;
   }// --------------------------------------------

    private String messageID = null;
    
    private String topic;

    private EndPointReference producerReference;

    private String message;

    private Serializable embeddedObject;
    
    static final long serialVersionUID = NotificationMessage.class.getName()
   .hashCode();

}

