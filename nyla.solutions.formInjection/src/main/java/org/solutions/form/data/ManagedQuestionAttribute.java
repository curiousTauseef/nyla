/*
 * Created on Nov 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.solutions.form.data;

/**
 * 
 * <pre>
 * ManagedQuestionAttribute provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class ManagedQuestionAttribute extends QuestionAttribute 
{
   static final long serialVersionUID = ManagedQuestionAttribute.class
   .getName().hashCode();

    public ManagedQuestionAttribute() {
        super();
    }
    public ManagedQuestionAttribute(String key, String value) {
        super(key, value);
    }
}
