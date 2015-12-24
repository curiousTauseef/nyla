/**
  * TEXT FIELD TYPE NAME global variable
 */
var TEXT_TYPE = "text";

/**
  * TEXT AREA TYPE NAME global variable
 */
var TEXTAREA_TYPE = "textarea";

var validateOnSaveFields = new Array();
var validateOnSubmitFields = new Array();

function markValidateOnSave(elem) {

	if (!validateOnSaveFields[elem.name]) {
//	alert('in markValidateOnSave ' + elem.name);
		validateOnSaveFields[elem.name] = elem;
		validateOnSaveFields.push(elem);
		validateOnSubmitFields[elem.name] = null;
		//alert(validateOnSaveFields.length);
		//alert(validateOnSaveFields[elem.name]);
	}
}
function markValidateOnSubmit(elem) {
	if (!validateOnSaveFields[elem.name] && !validateOnSubmitFields[elem.name]) {
		validateOnSubmitFields[elem.name] = elem;
		validateOnSubmitFields(elem);
		//alert(validateOnSaveFields.length);
		//alert(validateOnSaveFields[elem.name]);
	}
}

//Validate date
function validateDate_MMDDYYYY(str)
{
	var pattern_date = new RegExp("[0-9]?[0-9][\/][0-9]?[0-9][\/][0-9][0-9][0-9][0-9]");		
	
	if ( (str.length) < 8 || str.length > 10  || !pattern_date.test(str) )
		return false;
	var iMonth = str.substring (0, str.indexOf("/"));
	var iDay  = str.substring (str.indexOf("/")+1 , str.lastIndexOf("/"));
	var iYear  = str.substring (str.lastIndexOf("/") + 1, str.length);
			
	iMonth = parseInt(iMonth,10);
	iDay  = parseInt(iDay,10);
	iYear  = parseInt(iYear,10);	
		
	if ( iMonth < 1  || iMonth > 12 )
		return false;
		
		
	if ( iMonth == 1 || iMonth ==3 || iMonth == 5 || iMonth == 7 || iMonth == 8 || iMonth ==10 || iMonth ==12 )
	{
		if ( iDay < 1 || iDay > 31)
			return false;
	}
	else if ( iMonth == 4 || iMonth == 6 || iMonth == 9 || iMonth == 11 )
	{
	
		
		if ( iDay < 1 || iDay > 30)
			return false;
	}				
	else if ( iMonth ==2 )
	{
	
		if (isLeapYear (iYear))
		{
			if (iDay < 1 || iDay > 29)
				return false;
		}
		else
		{
			if (iDay < 1 || iDay > 28)
					return false;		
		}
	}
	str = iMonth + '/' + iDay + '/' + iYear;
	
	return true;
}//-------------------------------------------------------
//Validate date
function validateDate_DDMMMYYYY(str)
{
	//var pattern_date = new RegExp("[0-9]?[0-9][\/][0-9]?[0-9][\/][0-9][0-9][0-9][0-9]");
	var pattern_date = new RegExp("[0-9]?[0-9][-](JAN|Jan|FEB|Feb|MAR|Mar|APR|Apr|MAY|May|JUN|Jun|JUL|Jul|AUG|Aug|SEP|Sep|OCT|Oct|NOV|Nov|DEC|Dec)[-][0-9][0-9][0-9][0-9][ ]*");

	if ( (str.length) < 8 || str.length > 11  || !pattern_date.test(str.toUpperCase()) )
		return false;
					
	return true;
}//-------------------------------------------------------
function isLeapYear(Year)
{
		if (((Year % 4) ==0) && ((Year % 100) == 0) || ((Year % 400) == 0))
			return true;
		else
			return false;
}//-------------------------------------------------------
function checkIfFirstDateIsLessThanOrEqualToSecondDate (strFirstDate, strSecondDate)
{
    //Date expected in MM/DD/YYYY format
    var array_FirstDate	= strFirstDate.split('/');
    var array_SecondDate	= strSecondDate.split('/');
      
	var aMonth_FirstDate	= array_FirstDate[0];
	var aDay_FirstDate		= array_FirstDate[1];
	var aYear_FirstDate		= array_FirstDate[2];
      
	var aMonth_SecondDate	= array_SecondDate[0];
	var aDay_SecondDate		= array_SecondDate[1];
	var aYear_SecondDate	= array_SecondDate[2];

	var dateFirst = new Date(aYear_FirstDate, aMonth_FirstDate-1, aDay_FirstDate);
	var dateSecond = new Date(aYear_SecondDate, aMonth_SecondDate-1, aDay_SecondDate);

	if (dateFirst <= dateSecond)
		return false;
	else
  		return true;
}//-----------------------------------------------------------
function checkIfDateLaterThanToday(strDate)
{
	var aNow = new Date();

    //Date expected in MM/DD/YYYY format
    var array  = strDate.split('/');
      
	var aMonth = array[0];
	var aDay = array[1];
	var aYear  = array[2];
      
	var enteredDate = new Date(aYear, aMonth-1, aDay);

	if (enteredDate < aNow)
		return false;
	else
  		return true;
}//-----------------------------------------------------------
function checkIfDateLaterThanTodayDDMMMYYYY(aFieldName, element, aRequired)
{
       
   	 var aNow = new Date();
       var month = 0;
   	
   	clearError(element);
   	      
       if(aRequired && (element == null || element.value == null || element.value.length == 0))
       {
            printError( element, aFieldName + " is required.");
            return false;
       }

       if(!aRequired &&  (element == null || element.value == null || element.value == "" || element.value.length == 0))
       {
          return true;
       }
              
       var array  = element.value.split('-');       
       
       if(array.length != 3)
       {
          printError( element, aFieldName + " must be a valid date. Format DD-MMM-YYYY (ex. 12-AUG-2005).");
          return false;
        }
       
        var aDay            = array[0];      
   	  var monthName = array[1];
     	  var aYear            = array[2];     
       
         if(monthName == "JAN")
         {
            month = 0;
         }
         else if(monthName ==  "FEB")
         {
             month = 1;
         }
         else if(monthName ==  "MAR")
         {
             month = 2;
         }
         else if(monthName ==  "APR")
         {
             month = 3;
         }      
         else if(monthName ==  "MAY")
         {
             month = 4;
         }
         else if(monthName ==  "JUN")
         {
             month = 5;
         }
         else if(monthName ==  "JUL")
         {
             month = 6;
         }      
         else if(monthName ==  "AUG")
         {
             month = 7;
         }    
         else if(monthName ==  "SEP")
         {
             month = 8;
         }     
         else if(monthName ==  "OCT")
         {
             month = 9;
         }                  
         else if(monthName ==  "NOV")
         {
             month = 10;
         }         
         else if(monthName ==  "DEC")
         {
             month = 11;
         }   
         else
         {
            printError( element, aFieldName + " must be a valid date. Format DD-MMM-YYYY (ex. 12-AUG-2005).");
            return false;
         } 
         
         var tempMonth=month+1;
         
         var temp=tempMonth+"/"+aDay+"/"+aYear; 
         
                
         if(!validateDate_MMDDYYYY(temp))
         {
            printError( element, aFieldName + " must be a valid date.");
            return false;
         }
              
   	 var enteredDate = new Date(aYear, month, aDay);

         if (enteredDate > aNow)
         {
            printError( element, aFieldName + " must be less than or equal to today's date.");
      	    return false;
      	 }
   	 else
   	 {
   	      clearError(element);
      	      return true;
         }
}//-----------------------------------------------------------
function toDateDDMMMYYY(aElement)
{
   var array  = aElement.value.split('-');       
       
   if(array.length != 3)
   {
      printError( aElement, "Field must be a valid date. Format DD-MMM-YYYY (ex. 12-AUG-2005).");
          return false;
   }
   
   var aDay      = array[0];      
   var monthName = array[1];
   var aYear     = array[2];     
       
   if(monthName == "JAN")
   {
            month = 0;
         }
         else if(monthName ==  "FEB")
         {
             month = 1;
         }
         else if(monthName ==  "MAR")
         {
             month = 2;
         }
         else if(monthName ==  "APR")
         {
             month = 3;
         }      
         else if(monthName ==  "MAY")
         {
             month = 4;
         }
         else if(monthName ==  "JUN")
         {
             month = 5;
         }
         else if(monthName ==  "JUL")
         {
             month = 6;
         }      
         else if(monthName ==  "AUG")
         {
             month = 7;
         }    
         else if(monthName ==  "SEP")
         {
             month = 8;
         }     
         else if(monthName ==  "OCT")
         {
             month = 9;
         }                  
         else if(monthName ==  "NOV")
         {
             month = 10;
         }         
         else if(monthName ==  "DEC")
         {
             month = 11;
         }   
         else
         {
            printError( aElement, aFieldName + " must be a valid date. Format DD-MMM-YYYY (ex. 12-AUG-2005).");
            return false;
         }         
              
   	 return new Date(aYear, month, aDay);
}//-------------------------------------------------------------------------------------------
function needsValidation(aElement, aRequired)
{
   if (aRequired == false && aElement.value.length <= 0)
   {
      return false;
   }

   return true;
}//-------------------------------------------------------
function isValidLen(aFieldName, aElement, aMinLen, aMaxLen)
{   
   clearError(aElement);
   
   if (aElement.value.length < aMinLen ||
       aElement.value.length > aMaxLen)
   {
      if (aMinLen != aMaxLen)
      {
         if(aMinLen ==  0 )
         {
              printError(aElement, aFieldName + " must be no more than " + aMaxLen + " characters, the current character count is "+aElement.value.length+".");                     
         }
         else
         {
              printError(aElement, aFieldName + " must be at least " + aMinLen +
                     " but no more than " + aMaxLen + " characters, the current character count is "+aElement.value.length+".");         
         }
      }
      else
      {
         printError( aElement, aFieldName + " must be " + aMinLen + " characters.");
      }

     // aElement.focus();
      return false;
   }

   return true;
}//-------------------------------------------------------
function isValidText(aFieldName, aElement, aMinLen, aMaxLen, aRequired)
{
   if (!needsValidation(aElement, aRequired))
   {
      return true;
   }
   
   if(aRequired && !validateRequired(aElement) )
   {
      return false;
   }

   if (!isValidLen(aFieldName, aElement, aMinLen, aMaxLen))
   {
     // aElement.focus();
      return false;
   }

   return true;
}//--------------------------------------------------------------

