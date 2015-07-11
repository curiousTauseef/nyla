package nyla.solutions.global.patterns.decorator;



import nyla.solutions.global.data.Textable;
import nyla.solutions.global.xml.XML;


public class XmlReflection implements Textable
{

   /**
    *
    * @see nyla.solutions.global.patterns.decorator.TextDecorator#getTarget(java.lang.Object)
    */
   public Object getTarget(Object target)
   {
      return target;
   }//--------------------------------------------

   /**
    *
    * @see nyla.solutions.global.patterns.decorator.TextDecorator#setTarget(java.lang.Object)
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
