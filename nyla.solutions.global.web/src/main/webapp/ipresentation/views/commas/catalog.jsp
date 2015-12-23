<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="test_error.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="ui" class="nyla.solutions.global.web.commas.CommandUIDecorator" scope="session"/>

<html>
    <head>
        <title>Web Catalog</title>

        <!-- Grab some style sheets for the built-in tundra theme that Dojo offers for
         styling the page, equipping you with a professional style without
any additional
         effort required. -->        
         <!-- script type="text/javascript">
            djConfig = {
                        baseUrl = "<c:out value="${pageContext.request.contextPath}/common"/>"
            }
         </script-->
         
         
        <link rel="stylesheet" type="text/css"
          href="<c:out value="${pageContext.request.contextPath}/common/dijit/themes/tundra/tundra.css"/>"/>      
        <link rel="stylesheet" type="text/css"
          href="<c:out value="${pageContext.request.contextPath}/common/dojo/resources/dojo.css"/>" />
        <!-- Add in some plain old CSS to line up the form elements more nicely -->

        <style type="text/css">
            h3 {
                margin : 10px;
            }
            label,input {
                display: block;
                float: left;
                margin-bottom: 5px;
             }
             label {
                text-align: right;
                width: 70px;
                padding-right: 20px;
             }
             br {
                clear: left;
             }
             .grouping {
                width:750px;
                border:solid 1px rgb(230,230,230);
                padding:5px;
                margin:10px;
             }
        </style>

        <!-- Load Base and specify that the page should be parsed for dijits after it
          loads --> 
        <script
            type="text/javascript"
            src="<c:out value="${pageContext.request.contextPath}/common/dojo/dojo.js"/>"
            djConfig="parseOnLoad: true"
            >
            
        </script>

         
        <!-- Load some dijits via dojo.require in the same manner that you