function isValidNumber(aFieldName, aElement, aMinLen, aMaxLen, aRequired)
{
   if (!needsValidation(aElement, aRequired))
   {
      clearError(aElement );
      return true;
   }
   
   if(aRequired && !validateRequired(aElement,aFieldName) )
   {
      return false;
   }
   
   if (!isValidLen(aFieldName, aElement, aMinLen, aMaxLen))
   {
     // aElement.focus();
      return false;
   }

   var ParseOK = true;

   for (i = 0; i < aElement.value.length; ++ i)
   {
      if (aElement.value.charAt(i) < '0' ||
          aElement.value.charAt(i) > '9')
      {
         ParseOK = false;

         break;
      }
   }

   if (ParseOK == false)
   {
      printError(aElement, aFieldName + " must be all numbers.");
     // aElement.focus();
      return false;
   }
   else
   {
      clearError(aElement );
   }

   return true;
}//--------------------------------------------------------------

function isValidDouble(aFieldName, aElement, aMinLen, aMaxLen, aRequired)
{
   if (!needsValidation(aElement, aRequired))
   {
      return true;
   }
   
    if(aRequired && !validateRequired(aElement) )
   {
      return false;
   }

   if (!isValidLen(aFieldName, aElement, aMinLen, aMaxLen))
   {
     // aElement.focus();
      return false;
   }

   var ParseOK = true;
   var nDots   = 0;

   for (i = 0; i < aElement.value.length; ++ i)
   {
      if ((aElement.value.charAt(i) <  '0'  ||
           aElement.value.charAt(i) >  '9') &&
           aElement.value.charAt(i) != '.')
      {
         ParseOK = false;

         break;
      }

      if (aElement.value.charAt(i) == '.')
      {
         ++ nDots
      }

      if (nDots > 1)
      {
         ParseOK = false;

         break;
      }
   }

   if (ParseOK == false)
   {
      printError(aElement,  "Please enter all numbers with or without a decimal point.");
            
   //  aElement.focus();
      return false;
   }

   return true;
}//--------------------------------------------------------------
function isValidSelect(aFieldName, aElement, aRequired)
{
 
  
   if (aRequired == false)
   {
      return true;
   }
   
   if(aRequired && !validateRequired(aElement) )
   {
      return false;
   }

  if(aElement.options.length == 0)
  {
     return true;
  }
  
   var SelectedKey = aElement.options[aElement.selectedIndex].text;    

   if (SelectedKey == "")
   {
      printError(aElement,  "Required");
      
   //   aElement.focus();
      return false;
   }

   return true;
}//--------------------------------------------------------------

