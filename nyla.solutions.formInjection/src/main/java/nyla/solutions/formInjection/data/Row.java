package nyla.solutions.formInjection.data;


/**
 * 
 * <pre>
 * Row is a value object representation of the Row table
and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class Row extends FormComponent 
{
   static final long serialVersionUID = Row.class.getName().hashCode();
   
    private Integer responseTableId;
    private Integer rowNumber;
    private String rowText;
    
    public Object getKey() {
        return rowNumber;
    }
    public Integer getResponseTableId() {
        return responseTableId;
    }
    protected void setResponseTableId(Integer responseTableId) {
        this.responseTableId = responseTableId;
    }
    public Integer getRowNumber() {
        return rowNumber;
    }
    protected void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }
    public String getRowText() {
        return rowText;
    }
    protected void setRowText(String rowText) {
        this.rowText = rowText;
    }
    
    public String getText() {
        return getRowText();
    }
}
