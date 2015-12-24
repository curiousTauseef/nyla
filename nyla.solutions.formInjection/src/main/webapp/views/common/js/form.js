   function openHelp(aQuestionId)
   {
     var helpLink = document.getElementById("helpLink");
      var help = window.open(helpLink.href+"?questionID="+aQuestionId,    "helpText", "height=400,width=500,scrollbars=yes,resizable=yes", false);
      help.focus();
   }//------------------------------------------------------------------------------
   function isEmpty(inputStr) 
   {
      if (inputStr == "" || inputStr == null) 
      {
         return true;
      }
     
     return false
 }//------------------------------------------------------------------------------------
   function openStudyHelp(aQuestionId)
   {
     var helpLink = document.getElementById("helpLink");   
       var help = window.open(helpLink.href+"?questionID="+aQuestionId+"#StudyHelp", "helpText", "height=400,width=500,scrollbars=yes,resizable=yes", false);
      help.focus();
   }//------------------------------------------------------------------------------   
   function selectDefaultByElementID(aElementId, aOptionText)   
   {
            var aSelect = document.getElementById(aElementId);
            
            if(aSelect != null)
            {
               //alert(aSelect.name);
               var elementOption = findOptionByText(aSelect, aOptionText)
               
               if(elementOption != null)
               {
                  //pick option
                  aSelect.selectedIndex = elementOption.index;
               }
            }
   }//-----------------------------------------------------------
   function findOptionByText(aSelect, aOptionText)
   {
       var aSelectOption = null;
       
       for(i=0; i < aSelect.options.length; i++)
       {
          aSelectOption = aSelect.options[i];
          
          if(aSelectOption.text == aOptionText)
          {
              return aSelectOption;
          }
       }
              
       return null;
   }//-----------------------------------------------------------
function getRadioAnswer(question) 
{
   var app_option = -1;
   var app_value;
   for (i=0; i<question.length;i++) {
      if (question[i].checked) {
         app_option = i;
         app_value = question[i].value;
      }
   }
   //get the value; if question has one choice, default id is populated
   if (typeof(app_value) == "undefined" && app_option == -1) {
      app_value = question.value;
   }
   
   if (app_value == null) {
      return null;
   } else {
      return app_value;
   }
}//-------------------------------
function renewForm(aForm)
{
    var msg = "";   
    if(aForm.filed == "true")
    {
       msg = "Editing this 'Posted to ESF' Report will create a new version of its form with a status of Incomplete. Are you sure you want to edit this 'Posted to ESF' Report ?";
    }
    else
    {
        msg  = "Editing this 'Submitted' Report  will set its status to Incomplete and it will no longer be viewable to other personnel until it is resubmitted. Are you sure you want to edit this Report?";   
     }
     
      if(confirm(msg))
      {
         aForm.action = aForm.action+"?op=renew";
        //alert(' aForm.op.value='+ aForm.op.value);    
        aForm.submit();
      }
}//------------------------------
function saveForm(aForm)
{
  if(checkFormSave(aForm))
  {
      aForm.action = aForm.action+"?op=save";
     aForm.op.value = "save";
     //alert(' aForm.op.value='+ aForm.op.value);    
     aForm.submit();
    }
    else
    {
       printConfirmation('<span class=error>THE FORM HAS NOT BEEN SAVED.</span>');
    }
}//------------------------------
   function checkFormSave(aForm)
   {
      clearConfirmation();
      
      if(validateSave(aForm))
      {
         return true;
       }
       else
       {
          return false;
       }
   }//-------------------------------------------------------
   function clearConfirmation()
   {
         var confirmationText = document.getElementById("confirmation");
         if(confirmationText  !=  null)
         {
            confirmationText.innerHTML = "&nbsp;";
         }
   }//------------------------------------------
   function completeForm(aForm)
   {  
      //aForm.op.value = "complete";
      var foundError = false;
      
      if(validateRequiredInput(aForm.loginID)  &
         validateRequiredInput(aForm.password) )
      {         
         aForm.action = aForm.action+"?op=complete";
         aForm.submit();
      }
   }//------------------------------
