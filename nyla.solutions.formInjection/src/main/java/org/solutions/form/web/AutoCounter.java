package org.solutions.form.web;

import java.io.Serializable;


/**
 * 
 * <pre>
 * AutoCounter provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class AutoCounter implements Serializable 
{
   static final long serialVersionUID = AutoCounter.class.getName().hashCode();
   
    private int sectionId;
    private int formComponentId;
    private int count;
    private AutoCounter parent;
    //private AutoCounter child;
    
    public AutoCounter() {
    }
    
    public AutoCounter(int sectionId) {
        this.sectionId = sectionId;
    }
    
    public int getSectionId() {
        return sectionId;
    }
    
    public int getFormComponentId() {
        return formComponentId;
    }
    
    public void setFormComponentId(int formComponentId) {
        this.formComponentId = formComponentId;
    }
        
    public int increment() {
        // remove child counter
        //child = null;
        return ++count;
    }
    
    public int getNumber() {
        return count;
    }
    
    public void setNumber(int count) {
        this.count = count;
    }
    
    public AutoCounter getParent() {
        return parent;
    }
    
    public void setParent(AutoCounter counter) {
        this.parent = counter;
    }
    /*
    public AutoCounter getChild() {
        return child;
    }
    
    public void setChild(AutoCounter child) {
        this.child = child;
    }
    */
    public String toString() {
        if (parent == null)
            return sectionId + "-" + count;
        return "<span class='textIndent'>" + parent.toString() + "." + count + "</span>";
    }
}
