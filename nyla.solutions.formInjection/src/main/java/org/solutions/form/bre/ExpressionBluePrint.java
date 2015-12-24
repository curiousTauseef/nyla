package org.solutions.form.bre;

import java.io.Serializable;

import nyla.solutions.global.data.Data;
import nyla.solutions.global.data.Mappable;
import nyla.solutions.global.exception.SetupException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.patterns.servicefactory.ServiceFactory;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.util.Text;



/**
 * ExpressionBluePrint is a value object representation of the LogicalExpression
 * table and associated entities.
 * 
 * 
 * @author Gregory Green
 * @version 1.0
 */
public class ExpressionBluePrint implements Cloneable, Serializable, Mappable
{
   /**
    * 
    * @return this.expressionBluePrint1 != null
    */
   public boolean hasExpression1()
   {
      return this.expressionBluePrint1 != null;
   }//--------------------------------------------
   /**
    * 
    * @return this.expressionBluePrint2 != null
    */
   public boolean hasExpression2()
   {
      return this.expressionBluePrint2 != null;
   }//--------------------------------------------   
   /**
    * @return Returns the code. ('Q1', 'not E1', 'E1 and E2', 'Q1=Q2', 'E1 or
    *         E2', 'Q1=C', 'Q1>=Q2', 'Q1>Q2', 'Q1 <=Q2', 'Q1 <Q2')
    */
   public String getCode()
   {
      return code;
   }//--------------------------------------------

   /**
    * @param code
    *           The code to set. 'Q1', 'not E1', 'E1 and E2', 'Q1=Q2', 'E1 or
    *           E2', 'Q1=C', 'Q1>=Q2', 'Q1>Q2', 'Q1 <=Q2', 'Q1 <Q2'
    */
   public void setCode(String code)
   {
      if (code == null)
         code = "";

      this.code = code;
   }//--------------------------------------------

   /**
    * 
    * @see java.lang.Object#clone()
    */
   public Object clone() throws CloneNotSupportedException
   {
      return super.clone();
   }//--------------------------------------------
   /**
    * @return Returns the question1PK.
    */
   public Integer getQuestion1PK()
   {
      if(question1PK == null )
         throw new SetupException("question1PK required in "+this);
      
      return question1PK;
   }//--------------------------------------------

   /**
    * @param question1PK
    *           The question1PK to set.
    */
   public void setQuestion1PK(Integer question1PK)
   {

      this.question1PK = question1PK;
   }//--------------------------------------------

   /**
    * @return Returns the question2PK.
    */
   public Integer getQuestion2PK()
   {
      if(question2PK == null )
         throw new SetupException("question2PK required in "+this);
      
      return question2PK;
   }//--------------------------------------------

   /**
    * @param question2PK
    *           The question2PK to set.
    */
   public void setQuestion2PK(Integer question2PK)
   {

      this.question2PK = question2PK;
   }//--------------------------------------------

   /**
    * @return Returns the logicalExpression.
    */
   public LogicalExpression getLogicalExpression()
   {
      if (logicalExpression == null)
         logicalExpression = createLogicalExpression(this);
      return logicalExpression;
   }//--------------------------------------------

