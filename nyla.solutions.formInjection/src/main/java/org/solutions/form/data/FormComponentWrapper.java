package org.solutions.form.data;

import nyla.solutions.global.data.Textable;



/**
 * 
 * <pre>
 * FormComponentWrapper provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public interface FormComponentWrapper extends Textable{
    public abstract Object getManagedObject();
    public abstract Form getForm();
    public abstract Form retrieveForm();
}
