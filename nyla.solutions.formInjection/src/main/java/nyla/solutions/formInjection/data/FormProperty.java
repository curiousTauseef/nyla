/*
 * Created on Nov 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package nyla.solutions.formInjection.data;



/**
 * 
 * <pre>
 * FormProperty is a value object representation of the FormProperty table
 * and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormProperty extends FormComponentAttribute {
    public Integer formId;
    
    public FormProperty() {
        super();
    }
    public FormProperty(String key, String value) {
        super(key, value);
    }
    public Integer getFormId() {
        return formId;
    }
    protected void setFormId(Integer formId) {
        this.formId = formId;
    }
    static final long serialVersionUID = FormProperty.class.getName().hashCode();
}