function submitForm(aForm)
{   
   if(validateRequiredInput(aForm.loginID)  &&
         validateRequiredInput(aForm.password) )
   {
      aForm.action = aForm.action+"?op=submit";
      aForm.submit();
   }
}//--------------------------------------
function signForm(aForm)
{
   clearConfirmation();
   
   if(validate(aForm,false))
   {
      aForm.action = aForm.action+"?op=sign";
      //alert('aForm.action='+aForm.action);
      aForm.submit();
   }
   else
   {
      printConfirmation('<span class=error>THE FORM CANNOT BE SUBMITTED.</span>');
   }
}//--------------------------------------
function printConfirmation(aMessage)
{
        var confirmationText = document.getElementById("confirmation");
         if(confirmationText  !=  null)
         {
            confirmationText.innerHTML = aMessage;
         }
}//-------------------------------------------------------------
function viewForm(aForm)
{
   aForm.action = aForm.action+"?op=view";
      //alert('aForm.action='+aForm.action);
      aForm.submit();
}//--------------------------------------------
//postFormIssue
function postFormIssue(aForm)
{
    //var postIssueWindow = window.open("/gcsm/do/PostIssueAction?method=init&formID="+aForm.formID.value,  "Post Issue", "height=400,width=500,scrollbars=yes,resizable=yes", false);
    
    window.open("/gcsm/do/PostIssueAction?method=init&formID="+aForm.formID.value,"PostIssue","height=680,width=490,scrollbars=no,resizable=yes", false );
}//--------------------------------------------
   //Mark form as complete
   function signCompletionForm(aForm)
   {  
      aForm.op.value = "signCompletion";
      aForm.action = aForm.action+"?op=signCompletion";
      aForm.submit();
   }//------------------------------
function deleteForm(aForm)
{
   if(confirm('Are you sure you want to delete this form? Any changes will be lost.'))
   {
      aForm.action = aForm.action+"?op=delete";
      //alert('aForm.action='+aForm.action);
      aForm.submit();
   }
}//--------------------------------------
function addRowWithImagePath(aTableId,aImagePath )
{

    var table =document.getElementById(aTableId);
    var newAnswerRowIndex = table.rows.length - 2;
    var lastRow = table.rows[ table.rows.length-1];    
    var footerRow = null;
    var hasFooter = false;
    var insertIndex =  table.rows.length;
    
  if((lastRow.innerHTML.indexOf("=footer") > -1 ))
  {
     hasFooter = true;
     insertIndex =  table.rows.length -1;      
      footerRow = lastRow;
       
       newAnswerRowIndex = newAnswerRowIndex -1;     
      lastRow = table.rows[ table.rows.length-2];

   }

 //alert('newAnswerRowIndex='+newAnswerRowIndex); // remove
  
    var newRow = table.insertRow ( insertIndex );     
    var newCell = "";
    var newHTML = "";
      // alert('newAnswerRowIndex='+newAnswerRowIndex); //TODO: remove
       
    for(i = 0; i < lastRow.cells.length; i++)
    {
       newCell =  newRow.insertCell(i);

       newHTML = replaceRowIndex( lastRow.cells[i].innerHTML,newAnswerRowIndex);           

       if( i  + 1== lastRow.cells.length )
       {

          // alert(newHTML); 
           if(  newHTML.indexOf("form_delete_dynamic_row")  <  0 )
            {
                //add delete row image
               newHTML = newHTML + "<IMG style='float:right;vertical-align:bottom' src='"+aImagePath+"' onclick=\"deleteRow('" + aTableId + "'," + newAnswerRowIndex + ");\"/>";
            }
            
            if(newHTML.indexOf("property=canDeleteRow") < 0)
            {
                //add property to row that indicates that it was entered manually  input hidden input field
                newHTML = newHTML + "<INPUT type='hidden' name='property=canDeleteRow;tableId="+aTableId+";row="
                                                            + newAnswerRowIndex+";' value='true'/>";
                                                            
                //alert("canDelete input="+newHTML);
            }
          }//end if  i  + 1== lastRow.cells.length
          
        //alert(newHTML);
       newCell.innerHTML = newHTML;   
       
       }//end for loop     
         
 	addHiddenRowNumber(aTableId);
 	//alert(table.innerHTML);
 	/*
 	for (var i = 0; i , table.rows.length; i++) {
 		alert(table.rows[i].id);
 	}
 	*/
 	
}//-----------------------------------------------------------------------------
function addRowWithCells(aTable,  aRow)
{
    var newRow = aTable.insertRow(aTable.rows.length); 
    var newRowCell = null;
    
     for(i = 0; i < aRow.cells.length; i++)
      {
         newRowCell =  newRow.insertCell(i);
         newRowCell.innerHTML = aRow.cells[i].innerHTML;
      }
}//---------------------------------------------------------------------------
function addRow(aTableId)
{
   //addRowWithImagePath(aTableId, "/gcsm/skins/default/images/"); 
   var img = document.getElementById("img_delRow");
   addRowWithImagePath(aTableId, img.src);  
}//-----------------------------------------------------------------------------
function deleteRow(aTableID, aAnswerRowNumber)
{
    var table =document.getElementById(aTableID);
    var tbodies = table.getElementsByTagName("tbody");
    if (tbodies.length > 0)
    	table = tbodies[0];
    
    //loop thrus rows
    var i = 2;
    var rowHTML = "";
    var matchText = "";
    var rowNumber = 0;
    for( i=0; i  < table.rows.length; i++  )
    {
        rowHTML = table.rows[i].innerHTML;
        matchText = "]["+aAnswerRowNumber + "]";
        //alert(matchText + "\n" + rowHTML);
       // alert("matchText="+matchText);  remove
        
        if(rowHTML.indexOf( matchText  ) > -1 )
        {        
            //clear HTML
            for(col=0; col < table.rows[i].cells.length; col ++)
            {
                table.rows[i].cells[col].innerHTML = "";
            }
            
          //alert("Deleting row "+ i);  //remove
           table.deleteRow( i );
           removeHiddenRowNumber(aTableID);
           i--;
        }
        else
        {
           //re order
            changeRowNumber(table, i,  rowNumber );
            rowNumber ++;
        }        
    }//end loop
    //alert("deleteRow():\n" + table.innerHTML);
}//-----------------------------------------------
//  ex.<INPUT  class=answer 
 // name=v_false_table__0_2000_q1[0][2]>
