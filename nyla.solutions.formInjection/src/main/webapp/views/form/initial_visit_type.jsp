<!--
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://gcsm.bms.com/tld/form.tld" prefix="form"%>
-->

<script>
   function initializeSMVVisitType(aVisitTypeElement,aForm)
   {
       var choice = aVisitTypeElement.options[ aVisitTypeElement.selectedIndex ];
       
       if (choice.text == 'Phone' )
       {
           <c:forEach items="${form.GCSMQuestionsWithStudyHelp}" var="gcsmQuestion">
               selectDefaultByElementID('<c:out value="${gcsmQuestion.value.inputName}"/>','N/E');
            </c:forEach>


       }
   }//----------------------------------------------------

      function setDefaultToNE(idArray)
      {
      for (x = 0; x < idArray.length; x++)
         {
           selectDefaultByElementID(""+idArray[x],"N/E");

         }

      }  //----------------------------------------------------
       function clearDefaultToNE(idArray)
            {
            for (x = 0; x < idArray.length; x++)
               {
                 selectDefaultByElementID(""+idArray[x],"");

               }

            }  //----------------------------------------------------



 

</script>