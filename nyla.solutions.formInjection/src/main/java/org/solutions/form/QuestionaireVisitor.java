package org.solutions.form;

import java.io.Serializable;

import org.solutions.form.data.*;


/**
 * 
 * <pre>
 * FormVisitor visitor design pattern for form component management
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public interface QuestionaireVisitor extends Serializable
{
   /**
    * 
    * @param aResponseTable the table to visit
    */
   public void visit(ResponseTable aResponseTable);
   
    /**
     * Visit section element 
     * @param aSection the section element
     */
    public void visit(Section aSection);

    
    /**
     * Visit question
     * @param aQuestion the form question
     */
    public void visit(Question aQuestion);
    
    /**
     * Visit question choice
     * @param aChoice
     */
    public void visit(QuestionChoice aChoice);
        
    /**
     * 
     * @param aColumn the column
     */
    public void visit(Column aColumn);
    
    /**
     * 
     * @param aRow the table row
     */
    public void visit(Row aRow);
    
}
