package org.solutions.form.formatter.html;

import java.net.URLEncoder;

import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.web.View;

import org.solutions.form.data.Form;
import org.solutions.form.data.FormComponent;
import org.solutions.form.web.AutoCounter;

/**
 * 
 * <pre>
 * FormComponentView provides a set of functions to decorate the presentation of a form component
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 */
public abstract class FormComponentView
{
   /**
    * 
    * Constructor for FormComponentView initializes internal
    */
   public FormComponentView()
   {
   }//--------------------------------------------

   /**
    * 
    * Constructor for FormComponentView initializes internal 
    * @param component the form component
    */
   public FormComponentView(FormComponent component)
   {
      setFormComponent(component);
   }

   public abstract FormComponent getFormComponent();

   /**
    * 
    * @param formComponent the form component to set
    */
   public abstract void setFormComponent(FormComponent formComponent);

   /**
    * 
    * @return the instance of the form to process
    */
   public abstract Form getForm();

   /**
    * 
    * @return auto number
    */
   public boolean isAutoNumber()
   {
      return autoNumber;
   }

   public void setAutoNumber(boolean autoNumber)
   {
      this.autoNumber = autoNumber;
   }

   public AutoCounter getCounter()
   {
      return counter;
   }

   public void setCounter(AutoCounter counter)
   {
      this.counter = counter;
   }

   public String getStyleClass()
   {
      return fix(styleClass);
   }

   public void setStyleClass(String styleClass)
   {
      this.styleClass = styleClass;
   }

   public String getWidth()
   {
      return fix(width);
   }

   public void setWidth(String width)
   {
      this.width = width;
   }

   public String getText()
   {
      return fix(getFormComponent().getText());
   }

   public String getId()
   {
      return fix(getFormComponent().getKey());
   }

   public String getName()
   {
      return fix(name);
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public boolean isReadOnly()
   {
      return readOnly;
   }

   public void setReadOnly(boolean readOnly)
   {
      this.readOnly = readOnly;
   }//--------------------------------------------
   public String fix(Object o)
   {
      return fix(o, "");
   }//--------------------------------------------

   public String fix(Object o, String def)
   {
      return o != null && !o.equals("") ? o.toString() : def;
   }//--------------------------------------------
   public String getImgPath()
   {
      return fix(imgPath, getContextPath() + "/images");
   }//--------------------------------------------

   public void setImgPath(String imgPath)
   {
      this.imgPath = imgPath;
   }

   public String urlEncode(String s)
   {
      try
      {
         s = fix(s);
         return URLEncoder.encode(s, "US-ASCII");
      }
      catch (Exception e)
      {
         throw new SystemException(Debugger.stackTrace(e));
      }
   }
   /**
    * @return the contextPath
    */
   public String getContextPath()
   {
      return contextPath;
   }

   /**
    * @param contextPath the contextPath to set
    */
   public void setContextPath(String contextPath)
   {
      this.contextPath = contextPath;
   }

   private String contextPath = View.getDefaultContextPath();
   private String name = null;
   private String styleClass = null;
   private String width = null;
   private boolean autoNumber = false;
   private AutoCounter counter = null;
   private boolean readOnly = false;
   private String imgPath;

}