   /**
    * Logical Expression factory method
    * 
    * @param aCode
    * @return LogicalExpression
    */
   protected static LogicalExpression createLogicalExpression(
                                                              ExpressionBluePrint aExpressionBluePrint)
   {
      if (aExpressionBluePrint == null)
         throw new IllegalArgumentException(
         "aExpressionBluePrint required in ExpressionBluePrint");

      String expressionClassName = aExpressionBluePrint
      .getExpressionClassName();

      if (Text.isNull(expressionClassName))
      {
         return (LogicalExpression)ServiceFactory.getInstance().create(aExpressionBluePrint.getCode());
      }

      Debugger.println(ExpressionBluePrint.class,
      "creating logical expression " + expressionClassName);

      try
      {
         LogicalExpression exp = (LogicalExpression) Class.forName(
         expressionClassName).newInstance();
         exp.initialize(aExpressionBluePrint);

         return exp;
      }
      catch(Exception e)
      {
         throw new SetupException(aExpressionBluePrint+Debugger.stackTrace(e));
      }


   }//--------------------------------------------------
   /**
    * @return Returns the expressionBluePrint1.
    */
   public ExpressionBluePrint getExpressionBluePrint1()
   {
      if (expressionBluePrint1 == null)
         throw new SetupException("Expression 1 not provided");

      //check for circular expressions
      checkCircularExpressions(this.expressionBluePrint2,
      this.expressionBluePrint1);

      if (expressionBluePrint1.equals(expressionBluePrint2))
      {
         throw new SetupException(
         "Circular reference constraint violation, Expression1 with same PK a the parent expression blue print "
         + this.getCode());
      }

      return expressionBluePrint1;
   }//--------------------------------------------

   /**
    * @param expressionBluePrint1
    *           The expressionBluePrint1 to set.
    */
   public void setExpressionBluePrint1(ExpressionBluePrint aExpressionBluePrint1)
   {
      if (aExpressionBluePrint1 == null)
         throw new IllegalArgumentException(
         "expressionBluePrint1 required in ExpressionBluePrint.setExpressionBluePrint1");

      if (Text.isNull(aExpressionBluePrint1.getCode()))
         throw new IllegalArgumentException(
         "aExpressionBluePrint1.getCode() > 0 required in ExpressionBluePrint.setExpressionBluePrint1");

      if (aExpressionBluePrint1.equals(this))
      {
         throw new SetupException(
         "Circular reference constraint violation, Expression1 with same PK a the parent expression blue print "
         + this.getCode());
      }

      //check for circular expressions
      checkCircularExpressions(aExpressionBluePrint1, this.expressionBluePrint2);

      this.expressionBluePrint1 = aExpressionBluePrint1;
   }//--------------------------------------------

   /**
    * @return Returns the expressionBluePrint2.
    */
   public ExpressionBluePrint getExpressionBluePrint2()
   {
      if (expressionBluePrint2 == null)
         throw new IllegalStateException("Expression 2 not provided");

      if (expressionBluePrint2.equals(this))
      {
         throw new SetupException(
         "Circular reference constraint violation, Expression2 with same PK a the parent expression blue print "
         + this);
      }
      //check for circular expressions
      checkCircularExpressions(this.expressionBluePrint2,
      this.expressionBluePrint1);

      return expressionBluePrint2;
   }//--------------------------------------------

   /**
    * @param expressionBluePrint2
    *           The expressionBluePrint2 to set.
    */
   public void setExpressionBluePrint2(ExpressionBluePrint aExpressionBluePrint2)
   {
      if (aExpressionBluePrint2 == null)
         throw new IllegalArgumentException(
         "expressionBluePrint2 required in ExpressionBluePrint.setExpressionBluePrint2");

      if (aExpressionBluePrint2.equals(this))
      {
         throw new SetupException(
         "Circular reference constraint violation, Expression2 with same PK a the parent expression blue print "
         + this);
      }
      //check against expression 1
      checkCircularExpressions(aExpressionBluePrint2, this.expressionBluePrint1);

      this.expressionBluePrint2 = aExpressionBluePrint2;
   }//--------------------------------------------

