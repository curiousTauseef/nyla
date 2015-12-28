package nyla.solutions.formInjection.data;


public class QuestionChoice extends FormComponent {

   /**
    * 
    * Compare the two codes
    * @see nyla.solutions.formInjection.data.FormComponent#compareTo(java.lang.Object)
    */
   public int compareTo(Object o)
   {
      QuestionChoice other = (QuestionChoice)o;
      
      return this.code.compareTo(other.code);
   }//--------------------------------------------
    
    public Object getKey() {
        //return useTextAsValue ? getText() : (getChoiceId() != null ? getChoiceId().toString() : "");
       return code;
    }//--------------------------------------------
    public Integer getChoiceId() {
        return choiceId;
    }
    public void setPrimaryKey(int id) {
        setChoiceId(new Integer(id));
    }
    public void setChoiceId(Integer choiceId) {
        this.choiceId = choiceId;
    }
    public Integer getColNumber() {
        return colNumber;
    }
    public void setColNumber(Integer colNumber) {
        this.colNumber = colNumber;
    }
    public String getDefaultCode() 
    {
       if(defaultCode == null)
          defaultCode = "N";
       
        return defaultCode;
    }// --------------------------------------------

    public boolean isDefault() {
        return "Y".equals(getDefaultCode());
    }
    public void setDefaultCode(String defaultCode) {
        this.defaultCode = "Y".equals(defaultCode) ? "Y" : "N";
    }
    public Integer getFormTypeId() {
        return formTypeId;
    }
    public void setFormTypeId(Integer formTypeId) {
        this.formTypeId = formTypeId;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Integer getQuestionId() {
        return questionId;
    }
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
    public Integer getResponseTableId() {
        return responseTableId;
    }
    public void setResponseTableId(Integer responseTableId) {
        this.responseTableId = responseTableId;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getName() {
        return getText();
    }
    public void setName(String text) {
        this.text = text;
    }
    public boolean isUseTextAsValue() {
        return useTextAsValue;
    }
    public void setUseTextAsValue(boolean useTextAsValue) {
        this.useTextAsValue = useTextAsValue;
    }
    /**
     * @return the code
     */
    public String getCode()
    {
       return code;
    }//--------------------------------------------
    /**
     * @param code the code to set
     */
    public void setCode(String code)
    {
       this.code = code;
    }//--------------------------------------------
    
    private String code = null;
    
    static final long serialVersionUID = QuestionChoice.class.getName().hashCode();
    private Integer choiceId;
    private Integer questionId;
    private Integer responseTableId;
    private int number;
    private String defaultCode = "N";
    private String text;
    private Integer formTypeId;
    private Integer colNumber;
    private boolean useTextAsValue;

}
