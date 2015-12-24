package org.solutions.form.bre;
import java.io.Serializable;
import java.util.Collection;

import nyla.solutions.global.data.AbstractAudit;
import nyla.solutions.global.data.Criteria;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.patterns.servicefactory.ServiceFactory;


/**
 * <pre>
 * OperationBluePrint is a value object representation of the 
 * OPERATION_TBL table and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class OperationBluePrint extends AbstractAudit
implements Serializable
{

   /**
    * Constructor for OperationBluePrint initalizes internal 
    * data settings.
    * 
    */
   public OperationBluePrint()
   {
      super();
   }//--------------------------------------------
   /**
    * Constructor for OperationBluePrint initalizes internal 
    * data settings.
    * @param aPK the primary key
    * @throws IllegalArgumentException
    */
   public OperationBluePrint(int aPK) throws IllegalArgumentException
   {
      super(aPK);
   }//--------------------------------------------
   /**
    * Constructor for OperationBluePrint initalizes internal 
    * data settings.
    * @param aPK the operation primary key
    * @throws IllegalArgumentException
    */
   public OperationBluePrint(Criteria aPK) throws IllegalArgumentException
   {
      super(aPK);
   }//--------------------------------------------
   /**
    * Constructor for OperationBluePrint initalizes internal 
    * data settings.
    * @param aPK
    * @throws IllegalArgumentException
    */
   public OperationBluePrint(String aPK) throws IllegalArgumentException
   {
      super(aPK);
   }//--------------------------------------------
   /**
    * @return Returns the className.
    */
   public String getClassName()
   {
      return className;
   }//--------------------------------------------
   /**
    * @param className The className to set.
    */
   public void setClassName(String className)
   {
      if (className == null)
         className = "";

      this.className = className;
   }//--------------------------------------------
   /**
    * 
    * @return the operation
    */
   public Operation getOperation()
   {
      //if(operation == null )
      //{
         try
         {
         
            if(this.name == null || this.name.length() == 0)
               throw new IllegalArgumentException("Name set for operation PK="+getPrimaryKey()+" name="+name);
            
            Operation operation = null;
            
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            if(className == null)
            {
                  operation = (Operation)serviceFactory.create(this.getName());

            }
            else
            {
               operation = (Operation)Class.forName(className).newInstance();   
            }
            
            if(this.operationParameters != null && !this.operationParameters.isEmpty())
            {
               operation.setPrimaryKey(this.getPrimaryKey());
               operation.setName(this.getName());
               
               //initial operation inputs
               operation.initialize(getOperationParameterArray());
            }
            return operation;
         }
         catch(Exception e)
         {
            throw new SetupException(e);
         }
      //}
      
      

   }//--------------------------------------------
   /**
    * @return Returns the name.
    */
   public String getName()
   {
      return name;
   }//--------------------------------------------
   /**
    * @param name The name to set.
    */
   public void setName(String name)
   {
      if (name == null)
         name = "";

      this.name = name;
   }//--------------------------------------------
   private OperationParameter[] getOperationParameterArray()
   {
      OperationParameter[] paramArray = new OperationParameter[this.operationParameters.size()]; 
      System.arraycopy(operationParameters.toArray(),0,
                        paramArray,0,paramArray.length);
      
      return paramArray;
   }//--------------------------------------------
   private Collection<Object> operationParameters = null;
   private String name = null;
   private String className = null;
   static final long serialVersionUID = OperationBluePrint.class.getName()
   .hashCode();
}
