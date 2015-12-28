package nyla.solutions.formInjection.data;


/**
 * 
 * <pre>
 * AnswerProperty is a value object representation of the 
 * AnswerProperty table and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class AnswerProperty extends FormComponentAttribute {
    private String answerId;
    
    
    public AnswerProperty() {
        super();
    }
    public AnswerProperty(String key, String value) {
        super(key, value);
    }
    public String getFormAnswerId() {
        return answerId;
    }
    protected void setFormAnswerId(String answerId) {
        this.answerId = answerId;
    }
    
    static final long serialVersionUID = AnswerProperty.class.getName()
   .hashCode();
}