would #include
          some files in C programming or perform an import in Java -->
        <script type="text/javascript">
            dojo.require("dojo.parser");
            dojo.require("dijit.form.TextBox");
            dojo.require("dijit.form.ValidationTextBox");
            dojo.require("dijit.form.Button");
            dojo.require("dijit.form.ComboBox");     
            
            dojo.addOnLoad(function(){
                  //build crates
                        //var cratesList = dojo.byId("crates");
                      //alert("cratesList.length="+cratesList.innerHTML);
                  });
                        
        </script>
        
        <script type="text/javascript">
            function executeFunction()
            {
                  clearOutput();
                  
                  //alert('executeFunction()');
                       //Get form
                         
                         //convert to JSON
                         //var jsonFormText = escape(dojo.formToJson("functionBuiltForm"));
                         var jsonFormText = dojo.formToJson("functionBuiltForm");
                         
                         if(jsonFormText == "{}")
                          {
                               var requestFunctionTextRecord = dojo.byId("requestFunctionText");
                               if(requestFunctionTextRecord != null && requestFunctionTextRecord != undefined)
                               {
                                     jsonFormText = requestFunctionTextRecord.value;
                               } 
                          }
                         
                         
                   //alert("executeFunction="+jsonFormText);
                   var theSelectedFunctionText = getSelectedFunctionText();
                   
                   if(theSelectedFunctionText == null)
                         return;
                   
                         var executeFunctionUrl = "<c:out value="${pageContext.request.contextPath}/grid/"/>"+dojo.byId("crates").value+"/"+theSelectedFunctionText;
                        //alert("executeFunctionUrl:"+executeFunctionUrl);

               dojo.rawXhrPost({
                                     url: executeFunctionUrl,
                                     handleAs: "text",            
                                           postData : jsonFormText,
                                     load : function(response, ioArgs) {
                                                //Set some element 
                                                //dojo.byId("outputPanel").innerHTML = escape(response);
                                                      println(response);
                                                return response;
                                          },
                                          //---------------------------------------------------
                                          error: function(response, ioArgs) {
                                                //console.log("error xhrGet", response, ioArgs);
                                                
                                                printError(ioArgs.xhr.responseText);
                                                return response;
                                          }
                                                 
                                    });
            }//-------------------------------------------------
            function println(text)
            {
                  //Set some element 
                              dojo.byId("outputPanel").innerHTML = text;
            }//--------------------------------------------------
            function printError(text)
            {
                  //Set some element 
                              dojo.byId("outputPanel").innerHTML = "<div style='color:red'>"+text+"</div>";
            }
            
            function displayFunctionSelect()
            {
                  clearOutput();
                  clearOutputType();
                  
                  var displayFunctionSelectCrateName = dojo.byId("crates").value;
                  
                  //Clear input form
                  dojo.byId("functionBuiltForm").innerHTML = "";                    
                  
                  var displayFunctionSelectUrl = "<c:out value="${pageContext.request.contextPath}/grid"/>/com.medco.fabrix.grid.daf.ws.functions.WebCrate/getFunctionInfos?"+dojo.byId("crates").value;
                  //get functions
                 alert("displayFunctionSelectUrl="+displayFunctionSelectUrl);
                  
                 dojo.xhrGet({
                               url: displayFunctionSelectUrl,
                               handleAs: "json",            
                               load : function(response, ioArgs) {
                                          //Set some element 
                                          //dojo.byId("outputPanel").innerHTML = escape(response);
                                          var selectHTML = "<label>Function:</label><select id='function' onchange='displayForm();' name='function'><option></option>";
                                          
                                          for(i in response)
                                          {
                                                
                                                selectHTML = selectHTML +"<option>"+response[i].functionFacts.functionName+"</option>";
                                          }
                                          selectHTML = selectHTML + "</select><br/>";
                                          
                                          dojo.byId("functionSelectCanvas").innerHTML= selectHTML;
                                          
                                          return response;
                                    },
                                    //---------------------------------------------------
                                    error: function(response, ioArgs) {
                                          dojo.byId("outputPanel").innerHTML = ioArgs.xhr.responseText;
                                          return response;
                                    }
                              });
            }//--------------------------------------------------
            function getSelectedFunctionText()
            {
                var selectedFunction = dojo.byId("function");
                  
                  if(selectedFunction.selectedIndex == 0)
                {
                        alert("Please select a function");
                        return null;
                }
                  
                  return selectedFunction.options[selectedFunction.selectedIndex].text;
            }//-----------------
            function clearOutput()
            {
                  dojo.byId("outputPanel").innerHTML = "";
                  
            }//-----------------------------------------
            function clearOutputType()
            {
                  dojo.byId("outputClassTypePanel").innerHTML = "";
            }//---------------------------------------------
            function displayForm()
            {
                  clearOutput();
            
                  var selectedFunctionText = getSelectedFunctionText();
                  
                  if( selectedFunctionText == null)
                        return;   //Function not selected
                  
                  //build request ex:
                        var getUrl = "<c:out value="${pageContext.request.contextPath}/grid"/>/solutions.functions.WebCrate/getGridFunctionDef?{crateName:"+dojo.byId("crates").value+",functionName:"+selectedFunctionText+"}";
                     dojo.xhrGet({
                                     url: getUrl,
                                     handleAs: "json",            
                                     load : function(response, ioArgs) {
                                                console.log("success xhrGet", response, ioArgs);
                                                
                                                if(response.outputClassSchema != undefined &&  response.outputClassSchema != null)
                                                {
                                                      dojo.byId("outputClassTypePanel").innerHTML = "<b>"+response.outputClassSchema.className+"</b>";
                                                }
                                                
                                                //alert("response="+response);
                                                if(response.inputClassSchema == null || response.inputClassSchema == "")
                                                {
                                                      var noArgHMTL = "No input arguments<br/> <input type='button' name='Execute' value='Execute' onclick='executeFunction();'/>";
                                                      
                                                      dojo.byId("functionBuiltForm").innerHTML = noArgHMTL;
                                                      
                                                      return response;
                                                }
                                                
                              
                                                
                                                //Set some element 
                                                dojo.byId("functionBuiltForm").innerHTML = "<b>"+response.inputClassSchema.className+"</b><br/>";
                                                
                                                //Loop thru fieldSchemas
                                                var responseHTML ="<p> Date format: <%=nyla.solutions.global.util.Text.DATE_FORMAT%> ex: 01/12/1984 23:00:00:000</p>";
                                                
                                                
                                                if(response.inputClassSchema != null 
                                                            && response.inputClassSchema.fieldSchemas != null
                                                            && response.inputClassSchema.fieldSchemas.length  > 0 )
                                                {
                                                      for(i in response.inputClassSchema.fieldSchemas)
                                                      {
                                                            if("serialVersionUID" == response.inputClassSchema.fieldSchemas[i].fieldName)
                                                                  continue; //skip
                                                            
                                                            responseHTML= responseHTML+"<label>"
                                                                                            +response.inputClassSchema.fieldSchemas[i].fieldName
                                                                                                            +"</label>";
                                                            
                                                            responseHTML= responseHTML+"<input name='"+response.inputClassSchema.fieldSchemas[i].fieldName+"'/>"
                                                             +"("+response.inputClassSchema.fieldSchemas[i].className+")<br/>";
                                                      }
                                                }
                                                else
                                                {
                                                      responseHTML= responseHTML+"<input id='requestFunctionText'/><br/>";
                                                }
                                          
                                                
                                                
                                                
                                                
                                                //Add form button
                                                responseHTML= responseHTML+"<input type='button' name='Execute' value='Execute' onclick='executeFunction();'/>";
                                                
                                                dojo.byId("functionBuiltForm").innerHTML = dojo.byId("functionBuiltForm").innerHTML+responseHTML;
                                          
                                                
                                                return response;
                                          },
                                          //---------------------------------------------------
                                          error: function(response, ioArgs) {
                                                console.log("error xhrGet", response, ioArgs);
                                                
                                                //Set some element 
                                                dojo.byId("functionFormPanel").innerHTML = ioArgs.xhr.responseText;                                                
                                                return response;
                                          }
                                                 
                                    });
               }//------------------------------------------------
            
        </script>
    <head>

        <!-- Specify that the built-in tundra theme should be applied to
