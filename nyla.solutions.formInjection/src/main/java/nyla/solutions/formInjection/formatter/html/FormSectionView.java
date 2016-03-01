/*
 * Created on Nov 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package nyla.solutions.formInjection.formatter.html;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.data.FormSection;

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
    public Collection<FormQuestionView> getQuestions() {
        return questions;
    }
    public FormSection getSection() {
        return section;
    }
    public void setSection(FormSection section) {
        this.section = section;
        Collection<FormQuestion> c = section.getFormQuestions();
        questions = new LinkedList<FormQuestionView>();
        for (Iterator<FormQuestion> i = c.iterator(); i.hasNext(); )
            questions.add(new FormQuestionView(i.next()));
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
    private Collection<FormQuestionView> questions;
}
