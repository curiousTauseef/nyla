package org.solutions.form.data;


import nyla.solutions.global.data.Textable;

import org.solutions.form.QuestionaireVisitor;



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