   private static void checkCircularExpressions(
                                                ExpressionBluePrint aExpressionBluePrint1,
                                                ExpressionBluePrint aExpressionBluePrint2)
   {
      if (aExpressionBluePrint1 == null)
         return;

      if (aExpressionBluePrint2 == null)
         return;

      if (Text.isNull(aExpressionBluePrint1.getCode()))
         throw new SetupException(
         "aExpressionBluePrint1.getCode() > 0 required in ExpressionBluePrint.checkCircularExpressions");

      if (Text.isNull(aExpressionBluePrint2.getCode()))
         throw new SetupException(
         "aExpressionBluePrint2.getCode() > 0 required in ExpressionBluePrint.checkCircularExpressions");

      if (aExpressionBluePrint1.getCode().equals(
      aExpressionBluePrint2.getCode()))
         throw new SetupException(
         "A single expression cannot reference two circular expressions PK "
         + " aExpressionBluePrint1.getCode()="
         + aExpressionBluePrint1.getCode()
         + " aExpressionBluePrint2.getCode()="
         + aExpressionBluePrint2.getCode());

   }//--------------------------------------------

   /**
    * 
    * @return this.getCode()
    * @see org.solutions.data.Mappable#getKey()
    */
   public Object getKey()
   {
      return this.getFormTypePK()+"-"+this.getCode();
   }//--------------------------------------------
   /**
    * @deprecated please set formTypePK and code
    * @see org.solutions.data.Key#setKey(java.lang.Object)
    */
   public void setKey(Object key)
   {
   }//--------------------------------------------
   /**
    * @return this
    * @see org.solutions.data.Mappable#getValue()
    */
   public Object getValue()
   {
      return this;
   }//----------------------------------------

   /**
    * @return Returns the expressionClassName.
    */
   public String getExpressionClassName()
   {
      return expressionClassName;
   }//--------------------------------------------
   /**
    * @param expressionClassName
    *           The expressionClassName to set.
    */
   public void setExpressionClassName(Class expressionClass)
   {
      if (expressionClass == null)
         throw new IllegalArgumentException(
         "expressionClass required in ExpressionBluePrint.setExpressionClassName");
      
      this.setExpressionClassName(expressionClass.getName());
      
   }//--------------------------------------------
   /**
    * @param expressionClassName
    *           The expressionClassName to set.
    */
   public void setExpressionClassName(String expressionClassName)
   {
      if (expressionClassName == null)
         expressionClassName = "";

      this.expressionClassName = expressionClassName;
   }//--------------------------------------------

   /**
    * @return Returns the formTypePK.
    */
   public int getFormTypePK()
   {
      if(this.formTypePK < 1)
         throw new SystemException("formTypePK < 1");
      
      return formTypePK;
   }//--------------------------------------------

   /**
    * @param formTypePK
    *           The formTypePK to set.
    */
   public void setFormTypePK(int formTypePK)
   {
      if (formTypePK < 1)
         throw new IllegalArgumentException("formTypePK < 1");

      this.formTypePK = formTypePK;
   }//--------------------------------------------

   /**
    * 
    * Override equal operations
    * 
    * @return true if aOther equals this
    */
   public boolean equals(Object aOther)
   {
      if (aOther == null)
         return false;

      if (aOther == this)
         return true;

      if (this.code == null)
         return false;

      if (!(aOther instanceof ExpressionBluePrint))
         return false;

      ExpressionBluePrint other = (ExpressionBluePrint) aOther;

      return this.code.equals(other.getCode());
   }//--------------------------------------------
   
