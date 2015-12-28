package nyla.solutions.formInjection.data;

/**
 * 
 * <pre>
 * Section is a value object representation of the Section table
and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class Section extends FormComponent {

    
    
    public Object getKey() {
        return getNumber();
    }
    public Integer getFormTypeId() {
        return formTypeId;
    }
    protected void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }
    public String getName() {
        return name;
    }
    protected void setName(String name) {
        this.name = name;
    }
    public Integer getNumber() {
        return number != null ? number : new Integer(-1);
    }
    protected void setNumber(Integer number) {
        this.number = number;
    }
    public String getText() {
        return getName();
    }
    public String toString()
   {
    
      return super.toString()+" formTypeId="+formTypeId+" name="+name+" number="+number;
   }
    static final long serialVersionUID = Section.class.getName().hashCode();
    private Integer formTypeId;
    private String name;
    private Integer number;
}
