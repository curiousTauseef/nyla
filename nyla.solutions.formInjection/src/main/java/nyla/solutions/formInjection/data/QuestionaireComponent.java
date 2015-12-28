package nyla.solutions.formInjection.data;


import nyla.solutions.formInjection.QuestionaireVisitor;
import nyla.solutions.global.data.Textable;



/** 
 * <b>com.chubb.sa.demo.application.data.SmartComponent</b>
 * Abstract interface for application components 
 */
public interface QuestionaireComponent 
extends java.io.Serializable, Comparable, Textable 
{
   /**
    * 
    * @param aVisitor accept application visitor
    */
    public void accept(QuestionaireVisitor aVisitor);
    
        
}