everything in the
          body of the page. (Dijit relies heavily on CSS so including the appropriate
          theme is crucial.)-->
        <body class="tundra">

            <h1>Function Test Tool:</h1>

            <form id="registration_form">

                <!-- Weave some widgets into the page by supplying the tags and
including
                  a dojoType attribute so the parser can find them and swap
them out -->

                <div class="grouping">
                              <label>Crates:</label>
                              <select name="crates" dojoType="dijit.form.ComboBox" id="crates" onchange="displayFunctionSelect();"  autoComplete="true">
                                    <c:forEach var="crate" items="${ui.crates}">
                                          <option><c:out value="${crate.name}"></c:out></option>
                                    </c:forEach>
                              </select>
                              <br/>
                              <div id="functionSelectCanvas">
                              </div>
                                                                  
                    <!--  button dojoType="dijit.form.Button"
                     onClick="displayForm();">Build Form</button>
                  </div-->
            </form>
            
             <h2>Request</h2>
              <div id="functionFormPanel" class="grouping">
                  <form id="functionBuiltForm">
                        </form>
              </div>
                  
                  <br/>
      
                  <h2>Response</h2> 
                  <div id="outputClassTypePanel"></div>           
                  <div id="outputPanel" class="grouping">
                  </div>
<!-- 
   Config.properties.gemfire.cache-xml-file=<%=nyla.solutions.global.util.Config.getProperty("gemfire.cache-xml-file","")%>
   Config.properties.gemfirePropertyFile=<%=nyla.solutions.global.util.Config.getProperty("gemfirePropertyFile","")%>
 -->
    </body>
</html>

