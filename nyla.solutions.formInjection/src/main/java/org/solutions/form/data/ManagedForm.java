package org.solutions.form.data;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class ManagedForm extends FormComponent 
{
    private Integer formId;
    private String formTypeCode;
    private Integer formStatusId;
    private Map formProps = new HashMap();

    public Integer getFormId() {
        return replaceWithNull(formId);
    }
    protected void setFormId(Integer formId) {
        this.formId = replaceWithNull(formId);
    }
    public Object getKey() {
        return getFormId();
    }
    public Integer getFormStatusId() {
        return replaceWithNull(formStatusId);
    }
    public void setFormStatusId(Integer formStatusId) {
        this.formStatusId = replaceWithNull(formStatusId);
    }
    public String getFormTypeCode() {
        return formTypeCode;
    }
    public void setFormTypeCode(String aFormTypeCode) {
        this.formTypeCode = aFormTypeCode;
    }// --------------------------------------------

    
    public Map getFormProps() {
        return formProps;
    }
    protected void setFormProps(Map formProps) {
    	if (formProps == null)
    		this.formProps.clear();
        for (Iterator i = formProps.values().iterator(); i.hasNext(); ) {
        	FormProperty prop = (FormProperty) i.next();
        	addProperty(prop);
        }
    }// --------------------------------------------

    public void setProperties(Collection formProps) {
    if (formProps == null || formProps.isEmpty())
        this.formProps.clear();
       for (Iterator i = formProps.iterator(); i.hasNext(); ) {
        FormProperty prop = (FormProperty) i.next();
        addProperty(prop);
       }
   }// --------------------------------------------

    public void addProperty(FormProperty property) {
    	if (formProps == null)
    		formProps = new HashMap();
        if (property != null) {
            property.setFormId(getFormId());
            formProps.put(property.getName(), property);
        }
    }
    public FormProperty getProperty(String key) {
        return (FormProperty) formProps.get(key);
    }
    public FormProperty removeProperty(String key) {
        return (FormProperty) formProps.remove(key);
    }
    public String getText() {
        return getFormId() != null ? String.valueOf(getFormId()) : "";
    }
    static final long serialVersionUID = ManagedForm.class.getName().hashCode();
}
