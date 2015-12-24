package org.solutions.form.web;


import javax.servlet.jsp.JspException;

import nyla.solutions.global.util.Debugger;

import org.solutions.form.data.Application;
import org.solutions.form.data.FormQuestion;


/**
 * <pre>
 * 
 * This custom JSP tag "UseQuestionTag" sets a GCSMQuestion object in a 
 * given scope (page, request, session, or application). The default 
 * scope definition is "request". This tag is similar to the 
 * JSP useBean. The following is an example usage of this tag.

<!-- question 2 totals -->
<form:useQuestion id="2000" var="q2000"/>
<form:useQuestion id="2001" var="q2001"/>
<form:useQuestion id="2002" var="q2002"/>
<form:useQuestion id="2003" var="q2003"/>

   <table width="100%">
     <form:table id="6" autoNumber="false" styleClass="bgLtGray" template="vertical_table">
                  <form:row template="vertical_table_rows">                   
                     <form:column>
                               <td id="answer">${answer}</td>
                     </form:column>   
                  </form:row>                      
           <form:footer>
                <c:if test="${!form.readOnly}">
                     <tr>
                        <td id="footer">Totals </td>                    
                        <td><input class="answer" maxlength="20" type="text" name="<c:out value="${q2000.inputName}"/>" value="<c:out value="${q2000.answer.value}"/>"/></td>
                        <td><input class="answer" maxlength="20"type="text"  name="<c:out value="${q2001.inputName}"/>"value="<c:out value="${q2001.answer.value}"/>"/></td>
                       <td><input class="answer" maxlength="20" type="text"  name="<c:out value="${q2002.inputName}"/>"value="<c:out value="${q2002.answer.value}"/>"/></td>
                       <td><c:out value="${q2003.text}"/> </td>
                       <td><input class="answer" maxlength="20" type="text"  name="<c:out value="${q2003.inputName}"/>"value="<c:out value="${q2003.answer.value}"/>"/></td>              
                     </tr>                       
                </c:if>            
                <c:if test="${form.readOnly}">
                     <tr>
                        <td>Totals: </td>            
                        <td><c:out value="${q2000.answer.value}"/></td>
                        <td><c:out value="${q2001.answer.value}"/></td>
                        <td> <c:out value="${q2002.answer.value}"/></td>
                       <td><c:out value="${q2003.text}"/> </td>     
                        <td> <c:out value="${q2003.answer.value}"/></td>               
                     </tr>       
                </c:if>                         
           </form:footer>
    </form:table>    

 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class UseQuestionTag extends FormTag
{
   static final long serialVersionUID = UseQuestionTag.class.getName()
   .hashCode();
   
   /**
    * Constructor for SectionContainerTag initalizes internal 
    * data settings.
    * 
    */
   public UseQuestionTag()
   {
      super();
   }//--------------------------------------------
   /**
    * 
    * @see javax.servlet.jsp.tagext.TagSupport#doStartTag()
    */
   public int doStartTag() throws JspException
   {
      try
      {
         Application form = (Application)this.getForm();
         
         FormQuestion question = form.findQuestionByID(new Integer(this.getId()));
         
         setAttribute(question);
         return EVAL_BODY_INCLUDE;
      }
      catch(Exception e)
      {
         throw new JspException(Debugger.stackTrace(e));
      }
   }//--------------------------------------------
   private final void setAttribute(FormQuestion aQuestion)
   {
      //aQuestion.setOffline(Boolean.TRUE.toString().equalsIgnoreCase(pageContext.getRequest().getParameter(FormConstants.OFFLINE_PARAM_NM)));
      if("page".equals(this.scope))
      {
         pageContext.setAttribute(this.var,aQuestion);
      }
      else if("session".equals(this.scope))
      {
         pageContext.getSession().setAttribute(this.var,aQuestion);
      }
      else if("application".equals(this.scope))
      {
         pageContext.getServletContext().setAttribute(this.var,aQuestion);
      }      
      else
      {
         //request
         pageContext.getRequest().setAttribute(this.var,aQuestion);
      }
   }//--------------------------------------------
   /**
    * @return Returns the scope.
    */
   public final String getScope()
   {
      return scope;
   }//--------------------------------------------
   /**
    * @param scope The scope to set.
    */
   public final void setScope(String scope)
   {
      if (scope == null)
         throw new IllegalArgumentException(
         "scope required in UseQuestionTag.setScope");
      
      this.scope = scope;
   }//--------------------------------------------
   
   /**
    * @return Returns the var.
    */
   public final String getVar()
   {
      return var;
   }//--------------------------------------------
   /**
    * @param var The var to set.
    */
   public final void setVar(String var)
   {
      if (var == null)
         throw new IllegalArgumentException(
         "var required in UseQuestionTag.setVar");
      
      this.var = var;
   }//--------------------------------------------
   private String scope = "request";
   private String var =   "question";
}
