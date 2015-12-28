package nyla.solutions.formInjection.data;

/**
 * 
 * <pre>
 * QuestionAttribute provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class QuestionAttribute extends FormComponentAttribute {
    private Integer formTypeId;
    private Integer questionId;
    
    public QuestionAttribute() {
        super();
    }
    public QuestionAttribute(String key, String value) {
        super(key, value);
    }
    public Integer getFormTypeId() {
        return formTypeId;
    }
    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }
    public Integer getQuestionId() {
        return questionId;
    }
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    
    static final long serialVersionUID = QuestionAttribute.class.getName()
   .hashCode();
}