function isValidDate(aFieldName, aElement, aRequired)
{
   if (!needsValidation(aElement, aRequired))
   {
      return true;
   }
   
   if(aRequired && !validateRequired(aElement) )
   {
      return false;
   }

   if(!validateDate_DDMMMYYYY(aElement.value))
   {
      printError(aElement,  "Invalid date format. (Example 8-AUG-2005).");

   //   aElement.focus();
      return false;
   }

   return true;
}//------------------------------------------------------
function  printError(aElement , aMessage)
{
       var errorBock = document.getElementById(aElement.name+"Error");
                
       if( errorBock == null )
       {
                  alert(aMessage);                 
         }
          else
          {            
                   errorBock.innerHTML = aMessage;
           }
           
   //         aElement.focus();
           
           return false;
}//---------------------------------------------------------------------
function  clearError(aElement )
{
       var errorBock = document.getElementById(aElement.name+"Error");
                
       if( errorBock == null )
       {
        }
          else
          {            
                   errorBock.innerHTML = "";
           }
}//---------------------------------------------------------------------
/**
 * @return true if the radio button has a checked value
 * @param aFieldName the field name
 *
 */
function isValidRadio(aFieldName, aElement, aForm, aRequired)
{
 
   if (aRequired == false)
   {
      return true;
   }
   
    if(aRequired && !validateRequired(aElement) )
   {
   //   aElement.focus();
      return false;
   }
   
   if(aElement.checked)
   {
      return true;
   }

	var the_formElements = aForm.elements;

    var the_element = null;
    
    var elementCount=0;

	for (elementCount=0; elementCount<the_formElements.length; elementCount++) 
	{
      	   the_element = the_formElements[elementCount];
      	
      	   if(isRadio(the_element) && 	the_element.name == aElement.name)
      	   {
      	  
      	     if(the_element.checked)
      	     {
      	        return true;
      	     }
      	   }
	}
	   

   //alert(  aFieldName + " must be selected.");
   printError(aElement, "Required");
    
   //if(aElement.length > 0)
  // {
  //    aElement[0].focus();
  // }
  // else
  // {
  //    aElement.focus();
  // }
   
   return false;
}//------------------------------------------------------
/**
 * @return true if the check button has a checked value
 * @param aFieldName the field name
 *
 */
