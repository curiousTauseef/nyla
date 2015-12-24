package org.solutions.form.data;

import java.io.Serializable;

import nyla.solutions.global.data.Criteria;

/**
 * @author Gregory Green
 * @version 1.0
 *
 * <b>Comment</b>  Answer comment object
 * 
 */
public class Comment extends Criteria
implements Serializable
{
   /**
    * 
    *
    */
   public Comment()
   {   
   }//--------------------------------------------
   /**
    * @return comment object
    */
   public Criteria retrieveCommented()
   {
      return commented;
   }//-------------------------------------

   /**
    * @param criteria the commented object
    */
   public void setCommented(Criteria criteria)
   {
      commented = criteria;
   }//-------------------------------------
   public String toString()
   {
      StringBuffer sb = new StringBuffer(getClass().getName())
                 .append("[")
                 .append(" pk"+getPrimaryKey())
                 .append(" name"+getText());
                 
      return sb.toString();
   }//--------------------------------------------
   /**
    * @return Returns the text.
    */
   public String getText()
   {
      return text;
   }//--------------------------------------------
   /**
    * @param text The text to set.
    */
   public void setText(String text)
   {
      if (text == null)
         text = "";

      this.text = text;
   }
   private Criteria commented  = null;   
   private String text = null;
   static final long serialVersionUID = Comment.class.getName().hashCode();
}
