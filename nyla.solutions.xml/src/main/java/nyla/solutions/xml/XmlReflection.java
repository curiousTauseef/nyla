package nyla.solutions.xml;



import nyla.solutions.core.data.Textable;


public class XmlReflection implements Textable
{

   /**
    *
    * @see nyla.solutions.core.patterns.decorator.TextDecorator#getTarget(java.lang.Object)
    */
   public Object getTarget(Object target)
   {
      return target;
   }//--------------------------------------------

   /**
    *
    * @see nyla.solutions.core.patterns.decorator.TextDecorator#setTarget(java.lang.Object)
    */
   public void setTarget(Object target)
   {
      this.target = target;
   }//--------------------------------------------
   /**
    *
    * @see nyla.solutions.global.data.Textable#getText()
    */
   public String getText()
   {          
         return XML.getInterpreter().toXML(target);

   }//--------------------------------------------
 
   private Object target = null;
   

}