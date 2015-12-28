/*
 * Created on Nov 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package nyla.solutions.formInjection.data;


/**
 * @author JIsrael
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SectionAttribute extends FormComponentAttribute {
    private Integer formTypeId;
    private Integer sectionNumber;
    
    public SectionAttribute() {
        super();
    }
    public SectionAttribute(String key, String value) {
        super(key, value);
    }
    public Integer getFormTypeId() {
        return formTypeId;
    }
    protected void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }
    public Integer getSectionNumber() {
        return sectionNumber;
    }
    protected void setSectionNumber(Integer sectionNumber) {
        this.sectionNumber = sectionNumber;
    }
    static final long serialVersionUID = SectionAttribute.class.getName()
   .hashCode();
}
