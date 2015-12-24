function processFilters(form, box, table) {
					// eval("var selectBox = form."+box);

			 var filters = box.split(",");
			 var multiFilters;

			 if (filters.length === undefined)
			   multiFilters = false;
			 else {
			   multiFilters = true;  
			 }

			 var filterValue;

			 if (multiFilters)
			 {
			    filterValue = new Array(filters.length);
			    for (var i=0; i < filters.length; i++) {
			 	  filters[i] = document.forms[form.name].elements[filters[i]];
			 	  filterValue[i] = filters[i].options[filters[i].selectedIndex].text;
			 	}
			 	
			 } else 
			   filterValue = filters.options[filters.selectedIndex].text;
			 
			 var agent = "<%=request.getHeader("User-Agent")%>";
			 var found;
			 
			 var table = document.getElementById(table);   
		     var tableItem = table.getElementsByTagName("tr");  
		     		
				 if (agent.indexOf("BackWeb-ProactivePortal") == -1) {
					 form.submit();
				 } else {
					 for (var i=1; i < tableItem.length; i++) {
					      
			            if (!multiFilters)
						  found = (tableItem[i].innerHTML.indexOf(filterValue) != -1) || (filterValue == "All");
						else {
						  found = true;
						  for (var j=0; j < filterValue.length && found; j++) {
                             if (found && (tableItem[i].innerHTML.indexOf(filterValue[j]) != -1 || filterValue[j] == "All"))
                               found = true;
                             else 
                               found = false;
                  
						  }
						}
			

						 if (found) {
							 tableItem[i].style.display="block";
						 } else {
							 tableItem[i].style.display="none";
						 }

					 }	
				 }
		 }	