function replaceRowIndex(aText,aAnswerRowIndex )
{

   //format [col][row]
  //replace all rows numbers
    //alert('table.rows.length='+table.rows.length);
    var startIndex = 0;
    var currentPosition = 0; 
    startIndex = aText.indexOf("][");
    var newHTML =""; 
    //alert('1');
    while(startIndex > -1)
    {

        newHTML = newHTML+aText.substring(currentPosition,startIndex+2);
        //alert('newHTML='+newHTML); 

        newHTML = newHTML +aAnswerRowIndex;
       // alert('replaced newHTML='+newHTML); 

        currentPosition = aText.indexOf("]", startIndex + 2);  //GREG changes 8-19-2005

        startIndex = aText.indexOf("][",currentPosition);

     }
    
     //copy rest of HTML test
     if(currentPosition < aText.length)
     {
        newHTML = newHTML + aText.substring(currentPosition,aText.length);    
     }
     
     if(newHTML.indexOf("deleteRow(") > -1 )
     {
        //replace ",${number})" with 
       //alert(newHTML.match(/, ?[0-9]*\)/));
       newHTML =  newHTML.replace(/, ?[0-9]*\)/, ","+aAnswerRowIndex+")");
       //alert("should have changed image onclick to answer row index " + aAnswerRowIndex + ":\n" + newHTML);
     }
     
     //replace hidden row properties
     if(newHTML.indexOf("row=") > -1)
     {
        newHTML =  newHTML.replace(/row\=[0-9]*/, "row="+aAnswerRowIndex );
       //alert("replaced row="+newHTML);
     }
     
    //alert('newHTML='+newHTML);
    
     return  newHTML;
}//-----------------------------------------------------------------------------------
//," + newAnswerRowIndex + ");
function   changeRowNumber(aTable, aRowIndex,  aRowNumber )
{
	//alert("rowIndex=" + aRowIndex + ", rowNumber=" + aRowNumber);
    //loop thru cells 
    var row = aTable.rows[aRowIndex];
    
 //   alert("Changing row="+aRowNumber);  remove
    var newHTML = "";
    for(i=0; i < row.cells.length; i++)
    {
    	//alert("before\n" + row.cells[i].innerHTML);
       newHTML = replaceRowIndex(row.cells[i].innerHTML,aRowNumber )        
        row.cells[i].innerHTML = newHTML;
        //alert("changeRowNumber: rowIndex=" + aRowIndex + ", rowNumber=" + aRowNumber + "\n" + row.cells[i].innerHTML);
    	//alert("after\n" + row.cells[i].innerHTML);
        
 //       alert("New cell "+ newHTML);: remove
    }
}///----------------------------------------------------------------------------
function addHorizontalRow(aTableId)
{
	var img = document.getElementById("img_delRow");
   addHorizontalRowWithImagePath(aTableId, img.src);   
}///-----------------------------------------------------------------------------
function addHorizontalRowWithImagePath(aTableId,aImagePath )
{

    var table =document.getElementById(aTableId);
    var newAnswerRowIndex = table.rows.length - 1;
    var lastRow = table.rows[ table.rows.length-1];
    var newRow = table.insertRow(table.rows.length);     
    var newCell = "";
    var newHTML = "";   
       
     // alert('aImagePath='+aImagePath);
       
    for(i = 0; i < lastRow.cells.length; i++)
    {
       newCell =  newRow.insertCell(i);
       newHTML = replaceRowIndex( lastRow.cells[i].innerHTML,newAnswerRowIndex);
       
       if( i  + 1== lastRow.cells.length )
       {
         // alert(newHTML); //TODO: remove
          
           if(  newHTML.indexOf(aImagePath)  <  0 )
            {
                //add delete row image
               newHTML = newHTML + "<IMG src='"+aImagePath+"' onclick=\"deleteRow('" + aTableId + "'," + newAnswerRowIndex + ");\"/>";
            }
            
            if(newHTML.indexOf("property=canDeleteRow") < 0)
            {
                //add property to row that indicates that it was entered manually  input hidden input field
                newHTML = newHTML.replace("<HR>","");
                newHTML = newHTML + "<INPUT type='hidden' name='property=canDeleteRow;tableId="+aTableId+";row="
                                                            + newAnswerRowIndex+";' value='true'/><HR>";
                                                            

            }
       }
       
       //alert(newHTML);
       newCell.innerHTML = newHTML;       
   }
   
 	addHiddenRowNumber(aTableId);
   
}//-----------------------------------------------------------------------------
function addHiddenRowNumber(aTableId) {
 	var hiddenInput = document.aForm.elements["tableRowNumbers_" + aTableId];
 	var count = 0;
 	if (hiddenInput.value != "")
 		count = parseInt(hiddenInput.value)+1;
 	else {
 		var table = document.getElementById(aTableId);
 		var tbodies = table.getElementsByTagName("tbody");
 		if (tbodies.length > 0) {
 			count = tbodies[0].rows.length;
 		}
 		else {
 			count = table.rows.length;
 		}
	}
 	hiddenInput.value = count;
 	//alert("addHiddenRowNumber()" + hiddenInput.value);
}//--------------------------------------
function removeHiddenRowNumber(aTableId) {
 	var hiddenInput = document.aForm.elements["tableRowNumbers_" + aTableId];
 	var count = 0;
 	if (hiddenInput.value != "")
 		count = parseInt(hiddenInput.value)-1;
 	else {
 		var table = document.getElementById(aTableId);
 		var tbodies = table.getElementsByTagName("tbody");
 		if (tbodies.length > 0) {
 			count = tbodies[0].rows.length;
 		}
 		else {
 			count = table.rows.length;
 		}
	}
 	hiddenInput.value = count;
 	//alert("removeHiddenRowNumber()" + hiddenInput.value);
}//--------------------------------------
function showSpecifyControl(aID)
{
   var elementStyle = document.getElementById(aID).style;   
   
   if(elementStyle.display == "none" || elementStyle.display.length == 0)
   {
     elementStyle.display = "block";      
   }
   else
   {   
      elementStyle.display = "none";
   }
}//-------------------------------------------------------------------------
   
    function populateChoices(aChoicesText, aInputName, aInputValue,aChoiceName)
   {
         var allChoices = aChoicesText.split(";");
         var choices  = document.multiSelectForm.choices;
         
         var idAndText = null;
         var aOption  = null;
         
         var formHeaderTextCell =  document.getElementById('formHeaderText');
         
         if( formHeaderTextCell  !=  null)
         {
            formHeaderTextCell.innerHTML = "&nbsp;Edit "+aChoiceName;
         }
        
         //document.multiSelectForm.answerID.value = aInputName +"_answer" ; 
         document.multiSelectForm.answerID.value = aInputName; 
         document.multiSelectForm.answerIDText.value = document.multiSelectForm.answerID.value + "_Text";
         
         //Populated Choices
         for(i=0; i< allChoices.length; i ++)
         {
            //format id=Text
            idAndText = allChoices[i].split("=");
            
            if (idAndText[1].length > 1) {          
	            aOption = new Option();
	            aOption.value =  idAndText[0]; 
	                      
	             if( idAndText.length > 1 )
	             {     
	               aOption.text =unescape( idAndText[1].replace(/\+/g, " "));                  
	             }
	                                         
	            choices.add(aOption);
            }
         } 
       
        //SELECTED ANSWERS CHOICES
        if(aInputValue != null && aInputValue.length > 0)
        {
             
              var selectedChoices  = document.multiSelectForm.selectedChoices;              
             
               //Populated Selected Choices
               var selectedChoiceIDs =   aInputValue.split(",");
               
               var choiceOption = null;
               for(i=0; i < selectedChoiceIDs.length; i++)
               {
                   choiceOption = findChoiceOptionByValue(choices, selectedChoiceIDs[i]);
                   
                   if(choiceOption  != null)
                   {
                               aOption = new Option();
                              aOption.value =  choiceOption.value;
                              aOption.text =  choiceOption.text;
                              selectedChoices.add(aOption);      
                   }
                   
               }
         }
         
         document.multiSelectForm.answerValue.value  = aInputValue;
         window.focus();
   }//-----------------------------------------------
   function findChoiceOptionByValue(aSelect, aValue)
   {
      if(aSelect == null)
         return null;
         
       for(c=0;c < aSelect.length; c++)
       {
           if(aSelect.options[c].value == aValue)
              return aSelect[c];
       }
       
        return null;
   }//-------------------------------------------------
   function resizeMultiSelect(win)
   {
      if(win == null)
      	return;
      var width = (win.document.multiSelectForm.choices.clientWidth*2)+100;
      if (width > screen.availWidth)
      	width = screen.availWidth;
      if (width > win.document.body.clientWidth)
      	win.resizeTo(width, win.document.body.clientHeight);
      win.document.multiSelectForm.selectedChoices.style.width = win.document.multiSelectForm.choices.clientWidth + "px";
      
      //alert(win.document.body.clientHeight);
   }//-------------------------------------------------
  function clearMultiSelect(aForm)
  {
       var selectedChoices = document.getElementById('selectedChoices');
      for(i=0; i < selectedChoices.length; i++)
      {
          selectedChoices.remove(i);
      }
      selectedChoices.length = 0;
      
      //clear answer values
      aForm.answerValue.value = "";
      
       return false;
  }//-----------------------------------
  function submitMultiSelect(aForm)
  { 
        
       
      //alert('aForm.answerID.value='+aForm.answerID.value);
      //pick answer hidden field
      var parentAnswerHiddenInput = opener.document.getElementById(aForm.answerID.value);      
      parentAnswerHiddenInput.value = aForm.answerValue.value;
      
      //Change Text
      var answerText = "";
      var selectAnswerIDs = parentAnswerHiddenInput.value.split(",");
      var choiceOption = null;
      for(x=0; x < selectAnswerIDs.length; x++)
      {
           //Find options
          choiceOption =  findChoiceOptionByValue(document.multiSelectForm.choices, selectAnswerIDs[x]);
          
                 
          
          if(choiceOption != null && choiceOption.text != "undefined")
           {
               if(answerText.length == 0 )
               {
                  answerText = choiceOption.text;
               }
                else
                {
                    //append 
                    answerText = answerText + "," + choiceOption.text;
                }
                
           }
      }
     
     // alert('answerText='+answerText);
      var   parentQuestionText = opener.document.getElementById(aForm.answerIDText.value);
      parentQuestionText.innerHTML = answerText;
      
      //alert('answerText='+answerText);
      window.close();
      return false;
  }//-----------------------------------
  function cancelMultiSelect(aForm)
  {
       window.close();
       return false;
     }//---------------------------------------------------------
  function unpickMultiSelectedChoices(aForm)
  {
     var selectedChoices = document.getElementById('selectedChoices');
      //alert('choices.selectedIndex='+choices.selectedIndex );
     for (var i = 0; i < selectedChoices.length; i++) {
     	 if (!selectedChoices[i].selected)
     		continue;
         
         selectedChoices.remove(i);
         i--;
     }
        
         if( aForm.answerValue.value != "")
         {
                aForm.answerValue.value = "";
                for(c=0; c < selectedChoices.length ; c++)
                {
                   aForm.answerValue.value += selectedChoices.options[c].value;
                   
                   if( c + 1 <  selectedChoices.length)
                   {
                        aForm.answerValue.value = aForm.answerValue.value + ",";
                   }
                }
                 
         }  
  }//------------------------------------------------------------
  function pickMultiSelectedChoices(aForm)
  {
     //alert('multiSelectForm');
     
     var choices = document.getElementById('choices');
     var selectedChoices = document.getElementById('selectedChoices');

     //alert('choices.selectedIndex='+choices.selectedIndex );

     for (var i = 0; i < choices.length; i++) {
     	if (!choices[i].selected)
     		continue;
     		
        var selectedOption = choices[i];
             
        //alert(selectedChoices.options[0].text);
        if( ! containsOptionValue(selectedChoices, selectedOption.value) )
        {
              var o = new Option( selectedOption.text, selectedOption.value, false, true);   
              selectedChoices.add(o);
              
               //check if answer already has choice selected
               if(! selectAnswerContainsIndex(aForm.answerValue,  selectedOption.value))
               {
                  if(aForm.answerValue.value.length == 0 )  //blank
                  {
                      aForm.answerValue.value = selectedOption.value;
                      
                      //alert("answerValue.value="+aForm.answerValue.value);
                  }
                  else
                  {
                        //add value  to answer 
                        aForm.answerValue.value = aForm.answerValue.value+","+selectedOption.value;
                        
                        //alert("answerValue.value="+aForm.answerValue.value);
                    }//end else blank
               }
         }
         selectedOption.selected = false;
    }      
  }//---------------------------------------------------------
  //detemer it answer has index
  function selectAnswerContainsIndex(aInput, aIndex)
  {
      if( aInput.value.length == 0)
         return false;
                  
       var answerIndexes = aInput.value.split(",");
       
       for(i=0; i< answerIndexes.length; i ++)
       {
          if(answerIndexes[i] == aIndex )
             return true;
       }
       
       return false;
  }//-----------------------------------------------------------------
  function containsOptionValue(aSelect, aOptionValue)
  {
     for(i=0; i < aSelect.length; i ++)
     {
        if( aSelect.options[i].value == aOptionValue )
           return true;
     }
     return false;
  }//---------------------------------------------------------------------------
  
  function scrollFormToAnchor(aId,divId) {
			aObj = document.getElementById(aId);
			divObj = document.getElementById(divId);
			divObj.scrollTop = aObj.offsetTop;			

		}

  // ------ CALENDAR POPUP -------
  var dateFieldName = null;
  var form;
  function showCalendar(img) 
  {
     alert('showCalendar');
     
  	if (!form) {
  		alert("using default form -- last one on page");
  		form = document.forms[document.forms.length-1];
  	}
	dateFieldName=img.id.substring("img_".length);
	var cal = document.getElementById("cal");
	var calFrame = cal.getElementsByTagName("iframe")[0];
  	var left = window.event.clientX+document.body.scrollLeft-window.event.offsetX;
  	var top = window.event.clientY+document.body.scrollTop-window.event.offsetY+img.height;
  	// tweaking
  	left -= 1;
  	top -= 1;
	
  	// check visible area and adjust to fit
  	var frameWidth = calFrame.clientWidth;
  	//var frameHeight = calFrame.clientHeight;
  	var frameHeight = 124;	// fix to height to account for months that have an extra row

  	var viewableRight = document.body.clientWidth+document.body.scrollLeft;
  	var frameRight = left+frameWidth;
  	if (frameRight > viewableRight) {
  		var adjust = frameRight-viewableRight;
  		left-=adjust;
  	}
  	
  	var viewableBottom = document.body.clientHeight+document.body.scrollTop;
  	var frameBottom = top+frameHeight;
  	if (frameBottom > viewableBottom) {
  		var adjust = frameBottom-viewableBottom;
  		top-=adjust;
  	}

	cal.style.left = left;
	cal.style.top = top;
	cal.style.display="";
	window.event.cancelBubble = true;
  }//------------------------------------------------------------------------
  
  function hideCalendar() 
  {
   	var cal = document.getElementById("cal");
   	if (cal)
   		cal.style.display="none";
  }//------------------------------------------------------------------------  
  function sumColumn(aColumnNumber, aTotalInputElement, aColumnLabel, aQuestionPrefix,aTablePK)
{  
      var rowNumber = 0;
       var total = 0;  
      var inputElement = null;
      var elementId = null;

      do
      {
      
         elementId =aQuestionPrefix+'['+aColumnNumber+']'+'['+rowNumber+'];tablePK='+aTablePK+';';
         //alert('elementId='+elementId);
         inputElement = document.getElementById(elementId);
         //alert('inputElement='+inputElement);
         
         if( inputElement != null &&
              isValidNumber(aColumnLabel, inputElement, 1, 20, false))
         {
              total = total +inputElement.value*1;
         }
         rowNumber++;        
      }
      while( inputElement != null );
      
      //set total      
      aTotalInputElement.value=total;
      
   }//----------------------------------------------------   
   function sumRows(tableId , aQuestionPrefix, aTablePK, aStartColumn, aSummaryColumn)
   {
      var columnNumber = new String(aStartColumn);
      var rowNumber = 0;      
      var elementId = null;
      var inputElement = null;
      var total = 0;
      
      do
      {

         elementId =aQuestionPrefix+'['+columnNumber+']'+'['+rowNumber+'];tablePK='+aTablePK+';';

         inputElement = document.getElementById(elementId);
       // alert('inputElement='+inputElement+' elementId='+elementId); // remove
         
         if( inputElement != null &&
              isValidNumber("", inputElement, 1, 20, false) &&
             columnNumber != aSummaryColumn )
         {
              total = total + inputElement.value*1;

         }
         
         
         //disable sum column and put total
         if(columnNumber >= aSummaryColumn && inputElement != null)
         {
               inputElement.onfocus = "blur();";
               inputElement.value  = total;
              //alert('total='+total);               
               total = 0;
         }
             
         
         //look at next row
         if(columnNumber >= aSummaryColumn)
         {
             columnNumber = new String(aStartColumn*1 - 1);
             rowNumber++;
         }
          columnNumber++;        
         
      }
      while( inputElement != null );
   }//---------------------------------------------------------------
   function validateDateLessThanTodayColumn(aColumnNumber, aColumnLabel, aQuestionPrefix,aTablePK)
  {              
       var rowNumber = 0;
      var inputElement = null;
      var elementId = null;

      do
      {
      
         elementId =aQuestionPrefix+'['+aColumnNumber+']'+'['+rowNumber+'];tablePK='+aTablePK+';';
         //alert('elementId='+elementId);
         inputElement = document.getElementById(elementId);
         //alert('inputElement='+inputElement);
         
         if( inputElement != null)
         {
              if(!checkIfDateLaterThanTodayDDMMMYYYY(aColumnLabel, inputElement, false))
             {
                 return false;
              }
         }
            
         rowNumber++;        
      }
      while( inputElement != null ); 
      
      return true;
   }//----------------------------------------------------   
   

 