function isValidCheckbox(aFieldName, aElement, aForm, aRequired)
{
   if (aRequired == false)
   {
      return true;
   }
   
    if(aRequired && !validateRequired(aElement) )
   {
    //  aElement.focus();
      return false;
   }
   
   if(aElement.checked)
   {
      return true;
   }

	var the_formElements = aForm.elements;

    var the_element = null;
    
    
    
    var elementCount=0;

	for (elementCount=0; elementCount<the_formElements.length; elementCount++) 
	{
      	   the_element = the_formElements[elementCount];
      	
      	   if(isCheckbox(the_element) && 	the_element.name == aElement.name)
      	   {
      	     if(the_element.checked)
      	     {
      	        return true;
      	     }
      	   }
	}
	
   //alert(  aFieldName + " must be selected.");
   printError(aElement , "Required");
  // alert('A response to all required questions is needed before proceeding');
    
//
 //  {
//      aElement[0].focus();
//   }
//   else
 //  {
 //     aElement.focus();
 //  }
   
   return false;
}//------------------------------------------------------

/**
 * @return true if the element is a text type
 * @param aElement the input form element
 */
function isText(aElement)
{
   if(aElement == null )
      return false;
  
   if(aElement.type == TEXT_TYPE || aElement.type == TEXTAREA_TYPE || aElement.type == "hidden" )
      return true;
   
   return false;
}//-------------------------------------------------------
/**
 * @return true if the element is a select element
 * @param aElement the input form element
 */
function isSelect(aElement)
{
   if(aElement == null )
      return false;
  
   if(aElement.type == "select-one" || aElement.type == "select-multiple" )
      return true;
   
   return false;
}//-------------------------------------------------------
/**
 * @return true if aElement.type == "radio"
 */
function isRadio(aElement)
{
   if(aElement == null)
      return false;
   
   if(aElement.type == "radio")
   {
      return true;
   }
   
   return false;
}//-------------------------------------------------
/**
 * @return true if aElement.type == "checkbox"
 */
function isCheckbox(aElement)
{
   if(aElement == null)
      return false;
   
   if(aElement.type == "checkbox")
   {
      return true;
   }
   
   return false;
}//-------------------------------------------------
/**
 * validate the element values against an regular expression
 * @param aFieldName the display name
 * @param aElement the element name
 */
