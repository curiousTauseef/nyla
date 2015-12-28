package nyla.solutions.formInjection.data;

/**
 * 
 * <pre>
 * FormType provides a set of functions to
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class FormType extends FormComponent
{

   public Object getKey()
   {
      return getFormTypeId();
   }

   public String getCode()
   {
      return formTypeCode;
   }

   public void setFormTypeCode(String formTypeCode)
   {
      this.formTypeCode = formTypeCode;
   }

   public Integer getFormTypeId()
   {
      return formTypeId;
   }

   public void setFormTypeId(Integer formTypeId)
   {
      this.formTypeId = formTypeId;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String formTypeName)
   {
      this.name = formTypeName;
   }

   public Integer getVersion()
   {
      return version;
   }

   public void setVersion(Integer version)
   {
      this.version = version;
   }

   public String getText()
   {
      return getName();
   }// --------------------------------------------

   /**
    * @return the formTypeCode
    */
   public String getFormTypeCode()
   {
      return formTypeCode;
   }

   static final long serialVersionUID = FormType.class.getName().hashCode();

   private Integer formTypeId;

   private String name;

   private String formTypeCode;

   private Integer version;
}
