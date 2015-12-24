package org.solutions.form.data;

/**
 * 
 * <pre>
 * ResponseTableAttribute is a value object representation of the ResponseTableAttribute table
 * and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class ResponseTableAttribute extends FormComponentAttribute 
{
   static final long serialVersionUID = ResponseTableAttribute.class.getName()
   .hashCode();
   
    private Integer responseTableId;
    
    public ResponseTableAttribute() {
        super();
    }
    public ResponseTableAttribute(String key, String value) {
        super(key, value);
    }
    public Integer getResponseTableId() {
        return responseTableId;
    }
    protected void setResponseTableId(Integer responseTableId) {
        this.responseTableId = responseTableId;
    }
}
