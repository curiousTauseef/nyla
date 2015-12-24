package org.solutions.form;


import org.solutions.form.data.*;


/**
 * 
 * <pre>
 * FormVisitor visitor design pattern for form component management
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public interface FormVisitor
{
    public boolean visitAnswers();
    public boolean visitQuestions();
    
   /**
    * Visit form element
    * @param aForm the form
    */
    public void visit(Form aForm);
    
    /**
     * Visit form question
     * @param aFormQuestion the form question
     */
    public void visit(FormQuestion aFormQuestion);

        
    /**
     * Visit the answer
     * @param aAnswer the answer
     */
    public void visit(FormAnswer aAnswer);
    
    /**
     * Visit form question choice
     * @param aFormChoice the form choice
     */
    public void visit(QuestionChoice aFormChoice);
        
    /**
     * 
     * @param aTable vist response table
     */
    public void visit(FormTable aTable);
            
    /**
     * 
     * @param aRow the form row
     */
    public void visit(FormRow aRow);
    
    
    /**
     * 
     * @param aFormColumn the form column
     */
    public void visit(FormColumn aFormColumn);    
}