function isValidFormat(aFieldName, aElement, aRegExpFormat, aDescription)
{
   if(aRegExpFormat != null && 
      aRegExpFormat.length > 0 &&
      isText(aElement) && 
      aElement.value != null &&
      aElement.value.length > 0 )
   {
      var patternFormat = new RegExp(aRegExpFormat);
      if(!patternFormat.test(aElement.value))
      {
         printError(aElement, aFieldName+" has an invalid value. "+aDescription);
       //  aElement.focus();
         return false;
      }
   }
   
   return true;
}//------------------------------------------------------
function validateSave(aForm)
{
   var the_formElements = validateOnSaveFields;
   //alert("validateSave(): " + the_formElements.length + " elements, total elements: " + aForm.elements.length);
   var the_element = null;
    
   var logicalFieldName="Input field";
   
   var validated = true;
    
    var elementCount=0;
    for (elementCount=0; elementCount<the_formElements.length; elementCount++) 
    {

             the_element = the_formElements[elementCount];
//             alert(the_element.name);
             
             if( isText(the_element) && ! validateLength(the_element)) {
                validated = false;
             }
      }
      
      return validated;
}//-------------------------------------------------------------
function validateLength(aElement)
{
        clearError(aElement);
        

    	   var the_array =  aElement.name.split("_");
	   
          var the_validateFlag = the_array[0];


   	   var the_type = "text";
   	   var the_minLength = 0;
   	   var the_maxLength = 2000;
   	   var the_name = "";

	   if (the_validateFlag == "v" && the_array.length >= 7) 
       {
       	     the_isRequired = (the_array[1] == "true");
                	
                	//alert('the_isRequired='+the_isRequired);
                	
      	        the_type = the_array[2];
        		    the_minLength = the_array[4];
      		    the_maxLength = the_array[5];
      		    the_name = the_array[3];
      		     
      	     return  isValidLen("Input field", aElement, the_minLength, the_maxLength);

	     }
	     
	     return true;
	
}//-----------------------------------------------------
/**
 * Validate theform elements
 * 
 * naming conventions
 * v_${required}_${type}_${format}_${minimumlength}_${maximumlength}_${name}
 */
