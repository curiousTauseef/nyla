package org.solutions.form.bre;

import java.io.Serializable;

import nyla.solutions.global.data.Data;
import nyla.solutions.global.data.NumberedProperty;

/**
 * <pre>
 * OperationParameter is a value object representation of the 
 * Operation_init_Param_tbl table and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class OperationParameter extends NumberedProperty
implements Serializable
{
   /**
    * Constructor for OperationParameter initializes internal 
    * data settings.
    * 
    */
   public OperationParameter()
   {
      super();
   }//--------------------------------------------
   /**
    * @return the operation id
    */
   public int getOperationID()
   {
      return operationID; 
   }//-------------------------------------------
   /**
    * @param aOperation the operation ID
    */
   public void setOperationID(int aOperationID)
   {
      this.operationID = aOperationID;
   }//--------------------------------------------   
   static final long serialVersionUID = OperationParameter.class.getName().hashCode();
   private int operationID = Data.NULL;
}
