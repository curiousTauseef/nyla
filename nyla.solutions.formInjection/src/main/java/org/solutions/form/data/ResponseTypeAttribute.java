/*
 * Created on Nov 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.solutions.form.data;


/**
 * 
 * <pre>
 * ResponseTypeAttribute provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class ResponseTypeAttribute extends FormComponentAttribute 
{
   static final long serialVersionUID = ResponseTypeAttribute.class.getName()
   .hashCode();
   
    private Integer responseTypeId;
    
    public ResponseTypeAttribute() {
        super();
    }
    public ResponseTypeAttribute(String key, String value) {
        super(key, value);
    }
    public Integer getResponseTypeId() {
        return responseTypeId;
    }
    protected void setResponseTypeId(Integer responseTypeId) {
        this.responseTypeId = responseTypeId;
    }
}