function validate(aForm, needSubmit) 
{
	var the_formElements = validateOnSaveFields.concat(validateOnSubmitFields);
   //alert("validating " + the_formElements.length + " elements, total elements: " + aForm.elements.length);
   //alert("first element: " + the_formElements[0]);

   var the_element = null;
    
   var logicalFieldName="Input field";
   
   var validated = true;
    var elementCount=0;
    try {
    for (elementCount=0; elementCount<the_formElements.length; elementCount++) 
    {
	      the_element = the_formElements[elementCount];
	      if (the_element.type == "button" || the_element.type == "submit")
	      	continue;
	      if (the_element.name == null) {
	      	//alert(typeof(the_element) + ": " + the_element);
	      	continue;
	      }
	      
	      clearError(the_element);
	   
	     var the_array = the_element.name.split("_");	
	   
          var the_validateFlag = the_array[0];
          var the_isRequired = false;
   	   var the_type = "text";
   	   var the_format = "";
   	   var the_minLength = 0;
   	   var the_maxLength = 2000;
   	   var the_name = "";

	   if (the_validateFlag == "v" && the_array.length >= 7) 
           {
          	the_isRequired = (the_array[1] == "true");
          	
          	//alert('the_isRequired='+the_isRequired);
          	
	        the_type = the_array[2];
		    the_format = the_array[3];
		    the_minLength = the_array[4];
		    the_maxLength = the_array[5];
		    the_name = the_array[6];
		    
       
       }//end if is validate flag "v"	
       
	   if(isText(the_element))
	   {
	   
	       var aFormatDescription = "";

			//do validation
			if(the_type == "currency")
			{
               logicalFieldName="Currency field";
               aFormatDescription = "Sample valid value is \"99.99\".";
               
			    //Floating number
          	   if(!isValidDouble(logicalFieldName, the_element, the_minLength, the_maxLength, the_isRequired))
          	   {
          	     validated = false;
          	   } 
			}
			if(the_type == "float")
			{
               logicalFieldName="Numeric field";
               aFormatDescription = "Sample valid value is \"999.999\".";
               
			    //Floating number
          	   if(!isValidDouble(logicalFieldName, the_element, the_minLength, the_maxLength, the_isRequired))
          	   {
          	     validated = false;
          	   } 
			}
			else if(the_type == "number" || the_type == "percent" || the_type == "integer")
			{
               logicalFieldName="Numeric field";			
               aFormatDescription = "Sample valid value is \"9999\".";
			   if(!isValidNumber(logicalFieldName, the_element, the_minLength, the_maxLength, the_isRequired))
			   {
			      validated = false;
			   }
			   

			   if(the_type == "percent" && (the_element.value  < 0 || the_element.value > 100))
			   {
	              logicalFieldName="Percentage field";			   
	              
                  aFormatDescription = "Sample valid value is \"99\"";
			      printError(the_element, " must be between 0 and 100");
			 //     the_element.focus();
			      validated = false;
			      
			   }
			}			
			else if(the_type == "multi-select")
			{
               logicalFieldName="Selection ";			   			
			   if(!isValidText(logicalFieldName,the_element,the_minLength, the_maxLength,the_isRequired))
			   {
			      validated = false;
			   }
			}			
			else if(the_type == "text" || the_type == "textarea")
			{
               logicalFieldName="Text field";			   			
			   if(!isValidText(logicalFieldName,the_element,the_minLength, the_maxLength,the_isRequired))
			   {
			      validated = false;
			   }
			}
         else if(the_type == "date")
			{

               logicalFieldName="Date field";			   						
               //aFormatDescription = " Expected format is MM/dd/yyyy.";
               aFormatDescription = " Expected format is dd-MMM-yyyy (example: 12-AUG-2005).";
               
			   if(the_minLength == 0 &&
			       ! the_isRequired &&
   			      (the_element == null ||
   			       the_element.value == null ||
   			       the_element.value.length == 0))
   			   {
   			      //do nothing
   			   }
   			   else
   			   {
   				   if(!isValidDate(logicalFieldName,the_element,the_isRequired))
   				   {
   				      validated = false;
   				   }
				}
			}
			
			if(!isValidFormat(logicalFieldName,the_element,the_format,aFormatDescription))
			{
			   validated = false;			   			   
			}
						
		}//end if is text
	    else if(isSelect(the_element))
		{
		   
		   if( !isValidSelect("An option ", the_element, the_isRequired))
		   {
		      validated = false;
		   }
		}//end else if
		else if( (the_minLength > 0 || the_isRequired) && isRadio(the_element))
		{
		   if(!isValidRadio("An option",the_element,aForm,the_isRequired))
		   {
		      validated = false;
		   }
		}
		else if( (the_minLength > 0 || the_isRequired) && isCheckbox(the_element))
		{
		   if(!isValidCheckbox("An option",the_element,aForm,the_isRequired))
		   {
		      validated = false;
		   }
		}
		
    }//end for
    
    }
    catch (err) {
    	alert(err.message + ": " + typeof(the_element));
    }
    
    //alert('validated='+validated);
    
    //test if needs submit
    if(needSubmit && validated)
    {
       aForm.submit();
    }
    
//alert("validateForm(): done");
    return validated;
 }//---------------------------------------
   function  validateRequired(aElement)
   {
       if(isText(aElement))
       {
          return validateRequiredInput(aElement);
       }              
       return true;
   }//---------------------------------------   
      function  validateRequired(aElement, aFieldName)
   {
       if(isText(aElement))
       {
          return validateRequiredInput(aElement,aFieldName);
       }              
       return true;
   }//---------------------------------------   

 function validateRequiredInput(aInputID)
   {
      var error_msg = "Required";
      // we're getting something undefined here
      if (!aInputID) {

      	return true;
      	}
      	
      if(aInputID.value == null || aInputID.value == "")
      {
                var errorBock = document.getElementById(aInputID.name+"Error");
                
                if( errorBock == null )
                {
                  alert(error_msg);
             //     aInputID.focus();
                }
                else
                {            
                   errorBock.innerHTML = error_msg;
                }
                return false;
       }
       else
       {
              //No error so clear previous error message
               var errorBock = document.getElementById(aInputID.name+"Error");
                
                if( errorBock != null )
                {          
                     errorBock.innerHTML = "";
                  }
       }       
       
       return true;
   }//-------------------------------------------------------------------------
 function validateRequiredInput(aInputID , aFieldName)
   {
      var error_msg = "Required";
      
      if(aFieldName  && (aFieldName != null || aFieldName.length() > 0))
      {
         error_msg = aFieldName +" "+ error_msg
      }
      
      //  -- we're getting something undefined here
      if (!aInputID) {
      	//alert(aInputID);
      	return true;
      	}
      	
      if(aInputID.value == null || aInputID.value == "")
      {
                var errorBock = document.getElementById(aInputID.name+"Error");
                
                if( errorBock == null )
                {
                  alert(error_msg);
             //     aInputID.focus();
                }
                else
                {            
                   errorBock.innerHTML = error_msg;
                }
                return false;
       }
       else
       {
              //No error so clear previous error message
               var errorBock = document.getElementById(aInputID.name+"Error");
                
                if( errorBock != null )
                {          
                     errorBock.innerHTML = "";
                  }
       }       
       
       return true;
   }//-------------------------------------------------------------------------

