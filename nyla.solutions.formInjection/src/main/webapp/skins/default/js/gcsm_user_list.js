  function search(aForm)
  {

     var criteria = aForm.code.value;
     if(criteria == null || criteria.length == 0)
     {
            alert("Search Criteria not provided");
            return false;
     }

     criteria = criteria.toLowerCase();

     //get Results list
     var resultList = aForm.resultList;
     resultList.options.length = 0;

     var rec = gcsmUserList.recordset;
     rec.moveFirst();

     var possibleNameMatch= "";
     var possibleNameMatchPK= "";
     var ct = false;
     while(!rec.EOF)
     {
            possibleNameMatch= new String(rec("name")).toLowerCase();
            possibleNameMatchPK= new String(rec("primaryKey")).toLowerCase();

            if( possibleNameMatch.indexOf(criteria) > -1)
            {  //found a match
                   //change Name
                   var tname = possibleNameMatch;
                   var fname = tname.substring(0,tname.indexOf(","));
                   var ch = new String(fname.charAt(0) + "");
                   ch = ch.toUpperCase();
                   fname = ch + fname.substring(1,fname.length);

                   var lname = tname.substring(tname.indexOf(" ")+1,tname.length);
                   ch = new String(lname.charAt(0) + "");
                   ch = ch.toUpperCase();
                   lname = ch + lname.substring(1, lname.length);
                   tname = fname +", " + lname;
                   possibleNameMatch = tname;

                   //add to the list
                   resultList.options.length += 1;
                   resultList.options(resultList.options.length -1).text = possibleNameMatch;
                   resultList.options(resultList.options.length -1).value= possibleNameMatchPK;

            }
             rec.moveNext();
     }

     if(resultList.options.length == 0)
     {
             resultList.options.length += 1;
             resultList.options(resultList.options.length -1).text = "No data Found";
     }
  }
