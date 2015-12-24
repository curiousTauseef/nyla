package org.solutions.form.data;

import java.util.HashMap;
import java.util.Map;

import org.solutions.form.FormGuide;
import org.solutions.form.QuestionGuide;
import org.solutions.form.bre.OperationBluePrint;


/**
 * 
 * <pre>
 * ResponseType is a value object representation of the 
 * ResponseType table and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class ResponseType extends FormComponent implements QuestionGuide 
{
    
    public Object getKey() {
        return getCode();
    }
    public String getDescription() {
        return description;
    }
    protected void setDescription(String description) {
        this.description = description;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code != null ? code.toLowerCase() : "";
        this.setType(code);
    }
    public short getType() {
        return type;
    }
    private void setType(String aCode)
    {
       if(aCode != null )
       {
          aCode = aCode.trim();
       }
       
       if( ResponseType.HEADER_CD.equals(aCode))
       {
          this.type = FormGuide.HEADER_TYPE;
       }
       else if( ResponseType.TEXT_CD.equals(aCode))
       {
          this.type = FormGuide.TEXT_TYPE;
       }
       else if( ResponseType.TEXT_AREA_CD.equals(aCode))
       {
          this.type = FormGuide.TEXT_AREA_TYPE;
       }
       else if( ResponseType.NUMBER_CD.equals(aCode))
       {
          this.type = FormGuide.NUMBER_TYPE;
       }
       else if( ResponseType.FLOAT_CD.equals(aCode))
       {
          this.type = FormGuide.FLOAT_TYPE;
       }      
       else if( ResponseType.CURRENCY_CD.equals(aCode))
       {
          this.type = FormGuide.CURRENCY_TYPE;
       }
       else if( ResponseType.DATE_CD.equals(aCode))
       {
          this.type = FormGuide.DATE_TYPE;
       }
       else if( ResponseType.SELECT_LIST_CD.equals(aCode))
       {
          this.type = FormGuide.SELECT_LIST_TYPE;
       }
       else if( ResponseType.CHECKBOXES_CD.equals(aCode))
       {
          this.type = FormGuide.CHECKBOXES_TYPE;
       }
       else if( ResponseType.PERCENT_CD.equals(aCode))
       {
          this.type = FormGuide.PERCENT_TYPE;
       }
       else if( ResponseType.TABLE_CD.equals(aCode))
       {
          this.type = FormGuide.TABLE_TYPE;
       }    
       else if( ResponseType.RADIO_CD.equals(aCode))
       {
          this.type = FormGuide.RADIO_TYPE;
       }
       else if (ResponseType.HIDDEN_CD.equals(aCode))
       {
          this.type = FormGuide.HIDDEN_TYPE;
       }
       else if (ResponseType.INTEGER_CD.equals(aCode))
       {
          this.type = FormGuide.INTEGER_TYPE;
       }      
       else if(ResponseType.MULTI_SELECT_CD.equals(aCode))
       {
          this.type = FormGuide.MULTI_SELECT_TYPE;
       }
       else
       {
          throw new IllegalArgumentException("Unknown question response type "+aCode
          +" expected 'header', 'text', 'textarea', 'integer', 'date', * 'float', 'number', 'currency', 'mult-select', 'percent', 'hidden', 'radioboxes', * 'checkboxes', 'table'");
       }
       
    }//-------------------------------------------------------

    public Integer getOperationId() {
        return operationId;
    }
    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }
    public ResponseTable getResponseTable() {
        return responseTable;
    }
    public void setResponseTable(ResponseTable responseTable) {
        this.responseTable = responseTable;
    }
    public Map getAttributes() {
        return attributes;
    }
    protected void setAttributes(Map attributes) {
        this.attributes = attributes != null ? attributes : new HashMap();
    }
    public ResponseTypeAttribute findAttributeByName(String name) {
        return (ResponseTypeAttribute) getAttributes().get(name);
    }

    public boolean hasAttributes() 
    {
        return attributes != null &&  !attributes.isEmpty();
    }
    /**
     * @return Returns the operationBluePrint.
     */
    public OperationBluePrint getOperationBluePrint()
    {
       return operationBluePrint;
    }//--------------------------------------------
    /**
     * @param operationBluePrint The operationBluePrint to set.
     */
    public void setOperationBluePrint(OperationBluePrint operationBluePrint)
    {
       this.operationBluePrint = operationBluePrint;
    }//--------------------------------------------
    
    public boolean hasOperation() {
        return getOperationBluePrint() != null;
    }

    /**
     * 
     * @return getType() == ResponseType.SELECT_LIST_TYPE ||
              getType() == ResponseType.MULTI_SELECT_TYPE
     */
    public boolean isListable()
    {
       return getType() == ResponseType.SELECT_LIST_TYPE ||
              getType() == ResponseType.MULTI_SELECT_TYPE;
    }//--------------------------------------------
    /**
     * 
     * @return SELECT_LIST_CD.equals(this.code) ||
              CHECKBOXES_CD.equals(this.code) ||
              RADIO_CD.equals(this.code) ||
              MULTI_SELECT_CD.equals(this.code)
     */
    public boolean isSelectable()
    {
       return SELECT_LIST_CD.equals(this.code) ||
              CHECKBOXES_CD.equals(this.code) ||
              RADIO_CD.equals(this.code) ||
              MULTI_SELECT_CD.equals(this.code);
    }//-----------------------------------------
    /**
     * 
     * @return isAnswerableWithNoChoice() ||
              isSelectable()
     */
    public boolean isAnswerable()
    {
       return isAnswerableNonSelectable() ||
              isSelectable();
    }//----------------------------------------
    /**
     * 
     * @return true if getType() == HEADER_TYPE ||
                 getType() == SUB_SECTION_HEADER_TYPE ||
                 getType() == GROUP_HEADER_TYPE ||
                 getType() == INSTRUCTION_HEADER_TYPE  ||
                 getType() == QUESTION_HEADER_TYPE ||
                 getType() == SECTION_HEADER_TYPE
     */
    public boolean isHeader()
    {
       return getType() == FormGuide.HEADER_TYPE;
    }//-----------------------------------------
    /**
     * 
     * @return ype == NUMBER_TYPE ||
       getType() == FLOAT_TYPE ||
       getType() == CURRENCY_TYPE  ||
       getType() == PERCENT_TYPE
     */
    public boolean isNumeric()
    {
      return getType() == FormGuide.NUMBER_TYPE ||
      getType() == FormGuide.FLOAT_TYPE ||
      getType() == FormGuide.INTEGER_TYPE ||
      getType() == FormGuide.CURRENCY_TYPE  ||
      getType() == FormGuide.PERCENT_TYPE;
    }//--------------------------------------------
    /**
     * 
     * @return t
     */
    public boolean isAnswerableNonSelectable()
    {
       return TEXT_CD.equals(this.code) ||
              TEXT_AREA_CD.equals(this.code) ||
              NUMBER_CD.equals(this.code) ||
              FLOAT_CD.equalsIgnoreCase(this.code) ||
              HIDDEN_CD.equals(this.code) ||
              INTEGER_CD.equals(this.code) ||
              CURRENCY_CD.equals(this.code) ||
              DATE_CD.equals(this.code) ||
              PERCENT_CD.equals(this.code);       
    }//----------------------------------------------------
    /**
     * @return getType() == TABLE_TYPE
     */
    public boolean isTable()
    {
       
       return getType() == FormGuide.TABLE_TYPE;
    }//------------------------------------------
    /**
     * 
     * @return getType() == HEADER_TYPE;
     */
    public boolean isHeaderOnly()
    {
         return getType() == FormGuide.HEADER_TYPE;
    }//----------------------------------------------------   
    /**
     * 
     * @return type == ResponseType.MULTI_SELECT_TYPE
     */
    public boolean isMultipleSelect()
    {  
       return type == ResponseType.MULTI_SELECT_TYPE;
    }//--------------------------------------------
    /**
     * 
     * @return FormGuide.MAX_ANSWER_VALUE_LENGHT if property not found else
     * new Integer(property.getValue().toString()).intValue()
     */
    public int getMaxLength()
    {
       ResponseTypeAttribute attribute = this.findAttributeByName(FormGuide.MAX_LENGTH_ATTRIB_NM);
       
       if(attribute == null || 
          attribute.getValue() == null || 
          attribute.getValue().toString().length() == 0)
       {
          return FormGuide.MAX_ANSWER_VALUE_LENGTH;
       }
       else
       {
          return new Integer(attribute.getValue().toString()).intValue();
       }
    }//--------------------------------------------
    
    public String getText() {
        return getCode();
    }
    
    static final long serialVersionUID = ResponseType.class.getName().hashCode();
    private short type;
    private Integer operationId;
    private OperationBluePrint operationBluePrint;
    private String code;
    private String description;
    private ResponseTable responseTable;
    private Map attributes = new HashMap();

}
