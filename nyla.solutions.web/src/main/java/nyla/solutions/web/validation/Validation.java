package nyla.solutions.web.validation;

import java.io.Serializable;
import java.util.StringTokenizer;

import nyla.solutions.core.util.Debugger;
import nyla.solutions.core.util.Text;
import nyla.solutions.web.WebTags;


/**
 * <pre>
 * Validation provides a set of functions to validate strings
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class Validation implements Serializable
{
   /**
    * 
    * @param aParameterName the parameter name
    * @return
    */
   public static Validation getInstance(String aInputName)
   {
      //Debugger.println("VALIDATOR PARAM ="+aParameterName);
      
      if (aInputName == null)
      {
         return null;
      }

      if(!aInputName.startsWith(WebTags.VALIDATION_PARM_PREFIX))
      {
	 return null;
      }
      
      //Debugger.println("VALIDATOR PARAM passed1 =");
      //parse name
      StringTokenizer tok =
         new StringTokenizer(Text.replace("__","_NONE_",aInputName), WebTags.VALIDED_PROP_SEPARATOR);
         
      //Debugger.println("VALIDATOR PARAM count ="+tok.countTokens());
      
      if (tok.countTokens() < WebTags.VALIDATION_PROP_LEN)
      {
         return null;
      }
      Validation validator = new Validation();
      //0
      validator.setValidateFlag(tok.nextToken());
      //1
      validator.setRequiredCode(tok.nextToken());
      //2
      validator.setTypeName(tok.nextToken());
      //3
      validator.setFormat(tok.nextToken());
      //4
      validator.setMinLength(tok.nextToken());
      //5
      validator.setMaxLength(tok.nextToken());
      //6
      validator.setFieldName(tok.nextToken());
      
      //Debugger.println("VALIDATOR "+validator);
      return validator;
   } //-----------------------------------------------------------

   /**
    * Constructor for Validation initalizes internal 
    * data settings.
    * 
    */
   public Validation()
   {
      super();
   }//--------------------------------------------
   /**
    * @return Returns the format.
    */
   public String getFormat()
   {
      return format;
   }
   /**
    * @param format The format to set.
    */
   public void setFormat(String format)
   {
      if (format == null)
         format = "";

      this.format = format;
   }
   /**
    * @return Returns the inputName.
    */
   public String getInputName()
   {
      return ValidationDecoder.createInputName(this);
   }//--------------------------------------------
   /**
    * @return Returns the maxLength.
    */
   public int getMaxLength()
   {
      return maxLength;
   }//--------------------------------------------
   /**
    * @param maxLength The maxLength to set.
    */
   public void setMaxLength(String maxLength)
   {   
      if(Text.isNull(maxLength))
         return;
      
      setMaxLength(Integer.parseInt(maxLength));
   }//--------------------------------------------
   /**
    * @param maxLength The maxLength to set.
    */
   public void setMaxLength(int maxLength)
   {

      this.maxLength = maxLength;
   }//--------------------------------------------
   /**
    * @return Returns the message.
    */
   public String getMessage()
   {
      return message;
   }//--------------------------------------------
   /**
    * @param message The message to set.
    */
   public void setMessage(String aMessage)
   {
      if (aMessage == null || aMessage.length() == 0)
         aMessage = "Invalid-Input";


      this.message = aMessage;
   }//--------------------------------------------
   /**
    * @return Returns the minLength.
    */
   public int getMinLength()
   {
      return minLength;
   }//--------------------------------------------
   /**
    * @param minLength The minLength to set.
    */
   public void setMinLength(String minLength)
   {
      if(Text.isNull(minLength))
         return;
      
      setMinLength(Integer.parseInt(minLength));
   }//--------------------------------------------
   /**
    * @param minLength The minLength to set.
    */
   public void setMinLength(int minLength)
   {
      this.minLength = minLength;
   }//--------------------------------------------
   /**
    * @return Returns the typeName.
    */
   public String getTypeName()
   {
      return typeName;
   }//--------------------------------------------
   /**
    * @param typeName The typeName to set.
    */
   public void setTypeName(String typeName)
   {
      if (typeName == null)
         typeName = "";

      this.typeName = typeName;
   }//--------------------------------------------
   /**
    * @return Returns the validateFlag.
    */
   public String getValidateFlag()
   {
      return validateFlag;
   }
   /**
    * @param validateFlag The validateFlag to set.
    */
   public void setValidateFlag(String validateFlag)
   {
      if (validateFlag == null)
         validateFlag = "";

      this.validateFlag = validateFlag;
   }//--------------------------------------------
   /**
    * @return  Boolean.TRUE.toString().equals(requiredCode)
    */
   public boolean isRequired()
   {
      return Boolean.TRUE.toString().equalsIgnoreCase(requiredCode);
   }//--------------------------------------------   
   protected String getErrorName()
   {
      return getInputName() + "_error";
   } //-------------------------------------------------------  
   /**
    * Validate the HTTP servlet request
    * @param request the HTTP request
    * @return true in the request parameter pass the validation rules
    */
    public boolean validate(javax.servlet.ServletRequest request)
       throws Exception
    {
       Debugger.println(this, "validate()");
       //Debugger.dump(this);



       request.setAttribute(getErrorName(), "");
      

          String value = request.getParameter(getInputName());
          Debugger.println(
             this,
             "validating value=" + value + " against=" + this.format);

          if (!Text.matches(value,this.format))
          {
             Debugger.println(this, "validation failed pattern="+this.format+" value="+value);
             //append to ErrorName value
             request.setAttribute(getErrorName(), getMessage());
             return false;
          }
          
          return true;
    }//------------------------------------------------------
   /**
    * @return Returns the fieldName.
    */
   public final String getFieldName()
   {
      return fieldName;
   }//--------------------------------------------
   /**
    * @param fieldName The fieldName to set.
    */
   public final void setFieldName(String fieldName)
   {
      if (fieldName == null)
         fieldName = "";

      this.fieldName = fieldName;
   }//--------------------------------------------
   /**
    * @return Returns the requiredCode.
    */
   public final String getRequiredCode()
   {
      return requiredCode;
   }//--------------------------------------------
   /**
    * @param requiredCode The requiredCode to set.
    */
   public final void setRequiredCode(String requiredCode)
   {
      if (requiredCode == null)
         requiredCode = "";

      this.requiredCode = requiredCode;
   }
   private String format = "";
   private String message = "";
   private String typeName = "";
   private String validateFlag = "v_";
   private String requiredCode = Boolean.FALSE.toString();
   private int minLength = 0;
   private int maxLength = 4000;
   private String fieldName = "";
   static final long serialVersionUID = Validation.class.getName().hashCode();
}
