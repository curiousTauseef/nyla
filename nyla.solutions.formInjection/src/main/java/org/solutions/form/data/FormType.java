package org.solutions.form.data;

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

   protected void setFormTypeCode(String formTypeCode)
   {
      this.formTypeCode = formTypeCode;
   }

   public Integer getFormTypeId()
   {
      return formTypeId;
   }

   protected void setFormTypeId(Integer formTypeId)
   {
      this.formTypeId = formTypeId;
   }

   public String getName()
   {
      return name;
   }

   protected void setName(String formTypeName)
   {
      this.name = formTypeName;
   }

   public Integer getVersion()
   {
      return version;
   }

   protected void setVersion(Integer version)
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
   protected String getFormTypeCode()
   {
      return formTypeCode;
   }

   static final long serialVersionUID = FormType.class.getName().hashCode();

   private Integer formTypeId;

   private String name;

   private String formTypeCode;

   private Integer version;
}
