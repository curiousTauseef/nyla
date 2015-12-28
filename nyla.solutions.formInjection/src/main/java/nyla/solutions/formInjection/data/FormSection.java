package nyla.solutions.formInjection.data;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import nyla.solutions.global.data.Copier;


/**
 * 
 * <pre>
 * FormSection is a value object representation of the FormSection table
 * and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormSection extends Section implements FormComponentWrapper {
    private Section section;
    private Form form;
    
    protected FormSection(Form form, Section section) {
        this.section = section;
        this.form = form;
    }
    
    public Form getForm() {
        return form.getForm();
    }
    public Collection getFormQuestions() 
    {
          return form.findQuestionsBySection(section.getNumber());  
        
    }
    public Object getManagedObject() {
        return section;
    }
    public String getText() {
        return getName();
    }
    
    public int compareTo(Object o) {
        return section.compareTo(o);
    }
    public void copy(Copier aFrom) {
        section.copy(aFrom);
    }
    public boolean equals(Object obj) {
        return section.equals(obj);
    }
    public Date getCreateDate() {
        return section.getCreateDate();
    }
    public Object getCreateUserID() {
        return section.getCreateUserID();
    }
    public String getDeletedCode() {
        return section.getDeletedCode();
    }
    public Integer getFormTypeId() {
        return section.getFormTypeId();
    }
    public Object getKey() {
        return section.getKey();
    }
    public String getName() {
        return section.getName();
    }
    public Integer getNumber() {
        return section.getNumber();
    }
    public int getPrimaryKey() {
        return section.getPrimaryKey();
    }
    public int hashCode() {
        return section.hashCode();
    }
    public boolean isDeleted() {
        return section.isDeleted();
    }
    public boolean isNew() {
        return section.isNew();
    }
    public void setDeleted() {
        section.delete();
    }
    public String toString() {
        return section.toString();
    }
    public void updateAudit(Integer userId) {
        section.updateAudit(userId);
    }
    public void setCreateDate(Date createDate) {
        section.setCreateDate(createDate);
    }
    public void setCreateUserID(Integer createUserId) {
        section.setCreateUserID(createUserId);
    }
    public void setDeletedCode(String deletedCode) {
        section.setDeletedCode(deletedCode);
    }
    public void setUpdateDate(Timestamp updateDate) {
        section.setUpdateDate(updateDate);
    }
    public void setUpdateUserID(Integer updateUserId) {
        section.setUpdateUserID(updateUserId);
    }
    public Form retrieveForm() {
        return getForm();
    }
    protected void setFormTypeId(Integer formTypeId) {
        section.setFormTypeId(formTypeId);
    }
    protected void setName(String name) {
        section.setName(name);
    }
    protected void setNumber(Integer number) {
        section.setNumber(number);
    }
    
    static final long serialVersionUID = FormSection.class.getName().hashCode();
}
