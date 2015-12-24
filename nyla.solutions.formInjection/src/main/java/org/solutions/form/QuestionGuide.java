package org.solutions.form;

import java.io.Serializable;

/**
 * <pre>
 * QuestionGuide constants for question/response type management
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public interface QuestionGuide extends Serializable
{
   /**
    * HEADER_CD ="header"
    */
   public static final String HEADER_CD ="header";
   
   /**
    * TEXT_CD ="text"
    */
   public static final String TEXT_CD ="text";
   
   /**
    * TEXT_AREA_CD ="textarea"
    */
   public static final String TEXT_AREA_CD ="textarea";
   
   /**
    * NUMBER_CD = "number"
    */
   public static final String NUMBER_CD = "number";
   
   /**
    * INTEGER_CD = "integer"
    */
   public static final String INTEGER_CD = "integer";
   
   
   /**
    * FLOAT_CD = "float"
    */
   public static final String FLOAT_CD = "float";
   
   /**
    * CURRENCY_CD="currency"
    */
   public static final String CURRENCY_CD="currency";
   
   /**
    * DATE_CD ="date"
    */
   public static final String DATE_CD ="date";
   
   /**
    * MULTI_SELECT_CD = "multi-select"
    */
   public static final String MULTI_SELECT_CD = "multi-select";
   
   /**
    * SELECT_LIST_CD ="listbox"
    */
   public static final String SELECT_LIST_CD ="listbox";
   
   /**
    * CHECKBOXES_CD = "checkboxes"
    */
   public static final String CHECKBOXES_CD = "checkboxes";
   
   /**
    * PERCENT_CD = percentage
    */
   public static final String PERCENT_CD ="percent";
   
   /**
    * TABLE_CD = "table"
    */
   public static final String TABLE_CD = "table";
   
   /**
    * RADIO_CD = "radioboxes"
    */
   public static final String RADIO_CD = "radioboxes";
   
   /**
    * HIDDEN_CD = "hidden"
    */
   public static final String HIDDEN_CD = "hidden";
   
   /**
    * HEADER_TYPE =1
    */
   public static final short HEADER_TYPE =1;
   
   /**
    * TEXT_TYPE =2
    */
   public static final short TEXT_TYPE =2;
   
   /**
    * TEXT_AREA_TYPE =3
    */
   public static final short TEXT_AREA_TYPE =3;
   
   /**
    * HIDDEN_TYPE = 33
    */
   public static final short HIDDEN_TYPE = 33;
   
   /**
    * NUMBER_TYPE = 4
    */
   public static final short NUMBER_TYPE = 4;
   
   /**
    * INTEGER_TYPE = 23
    */
   public static final short INTEGER_TYPE = 23;
   
   /**
    * FLOAT_TYPE  = 24
    */
   public static final short FLOAT_TYPE  = 24;
   
   /**
    * MULTI_SELECT_TYPE = 25
    */
   public static final short MULTI_SELECT_TYPE = 25;
   
   /**
    * CURRENCY_TYPE= 5
    */
   public static final short CURRENCY_TYPE= 5;
   
   /**
    * DATE_TYPE = 6
    */
   public static final short DATE_TYPE = 6;
   
   /**
    * SELECT_LIST_TYPE = 7
    */
   public static final short SELECT_LIST_TYPE = 7;
   
   /**
    * CHECKBOXES_TYPE = 8
    */
   public static final short CHECKBOXES_TYPE = 8;
   
   /**
    * PERCENT_TYPE = 9
    */
   public static final short PERCENT_TYPE = 9;
   
   /**
    * TABLE_TYPE = 10
    */
   public static final short TABLE_TYPE = 10;
   
   /**
    * RADIO_TYPE = 15
    */
   public static final short RADIO_TYPE = 15;
}
