/*
 * Created on Nov 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.solutions.form.formatter.html;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.solutions.form.data.Form;
import org.solutions.form.data.FormComponent;
import org.solutions.form.data.FormQuestion;
import org.solutions.form.data.FormSection;

/**
 * 
 * <pre>
 * FormSectionView provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormSectionView extends FormComponentView 
{

    
    public FormSectionView(FormSection section) {
        setSection(section);
    }
    public Collection getQuestions() {
        return questions;
    }
    public FormSection getSection() {
        return section;
    }
    public void setSection(FormSection section) {
        this.section = section;
        Collection c = section.getFormQuestions();
        questions = new LinkedList();
        for (Iterator i = c.iterator(); i.hasNext(); )
            questions.add(new FormQuestionView((FormQuestion) i.next()));
    }
    public FormComponent getFormComponent() {
        return getSection();
    }
    public Form getForm() {
        return getSection().getForm();
    }
    public void setFormComponent(FormComponent formComponent) {
        setSection((FormSection) formComponent);
    }
    public String getNumber() {
        return String.valueOf(section.getNumber());
    }
    public String getName() {
        return "section";
    }
    private FormSection section;
    private Collection questions;
}