   /**
    * @return Returns the inputArgument1.
    */
   public String getInputArgument1()
   {
      if(Text.isNull(inputArgument1))
         throw new SetupException(" inputArgument1 required ");
      
      return inputArgument1;
   }//--------------------------------------------
   /**
    * @param inputArgument1 The inputArgument1 to set.
    */
   public void setInputArgument1(String inputArgument1)
   {
      if (inputArgument1 == null)
         inputArgument1 = "";

      this.inputArgument1 = inputArgument1;
   }//--------------------------------------------
   /**
    * @return Returns the inputArgument2.
    */
   public String getInputArgument2()
   {
      if(Text.isNull(inputArgument2))
         throw new SetupException(" inputArgument2 required ");
      
      return inputArgument2;
   }//--------------------------------------------
   /**
    * @param inputArgument2 The inputArgument2 to set.
    */
   public void setInputArgument2(String inputArgument2)
   {
      if (inputArgument2 == null)
         inputArgument2 = "";

      this.inputArgument2 = inputArgument2;
   }//--------------------------------------------
   /**
    * @return Returns the inputArgument3.
    */
   public String getInputArgument3()
   {
      if(Text.isNull(inputArgument3))
         throw new SetupException(" inputArgument3 required ");      
      return inputArgument3;
   }//--------------------------------------------
   /**
    * @param inputArgument3 The inputArgument3 to set.
    */
   public void setInputArgument3(String inputArgument3)
   {
      if (inputArgument3 == null)
         inputArgument3 = "";

      this.inputArgument3 = inputArgument3;
   }//--------------------------------------------
   /**
    * @return Returns the inputArgument4.
    */
   public String getInputArgument4()
   {
      if(Text.isNull(inputArgument4))
         throw new SetupException(" inputArgument4 required ");
      
      return inputArgument4;
   }//--------------------------------------------
   /**
    * @param inputArgument4 The inputArgument4 to set.
    */
   public void setInputArgument4(String inputArgument4)
   {
      if (inputArgument4 == null)
         inputArgument4 = "";

      this.inputArgument4 = inputArgument4;
   }//--------------------------------------------
   
   /**
    * @return Returns the columnNumber.
    */
   public Integer getColumnNumber()
   {
      if (columnNumber == null)
         throw new SetupException("columnNumber 1 not provided");
      
      return columnNumber;
   }//--------------------------------------------
   /**
    * @param columnNumber The columnNumber to set.
    */
   public void setColumnNumber(Integer columnNumber)
   {
      if (columnNumber == null)
         throw new IllegalArgumentException(
         "columnNumber required in ExpressionBluePrint.setColumnNumber");
      this.columnNumber = columnNumber;
   }//--------------------------------------------
   /**
    * @return Returns the tablePK.
    */
   public Integer getTablePK()
   {
      if(tablePK == null)
         throw new SetupException(" tablePK required ");
      
      return tablePK;
   }//--------------------------------------------
   /**
    * @param tablePK The tablePK to set.
    */
   public void setTablePK(Integer tablePK)
   {
      if (tablePK == null)
         throw new IllegalArgumentException(
         "tablePK required in ExpressionBluePrint.setTablePK");

      this.tablePK = tablePK;
   }//--------------------------------------------
   /**
    * 
    * @return this.tablePK != null && this.columnNumber != null
    */
   public boolean hasColumn()
   {
      return this.tablePK != null && this.columnNumber != null;
   }//--------------------------------------------
   /**
    * 
    * @see java.lang.Object#toString()
    */
   public String toString()
   {
    
      StringBuffer text = new StringBuffer();
      text.append(getClass().getName());
      text.append(" code=").append(code);      
      text.append(" formTypePK=").append(formTypePK);
      text.append(" tablePK=").append(tablePK);
      text.append(" columnNumber=").append(columnNumber);
      text.append(" question1PK=").append(question1PK);
      text.append(" question2PK=").append(question2PK);
      text.append(" question2PK=").append(question2PK);
      
      return text.toString();
   }//--------------------------------------------
   private int formTypePK = Data.NULL;

   private Integer tablePK = null;
   private Integer columnNumber = null;
   private Integer question1PK = null;
   private Integer question2PK = null;
   private ExpressionBluePrint expressionBluePrint1 = null;
   private ExpressionBluePrint expressionBluePrint2 = null;
   private String code = null;
   private String inputArgument1 = "";
   private String inputArgument2 = "";
   private String inputArgument3 = "";
   private String inputArgument4 = "";
   private LogicalExpression logicalExpression = null;
   private String expressionClassName = null;
   static final long serialVersionUID = ExpressionBluePrint.class.getName()
   .hashCode();
}
