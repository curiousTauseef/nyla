package org.solutions.form.data;


/**
 * 
 * <pre>
 * FormStatus is a value object representation of the FormStatus table
 * and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormStatus extends FormComponent {
    private Integer statusId;
    private String name;
    
    public Object getKey() {
        return getStatusId();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getStatusId() {
        return statusId;
    }
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
    public String getText() {
        return getName();
    }
    public void setPrimaryKey(int id) {
        setStatusId(new Integer(id));
    }
    public int getPrimaryKey() {
        return getStatusId() != null ? getStatusId().intValue() : -1;
    }
    static final long serialVersionUID = FormStatus.class.getName().hashCode();
}
