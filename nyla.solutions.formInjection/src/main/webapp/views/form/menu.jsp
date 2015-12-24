<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>

  	         	 <!-- SITE PK: <c:out value="${form.sitePK}"/>   -->
     	         <!-- REVIEWER PK: <c:out value="${form.reviewerPK}"/>   -->
     	         <!-- STATUS NM <c:out value="${form.status.name}"/>   -->    
     	         <!-- STATUS PK <c:out value="${form.status.primaryKey}"/>   -->         	         
     	          <form:guard checkPoint="edit">
     	                         <input type="button" value="   Edit   " onclick="renewForm(aForm);" />
     	           </form:guard>     	          	         
     	          <form:guard checkPoint="complete">
     	                         <input type="button" value="Complete" onclick="signCompletionForm(aForm);" />
     	           </form:guard>
       	        <form:guard> 
                   	        <form:guard checkPoint="site">   	                           	   
                                 	       <input type="button" value="Protocol Deviation" onclick="postFormDeviation(aForm);" />                      	        
                                 	       <input type="button" value="Post Issue" onclick="postFormIssue(aForm);" />                         	                                       	       
                    	        </form:guard>       	          	        
          	                 <input type="button" value="Save" onclick="saveForm(aForm);" />
               	          <input type="button" value="Delete" onclick="deleteForm(aForm);" />
               	          <c:if test="${not empty param.signForm}">
                        	      <input type="button" value="Submit" onclick="<c:out value="${param.signForm}"/>(aForm);"/>
                        	  </c:if>
               	          <c:if test="${empty param.signForm}">
                        	      <input type="button" value="Submit" onclick="signForm(aForm);"/>
                        	  </c:if>

        	       </form:guard>