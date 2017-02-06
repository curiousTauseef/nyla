package nyla.solutions.web.validation;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import nyla.solutions.web.Web;
import nyla.solutions.web.WebTags;




/**
 * 
 * <pre>
 * ValidationDecoder provides a set of function encoder and decoder
 * Validation instances.
 * 
 * 
 * Sample validation text: v_false_textarea_^[0-9]$_0_2000_q8 
 * 
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class ValidationDecoder
{
    /**
     * Represent a single HTTP parameter validation
     */
    public static final int SINGLE = 1;
    /**
     * Represent a mutliple HTTP parameter validation i.e. check boxes
     */
    public static final int MULTIPLE = 2;
    
    
    /**
     * @return the error message for a given input parameter
     */
    public static String getMessage(
       String aInputName,
       javax.servlet.ServletRequest request)
    {
       String v = String.valueOf(request.getAttribute(aInputName + "_error"));
       if (v.equals("null"))
          return "";
       else
          return v;
    } //-----------------------------------------------------
    /**
     * @return the formated HTTP input parameter for a given request.
     */
    public static String getInputValue(
       String aInputName,
       javax.servlet.ServletRequest request)
    {
       String v = String.valueOf(request.getParameter(aInputName));
       if (v.equals("null"))
          return "";
       else
          return v;
    } //-----------------------------------------------------
    /**
     * 
     * @param aMap {v_opt_TXT_[0-9]+_0_20_fieldName_pk }
     * @param aFieldName nextParameter 
     * @return
     */
    public static String getInputValueByFieldName(
       HttpServletRequest aRequest,
       String aFieldName)
    {
       return getInputValueByFieldName(Web.toMap(aRequest), aFieldName);
    } //-----------------------------------
    /**
     * 
     * @param aMap {v_opt_TXT_[0-9]+_0_20_fieldName }
     * @param aFieldName nextParameter 
     * @return
     */
    public static String getInputValueByFieldName(Map<?,?> aMap, String aFieldName)
    {
       if(aFieldName == null || aMap == null)
       {
          return "";
       }
       
       String key = null;
       Validation validator = null;
       String fieldName = null;
       for (Iterator<?> keyI = aMap.keySet().iterator(); keyI.hasNext();)
       {
          key = String.valueOf(keyI.next());
          
          if(!key.startsWith("v_"))
             continue;
          
          fieldName = Validation.getInstance(key).getFieldName();
          if(fieldName.equals(aFieldName))
          {
             //return value
             return (String)aMap.get(key);
          }
          validator = Validation.getInstance(key);
          if (validator != null && validator.getFieldName().equals(aFieldName))
          {
             return (String) aMap.get(validator.getInputName());
          }
       }
       return (String) aMap.get(aFieldName);
    } //------------------------------------------------------
    /**
     * Create the HTML form name (i.e. v_false_textarea_^[0-9]$_0_2000_q8
     * @param aValidation
     * @param aIsOptional
     * @param aID
     * @param aSeperator
     * @return
     */
    public static String createInputName(
       Validation aValidation
       )
    {
       
       StringBuffer sb = new StringBuffer("v" + WebTags.VALIDED_PROP_SEPARATOR);
       
       
       if (aValidation.isRequired())
       {
          sb.append(Boolean.TRUE).append(WebTags.VALIDED_PROP_SEPARATOR);
       }
       else
       {
          sb.append(Boolean.FALSE).append(WebTags.VALIDED_PROP_SEPARATOR);
       }
       
       //Add response type code
       sb.append(aValidation.getTypeName()).append(WebTags.VALIDED_PROP_SEPARATOR);
       
       //REG EXP format
       sb.append(aValidation.getFormat()).append(WebTags.VALIDED_PROP_SEPARATOR);
       
         //Add minimum size
       sb.append(aValidation.getMinLength()).append(WebTags.VALIDED_PROP_SEPARATOR);
       
         //Get max size
       sb.append(aValidation.getMaxLength()).append(WebTags.VALIDED_PROP_SEPARATOR);
       
       //field name
       sb.append(aValidation.getFieldName());
       
       return sb.toString();
    }//----------------------------------------------------------    
}