// general purpose function to see if an input value has been entered at all
function isEmpty(inputStr) {
   if (inputStr == "" || inputStr == null) {
      return true
   }
   return false
} //-------------------------------------------------------------------------

   /**********************************
 * performValidation(conditionalQuestionsTable, requiredQuestionsTable)
 * Performs actual validation,
 * This function needs to go into validation.js
 ********************************/

function performValidation(conditionalQuestionsTable, requiredQuestionsTable) {
var submitform = true;
var conditionalQuestionsKeys = new Array();
conditionalQuestionsKeys =  conditionalQuestionsTable.keys();

    for (var  i=0; i<conditionalQuestionsKeys.length; i++) {
/***************************************************************************
 * Could find solution to "and" and "or" condition scenarios, hard coding it for now.
 * there r number of scenarios to handle, in questions, and answers
 ***************************************************************************
            if (conditionalQuestionsKeys[i].search(/&&&/) != -1 ) {
                var conditionalQuestions=conditionalQuestionsKeys[i];
                var conditionalAnswers=conditionalQuestionsTable.get(conditionalQuestionsKeys[i]);
                var requiredQuestions=requiredQuestionsTable.get(conditionalQuestionsKeys[i]);
               submitform = performAndValidation(conditionalQuestions, conditionalAnswers, requiredQuestions);
            } else if (conditionalQuestionsKeys[i].search(/|||/) != -1 ) {
                var conditionalQuestions=conditionalQuestionsKeys[i];
                var conditionalAnswers=conditionalQuestionsTable.get(conditionalQuestionsKeys[i]);
                var requiredQuestions=requiredQuestionsTable.get(conditionalQuestionsKeys[i]);
                submitform = performOrValidation(conditionalQuestions, conditionalAnswers, requiredQuestions);
            } else
  ********************************************************************************/
if (conditionalQuestionsKeys[i].search(/list/) != -1 ) {
                     var listBoxElement = document.getElementById(conditionalQuestionsKeys[i]);
                     var selectedAnswer = listBoxElement.options[listBoxElement.selectedIndex].text;
                     var requiredAnswer =  conditionalQuestionsTable.get(conditionalQuestionsKeys[i]);

                         if (selectedAnswer.match(requiredAnswer) != null) {
                          var requiredQuestionsArray = requiredQuestionsTable.get(conditionalQuestionsKeys[i]).split(":");
                             for (var index=0; index < requiredQuestionsArray.length; index++) {
                                    /************************************
                                     *Conditional Check for Text and TextArea
                                     ************************************/
                                    if (requiredQuestionsArray[index].search(/text/) != -1) {
                                      if (document.getElementById(requiredQuestionsArray[index]).value == "" ||
                                            document.getElementById(requiredQuestionsArray[index]).value == null) {
                                            printError( document.getElementById(requiredQuestionsArray[index]), "    Required");
                                            submitform = false ;
                                       } // If isEmpty

                                    } // If text required
                                    /************************************
                                     *Conditional Check for List Box
                                     ************************************/
                                    if (requiredQuestionsArray[index].search(/list/) != -1) {
                                         listBoxElement = document.getElementById(requiredQuestionsArray[index]);
                                         selectedAnswer = listBoxElement.options[listBoxElement.selectedIndex].text;

                                      if (selectedAnswer == "" || selectedAnswer == null) {
                                            printError( document.getElementById(requiredQuestionsArray[index]), "Field Required");
                                            submitform = false ;
                                       } // If isEmpty

                                    } // If list required

                             }  // for Index
                        } // if Answer
                      } // If search list
    } // for i
     return submitform;
}