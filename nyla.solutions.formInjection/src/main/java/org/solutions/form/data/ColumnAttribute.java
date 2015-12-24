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
 * ColumnAttribute provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class ColumnAttribute extends QuestionAttribute {
    private Integer colNumber;
    private Integer responseTableId;
    

    public ColumnAttribute() {
        super();
    }
    public ColumnAttribute(String key, String value) {
        super(key, value);
    }
    public Integer getColNumber() {
        return colNumber;
    }
    

    public Integer getResponseTableId() {
        return responseTableId;
    }

    public void setColNumber(Integer colNumber) {
        this.colNumber = colNumber;
    }

    public void setResponseTableId(Integer responseTableId) {
        this.responseTableId = responseTableId;
    }

    static final long serialVersionUID = ColumnAttribute.class.getName()
   .hashCode();
}
