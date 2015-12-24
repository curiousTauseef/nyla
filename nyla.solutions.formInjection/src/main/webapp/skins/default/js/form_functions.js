function getElementIndex(frm, el) {
//getElementIndex - returns the index of the element object in the specified form.
//Returns:
//        -1 - If the element was not found in the form, this value is returned.
//        n  - If the element was found, the integer index is returned.
//frm - The form in which the search is to be performed.
//el - The element whose index is being searched for.
		var index = -1;
		for(i = 0; i < frm.elements.length; i++) {
				if(frm.elements[i].name == el.name) {
						index = i;
						break;
				}
		}
		return index;
}

function addToList(frm, sf, st) {
	//Retrieve the index values of the elements.
	var from = getElementIndex(frm, sf);
	var to   = getElementIndex(frm, st);

	fromLen = frm.elements[from].length ;
	for ( i=0; i<fromLen ; i++){
	  if (frm.elements[from].options[i].selected == true ) {
		toLen = frm.elements[to].length;
		frm.elements[to].options[toLen]= new Option(frm.elements[from].options[i].text,frm.elements[from].options[i].value);
	  }
	}

	for ( i = (fromLen -1); i>=0; i--){
	  if (frm.elements[from].options[i].selected == true ) {
		frm.elements[from].options[i] = null;
	  }
	}
  }

function moveAll (frm, sf, st) {
	var from = getElementIndex(frm,sf);
	var fromLen = frm.elements[from].length;

	for(var i = 0; i < fromLen; i++)
	frm.elements[from].options[i].selected = true;

	addToList(frm,sf,st);
}
// general purpose function to see if an input value has been entered at all
function isEmpty(inputStr) {
   if (inputStr == "" || inputStr == null) {
      return true
   }
   return false
}