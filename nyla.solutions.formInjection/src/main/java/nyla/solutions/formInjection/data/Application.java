
package nyla.solutions.formInjection.data;
import nyla.solutions.formInjection.FormGuide;
import nyla.solutions.global.util.Text;


/**
 * 
 * <pre>
 * Application is a value object representation of the Application table
 * and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class Application extends Form 
{
    
    public Application() {
        super();
    }
    public Application(ManagedForm form) {
        //TODO:
    }
    public boolean isReadOnly()
    {
       return false;
    }//--------------------------------------------
    /**
     * 
     * @return the form descrption form "description" FORM property
     */
    public String getDescription()
    {
       Object value = this.retrievePropertyValue(FormGuide.DESCRIPTION_PROP_NM);
       
       if(value == null)
          return "";
       
       return value.toString();
    }//--------------------------------------------
    /**
     * The application must maintain a relationship between some created forms 
     * and the orginating form (i.e. SMV). 
     * It will added as a form property "relatedFormID"
     * @param aRelatedFormID
     */
    public void setRelatedFormID(Integer  aRelatedFormID)
    {
       this.addProperty(FormGuide.RELATED_FORM_ID_PROP_NM, String.valueOf(aRelatedFormID));
    }//--------------------------------------------
    /**
     * The application must maintain a relationship between some created forms 
     * and the orginating form (i.e. SMV). 
     * It will added as a form property "relatedFormID"
     * @return integer version of retrievePropertyValue(FormGuide.RELATED_FORM_ID_PROP_NM)
     */
    public Integer getRelatedFormID()
    {
       Object relateFormID = this.retrievePropertyValue(FormGuide.RELATED_FORM_ID_PROP_NM);
       
       if(relateFormID == null || 
          !Text.isInteger(relateFormID.toString()))
          return null;
       
       return new Integer(relateFormID.toString());
    }//--------------------------------------------

    static final long serialVersionUID = Application.class.getName().hashCode();    

}
